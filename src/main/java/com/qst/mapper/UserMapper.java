package com.qst.mapper;

import com.qst.pojo.User;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

public interface UserMapper {

    List<User> findAll();

    //通过条件查询
    List<User> getUserByCondition(User user);

    List<User> findAllWithlastLogin();

    List<User> findAll2();

    List<User> getUserByIds(Set ids);

    User findUserById(int id);

    User findByLogin(String login);

    int insertUser(User user);

    int insertUser1(User user);

    int delUserById(int id);

    int updateUser(User user);

    int updateUser1(User user);

//    重置密码
    @Update("update users set passwd=#{passwd} where id = #{id}")
    int resetPwdByUser(User user);

    void updateLastLogin(User user);

    @Update("update users set passwd=#{passwd} where id = #{id}")
    int updatePasswd(User user);
}
