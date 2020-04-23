package com.myd.dao;

import com.myd.entity.NpaySettleOrdersBackup;

public interface NpaySettleOrdersBackupMapper {
    int insert(NpaySettleOrdersBackup record);

    int insertSelective(NpaySettleOrdersBackup record);
}