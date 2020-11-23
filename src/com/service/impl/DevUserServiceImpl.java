package com.service.impl;

import com.dao.DevUserMapper;
import com.pojo.DevUser;
import com.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DevUserServiceImpl implements DevUserService {
    @Autowired
    public DevUserMapper devUserMapper;
    @Override
    public DevUser login(String devCode, String devPassword) {
        return devUserMapper.login(devCode, devPassword);
    }
}
