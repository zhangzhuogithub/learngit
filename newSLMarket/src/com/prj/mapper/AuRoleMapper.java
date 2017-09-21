package com.prj.mapper;

import java.util.List;

import com.prj.bean.AuRole;

public interface AuRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuRole record);

    int insertSelective(AuRole record);

    AuRole selectByPrimaryKey(Long id);

    int hl_updateByPrimaryKeySelective(AuRole record);

    int updateByPrimaryKey(AuRole record);
    
    //查询全部角色
    public List<AuRole> queryAllRole();
    
    //查询已启用的角色
    public List<AuRole> queryAllRoleByIsstart();
    
    //验证角色编码或名称是否存在
    public AuRole checkIsExist(AuRole auRole);
    
    

}