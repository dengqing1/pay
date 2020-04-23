package com.myd.dao;

import com.myd.entity.NpayWithdrawOrders;

public interface NpayWithdrawOrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayWithdrawOrders record);

    int insertSelective(NpayWithdrawOrders record);

    NpayWithdrawOrders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayWithdrawOrders record);

    int updateByPrimaryKey(NpayWithdrawOrders record);
}