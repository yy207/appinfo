package com.service;

import com.pojo.DataDictionary;

import java.util.List;
import java.util.Map;

/**
 * 字典
 */
public interface DataDictionaryService {
    /**
     *
     * @return 获取所有字典
     */
    List<DataDictionary> getDataDictionary();

    /**
     *
     * @return 获取kv
     */
    Map<String,List<DataDictionary>> getMapDataDictionary();
}
