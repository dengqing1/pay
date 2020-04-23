package com.myd.entity;
/**
 * 三方代付回调
 *@author xiaoqiang lu
 *
 *2018/12/28 10:46
 */
public class ThirdDaifuReturn {
	
	private String ret_code ;	 	 	//业务返回码，详见状态码说明 	1001
	private String ret_msg ;	 	 	//业务返回码描述 	支付成功
	private String sign ;	 		//数据签名 	~
	private String merch_id ;	 	 	//商户号 	例：PAY10100090000033
	private String out_order_no ;	 		//商户订单号 	~
	private String trade_no; 	 	 	//平台订单号 	~
	private String amount ;	 	//交易金额，单位"分" 	10000
	private String fee ;      //手续费，金额，单位"分" 	10
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMerch_id() {
		return merch_id;
	}
	public void setMerch_id(String merch_id) {
		this.merch_id = merch_id;
	}
	public String getOut_order_no() {
		return out_order_no;
	}
	public void setOut_order_no(String out_order_no) {
		this.out_order_no = out_order_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "ThirdDaifuReturn [" + (ret_code != null ? "ret_code=" + ret_code + ", " : "")
				+ (ret_msg != null ? "ret_msg=" + ret_msg + ", " : "") + (sign != null ? "sign=" + sign + ", " : "")
				+ (merch_id != null ? "merch_id=" + merch_id + ", " : "")
				+ (out_order_no != null ? "out_order_no=" + out_order_no + ", " : "")
				+ (trade_no != null ? "trade_no=" + trade_no + ", " : "")
				+ (amount != null ? "amount=" + amount + ", " : "") + (fee != null ? "fee=" + fee : "") + "]";
	}
	
	
	
	
	
	

}
