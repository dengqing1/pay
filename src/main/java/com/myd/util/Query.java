package com.myd.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Query implements Serializable {

	private static final long serialVersionUID = -4869594085374385813L;

	private int pageSize = 10;
	private int pageNum = 1;
	private int pages = 1;

	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		
		return ( pageNum -1 ) * pageSize;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public void setPages(int count){
		  int p = count / pageSize;
	        if (count % pageSize == 0)
	        	this.pages =  p;
	        else
	        	this.pages =  p + 1;
	}
	
	public static void main(String[] args) {
		System.out.println(10/10);
	}

	public Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<>();
		rspData.put("rows", pageInfo.getList());
		rspData.put("total", pageInfo.getTotal());
		rspData.put("pages", pages);
		rspData.put("pageNum", pageNum);
		return rspData;
	}
}
