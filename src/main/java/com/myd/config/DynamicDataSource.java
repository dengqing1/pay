package com.myd.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource { 
	 public static final String DATASOURCE_A = "dataOhpay";
	    public static final String DATASOURCE_B = "dataUser";
	    public static final String DATASOURCE_C = "datamanagerUser";
	    //本地线程，获取当前正在执行的currentThread
	    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	 
	    public static void setCustomerType(String customerType) {
	        //把当前请求的参数，存入当前线程，这个参数是我们定义的DATASOURCE_A或者DATASOURCE_B
	        System.out.println("当前切换的数据源="+customerType);
	        contextHolder.set(customerType);
	    }
	    public static String getCustomerType() {
	 
	        return contextHolder.get();
	    }
	 
	    public static void clearCustomerType() {
	        contextHolder.remove();
	    }
	 
	    protected Object determineCurrentLookupKey() {
	        return getCustomerType();
	    }
	 	
//    @Override  
//    protected Object determineCurrentLookupKey() {  
//        return DataSourceContextHolder.getDBType();  
//    }  
}  