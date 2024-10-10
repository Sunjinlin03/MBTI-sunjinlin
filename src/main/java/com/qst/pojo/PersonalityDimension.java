package com.qst.pojo;

import com.qst.entity.BaseEntity;

public class PersonalityDimension extends BaseEntity {
    private Integer id;
    private String title;
    private String depict;
    private Integer assessmentId;

    @Override
    public String toString() {
        return "PersonalityDimension{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", depict='" + depict + '\'' +
                ", assessmentId=" + assessmentId +
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

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }
}
