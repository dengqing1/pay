package com.myd.service;

import com.myd.entity.NpayBfInfo;

public interface NpayBfInfoService {
	
	void addNpayBfInfo(NpayBfInfo npaybfInfo);
	void updateBfinfo(NpayBfInfo npaybfInfo);
	NpayBfInfo getInfoByMerOid(String merOid);
	

}
