package com.myd.entity;
/**
 * 
 * @author lu xiaoqiang
 *
 * @date 2018年12月17日
 */
public class User {
	private Integer id ;
	private String name ;
	private String merchantid ;
	private String merId;
	
	private String password ;
	private String email ;
	private String phone ;
	
	private String role;
	private String locked;
	
	private Long createTime;//创建时间
	private Long updateTime;//更新时间
	private Integer isHide;//是否隐藏 0:正常 1:隐藏
	
	private String old_password ;
	private String new_password ;
	private String emailCode ;
	private String phoneCode ;
	
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getIsHide() {
		return isHide;
	}
	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", merchantid="
				+ merchantid + ", merId=" + merId + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", role=" + role
				+ ", locked=" + locked + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", isHide=" + isHide
				+ ", old_password=" + old_password + ", new_password="
				+ new_password + ", emailCode=" + emailCode + ", phoneCode="
				+ phoneCode + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
