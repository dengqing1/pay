package com.myd.dao;

import com.myd.entity.NpayMerchantBalanceBackup;

public interface NpayMerchantBalanceBackupMapper {
    int insert(NpayMerchantBalanceBackup record);

    int insertSelective(NpayMerchantBalanceBackup record);
}