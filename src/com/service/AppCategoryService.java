package com.service;

import com.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * APP分类
 */
public interface AppCategoryService {
    List<AppCategory> getList(@Param("level")Integer level);
}
