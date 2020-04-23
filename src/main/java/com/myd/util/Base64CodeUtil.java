package com.myd.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * 字符串base64编码与解码
 *@author xiaoqiang lu
 *
 *2018/12/26 16:34
 */
public class Base64CodeUtil {
	/**
	 * 进行base64编码
	 * @param str
	 * @return
	 */
	public static String Base64Encoding(String str){
		String res = null;
		if(str == null){
			
		}else{
			try {
				res =  new String(Base64.encodeBase64(str.getBytes("UTF-8")), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
		
	}
	
	/**
	 * base64解码
	 * @param str
	 * @return
	 */
	public static String Base64Decoding(String str){
		String res = null;
		if(str == null){
		}else{
			
			Base64 base64 = new Base64();
			byte[] b = str.getBytes();
			try {
				res = new String( base64.decode(b), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return res;
		
	}
	
	public static void main(String[] args) {
		String base64Encoding = Base64CodeUtil.Base64Encoding("sample_600000000000002_20190116141411.xlsx");
		System.out.println(base64Encoding);
	}
	
}
