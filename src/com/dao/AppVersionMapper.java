package com.dao;

import com.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppVersionMapper {
    /**
     *  根据AppId获取版本信息
     * @param appId appId
     * @return 版本信息集合
     */
    List<AppVersion> getAppVersionList(@Param("appId") Integer appId);


}
