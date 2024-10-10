package com.qst.action.question;

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


    public static QuestionQueryParam create(HttpServletRequest request) {
        QuestionQueryParam param = new QuestionQueryParam();
        param.setAssessmentId(RequestUtil.getInt(request, "assessmentId"));
        int temp = RequestUtil.getInt(request, "status");
        param.setStatus(temp == 0 ? 2 : temp);

        param.setType(RequestUtil.getInt(request, "type"));
        return param;
    }

    @Override
    public String toString() {
        return "QuestionQueryParam{" +
                "assessmentId=" + assessmentId +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
