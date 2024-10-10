package com.qst.service;

import com.qst.entity.TestPersonnel;

import java.util.List;

//定义查询参与测评人员ID的方法
public interface ITestPersonnelService {

    TestPersonnel findById(int id);
    List<TestPersonnel> findByTeamId(int id);
    List<TestPersonnel> findByTeam(int id);
    List<TestPersonnel> query(int teamId,String name,String stdNo);
    void addTestPersonnel(TestPersonnel s);
    void importTestPersonnel(List<TestPersonnel> testPersonnelList);
    void updateTestPersonnel(TestPersonnel s);
    TestPersonnel deleteTestPersonnel(int id);

}
