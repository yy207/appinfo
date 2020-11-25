package com.service;

import com.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * App版本号
 */
public interface AppVersionService {
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
    Integer addversionsave(AppVersion appVersion);
    /**
     *  根据AppId==>versionId获取版本信息
     * @param appId appId versionId
     * @return 版本信息集合
     */
    List<AppVersion> getAppVersionListByVersionId(Integer appId,Integer versionId);

}
