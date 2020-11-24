package com.dao;

import com.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppCategoryMapper {
    /**
     * 获取指定界别的分类
     * @param level
     * @return 集合
     */
    List<AppCategory> getList();

    /**
     *
     * @param parentId
     * @return
     */
    List<AppCategory> getListByPid(@Param("parentId")Integer parentId);
}
