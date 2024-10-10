package com.qst.service;

import com.qst.entity.Team;

import java.util.List;

public interface ITeamService {
    List<Team> findByCreator(Integer creator_id);
    public List<Team> findAll();
    Team findById(Integer id);
    void saveTeam(Team t);
    void updateTeam(Team t);
    void deleteTeam(int id);
}
