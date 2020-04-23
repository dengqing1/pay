package com.myd.dao;

import com.myd.entity.NpayBalanceDaily;

public interface NpayBalanceDailyMapper {
    int insert(NpayBalanceDaily record);

    int insertSelective(NpayBalanceDaily record);
}