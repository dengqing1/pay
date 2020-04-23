package com.myd.serviceimpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.dao.UserMapper;
import com.myd.entity.User;
import com.myd.service.UserService;
import com.myd.util.Md5Util;
import com.myd.util.R;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;
	
	
	public User getUser(Long id){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		User user = userDao.getUser(id);
		return user;
	}


	@Override
	public void upadte(User user) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		userDao.update(user);
	}


	@Override
	public User toLogin(User user) {
		if(StringUtils.isNotBlank(user.getPassword())){
			user.setPassword(Md5Util.encryption(user.getPassword()+"@P#W$R%D^"));
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User find = userDao.toLogin(user);
		return find;
	}


	@Override
	public User getUserByEmail(String email) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = userDao.getUserByEmail(email);
		return user;
	}


	@Override
	public List<Map<String, Object>> selectByExampleUser(Map<String, Object> map, Integer page) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, Object>> list = userDao.selectByExampleUser(map);
		return list;
	}


	@Override
	public User findById(Integer id) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = userDao.findById(id);
		return user;
	}


	@Override
	public void updateByIsHide(User user) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDao.updateByIsHide(user);
	}


	@Override
	public void insertSelective(User user) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDao.insertSelective(user);
		
	}


	@Override
	public void updateByExampleSelective(User user) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		userDao.updateByExampleSelective(user);
		
	}
}
