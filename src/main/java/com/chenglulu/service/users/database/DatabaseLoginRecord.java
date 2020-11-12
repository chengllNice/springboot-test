package com.chenglulu.service.users.database;

import com.chenglulu.mybatis.dao.LoginRecordMapper;
import com.chenglulu.mybatis.entity.LoginRecord;
import com.chenglulu.mybatis.entity.LoginRecordExample;
import com.chenglulu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DatabaseLoginRecord {
    @Autowired(required=false)
    private LoginRecordMapper loginRecordMapper;

    /**
     * 新建用户登录记录
     * @param params LoginRecord
     */
    public void insertLoginRecord(LoginRecord params){
        LoginRecord loginRecord = new LoginRecord();
        Date date = new Date();

        loginRecord.setId(CommonUtils.getUuid());
        loginRecord.setUserId(params.getUserId());
        loginRecord.setToken(params.getToken());
        loginRecord.setIp(params.getIp());
        loginRecord.setPlace(params.getPlace());
        loginRecord.setEquipment(params.getEquipment());
        loginRecord.setCreateTime(date);
        int insertResult = loginRecordMapper.insertSelective(loginRecord);
        log.info("insertLoginRecord insertResult = {}", insertResult);
    }


    /**
     * 查询用户登录记录信息
     * @param userId 用户ID
     * @return List<LoginRecord>
     */
    public List<LoginRecord> findLoginRecordByUserId(String userId){
        LoginRecordExample example = new LoginRecordExample();
        LoginRecordExample.Criteria criteria = example.createCriteria();

        // 按照创建时间 降序排列, id 升序排列； ASC升序，DESC降序，多个条件用逗号分隔
        example.setOrderByClause("create_time desc, id asc");

        criteria.andUserIdEqualTo(userId);

        List<LoginRecord> loginRecords = loginRecordMapper.selectByExample(example);
        log.info("findLoginRecordByUserId loginRecords = {}", loginRecords);
        return loginRecords;
    }


    /**
     * 查询用户登录记录信息
     * @param token 用户token
     * @return List<LoginRecord>
     */
    public LoginRecord findLoginRecordByToken(String token){
        LoginRecordExample example = new LoginRecordExample();
        LoginRecordExample.Criteria criteria = example.createCriteria();

        criteria.andTokenEqualTo(token);

        List<LoginRecord> loginRecords = loginRecordMapper.selectByExample(example);
        log.info("findLoginRecordByToken loginRecords = {}", loginRecords);
        if(loginRecords.size() > 0){
            return loginRecords.get(0);
        }
        return null;
    }


    /**
     * 删除用户登录记录信息
     * @param userId 用户ID
     * @param token 用户token
     */
    public void deleteLoginRecordByToken(String userId, String token){
        log.info("deleteLoginRecordByToken params userId = {}, token = {}", userId, token);
        LoginRecordExample example = new LoginRecordExample();
        LoginRecordExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);

        int result = loginRecordMapper.deleteByExample(example);
        log.info("deleteLoginRecordByToken result = {}", result);
    }
}
