package com.chenglulu.controller.teams;

import com.chenglulu.controller.BaseController;
import com.chenglulu.controller.BaseResponse;
import com.chenglulu.controller.teams.domain.CreateTeamsParams;
import com.chenglulu.controller.teams.domain.FindTeamsListParams;
import com.chenglulu.mybatis.entity.Teams;
import com.chenglulu.service.TeamsService;
import com.chenglulu.utils.ApiAuth;
import com.chenglulu.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping( value = "/teams", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TeamsController extends BaseController {

    @Autowired
    private TeamsService teamsService;

    private static final Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseResponse createTeams(HttpServletRequest request, @Validated @RequestBody CreateTeamsParams params){
        ApiAuth auth = initAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, createTeams start params = {}", requestId, params);
        Teams result = teamsService.createTeams(auth, params);
        logger.info("requestId = {}, createTeams end users = {}", requestId, result);
        return ResponseUtil.success(request, requestId, result);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResponse queryTeamsList(HttpServletRequest request, @Validated FindTeamsListParams params){
        ApiAuth auth = initAuth(request);
        String requestId = auth.getRequestId();
        logger.info("requestId = {}, queryTeamsList start params = {}", requestId, params);
        List<Teams> result = teamsService.queryTeamsList(auth, params);
        logger.info("requestId = {}, queryTeamsList end users = {}", requestId, result);
        return ResponseUtil.success(request, requestId, result);
    }
}
