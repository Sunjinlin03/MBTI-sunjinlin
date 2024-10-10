package com.qst.entity;


import java.sql.Timestamp;
import java.util.List;

public class Exam extends BaseEntity {

    private Integer studentId;
    private Integer scheduleId;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Schedule schedule;
    private List<ExamQuestion> examQuestions;
    private int score;
    private int questionIndex;
    private String result;
    private Integer TestPersonnelId;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getTestPersonnelId() {
        return TestPersonnelId;
    }

    public void setTestPersonnelId(Integer testPersonnelId) {
        TestPersonnelId = testPersonnelId;
    }

    public ExamQuestion getQuestion() {
        if (questionIndex >= examQuestions.size()) {
            questionIndex = 0;
        } else if (questionIndex < 0) {
            questionIndex = examQuestions.size() - 1;
        }
        return examQuestions.get(questionIndex);
    }


    public int getPassedTime() {
        return (int) (System.currentTimeMillis() - beginTime.getTime()) / (1000 * 60);
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }
}
