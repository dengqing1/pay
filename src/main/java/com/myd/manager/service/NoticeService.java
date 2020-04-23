package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import com.myd.entity.Notice;

public interface NoticeService {

	List<Notice> selectByNotice(Notice notice);

	int insertSelective(Notice notice);

	int updateByPrimaryKeySelective(Notice notice);

	Map<String, Object> findById(Integer id);

}
