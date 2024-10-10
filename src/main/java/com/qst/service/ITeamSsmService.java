package com.qst.service;

import com.qst.pojo.Team;

import java.util.List;

public interface ITeamSsmService {
    List<com.qst.pojo.Team> findByCreator(Integer creator_id);
    List<com.qst.pojo.Team> findAll();
    com.qst.pojo.Team findById(Integer id);
    void save(Team team);
    void update(Team team);
    void delete(Integer id);
}
