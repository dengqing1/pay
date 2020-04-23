package com.myd.dao;

import java.util.List;

import com.myd.entity.Freeze;

public interface FreezeMapper {

	void insert(Freeze freeze);

	List<Freeze> selectByFreeze(Freeze freeze);

}
