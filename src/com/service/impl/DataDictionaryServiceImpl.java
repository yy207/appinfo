package com.service.impl;

import com.dao.DataDictionaryMapper;
import com.pojo.DataDictionary;
import com.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;


    @Override
    public List<DataDictionary> getDataDictionary() {
        return dataDictionaryMapper.getDataDictionary();
    }

    @Override
    public Map<String, List<DataDictionary>> getMapDataDictionary() {
        List<DataDictionary> list = dataDictionaryMapper.getDataDictionary();
        Map<String, List<DataDictionary>> map = new HashMap<>();
        List<DataDictionary> dataList = null;
        for (DataDictionary data: list) {
            String keyCode = data.getTypeCode();
            if (!map.containsKey(keyCode)){
                dataList = new ArrayList<>();
                map.put(keyCode,dataList);
            }
            map.get(keyCode).add(data);
        }
        return map;
    }

}
