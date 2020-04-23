package com.myd.dao;

import com.myd.entity.NpayCardBin;

public interface NpayCardBinMapper {
    int insert(NpayCardBin record);

    int insertSelective(NpayCardBin record);
}