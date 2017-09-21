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
    
    //����roleid��ѯ����
    public AuAuthority queryAuthorityByRoleidAndFuncid(AuAuthority record);
    //ɾ����ɫ�¹���
    int deleteFunctionByRoleid(long roleid);
}