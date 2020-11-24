package com.service;

import com.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

/**
 * 后台用户
 */
public interface BackendUserService {
    BackendUser login(String userCode,String userPassword);
}
