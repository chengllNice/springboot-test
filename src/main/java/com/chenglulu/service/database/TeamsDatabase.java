package com.chenglulu.service.database;

import com.chenglulu.controller.teams.domain.FindTeamsListParams;
import com.chenglulu.controller.users.domain.FindUsersParams;
import com.chenglulu.mybatis.dao.TeamsMapper;
import com.chenglulu.mybatis.entity.Teams;
import com.chenglulu.mybatis.entity.TeamsExample;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.mybatis.entity.UsersExample;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TeamsDatabase {
    @Autowired(required=false)
    private TeamsMapper teamsMapper;

    /**
     * 新建团队、公司
     * @param teams Teams
     * @return boolean
     */
    public Teams insertTeams(Teams teams){
        Date date = new Date();
        teams.setId(CommonUtils.getUuid());
        teams.setCreateTime(date);
        teams.setUpdateTime(date);
        int insertResult = teamsMapper.insertSelective(teams);
        log.info("insertTeams insertResult = {}", insertResult);
        if(insertResult == 1){
            return teams;
        }
        return null;
    }

    /**
     * 查询team列表
     * @param params FindUsersParams
     * @return List<Users>
     */
    public List<Teams> findTeams(FindTeamsListParams params){
        TeamsExample example = new TeamsExample();
        TeamsExample.Criteria criteria = example.createCriteria();

        // 按照创建时间 降序排列, id 升序排列； ASC升序，DESC降序，多个条件用逗号分隔
        example.setOrderByClause("create_time desc, id asc");

        // 用户名模糊查询
        if(StringUtils.isNotBlank(params.getName())){
            criteria.andTeamNameLike("%" + params.getName() + "%");
        }

        List<Teams> result = teamsMapper.selectByExampleWithBLOBs(example);
        log.info("findTeams result = {}", result);
        return result;
    }
}
