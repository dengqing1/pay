package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.Notice;

public interface NoticeMapper {

	List<Notice> selectByNotice(Notice notice);

	int insertSelective(Notice notice);

	int updateByPrimaryKeySelective(Notice notice);

	Map<String, Object> findById(Integer id);

}
