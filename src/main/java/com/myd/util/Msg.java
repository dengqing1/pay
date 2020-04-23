package com.myd.util;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	private int code;
	private String msg;
	private Map<String, Object> extend = new HashMap();
	private Object object;

	public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}
	
	public static Msg success(String msg, Object object) {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg(msg);
		result.setObject(object);
		return result;
	}

	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}

	public static Msg error(String errorMsg) {
		Msg result = new Msg();
		result.setCode(300);
		result.setMsg(errorMsg);
		return result;
	}

	public Msg add(String key, Object value) {
		getExtend().put(key, value);
		return this;
	}

	public Msg addObject(Object obj) {
		setObject(obj);
		return this;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return this.extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	public Object getObject() {
		return this.object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}