package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayChannels;
import com.myd.entity.NpayTf56Bank;

public interface NpayOrderService {

	List<Map<String, Object>> selectByExampleOrder(Map<String, Object> map,Integer page);

	List<NpayTf56Bank> selectByBank(NpayTf56Bank npayTf56Bank);

	List<Map<String, Object>> selectByPrimary(NpayChannels npayChannels);

}
