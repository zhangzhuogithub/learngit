package com.prj.mapper;

import java.util.List;

import com.prj.bean.DataDictionary;

public interface DataDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
    
    //��ѯȫ����Ϣ
    public List<DataDictionary> queryDataDictionarys(DataDictionary dataDictionary);
}