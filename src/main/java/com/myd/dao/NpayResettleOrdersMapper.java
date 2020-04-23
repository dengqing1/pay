package com.myd.dao;

import com.myd.entity.NpayResettleOrders;

public interface NpayResettleOrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayResettleOrders record);

    int insertSelective(NpayResettleOrders record);

    NpayResettleOrders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayResettleOrders record);

    int updateByPrimaryKey(NpayResettleOrders record);
}