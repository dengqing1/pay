package com.myd.dao;

import com.myd.entity.NpaySettleOrders;

public interface NpaySettleOrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpaySettleOrders record);

    int insertSelective(NpaySettleOrders record);

    NpaySettleOrders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpaySettleOrders record);

    int updateByPrimaryKey(NpaySettleOrders record);
}