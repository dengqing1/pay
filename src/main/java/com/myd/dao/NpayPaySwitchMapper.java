package com.myd.dao;

import com.myd.entity.NpayPaySwitch;

public interface NpayPaySwitchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayPaySwitch record);

    int insertSelective(NpayPaySwitch record);

    NpayPaySwitch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayPaySwitch record);

    int updateByPrimaryKey(NpayPaySwitch record);
}