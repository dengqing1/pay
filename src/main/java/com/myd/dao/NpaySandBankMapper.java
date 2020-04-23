package com.myd.dao;

import com.myd.entity.NpaySandBank;

public interface NpaySandBankMapper {
    int deleteByPrimaryKey(String zBankId);

    int insert(NpaySandBank record);

    int insertSelective(NpaySandBank record);

    NpaySandBank selectByPrimaryKey(String zBankId);

    int updateByPrimaryKeySelective(NpaySandBank record);

    int updateByPrimaryKey(NpaySandBank record);
}