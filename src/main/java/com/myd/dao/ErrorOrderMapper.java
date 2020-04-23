package com.myd.dao;

import com.myd.entity.ErrorOrder;

public interface ErrorOrderMapper {
    int insert(ErrorOrder record);

    int insertSelective(ErrorOrder record);
}