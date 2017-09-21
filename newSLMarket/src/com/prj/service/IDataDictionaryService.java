package com.prj.service;

import java.util.List;

import com.prj.bean.DataDictionary;

public interface IDataDictionaryService {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
    
    //查询全部信息
    public List<DataDictionary> queryDataDictionarys(DataDictionary dataDictionary);
    
}