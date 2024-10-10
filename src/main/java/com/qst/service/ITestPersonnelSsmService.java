package com.qst.service;

import com.qst.pojo.TestPersonnel;

import java.util.List;

public interface ITestPersonnelSsmService {

    //根据id查找参测人员
    TestPersonnel findById(int id);

    //根据teamId查找该批次的所有参测人员
    List<TestPersonnel> findByTeamId(int id);


    //添加参测人员（在批次管理中添加人员）
    void addTestPersonnel(TestPersonnel s);

    List<TestPersonnel> query(int teamId,String name,String phone);

    //修改参测人员信息
    void updateTestPersonnel(TestPersonnel ts);

    //根据id删除参测人员
    TestPersonnel deleteTestPersonnel(int id);

    void importTestPersonnel(List<TestPersonnel> testPersonnelList);
}
