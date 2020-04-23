package com.myd.dao;

import com.myd.entity.NpayZhongbangBank;

public interface NpayZhongbangBankMapper {
    int deleteByPrimaryKey(String zBankId);

    int insert(NpayZhongbangBank record);

    int insertSelective(NpayZhongbangBank record);

    NpayZhongbangBank selectByPrimaryKey(String zBankId);

    int updateByPrimaryKeySelective(NpayZhongbangBank record);

    int updateByPrimaryKey(NpayZhongbangBank record);
}