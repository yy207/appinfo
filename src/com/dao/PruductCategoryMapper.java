package com.dao;

import com.pojo.PruductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PruductCategoryMapper {
    int insert(@Param("pruductCategory") PruductCategory pruductCategory);
    List<PruductCategory> getPruductList();
}
