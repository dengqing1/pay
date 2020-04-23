package com.myd.dao;

import com.myd.entity.NpayMerMoneyLogs;

public interface NpayMerMoneyLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayMerMoneyLogs record);

    int insertSelective(NpayMerMoneyLogs record);

    NpayMerMoneyLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayMerMoneyLogs record);

    int updateByPrimaryKey(NpayMerMoneyLogs record);
}