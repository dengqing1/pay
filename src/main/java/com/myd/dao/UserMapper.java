package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.User;

public interface UserMapper {

    User getUser(Long id);

	void update(User user);

	User toLogin(User user);

	User getUserByEmail(String email);

	List<Map<String, Object>> selectByExampleUser(Map<String, Object> map);

	User findById(Integer id);

	void updateByIsHide(User user);

	void insertSelective(User user);

	void updateByExampleSelective(User user);

}
