package com.prj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.bean.AuUser;
import com.prj.mapper.AuUserMapper;
import com.prj.service.IAuUserService;
@Service
public class AuUserServiceImpl implements IAuUserService{
	@Resource
	AuUserMapper auUserMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return auUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AuUser record) {
		// TODO Auto-generated method stub
		return auUserMapper.insert(record);
	}

	@Override
	public int insertSelective(AuUser record) {
		// TODO Auto-generated method stub
		return auUserMapper.insertSelective(record);
	}

	@Override
	public AuUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return auUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AuUser user) {
		// TODO Auto-generated method stub
		return auUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateByPrimaryKey(AuUser record) {
		// TODO Auto-generated method stub
		return auUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public AuUser login(AuUser user) {
		// TODO Auto-generated method stub
		return auUserMapper.login(user);
	}

	@Override
	public int logincodeIsExist(AuUser user) {
		
		return auUserMapper.logincodeIsExist(user);
	}

	@Override
	public int count(AuUser user) {
		// TODO Auto-generated method stub
		return auUserMapper.count(user);
	}

	@Override
	public List<AuUser> queryUsersByPage(AuUser user) {
		// TODO Auto-generated method stub
		return auUserMapper.queryUsersByPage(user);
	}

	@Override
	public int delUserPic(AuUser user) {
		// TODO Auto-generated method stub
		return auUserMapper.delUserPic(user);
	}

	@Override
	public List<AuUser> queryUsersByRoleid(AuUser user) {
		return auUserMapper.queryUsersByRoleid(user);
	}

	@Override
	public int updateRolename(AuUser user) {
		// TODO Auto-generated method stub
		return auUserMapper.updateRolename(user);
	}

}