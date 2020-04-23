package com.myd.service;

import java.util.List;

import com.myd.entity.NpayInRoutes;

public interface NpayInRoutesService {
	 List<NpayInRoutes> getRoutesBychaneId(String chaneId,Integer money);

}
