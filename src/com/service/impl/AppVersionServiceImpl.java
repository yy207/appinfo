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
}
