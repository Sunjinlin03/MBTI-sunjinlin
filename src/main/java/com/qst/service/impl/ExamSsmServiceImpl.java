package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.controller.QuestionQueryParam;
import com.qst.mapper.ExamQuestionMapper;
import com.qst.pojo.ExamQuestion;
import com.qst.pojo.Question;
import com.qst.pojo.Exam;
import com.qst.pojo.Schedule;
import com.qst.pojo.TestPersonnel;
import com.qst.mapper.AssessmentMapper;
import com.qst.mapper.ExamMapper;
import com.qst.mapper.ScheduleMapper;
import com.qst.service.IExamSsmService;
import com.qst.service.IQuestionSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ExamSsmServiceImpl implements IExamSsmService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private AssessmentMapper assessmentMapper;
    @Autowired
    private IQuestionSsmService questionSsmService;
    @Autowired
    private ExamQuestionMapper examQuestionMapper;

    @Override
    public List<Schedule> findScheduleByTestPersonnel(TestPersonnel testPersonnel) {
        List<Exam> exams = examMapper.findByTestPersonnel(testPersonnel.getId());
        List<Schedule> schedules = scheduleMapper.findByTeamId(testPersonnel.getTeamId());
        Map<Integer, Schedule> map = new HashMap<>();
        for (Schedule h : schedules) {
            h.setAssessmentType(assessmentMapper.findById(h.getAssessmentId()));
            map.put(h.getId(), h);
        }
        for (Exam m : exams) {
            try {
                map.get(m.getScheduleId()).getExtras().put("exam", m);
            } catch (Exception e) {
                // TODO: handle exception
                e.getMessage();

            }
        }
        return schedules;
    }

    @Override
    public Exam begin(TestPersonnel testPersonnel, int scheduleId) {
       Schedule schedule = scheduleMapper.findById(scheduleId);
       Exam exam = examMapper.findByTestPersonnelAndSchedule(testPersonnel.getId(),
                schedule.getId());
        // 判断该考生能否参加本次测试
        isCanExam(testPersonnel, exam, schedule);
        if (exam == null) {
            exam = new Exam();
            exam.setTestPersonnelId(testPersonnel.getId());
            exam.setScheduleId(scheduleId);
            exam.setBeginTime(new Timestamp(System.currentTimeMillis()));
        }
        List<ExamQuestion> examQustions = new ArrayList<>();
        QuestionQueryParam param = new QuestionQueryParam();
        param.setAssessmentId(schedule.getAssessmentId());
        param.setStatus(2);// 有效
        // 抽取单选题
        param.setType(1);// 单选题
        examQustions.addAll(findQuestion(testPersonnel.getId(), param,(schedule.getQuestionNumber()/4),2
        ));
        // 抽取多选题
        param.setType(2);// 多选题
        examQustions.addAll(findQuestion(testPersonnel.getId(), param,(schedule.getQuestionNumber()/4),2
        ));
        param.setType(3);// 多选题
        examQustions.addAll(findQuestion(testPersonnel.getId(), param,(schedule.getQuestionNumber()/4),2
        ));
        param.setType(4);// 多选题
        examQustions.addAll(findQuestion(testPersonnel.getId(), param,(schedule.getQuestionNumber()/4),2
        ));
        exam.setExamQuestions(examQustions);

        return exam;
    }

    private boolean isCanExam(TestPersonnel personnel, Exam exam, Schedule schedule) {
        if (exam != null) {
            throw new ExamException("已经考过了，不能再考");
        }
        if (schedule.getTeamId() != personnel.getTeamId()) {
            throw new ExamException("不是指定班级的学生，不能参加测试");
        }
        if (schedule.getStatus() == 0) {
            throw new ExamException("本次测试已取消");
        }
        if (schedule.getStatus() == 1) {
            throw new ExamException("本次测试还没有开始");
        }
        if (schedule.getStatus() == 3) {
            throw new ExamException("本次测试已结束");
        }
        return true;
    }
    private List<ExamQuestion> findQuestion(int stuId,
                                            QuestionQueryParam param, int count, int score) {

        List<Question> questions = questionSsmService.find(param);
        // 打乱list的顺序和洗牌一样
        Collections.shuffle(questions);
        // count 为测试安排中指定的试题数量，questions.size()为按要求查找出来的试题数量
        count = questions.size() > count ? count : questions.size();
        // 如果按要求查找出来的试题数量为50，测试只要求20，所以截取20个题
        List<Question> tempList = questions.subList(0, count);

        List<ExamQuestion> examQustions = new ArrayList<>();
        for (Question q : tempList) {
            ExamQuestion eq = new ExamQuestion();
            eq.setQuestionId(q.getId());
            eq.setQuestion(q);
            eq.setStudentId(stuId);
            // 测试安排时，会指定每个单选或者多选的分数
            eq.setScore(score);
            examQustions.add(eq);
        }
        return examQustions;
    }

    @Override
    public void end(Exam exam) {
        // 设定交卷时间
        exam.setEndTime(new Timestamp(System.currentTimeMillis()));
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        // 判卷
        for (ExamQuestion eq : exam.getExamQuestions()) {
            eq.setRight(eq.getQuestion().isAnswerRight(eq.getAnswer()));
            if (eq.isRight()) {
                int a=map.get(eq.getQuestion().getType())+1;
                map.put(eq.getQuestion().getType(), a);
            }else{
                int a=map.get(eq.getQuestion().getType())-1;
                map.put(eq.getQuestion().getType(), a);
            }
        }
        StringBuffer stf=new StringBuffer();
        for(int i:map.keySet()){
            if(map.get(i)>0){
                if(i==1){
                    stf.append("J");
                }else if(i==2){
                    stf.append("T");
                }else if(i==3){
                    stf.append("S");
                }else{
                    stf.append("E");
                }
            }else{
                if(i==1){
                    stf.append("P");
                }else if(i==2){
                    stf.append("F");
                }else if(i==3){
                    stf.append("N");
                }else{
                    stf.append("I");
                }
            }
        }
        stf.reverse();
        exam.setResult(stf.toString());
        // 保存考试
        if (exam.getId() != null) {
            examMapper.updateExam(exam);
        } else {
            examMapper.saveExam(exam);
        }
        examQuestionMapper.deleteByExam(exam.getId());
        for (ExamQuestion eq : exam.getExamQuestions()) {
            eq.setExamId(exam.getId());
            examQuestionMapper.save(eq);
        }
    }
}
