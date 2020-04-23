package com.myd.dao;

import com.myd.entity.NpayOrderCheck;

public interface NpayOrderCheckMapper {
    int insert(NpayOrderCheck record);

    int insertSelective(NpayOrderCheck record);
}