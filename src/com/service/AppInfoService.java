package com.service;

import com.pojo.AppInfo;
import com.utils.Page;

import java.util.List;

/**
 * APP信息
 */
public interface AppInfoService {
    /**
     * 
     * @param appInfo APP筛选条件
     * @param page 分页
     * @return APP数据
     */
    List<AppInfo> getList(AppInfo appInfo,Page page);

    /**
     * 根据名称查询
     * @param APKName apk名称
     * @return
     */
    AppInfo getListByName(String APKName);
    /**
     *  添加一个app
     * @param appInfo
     * @return 影响行数
     */
    Integer appinfoAddSave(AppInfo appInfo);
}
