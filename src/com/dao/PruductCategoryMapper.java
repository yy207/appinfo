package com.dao;

import com.pojo.PruductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PruductCategoryMapper {
    int insert(@Param("pruductCategory") PruductCategory pruductCategory);
}
