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
    
    //��ѯȫ����ɫ
    public List<AuRole> queryAllRole();
    
    //��ѯ�����õĽ�ɫ
    public List<AuRole> queryAllRoleByIsstart();
    
    //��֤��ɫ����������Ƿ����
    public AuRole checkIsExist(AuRole auRole);
    
    

}