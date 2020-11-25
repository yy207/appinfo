package com.service.impl;

import com.dao.AppVersionMapper;
import com.pojo.AppVersion;
import com.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionMapper appVersionMapper;
    @Override
    public List<AppVersion> getAppVersionList(Integer appId) {
        return appVersionMapper.getAppVersionList(appId);
    }

    @Override
    public Integer addversionsave(AppVersion appVersion) {
        return appVersionMapper.addversionsave(appVersion);
    }

    @Override
    public List<AppVersion> getAppVersionListByVersionId(Integer appId, Integer versionId) {
        return appVersionMapper.getAppVersionListByVersionId(appId,versionId);
    }

}
