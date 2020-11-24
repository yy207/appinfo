package com.service.impl;

import com.dao.BackendUserMapper;
import com.pojo.BackendUser;
import com.service.BackendUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendUserServiceImpl implements BackendUserService {

    @Autowired
    private BackendUserMapper backendUserMapper;

    public BackendUser login(String userCode,String userPassword){
        return backendUserMapper.login(userCode,userPassword);
    }

}
