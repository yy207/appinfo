package com.dao;

import com.pojo.AppInfo;
import com.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppInfoMapper {

    List<AppInfo> getList(@Param("appInfo") AppInfo appInfo,@Param("page") Page page);
}
