package com.myd.dao;

import com.myd.entity.NpaySettleLogs2018;

public interface NpaySettleLogs2018Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpaySettleLogs2018 record);

    int insertSelective(NpaySettleLogs2018 record);

    NpaySettleLogs2018 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpaySettleLogs2018 record);

    int updateByPrimaryKey(NpaySettleLogs2018 record);
}