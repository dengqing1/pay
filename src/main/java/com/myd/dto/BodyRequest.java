package com.myd.dto;



import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by you on 2018/11/21.
 */
public class BodyRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6650940123884083035L;
    
	@NotEmpty(message="签名方法不能为空")
	private String signMethod;//签名方法
	
	@NotEmpty(message="签名信息不能为空")
	private String signature;//签名信息
	
	@NotEmpty(message="商户的唯一编号不能为空")
	private String merchantId;//由支付平台分配给商户的唯一编号

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
	
	
	
}
