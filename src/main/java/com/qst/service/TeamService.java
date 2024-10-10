package com.qst.service;

import com.qst.pojo.Team;

import java.util.List;

public interface TeamService {
    List<Team> findByCreator(Integer id);

    public List<Team> findAll();

    Team findById(Integer id);

    void saveTeam(Team t);

    void updateTeam(Team t);

}
