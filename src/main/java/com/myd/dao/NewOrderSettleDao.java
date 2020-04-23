package com.myd.dao;

import java.util.HashMap;

public interface NewOrderSettleDao {
	
	HashMap<Object,Object> callProcedure(String sql) ;
}
