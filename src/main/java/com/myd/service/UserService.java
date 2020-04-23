package com.myd.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.User;

public interface UserService {

	User getUser(Long id);

	void upadte(User user);

	User toLogin(User user);

	User getUserByEmail(String email);

	List<Map<String, Object>> selectByExampleUser(Map<String, Object> map,Integer page);

	User findById(Integer id);

	void updateByIsHide(User user);

	void insertSelective(User user);

	void updateByExampleSelective(User user);
}
