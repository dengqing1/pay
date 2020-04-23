package com.myd.dao;

import com.myd.entity.NpayPreblockBalance;

public interface NpayPreblockBalanceMapper {
    int deleteByPrimaryKey(String merchantid);

    int insert(NpayPreblockBalance record);

    int insertSelective(NpayPreblockBalance record);

    NpayPreblockBalance selectByPrimaryKey(String merchantid);

    int updateByPrimaryKeySelective(NpayPreblockBalance record);

    int updateByPrimaryKey(NpayPreblockBalance record);
}