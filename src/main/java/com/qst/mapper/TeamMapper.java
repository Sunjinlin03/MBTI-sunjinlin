package com.qst.mapper;

import com.qst.pojo.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<Team> findByCreator(Integer creator_id);

    List<Team> findAll();

    Team findById(Integer id);

    void save(Team t);

    void update(Team t);

    void delete(int id);
}
