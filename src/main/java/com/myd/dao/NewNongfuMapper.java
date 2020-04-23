package com.myd.dao;

import com.myd.entity.NewNongfu;

public interface NewNongfuMapper {
    int insert(NewNongfu record);

    int insertSelective(NewNongfu record);
}