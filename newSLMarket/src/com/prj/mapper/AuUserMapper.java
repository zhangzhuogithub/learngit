package com.prj.mapper;

import java.util.List;

import com.prj.bean.AuUser;

public interface AuUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuUser record);

    int insertSelective(AuUser record);

    AuUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuUser record);

    int updateByPrimaryKey(AuUser record);
    
    //登录
    public AuUser login(AuUser user);
    //登录账户查询是否存在
    public int logincodeIsExist(AuUser user);
    
    //查询总记录数
    public int count(AuUser user);
    
    //分页查询
    public List<AuUser> queryUsersByPage(AuUser user);
    
    //删除图片
    public int delUserPic(AuUser user);
    
    
    //根据roleId查询用户对象
    public List<AuUser> queryUsersByRoleid(AuUser user);
    
    //根据角色修改rolename
    public int updateRolename(AuUser user);
}