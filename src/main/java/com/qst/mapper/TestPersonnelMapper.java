package com.qst.mapper;

import com.qst.pojo.TestPersonnel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestPersonnelMapper {

    //根据id查找参测人员
    TestPersonnel findById(int id);

    //根据teamId查找该批次的所有参测人员
    List<TestPersonnel> findByTeamId(int id);




    //根据条件查找参测人员
    List<TestPersonnel> query(@Param("teamId") int teamId,
                              @Param("name") String name,
                              @Param("phone") String phone);



    //添加参测人员（在批次管理中添加人员）
    int addTestPersonnel(TestPersonnel s);


    //修改参测人员信息
    int updateTestPersonnel(TestPersonnel ts);

    //根据id删除参测人员
    int deleteTestPersonnel(int id);

    TestPersonnel findByPhone(String phone);
}
