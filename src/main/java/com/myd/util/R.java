package com.myd.util;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	// 成功
	private static final Integer SUCCESS = 0;
	// 警告
	private static final Integer WARN = 1;
	// 异常 失败
	private static final Integer FAIL = 500;

	public R() {
		put("code", SUCCESS);
		put("msg", "操作成功");
	}

	public static R error(Object msg) {
		R R = new R();
		R.put("code", FAIL);
		R.put("msg", msg);
		return R;
	}

	public static R warn(Object msg) {
		R R = new R();
		R.put("code", WARN);
		R.put("msg", msg);
		return R;
	}

	public static R ok(Object msg) {
		R R = new R();
		R.put("code", SUCCESS);
		R.put("msg", msg);
		return R;
	}

	public static R ok() {
		return new R();
	}

	public static R error() {
		return R.error("");
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
