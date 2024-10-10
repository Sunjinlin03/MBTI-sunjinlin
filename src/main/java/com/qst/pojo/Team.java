package com.qst.pojo;

import com.qst.entity.BaseEntity;

import java.util.Date;

public class Team extends BaseEntity {
    private String name;
    private Date beginYear;
    private int status;
    private int creatorId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Date beginYear) {
        this.beginYear = beginYear;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", beginYear=" + beginYear +
                ", status=" + status +
                ", creatorId=" + creatorId +
                '}';
    }
}
