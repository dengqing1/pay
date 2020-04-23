package com.myd.util_wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import com.myd.config.YiBaoPayConfig;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;

public class PayYiBaoUtil {

	public static SortedMap<String, Object>  PayYiBao(String gateway, Object obj, NpayOrder order, NpayTf56Bank nBank,NpayChannels channels) {
	
		String param=null;
		if("bank".equals(gateway)){
			OrdersBank obank = (OrdersBank)obj ;
			//网银支付
			 
			 
			
			String p0_Cmd			= "Buy";
			String p1_MerId         = YiBaoPayConfig.MCH_ID_YIBAO;
			String p2_Order         = generateOrderId();
			String p3_Amt           = new BigDecimal(order.getTxnamt()).divide(new BigDecimal(100)).stripTrailingZeros().toPlainString();  //金额    元
			String p4_Cur           = "CNY";
			String p5_Pid           = "商品名称";
			String p6_Pcat          = "";
			String p7_Pdesc         = "";
			String p8_Url           = YiBaoPayConfig.BANK_NOTIFY_URL_YIBAO;
			String p9_SAF           = "0";
			String pa_MP            = "";
			String pd_FrpId         = "";
			String pr_NeedResponse  = "";
			String keyValue			= YiBaoPayConfig.API_KEY_YIBAO;

			String[] strArr			= new String[] {p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, 
														p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse};
			String hmac				= DigestUtil.getHmac(strArr, keyValue);
			
			
			
			 SortedMap<String, Object> requestParams = new TreeMap<String, Object>();

			try {
				p0_Cmd			= URLEncoder.encode(p0_Cmd,		"GBK");
				p1_MerId		= URLEncoder.encode(p1_MerId, 	"GBK");
				p2_Order		= URLEncoder.encode(p2_Order, 	"GBK");
				p3_Amt			= URLEncoder.encode(p3_Amt, 	"GBK");
				p4_Cur			= URLEncoder.encode(p4_Cur, 	"GBK");
				p5_Pid			= URLEncoder.encode(p5_Pid, 	"GBK");
				p6_Pcat			= URLEncoder.encode(p6_Pcat, 	"GBK");
				p7_Pdesc		= URLEncoder.encode(p7_Pdesc, 	"GBK");
				p8_Url 			= URLEncoder.encode(p8_Url, 	"GBK");
				p9_SAF 			= URLEncoder.encode(p9_SAF, 	"GBK");
				pa_MP 			= URLEncoder.encode(pa_MP, 		"GBK");
				pd_FrpId		= URLEncoder.encode(pd_FrpId, 	"GBK");
				pr_NeedResponse	= URLEncoder.encode(pr_NeedResponse, "GBK");
				hmac			= URLEncoder.encode(hmac, 		"GBK");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		/*
			String requestURL	= "https://www.yeepay.com/app-merchant-proxy/node";
			StringBuffer payURL = new StringBuffer();

			payURL.append(requestURL).append("?");
			payURL.append("p0_Cmd=").append(p0_Cmd);
	        payURL.append("&p1_MerId=").append(p1_MerId);
	        payURL.append("&p2_Order=").append(p2_Order);
	        payURL.append("&p3_Amt=").append(p3_Amt);
	        payURL.append("&p4_Cur=").append(p4_Cur);
	        payURL.append("&p5_Pid=").append(p5_Pid);
	        payURL.append("&p6_Pcat=").append(p6_Pcat);
	        payURL.append("&p7_Pdesc=").append(p7_Pdesc);
	        payURL.append("&p8_Url=").append(p8_Url);
	        payURL.append("&p9_SAF=").append(p9_SAF);
	        payURL.append("&pa_MP=").append(pa_MP);
	        payURL.append("&pd_FrpId=").append(pd_FrpId);
	        payURL.append("&pr_NeedResponse=").append(pr_NeedResponse);
	        payURL.append("&hmac=").append(hmac);                  
			
			//			 返回一个url
			 return payURL.toString();
			*/
			
			 SortedMap<String, Object> map = new TreeMap<String, Object>();
				
			
			  	map.put("p0_Cmd", p0_Cmd);   //商户id
				map.put("p1_MerId", p1_MerId);   
				map.put("p2_Order", p2_Order);     //商户订单号(我们自己生成的id)
				map.put("p3_Amt", p3_Amt);
				map.put("p4_Cur", p4_Cur);
				map.put("p5_Pid", p5_Pid);
				map.put("p6_Pcat", p6_Pcat);
				map.put("p7_Pdesc", p7_Pdesc);
				map.put("p8_Url", p8_Url);  
				map.put("p9_SAF", p9_SAF);  
				map.put("pa_MP", pa_MP);  
				map.put("pd_FrpId", pd_FrpId);  
				map.put("pr_NeedResponse", pr_NeedResponse);  
				map.put("hmac", hmac);      
				
				return map;

			 
	
		}else if("daifu".equals(gateway)){
			OrdersDaifu odifu = (OrdersDaifu)obj ;
			//代付
			return  null;
		
		
		}
		
		return null ;
		
		
	}
	
	
	
	
	
	/**
	 * @author
	 * @date 2016-4-22
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSign(
			SortedMap<String, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"bank_number".equals(k) && !"pay_productname".equals(k) && !"pay_md5sign".equals(k)
					&& !"acct_name".equals(k)&& !"id_no".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		
		System.out.println("--------签名前数据-----》"+sb);
		String sign =MD5Util.md5(sb.toString());
				
		System.out.println(sign);
		return sign;
	}
	
		
	

	 //打印到界面
	 public static void jsonString(String str,HttpServletResponse response){
			PrintWriter out  = null;
			response.setCharacterEncoding("utf-8");
	  		response.setContentType("text/html;charset=utf-8");
	  		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
	  		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	  		response.setHeader("Pragma", "no-cache");
	        try {
				out = response.getWriter();
				out.write(str);
			    out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				out.close();
			}
	    	
			
			
			
			
		}
	 
	 
	 
	 public static String generateOrderId(){
	        String keyup_prefix=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String keyup_append=String.valueOf(new Random().nextInt(899999)+100000);
	        String pay_orderid=keyup_prefix+keyup_append;//订单号
	        return pay_orderid;
	    }
	 
	 
	 
	 
	 
	
	 //获取当前时间     ("yyyy-MM-dd HH:mm:ss")
	 public static String generateTime(){
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	    }
	
	
}
