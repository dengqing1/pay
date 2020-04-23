package com.myd.dao;

import com.myd.entity.NpayZtpoBank;

public interface NpayZtpoBankMapper {
    int deleteByPrimaryKey(String zBankId);

    int insert(NpayZtpoBank record);

    int insertSelective(NpayZtpoBank record);

    NpayZtpoBank selectByPrimaryKey(String zBankId);

    int updateByPrimaryKeySelective(NpayZtpoBank record);

    int updateByPrimaryKey(NpayZtpoBank record);
}