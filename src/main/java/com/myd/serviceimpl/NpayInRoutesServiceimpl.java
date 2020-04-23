package com.myd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.dao.NpayInRoutesMapper;
import com.myd.entity.NpayInRoutes;
import com.myd.service.NpayInRoutesService;

@Service("npayInRoutesService")
public class NpayInRoutesServiceimpl  implements NpayInRoutesService{
	
	@Autowired
	private NpayInRoutesMapper NpayInRoutesDao;
	
	
	@Override
	public List<NpayInRoutes> getRoutesBychaneId(String chaneId,Integer money) {
		// TODO Auto-generated method stub
		return NpayInRoutesDao.selectByChartId(chaneId,money);
	}

}
