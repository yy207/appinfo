package com.service.impl;

import com.dao.AppCategoryMapper;
import com.pojo.AppCategory;
import com.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCategoryServiceImpl implements AppCategoryService {
    @Autowired
    private AppCategoryMapper appCategoryMapper;
    @Override
    public List<AppCategory> getList(Integer level) {
        return appCategoryMapper.getList(level);
    }
}
