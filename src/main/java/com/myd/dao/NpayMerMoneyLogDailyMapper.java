package com.myd.dao;

import com.myd.entity.NpayMerMoneyLogDaily;

public interface NpayMerMoneyLogDailyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayMerMoneyLogDaily record);

    int insertSelective(NpayMerMoneyLogDaily record);

    NpayMerMoneyLogDaily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayMerMoneyLogDaily record);

    int updateByPrimaryKey(NpayMerMoneyLogDaily record);
}