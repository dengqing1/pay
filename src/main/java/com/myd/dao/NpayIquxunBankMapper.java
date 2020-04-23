package com.myd.dao;

import com.myd.entity.NpayIquxunBank;

public interface NpayIquxunBankMapper {
    int deleteByPrimaryKey(String zBankId);

    int insert(NpayIquxunBank record);

    int insertSelective(NpayIquxunBank record);

    NpayIquxunBank selectByPrimaryKey(String zBankId);

    int updateByPrimaryKeySelective(NpayIquxunBank record);

    int updateByPrimaryKey(NpayIquxunBank record);
}