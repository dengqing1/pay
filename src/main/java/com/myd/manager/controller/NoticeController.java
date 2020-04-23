package com.myd.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.entity.Notice;
import com.myd.manager.service.NoticeService;
import com.myd.util.DateUtil;
import com.myd.util.Msg;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 公告列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	public String noticelist(HttpServletRequest request){
		Notice notice = new Notice();
		List<Notice> list = noticeService.selectByNotice(notice);
		request.setAttribute("list",list);
		return "notice_list";
		
	}
	
	/**
	 * 编辑
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "/saveNotice", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveNotice(Notice notice,String id){
		if(id!=null){
			notice.setUpdatedAt(DateUtil.getDate());
			int update = noticeService.updateByPrimaryKeySelective(notice);
		}else{
			notice.setCreatedAt(DateUtil.getDate());
			int save = noticeService.insertSelective(notice);
		}
		return Msg.success();
		
	}
	
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	public Msg findById(Integer id){
		Map<String,Object> notice = noticeService.findById(id);
		return Msg.success().addObject(notice);
		
	}
}
