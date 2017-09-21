package com.prj.mapper;

import java.util.List;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;

public interface AuAuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuAuthority record);

    int insertSelective(AuAuthority record);

    AuAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuAuthority record);

    int updateByPrimaryKey(AuAuthority record);
    
    //根据roleid查询功能
    public AuAuthority queryAuthorityByRoleidAndFuncid(AuAuthority record);
    //删除角色下功能
    int deleteFunctionByRoleid(long roleid);
}