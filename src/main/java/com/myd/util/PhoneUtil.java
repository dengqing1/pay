package com.myd.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class PhoneUtil {


		public static void main(String path[]) throws Exception {
			
			sendPhone("15298719931","1111");
		}		
		
		
		
		
		public static boolean sendPhone(String phone,String code) throws Exception{
			String URL = "http://route.showapi.com/28-1?showapi_appid=78993&showapi_sign=e21055f1efd14516b67d5481f640fe3a&mobile="+phone+"&content={'code':"+code+"}&tNum=T170317004040";
			
			URL u = new URL(URL);
			InputStream in = u.openStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				byte buf[] = new byte[1024];
				int read = 0;
				while ((read = in .read(buf)) > 0) {
					out.write(buf, 0, read);
				}
			} finally {
				if ( in != null) {
					in .close();
				}
			}
			byte b[] = out.toByteArray();
			String string = new String(b, "utf-8");
			String ret_code = JSONObject.parseObject(JSONObject.parseObject(string).getString("showapi_res_body")).getString("ret_code");
			//System.out.println(new String(b, "utf-8"));
			if ("0".equals(ret_code)) {
				return true;
			}
			return false;
		}
		
}
