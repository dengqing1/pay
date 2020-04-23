package com.myd.dao;

import com.myd.entity.NpayAdjustLog;

public interface NpayAdjustLogMapper {
    int insert(NpayAdjustLog record);

    int insertSelective(NpayAdjustLog record);
}