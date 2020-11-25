package com.dao;

import com.pojo.AppInfo;
import com.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppInfoMapper {
    /**
     *  按条件查询
     * @param appInfo 参数条件
     * @param page 分页
     * @return
     */
    List<AppInfo> getList(@Param("appInfo") AppInfo appInfo,@Param("page") Page page);

    /**
     * 根据名称查询
     * @param APKName APK名称
     * @return
     */
    AppInfo getListByName(@Param("APKName") String APKName);

    /**
     *  添加一个app
     * @param appInfo
     * @return 影响行数
     */
    Integer appinfoAddSave(@Param("appInfo")AppInfo appInfo);

}
