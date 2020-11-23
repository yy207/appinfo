package com.dao;

import com.pojo.DevUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DevUserMapper {
    DevUser login(@Param("devCode") String devCode, @Param("devPassword") String devPassword);

}
