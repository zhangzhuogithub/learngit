package com.prj.mapper;

import com.prj.bean.LeaveMessage;

public interface LeaveMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LeaveMessage record);

    int insertSelective(LeaveMessage record);

    LeaveMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LeaveMessage record);

    int updateByPrimaryKeyWithBLOBs(LeaveMessage record);

    int updateByPrimaryKey(LeaveMessage record);
}