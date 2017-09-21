package com.prj.service;

import java.util.List;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;

public interface IAuFunctionService {
    int deleteByPrimaryKey(Long id);

    int insert(AuFunction record);

    int insertSelective(AuFunction record);

    AuFunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuFunction record);

    int updateByPrimaryKey(AuFunction record);
    
    //��ȡ�����˵��б�
    public List<AuFunction> queryMainFunctionList(AuAuthority authority);
    public List<AuFunction> querySubFunctionList(AuFunction auFunction);
    
    //Ȩ�����б�
   
    public List<AuFunction> querySubauFunctionList(AuFunction auFunction);
    
}