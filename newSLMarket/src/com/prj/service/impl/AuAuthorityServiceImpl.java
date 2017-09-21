package com.prj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;
import com.prj.mapper.AuAuthorityMapper;
import com.prj.mapper.AuFunctionMapper;
import com.prj.service.IAuAuthorityService;
@Service
public class AuAuthorityServiceImpl implements IAuAuthorityService{
	@Autowired
	AuAuthorityMapper auAuthorityMapper;
	@Autowired
	AuFunctionMapper auFunctionMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AuAuthority record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AuAuthority record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AuAuthority selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AuAuthority record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AuAuthority record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AuAuthority queryAuthorityByRoleidAndFuncid(AuAuthority record) {
		return auAuthorityMapper.queryAuthorityByRoleidAndFuncid(record);
	}

	@Override
	public boolean hl_addAuthority(String[] ids, String createdby) {
		AuAuthority auAuthority = new AuAuthority();
		auAuthority.setRoleid(Long.valueOf(ids[0]));
		
		//删除角色下功能
		auAuthorityMapper.deleteFunctionByRoleid(Long.valueOf(ids[0]));
		
		//增加权限
		String sqlInString = "";
		for (int i = 1; i < ids.length; i++) {
			sqlInString += ids[i]+",";
		}
		if (sqlInString!=null && sqlInString.contains(",")) {
			sqlInString = sqlInString.substring(0, sqlInString.lastIndexOf(","));
			
			List<AuFunction> fList = auFunctionMapper.getFunctionListByIn(sqlInString);
			if (fList !=null && fList.size()>0) {
				for (AuFunction auFunction : fList) {
					auAuthority.setFunctionid(auFunction.getId());
					auAuthority.setCreatedby(createdby);
					auAuthority.setCreationtime(new Date());
					//循环增加
					auAuthorityMapper.insertSelective(auAuthority);
				}
			}
		}
		
		return true;
	}

}