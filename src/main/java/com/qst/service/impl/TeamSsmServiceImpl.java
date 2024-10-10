package com.qst.service.impl;

import com.qst.mapper.TeamMapper;
import com.qst.pojo.Team;
import com.qst.service.ITeamSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSsmServiceImpl implements ITeamSsmService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<com.qst.pojo.Team> findByCreator(Integer creator_id){
        return teamMapper.findByCreator(creator_id);
    }

    @Override
    public List<com.qst.pojo.Team> findAll(){
        return teamMapper.findAll();
    }

    @Override
    public com.qst.pojo.Team findById(Integer id){
        return teamMapper.findById(id);
    }

    @Override
    public void save(Team team){
        teamMapper.save(team);
    }

    @Override
    public void update(Team team){
        teamMapper.update(team);
    }

    @Override
    public void delete(Integer id){
        teamMapper.delete(id);
    }

}
