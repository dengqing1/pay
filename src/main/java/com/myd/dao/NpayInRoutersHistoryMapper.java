package com.myd.dao;

import java.util.List;

import com.myd.entity.NpayInRoutersHistory;

public interface NpayInRoutersHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NpayInRoutersHistory record);

    int insertSelective(NpayInRoutersHistory record);

    NpayInRoutersHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NpayInRoutersHistory record);

    int updateByPrimaryKeyWithBLOBs(NpayInRoutersHistory record);

    int updateByPrimaryKey(NpayInRoutersHistory record);

	List<NpayInRoutersHistory> selectByExamplehistory(NpayInRoutersHistory npayInRoutersHistory);
}