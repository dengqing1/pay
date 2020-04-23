package com.myd.dao;

import com.myd.entity.NpayCheckOrders;

public interface NpayCheckOrdersMapper {
    int insert(NpayCheckOrders record);

    int insertSelective(NpayCheckOrders record);
}