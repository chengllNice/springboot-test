package com.chenglulu.service.impl;

import com.chenglulu.controller.teams.domain.CreateTeamsParams;
import com.chenglulu.controller.teams.domain.FindTeamsListParams;
import com.chenglulu.mybatis.entity.Teams;
import com.chenglulu.service.database.TeamsDatabase;
import com.chenglulu.service.TeamsService;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    private TeamsDatabase teamsDatabase;

    private static final Logger logger = LoggerFactory.getLogger(TeamsServiceImpl.class);

    /**
     * 新建团队
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    @Override
    public Teams createTeams(ApiAuth auth, CreateTeamsParams params){

        Teams teams = new Teams();
        teams.setTeamName(params.getName());
        teams.setDescription(params.getDescription());
        teams.setAddress(params.getAddress());
        return teamsDatabase.insertTeams(teams);
    }

    /**
     * 查询Teams列表
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    @Override
    public List<Teams> queryTeamsList(ApiAuth auth, FindTeamsListParams params){
        return teamsDatabase.findTeams(params);
    }
}
