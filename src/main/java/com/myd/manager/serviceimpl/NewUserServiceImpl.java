package com.myd.manager.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NewUserMappper;
import com.myd.entity.NewUser;
import com.myd.entity.User;
import com.myd.manager.service.NewUserService;

@Service
public class NewUserServiceImpl implements NewUserService {

	@Autowired
	public NewUserMappper newUserMappper;
	
	@Override
	public NewUser selectByPrimaryKey(String name) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_C);
		NewUser newUser = newUserMappper.selectByPrimaryKey(name);
		return newUser;
	}

	@Override
	public NewUser selectByPrimary(NewUser newUser) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_C);
		NewUser nUser = newUserMappper.selectByPrimary(newUser);
		return nUser;
	}

}
