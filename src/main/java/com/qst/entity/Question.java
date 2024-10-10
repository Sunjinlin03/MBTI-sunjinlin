package com.qst.entity;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Question extends BaseEntity {
    //成员变量
    private int userId;
    private int assessmentId;
    private String title;
    private String hint;
    private int type;
    private int difficulty;
    private int status;
    private List<Choice> choices;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public boolean isAnswerRight(int[] answers) {
        if (answers == null || answers.length == 0) {
            return false;
        }

        Set<Integer> giveAnswer = new HashSet<>();
        for (int i : answers) {
            giveAnswer.add(i);
        }
        Set<Integer> answerSet = new HashSet<>();
        for (Choice ch : choices) {
            if (ch.isChecked()){
                answerSet.add(ch.getId());
            }
        }
        return answerSet.equals(giveAnswer);
    }
}
