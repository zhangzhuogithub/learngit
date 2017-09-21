package com.prj.service;

import java.util.List;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;

public interface IAuAuthorityService {
    int deleteByPrimaryKey(Long id);

    int insert(AuAuthority record);

    int insertSelective(AuAuthority record);

    AuAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuAuthority record);

    int updateByPrimaryKey(AuAuthority record);
    
    //����roleid��ѯ����
    public AuAuthority queryAuthorityByRoleidAndFuncid(AuAuthority record);
    
    //�޸�Ȩ��
    public boolean hl_addAuthority(String[] ids,String createdby); 
}