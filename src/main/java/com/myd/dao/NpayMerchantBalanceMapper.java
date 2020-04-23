package com.myd.dao;

import com.myd.entity.NpayMerchantBalance;

public interface NpayMerchantBalanceMapper {
    int deleteByPrimaryKey(String merchantid);

    int insert(NpayMerchantBalance record);

    int insertSelective(NpayMerchantBalance record);

    NpayMerchantBalance selectByPrimaryKey(String merchantid);

    int updateByPrimaryKeySelective(NpayMerchantBalance record);

    int updateByPrimaryKey(NpayMerchantBalance record);
}