package com.myd.manager.service;

import com.myd.entity.NewUser;

public interface NewUserService {

	//查询出一个对象       判断登录
	    NewUser selectByPrimaryKey(String name);

		NewUser selectByPrimary(NewUser newUser);

}
