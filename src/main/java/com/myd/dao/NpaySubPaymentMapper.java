package com.myd.dao;

import com.myd.entity.NpaySubPayment;
import com.myd.entity.NpaySubPaymentKey;

public interface NpaySubPaymentMapper {
    int deleteByPrimaryKey(NpaySubPaymentKey key);

    int insert(NpaySubPayment record);

    int insertSelective(NpaySubPayment record);

    NpaySubPayment selectByPrimaryKey(NpaySubPaymentKey key);

    int updateByPrimaryKeySelective(NpaySubPayment record);

    int updateByPrimaryKey(NpaySubPayment record);
}