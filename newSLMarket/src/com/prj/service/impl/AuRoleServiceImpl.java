package com.prj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.prj.bean.AuRole;
import com.prj.bean.AuUser;
import com.prj.mapper.AuRoleMapper;
import com.prj.mapper.AuUserMapper;
import com.prj.service.IAuRoleService;
@Service
public class AuRoleServiceImpl implements IAuRoleService{
	@Resource
	AuRoleMapper auRoleMapper;
	@Resource
	AuUserMapper auUserMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return auRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AuRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AuRole record) {
		return auRoleMapper.insertSelective(record);
	}

	@Override
	public AuRole selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hl_updateByPrimaryKeySelective(AuRole record) {
		//根据角色修改用户rolename
		AuUser tempUser = new AuUser();
		tempUser.setRoleid(record.getId());
		tempUser.setRolename((record.getRolename()));
		//执行修改用户rolename方法
		auUserMapper.updateRolename(tempUser);
		return auRoleMapper.hl_updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AuRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AuRole> queryAllRole() {
		return auRoleMapper.queryAllRole();
	}

	@Override
	public List<AuRole> queryAllRoleByIsstart() {
		return auRoleMapper.queryAllRoleByIsstart();
	}

	@Override
	public AuRole checkIsExist(AuRole auRole) {
		return auRoleMapper.checkIsExist(auRole);
	}

}