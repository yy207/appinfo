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

    @Override
    public AppInfo getById(Integer id) {
        return appInfoMapper.getById(id);
    }

    @Override
    public Integer appinfoAddSave(AppInfo appInfo) {
        return appInfoMapper.appinfoAddSave(appInfo);
    }

    @Override
    public Integer appinfoDelById(Integer id) {
        return appInfoMapper.appinfoDelById(id);
    }

    @Override
    public Integer appSale(Integer appId, Integer status) {
        return appInfoMapper.appSale(appId, status);
    }

}
