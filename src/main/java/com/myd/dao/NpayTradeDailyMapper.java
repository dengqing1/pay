package com.myd.dao;

import com.myd.entity.NpayTradeDaily;

public interface NpayTradeDailyMapper {
    int insert(NpayTradeDaily record);

    int insertSelective(NpayTradeDaily record);
}