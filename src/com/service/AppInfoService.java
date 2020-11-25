package com.service;

import com.pojo.AppInfo;
import com.utils.Page;
import org.apache.ibatis.annotations.Param;

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
    AppInfo getListByName(@Param("APKName") String APKName);
}
