package com.qst.controller;

import com.qst.*;
import com.qst.action.exam.BeginServlet;
import com.qst.pojo.Choice;
import com.qst.pojo.Exam;
import com.qst.pojo.ExamQuestion;
import com.qst.pojo.Schedule;
import com.qst.pojo.TestPersonnel;
import com.qst.service.AssessmentSsmService;
import com.qst.service.IExamSsmService;
import com.qst.service.IQuestionSsmService;
import com.qst.service.IScheduleSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;

import static com.qst.Constant.CURRENT_EXAM;
import static com.qst.Constant.CURRENT_TESTPERSONNEL;

@Controller
@RequestMapping("/exam/")
public class ExamController extends BaseServlet {
    @Autowired
    private IExamSsmService examSsmService;
    @Autowired
    private AssessmentSsmService assessmentSsmService;
    @Autowired
    private IScheduleSsmService scheduleSsmService;
    @Autowired
    private IQuestionSsmService questionSsmService;

    @RequestMapping("begin.action")
    public String begin(HttpServletRequest request){
        TestPersonnel testPersonnel = (TestPersonnel) request.getSession().getAttribute(CURRENT_TESTPERSONNEL);
        int scheduleId= RequestUtil.getInt(request,"id");
        try {
            // 生成某个考生的试题：包含考生信息和试题集合
            Exam exam=examSsmService.begin(testPersonnel,scheduleId);
            request.getSession().setAttribute(CURRENT_EXAM, exam);
           return "redirect:exam.action";
        }catch (Exception ex){
            ex.printStackTrace();
            return "redirect:list.action";
        }
    }
    @RequestMapping("list.action")
    public String list(HttpServletRequest request, Model model){
        TestPersonnel stu = (TestPersonnel) request.getSession().getAttribute(Constant.CURRENT_TESTPERSONNEL);
        List<Schedule> scheduleList = examSsmService.findScheduleByTestPersonnel(stu);
        model.addAttribute("scheduleList", scheduleList);
        return "exam/list";
    }

    @RequestMapping("exam.action")
    public String exam(HttpServletRequest req) {
        Exam exam = (Exam) req.getSession().getAttribute(CURRENT_EXAM);

        int[] answer = RequestUtil.getIntArray(req, "answer");
        ExamQuestion eq = exam.getQuestion();
        eq.setAnswer(answer);

        int index = RequestUtil.getInt(req, "index");// -1：前一题，－２　后一题，　其他：指定下标
        if (index == -2) // 后一题
        {
            exam.setQuestionIndex(exam.getQuestionIndex() + 1);
        } else if (index == -1)// 前一题
        {
            exam.setQuestionIndex(exam.getQuestionIndex() - 1);
        }else if (index == -3)// 交卷
        {
            return "forward:/end.action";
        } else // 指定下标的题
        {
            exam.setQuestionIndex(index);
        }
        eq = exam.getQuestion();

        // 第一次加载题目时，题目没有选项，因为生成题目时，没有生成选项
//        List<Choice> choices = eq.getQuestion().getChoices();
        List<Choice> choices = eq.getQuestion().getChoices();
        if (choices == null) {
            choices = questionSsmService.findChoices(eq.getQuestionId());
            Collections.shuffle(choices);
            eq.getQuestion().setChoices(choices);
        } else {
            // 确定哪个choice在页面上应该被选中
            for (Choice ch : choices) {
                ch.getExtras().remove("checked");
                if (eq.getAnswer() != null) {
                    for (int ans : eq.getAnswer()) {
                        if (ans == ch.getId()) {
                            ch.getExtras().put("checked", "checked");
                            break;
                        }
                    }
                }
            }

        }
        return "/exam/exam";

    }
    @RequestMapping("end.action")
    public String end(HttpServletRequest req){
        Exam exam = (Exam) req.getSession().getAttribute(CURRENT_EXAM);
        try {
            examSsmService.end(exam);
            StringBuffer stf=new StringBuffer();
            stf.append("../analysis/");
            stf.append(exam.getResult());
            stf.append(".jsp");
            req.setAttribute("examresult", stf.toString());
            req.setAttribute("score",exam.getResult());
        } catch (ExamException ex) {
            ex.printStackTrace();
            req.setAttribute("score", "您未能成功交卷，请重新交卷");
        }
        return "forward:/exam/end";
    }

}
