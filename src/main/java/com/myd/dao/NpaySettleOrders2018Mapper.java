package com.myd.dao;

import com.myd.entity.NpaySettleOrders2018;

public interface NpaySettleOrders2018Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpaySettleOrders2018 record);

    int insertSelective(NpaySettleOrders2018 record);

    NpaySettleOrders2018 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpaySettleOrders2018 record);

    int updateByPrimaryKey(NpaySettleOrders2018 record);
}