package com.myd.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by you on 2018/11/21.
 */
public class StatusRequest extends BodyRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1942860176275197396L;
	@NotEmpty(message="商户的订单号不能为空")
	private String merOrderId; //商户订单号

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	
	
	
	
	
}
