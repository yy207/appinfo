package com.dao;

import com.pojo.DataDictionary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataDictionaryMapper {
//    Map<String,List<DataDictionary>>
    List<DataDictionary> getDataDictionary();
}
