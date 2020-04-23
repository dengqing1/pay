package com.myd.manager.service;

import java.util.List;

import com.myd.entity.NpayInRoutersHistory;

public interface NpayInRoutersHistoryService {

	List<NpayInRoutersHistory> selectByExamplehistory(NpayInRoutersHistory npayInRoutersHistory);

	int deleteByPrimaryKey(Integer id);

	int insertSelective(NpayInRoutersHistory npayInRoutersHistory);

}
