package com.myd.manager.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myd.config.DynamicDataSource;
import com.myd.dao.NoticeMapper;
import com.myd.entity.Notice;
import com.myd.manager.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice> selectByNotice(Notice notice) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		List<Notice> list =  noticeMapper.selectByNotice(notice);
		return list;
	}

	@Override
	public int insertSelective(Notice notice) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		int save = noticeMapper.insertSelective(notice);
		return save;
	}

	@Override
	public int updateByPrimaryKeySelective(Notice notice) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		int update = noticeMapper.updateByPrimaryKeySelective(notice);
		return update;
	}

	@Override
	public Map<String, Object> findById(Integer id) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_B);
		Map<String, Object> notice = noticeMapper.findById(id);
		return notice;
	}
}
