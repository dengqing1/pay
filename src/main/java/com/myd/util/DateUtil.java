package com.myd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getNowTimeStamp(){
		return String.valueOf(System.currentTimeMillis()/1000);
		
	}

	
	
	
	public static String getNowTimeWithyyyyMMddHHmmss(){
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
		return sim.format(new Date());
	}
	
	
	public static Date getDate(){
		Date d  = null;
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d =  sim.parse(sim.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d ;
		
	}
	
	
	/**
	 * 得到当前的yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String getStringTime(Date date){
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
		return sim.format(date);
		
	}
	
	
	/**
	 * 的到当前的yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static Date getDateFormart(Date date){
		Date d  = null;
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d =  sim.parse(sim.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d ;
		
	}
	
	
	/**
	 * 得到当前的yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String getOrderId(Date date){
		int a = (int)((Math.random()*9+1)*100000);//6位数
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
		return sim.format(date)+String.valueOf(a);
		
	}
	
	
	
	
	
	
}
