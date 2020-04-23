package com.myd.dao;

import com.myd.entity.DailyOrderCheck;

public interface DailyOrderCheckMapper {
    int insert(DailyOrderCheck record);

    int insertSelective(DailyOrderCheck record);
}