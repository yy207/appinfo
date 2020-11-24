package com.dao;

import com.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BackendUserMapper {
    BackendUser login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

}
