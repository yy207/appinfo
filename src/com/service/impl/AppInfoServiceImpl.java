package com.service.impl;

import com.dao.AppInfoMapper;
import com.pojo.AppInfo;
import com.service.AppInfoService;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    private AppInfoMapper appInfoMapper;
    @Override
    public List<AppInfo> getList(AppInfo appInfo, Page page) {
        return appInfoMapper.getList(appInfo,page);
    }

    @Override
    public AppInfo getListByName(String APKName) {
        return appInfoMapper.getListByName(APKName);
    }

}
