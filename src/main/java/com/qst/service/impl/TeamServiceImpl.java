package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.DAOFactory;
import com.qst.dao.TeamDao;
import com.qst.entity.TestPersonnel;
import com.qst.entity.Team;
import com.qst.service.ITeamService;
import com.qst.service.ITestPersonnelService;


import java.sql.SQLException;
import java.util.List;

public class TeamServiceImpl implements ITeamService {
     private TeamDao teamDao= DAOFactory.getDao(TeamDao.class);
     private ITestPersonnelService testPersonnelService =new TestPersonnelServiceImpl();

    @Override
    public List<Team> findByCreator(Integer creator_id) {
        try {
            return teamDao.findByCreator(creator_id);
        }catch (SQLException ex){
            throw new ExamException("数据查询出错");
        }
    }

    @Override
    public List<Team> findAll() {
        try {
            return teamDao.findAll();
        } catch (SQLException ex) {
            throw new ExamException("数据库查询出错");
        }
    }


    @Override
    public Team findById(Integer id) {
        try {
            return teamDao.findById(id);
        } catch (Exception ex) {
            throw new RuntimeException("数据库查询出错");
        }
    }

    @Override
    public void saveTeam(Team t) {
        try {
            teamDao.save(t);
        } catch (SQLException ex) {
            throw new ExamException("数据库查询出错");
        }

    }
    @Override
    public void updateTeam(Team t) {

        try {
            teamDao.update(t);
        } catch (SQLException ex) {
            throw new ExamException("数据库查询出错");
        }
    }

    @Override
    public void deleteTeam(int id) {
        try {
            List<TestPersonnel> personnels = testPersonnelService.findByTeam(id);
            if (personnels.size() > 0) {
                throw new ExamException("有参测人员的批次不能删除");
            }
            teamDao.delete(id);
        } catch (SQLException ex) {
            throw new ExamException("数据库查询出错");
        }
    }
}
