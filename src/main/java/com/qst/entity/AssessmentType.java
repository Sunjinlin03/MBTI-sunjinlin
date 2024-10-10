package com.qst.entity;

public class AssessmentType extends BaseEntity{



    //成员变量
    private String title;
    private double cost;
    private int status;


    public AssessmentType() {
    }


    public AssessmentType(String title, int cost, int status) {
        this.title = title;
        this.cost = cost;
        this.status = status;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




}
