package com.prj.service;

import com.prj.bean.Information;

public interface IInformationService {
    int deleteByPrimaryKey(Long id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);
}