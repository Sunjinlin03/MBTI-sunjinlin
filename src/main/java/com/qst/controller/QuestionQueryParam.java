package com.qst.controller;

import com.qst.RequestUtil;

import javax.servlet.http.HttpServletRequest;

public class QuestionQueryParam {
    private int assessmentId;
    private int status;
    private int type;


    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QuestionQueryParam{" +
                "assessmentId=" + assessmentId +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    public static com.qst.controller.QuestionQueryParam create(HttpServletRequest req) {
        //com.qst.action.question.QuestionQueryParam param = new com.qst.action.question.QuestionQueryParam();
        com.qst.controller.QuestionQueryParam param = new com.qst.controller.QuestionQueryParam();
        param.setAssessmentId(RequestUtil.getInt(req,"assessmentId"));
        int temp = RequestUtil.getInt(req,"status");
        param.setStatus(temp == 0?2:temp);
        param.setType(RequestUtil.getInt(req,"type"));
        return param;
    }



}
