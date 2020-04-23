package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.NpayInRoutersTemp;

public interface NpayInRoutersTempService {

	List<Map<String, Object>> selectByRoutersTemp(NpayInRoutersTemp npayInRoutersTemp,Integer page);

}
