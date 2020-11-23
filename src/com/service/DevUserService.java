package com.service;

import com.pojo.DevUser;

/**
 * 开发者
 */
public interface DevUserService {
    DevUser login( String devCode,  String devPassword);


}
