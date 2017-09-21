package com.prj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.prj.bean.DataDictionary;
import com.prj.mapper.DataDictionaryMapper;
import com.prj.service.IDataDictionaryService;
@Service
public class DataDictionaryServiceImpl implements IDataDictionaryService{
	@Resource
	DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataDictionary selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataDictionary> queryDataDictionarys(DataDictionary dataDictionary) {
		
		
		return dataDictionaryMapper.queryDataDictionarys(dataDictionary);
	}

}