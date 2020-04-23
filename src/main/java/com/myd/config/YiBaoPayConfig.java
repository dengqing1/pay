package com.myd.config;


/**
 * 易宝支付参数配置
 * @author admin
 *
 */


public class YiBaoPayConfig {

	
	
//	易宝参数----------------------------------------
	
	
	/**
	 * 商户号      平台分配
	 */
	public static String MCH_ID_YIBAO = "10012442782";
	
	/**
	 * 秘钥        平台提供
	 */
	public static String API_KEY_YIBAO = "mP42238826nuW64r7yh26DGK34o2L2m81L25RG32lD7Lo1058A7iJ28at6QS";
	
	
	/**
	 * 聚合网银支付地址     平台提供的地址
	 */
	public static String BANK_URL_YIBAO = "https://www.yeepay.com/app-merchant-proxy/node";
	
	
	
	/**
	 * 聚合网银回调  后台地址地址      自己填写的地址
	 */
	public static String BANK_NOTIFY_URL_YIBAO = "http://154.223.71.4:8080/fengmai/juheBanks_yibao";
	
	
	
	
}
