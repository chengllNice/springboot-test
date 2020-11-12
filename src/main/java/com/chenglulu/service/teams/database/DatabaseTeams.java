package com.chenglulu.service.teams.database;

import com.chenglulu.mybatis.dao.TeamsMapper;
import com.chenglulu.mybatis.entity.Teams;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class DatabaseTeams {
    @Autowired(required=false)
    private TeamsMapper teamsMapper;

    /**
     * 新建团队、公司
     * @param params Teams
     * @return boolean
     */
    public Teams insertTeams(Teams params){
        Teams teams = new Teams();
        Date date = new Date();

        teams.setId(CommonUtils.getUuid());
        teams.setTeamName(params.getTeamName());
        teams.setAddress(params.getAddress());
        teams.setDescription(params.getDescription());
        teams.setCreateTime(date);
        teams.setUpdateTime(date);
        int insertResult = teamsMapper.insertSelective(teams);
        log.info("insertTeams insertResult = {}", insertResult);
        if(insertResult == 1){
            return teams;
        }
        return null;
    }
}
