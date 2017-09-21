package com.prj.service;

import com.prj.bean.LeaveMessage;

public interface ILeaveMessageService {
    int deleteByPrimaryKey(Long id);

    int insert(LeaveMessage record);

    int insertSelective(LeaveMessage record);

    LeaveMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LeaveMessage record);

    int updateByPrimaryKeyWithBLOBs(LeaveMessage record);

    int updateByPrimaryKey(LeaveMessage record);
}