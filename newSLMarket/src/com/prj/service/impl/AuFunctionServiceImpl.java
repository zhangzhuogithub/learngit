package com.prj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;
import com.prj.mapper.AuFunctionMapper;
import com.prj.service.IAuFunctionService;
@Service
public class AuFunctionServiceImpl implements IAuFunctionService{
	@Resource
	private AuFunctionMapper auFunctionMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AuFunction record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AuFunction record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AuFunction selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AuFunction record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AuFunction record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AuFunction> queryMainFunctionList(AuAuthority authority) {
		return auFunctionMapper.queryMainFunctionList(authority);
	}

	@Override
	public List<AuFunction> querySubFunctionList(AuFunction auFunction) {
		// TODO Auto-generated method stub
		return auFunctionMapper.querySubFunctionList(auFunction);
	}

	

	@Override
	public List<AuFunction> querySubauFunctionList(AuFunction auFunction) {
		// TODO Auto-generated method stub
		return auFunctionMapper.querySubauFunctionList(auFunction);
	}

}