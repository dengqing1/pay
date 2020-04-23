package com.myd.dao;

import com.myd.entity.NpaySettleWarning;

public interface NpaySettleWarningMapper {
    int insert(NpaySettleWarning record);

    int insertSelective(NpaySettleWarning record);
}