package com.myd.manager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.myd.entity.Statement;
@Service
public interface StatementService {

    int insert(Statement record);
    
    List<Statement>  selectByList(String str);
    
    List<Map<String,Object>>  selectOrder(String str);
    
    List<Statement> selectCheckDate(String str);
    
    List<Statement> selectOneMerchant(String str);
    
}
