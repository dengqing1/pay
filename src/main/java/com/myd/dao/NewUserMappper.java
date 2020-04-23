package com.myd.dao;

import com.myd.entity.NewUser;

public interface NewUserMappper {

	//查询出一个对象       判断登录
	    NewUser selectByPrimaryKey(String name);

		NewUser selectByPrimary(NewUser newUser);

}
