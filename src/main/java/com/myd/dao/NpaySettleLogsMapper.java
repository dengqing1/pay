package com.myd.dao;

import com.myd.entity.NpaySettleLogs;

public interface NpaySettleLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpaySettleLogs record);

    int insertSelective(NpaySettleLogs record);

    NpaySettleLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpaySettleLogs record);

    int updateByPrimaryKey(NpaySettleLogs record);
}