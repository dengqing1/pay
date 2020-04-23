package com.myd.util;

import java.security.PublicKey;
import java.util.SortedMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.Charsets;

public class ThirdConfirmSign {
	
	
	private static final String PLAT_PUBLIC_KEY_TEXT =""+
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyaa3jwxTs+WD4yjjTt2LqM5Yi"+
			"5PUZ00kD5+ZEBjV18YLY0iEVCF2SVx7+bEfsGFzdGw3nk3BzR6VM0TldrJFMlaI2"+
			"J0JYAKXnDFBG7FzlGgH0Ij9YejWOzzv44Y3WGLuIO2vcFiaCeuf4cLyTW06jLpV8"+
			"FB9yBwROJ46wzLj+OQIDAQAB";

	public static boolean confirmSign(SortedMap<String, Object> map,String sign){	
		if(map != null){
			String content = PayUtil.signature(map);
			PublicKey publicKey =  RSA.toPublicKey(PLAT_PUBLIC_KEY_TEXT);
//			byte[] contentBytes = content.getBytes(Charsets.UTF_8);
//			byte[] signBytes = Base64.decodeBase64(sign);
//			boolean res = RSA.verify(contentBytes, signBytes, publicKey);
			boolean verify = RSA.verify(content, sign, publicKey);
			return verify;
		 }
		
		
		return false;
	}
	
	

}
