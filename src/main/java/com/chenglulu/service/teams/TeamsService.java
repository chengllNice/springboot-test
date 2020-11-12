package com.chenglulu.service.teams;

import com.chenglulu.controller.roles.domain.CreateRolesParams;
import com.chenglulu.controller.teams.domain.CreateTeamsParams;
import com.chenglulu.controller.users.domain.RegisterUsersParams;
import com.chenglulu.mybatis.entity.Roles;
import com.chenglulu.mybatis.entity.Teams;
import com.chenglulu.mybatis.entity.Users;
import com.chenglulu.service.teams.database.DatabaseTeams;
import com.chenglulu.service.users.UsersService;
import com.chenglulu.utils.ApiAuth;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeamsService {

    @Autowired
    private DatabaseTeams databaseTeams;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    /**
     * 新建团队
     * @param auth auth
     * @param params 请求参数
     * @return Users
     */
    public Teams createTeams(ApiAuth auth, CreateTeamsParams params){

        Teams teams = new Teams();
        teams.setTeamName(params.getName());
        teams.setDescription(params.getDescription());
        teams.setAddress(params.getAddress());
        return databaseTeams.insertTeams(teams);
    }
}
