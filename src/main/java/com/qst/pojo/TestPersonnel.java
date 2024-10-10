package com.qst.pojo;

import com.qst.entity.BaseEntity;

import java.util.Date;

public class TestPersonnel extends BaseEntity {

    private Integer id;
    private String gender;
    private String phone;
    private Date birthdate;
    private String formattedDate;
    private int teamId;
    private User user;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Date getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
    @Override
    public String toString() {
        return "TestPersonnel{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthdate +
                ", teamId=" + teamId +
                ", user=" + user +
                '}';
    }
}
