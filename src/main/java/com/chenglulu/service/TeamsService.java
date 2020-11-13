package com.chenglulu.service;

import com.chenglulu.controller.teams.domain.CreateTeamsParams;
import com.chenglulu.controller.teams.domain.FindTeamsListParams;
import com.chenglulu.mybatis.entity.Teams;
import com.chenglulu.utils.ApiAuth;

import java.util.List;

public interface TeamsService {

    /**
     * 新建团队
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    Teams createTeams(ApiAuth auth, CreateTeamsParams params);

    /**
     * 查询Teams列表
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    List<Teams> queryTeamsList(ApiAuth auth, FindTeamsListParams params);
}
