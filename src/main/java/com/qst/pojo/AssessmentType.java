package com.qst.pojo;

import com.qst.entity.BaseEntity;

public class AssessmentType extends BaseEntity {
    private Integer id;
    private String title;
    private Double cost;
    private Integer status;

    @Override
    public String toString() {
        return "AssessmentType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
