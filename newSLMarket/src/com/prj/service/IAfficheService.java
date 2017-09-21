package com.prj.service;

import com.prj.bean.Affiche;

public interface IAfficheService {
    int deleteByPrimaryKey(Long id);

    int insert(Affiche record);

    int insertSelective(Affiche record);

    Affiche selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Affiche record);

    int updateByPrimaryKeyWithBLOBs(Affiche record);

    int updateByPrimaryKey(Affiche record);
}