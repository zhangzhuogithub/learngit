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
    
    //��¼
    public AuUser login(AuUser user);
    //��¼�˻���ѯ�Ƿ����
    public int logincodeIsExist(AuUser user);
    
    //��ѯ�ܼ�¼��
    public int count(AuUser user);
    
    //��ҳ��ѯ
    public List<AuUser> queryUsersByPage(AuUser user);
    
    //ɾ��ͼƬ
    public int delUserPic(AuUser user);
    
    
    //����roleId��ѯ�û�����
    public List<AuUser> queryUsersByRoleid(AuUser user);
    
    //���ݽ�ɫ�޸�rolename
    public int updateRolename(AuUser user);
}