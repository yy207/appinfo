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
    List<AppVersion> getAppVersionList(Integer appId);
    /**
     *  添加一个appVersion
     * @param appVersion
     * @return 影响行数
     */
    Integer addversionsave(@Param("appVersion") AppVersion appVersion);
    /**
     *  根据AppId==>versionId获取版本信息
     * @param appId appId versionId
     * @return 版本信息集合
     */
    List<AppVersion> getAppVersionListByVersionId(@Param("appId")Integer appId,@Param("versionId")Integer versionId);
}
