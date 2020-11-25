package com.service;

import com.pojo.AppVersion;

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
}
