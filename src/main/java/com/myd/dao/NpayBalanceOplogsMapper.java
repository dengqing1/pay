package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayBalanceOplogs;

public interface NpayBalanceOplogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayBalanceOplogs record);

    int insertSelective(NpayBalanceOplogs npayBalanceOplogs);

    NpayBalanceOplogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayBalanceOplogs record);

    int updateByPrimaryKey(NpayBalanceOplogs record);

	List<NpayBalanceOplogs> selectByExampleoplogs(NpayBalanceOplogs npayBalanceOplogs);

	List<Map<String, Object>> findByAbnormal(Map<String, Object> map);
}