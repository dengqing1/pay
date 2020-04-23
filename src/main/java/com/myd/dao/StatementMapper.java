package com.myd.dao;

import java.util.List;
import java.util.Map;

import com.myd.entity.Statement;

public interface StatementMapper {
    int deleteByPrimaryKey(Integer staId);

    int insert(Statement record);
    
    List<Statement>  selectByList(String str);
    
    List<Map<String,Object>> selectOrder(String str);
    
    List<Statement> selectCheckDate(String str);
    
    List<Statement> selectOneMerchant(String str);

    
    int insertSelective(Statement record);

    Statement selectByPrimaryKey(Integer staId);

    int updateByPrimaryKeySelective(Statement record);

    int updateByPrimaryKey(Statement record);
}