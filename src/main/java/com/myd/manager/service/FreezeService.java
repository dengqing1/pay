package com.myd.manager.service;

import java.util.List;

import com.myd.entity.Freeze;

public interface FreezeService {

	void insert(Freeze freeze);

	List<Freeze> selectByFreeze(Freeze freeze, Integer page);

}
