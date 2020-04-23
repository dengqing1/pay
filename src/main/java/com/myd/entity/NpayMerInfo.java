package com.myd.entity;

import java.text.SimpleDateFormat;

/**
 * 商户信息
 * @author lu xiaoqiang
 *
 * @date 2018年12月18日
 */
public class NpayMerInfo {
    private String merId;

    private String merShortName;//商户简称

    private String merName;//工商注册名称

    private String merRegNo;//工商注册号码

    private String merRegAddr;//工商注册地址

    private String merTaxNo;//税务登记号

    private String merOrganizationCertificate;//组织机构证

    private String merRegCapital;//注册资本

    private String merKind;//公司性质

    private Integer merRiskLevel;//风险等级

    private String merLicenseValidDate;//执照有效期

    private String merBusinessType;//MCC类型

    private Integer merProvinceId;//省

    private Integer merCityId;//市

    private String merAddress;//办公地址

    private String merLegalPerson;//法人代表

    private String merLegalIdType;//证件类型

    private String merLegalIdNumber;//法人身份证

    private String merLegalIdValidDate;//证件有效期

    private String openDate;//开业日期

    private String contact;//业务联系人

    private String contactPhone;//业务联系电话

    private String contactEmail;//业务联系EMAIL

    private String settBankId;//收款银行编号

    private String settBankType;//账户类型

    private String settBankAccountNo;//结算账号

    private String settAccountName;//结算账号名

    private String settCertifyId;//结算开户身份证号

    private String openProductIds;//开通产品

    private String openGateways;//开通支付网关

    private Integer merInfoCreateTime;//商户信息录入时间

    private Integer merInfoUpdateTime;//商户信息更新时间

    private Integer merCheckStatus;//商户审核状态

    private Integer merInfoCheckTime;//商户审核时间

    private Integer merOpenStatus;//商户开通状态

    private String merSecretKey;//商户秘钥

    private String merIpWhitelist;//IP白名单

    private Integer merNeedCheckIpWhitelist;//是否需要验证IP白名单, 0:不验证, 1:验证(默认1)

    private Integer merOpenTime;//商户开通时间

    private Integer merOpenMailSentTime;//邮件发送时间

    private String daifuChannelId;//代付 通道号

    private String daifuChannelMerAbbr;//代付渠道abbr

    private String daifuChannelMerId;//代付 通道商户号

    private String daifuChannelChannelId;//代付 通道 channel_id

    private Integer daifuChannelT1;//代付 通道是否T1

    private Integer limitInMoneyDaily;//每日入金限额

    private Integer limitInMoneySingle;//单笔入金限额

    private Integer limitOutMoneySingle;//单笔代付限额

    private Integer limitOutMoneyCardDaily;//单日单卡出金限额

    private Integer t1;//是否T1[0:T0, 1:T1]

    private String kftMerId;//快付通一级商户号

    private String kftSceMerIds;//快付通二级商户号

    private Integer isDelete;//是否删除, 0:未删除, 1:删除

    private String authkey;

    private Integer isHide;//是否隐藏 0显示 1 隐藏

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getMerShortName() {
        return merShortName;
    }

    public void setMerShortName(String merShortName) {
        this.merShortName = merShortName == null ? null : merShortName.trim();
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public String getMerRegNo() {
        return merRegNo;
    }

    public void setMerRegNo(String merRegNo) {
        this.merRegNo = merRegNo == null ? null : merRegNo.trim();
    }

    public String getMerRegAddr() {
        return merRegAddr;
    }

    public void setMerRegAddr(String merRegAddr) {
        this.merRegAddr = merRegAddr == null ? null : merRegAddr.trim();
    }

    public String getMerTaxNo() {
        return merTaxNo;
    }

    public void setMerTaxNo(String merTaxNo) {
        this.merTaxNo = merTaxNo == null ? null : merTaxNo.trim();
    }

    public String getMerOrganizationCertificate() {
        return merOrganizationCertificate;
    }

    public void setMerOrganizationCertificate(String merOrganizationCertificate) {
        this.merOrganizationCertificate = merOrganizationCertificate == null ? null : merOrganizationCertificate.trim();
    }

    public String getMerRegCapital() {
        return merRegCapital;
    }

    public void setMerRegCapital(String merRegCapital) {
        this.merRegCapital = merRegCapital == null ? null : merRegCapital.trim();
    }

    public String getMerKind() {
        return merKind;
    }

    public void setMerKind(String merKind) {
        this.merKind = merKind == null ? null : merKind.trim();
    }

    public Integer getMerRiskLevel() {
        return merRiskLevel;
    }

    public void setMerRiskLevel(Integer merRiskLevel) {
        this.merRiskLevel = merRiskLevel;
    }

    public String getMerLicenseValidDate() {
        return merLicenseValidDate;
    }

    public void setMerLicenseValidDate(String merLicenseValidDate) {
        this.merLicenseValidDate = merLicenseValidDate == null ? null : merLicenseValidDate.trim();
    }

    public String getMerBusinessType() {
        return merBusinessType;
    }

    public void setMerBusinessType(String merBusinessType) {
        this.merBusinessType = merBusinessType == null ? null : merBusinessType.trim();
    }

    public Integer getMerProvinceId() {
        return merProvinceId;
    }

    public void setMerProvinceId(Integer merProvinceId) {
        this.merProvinceId = merProvinceId;
    }

    public Integer getMerCityId() {
        return merCityId;
    }

    public void setMerCityId(Integer merCityId) {
        this.merCityId = merCityId;
    }

    public String getMerAddress() {
        return merAddress;
    }

    public void setMerAddress(String merAddress) {
        this.merAddress = merAddress == null ? null : merAddress.trim();
    }

    public String getMerLegalPerson() {
        return merLegalPerson;
    }

    public void setMerLegalPerson(String merLegalPerson) {
        this.merLegalPerson = merLegalPerson == null ? null : merLegalPerson.trim();
    }

    public String getMerLegalIdType() {
        return merLegalIdType;
    }

    public void setMerLegalIdType(String merLegalIdType) {
        this.merLegalIdType = merLegalIdType == null ? null : merLegalIdType.trim();
    }

    public String getMerLegalIdNumber() {
        return merLegalIdNumber;
    }

    public void setMerLegalIdNumber(String merLegalIdNumber) {
        this.merLegalIdNumber = merLegalIdNumber == null ? null : merLegalIdNumber.trim();
    }

    public String getMerLegalIdValidDate() {
        return merLegalIdValidDate;
    }

    public void setMerLegalIdValidDate(String merLegalIdValidDate) {
        this.merLegalIdValidDate = merLegalIdValidDate == null ? null : merLegalIdValidDate.trim();
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate == null ? null : openDate.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getSettBankId() {
        return settBankId;
    }

    public void setSettBankId(String settBankId) {
        this.settBankId = settBankId == null ? null : settBankId.trim();
    }

    public String getSettBankType() {
        return settBankType;
    }

    public void setSettBankType(String settBankType) {
        this.settBankType = settBankType == null ? null : settBankType.trim();
    }

    public String getSettBankAccountNo() {
        return settBankAccountNo;
    }

    public void setSettBankAccountNo(String settBankAccountNo) {
        this.settBankAccountNo = settBankAccountNo == null ? null : settBankAccountNo.trim();
    }

    public String getSettAccountName() {
        return settAccountName;
    }

    public void setSettAccountName(String settAccountName) {
        this.settAccountName = settAccountName == null ? null : settAccountName.trim();
    }

    public String getSettCertifyId() {
        return settCertifyId;
    }

    public void setSettCertifyId(String settCertifyId) {
        this.settCertifyId = settCertifyId == null ? null : settCertifyId.trim();
    }

    public String getOpenProductIds() {
        return openProductIds;
    }

    public void setOpenProductIds(String openProductIds) {
        this.openProductIds = openProductIds == null ? null : openProductIds.trim();
    }

    public String getOpenGateways() {
        return openGateways;
    }

    public void setOpenGateways(String openGateways) {
        this.openGateways = openGateways == null ? null : openGateways.trim();
    }

    public Integer getMerInfoCreateTime() {
        return merInfoCreateTime;
    }

    public void setMerInfoCreateTime(Integer merInfoCreateTime) {
        this.merInfoCreateTime = merInfoCreateTime;
    }

   
    public Integer getMerInfoUpdateTime() {
		return merInfoUpdateTime;
	}

	public void setMerInfoUpdateTime(Integer merInfoUpdateTime) {
		this.merInfoUpdateTime = merInfoUpdateTime;
	}

	public Integer getMerCheckStatus() {
        return merCheckStatus;
    }

    public void setMerCheckStatus(Integer merCheckStatus) {
        this.merCheckStatus = merCheckStatus;
    }

    public Integer getMerInfoCheckTime() {
        return merInfoCheckTime;
    }

    public void setMerInfoCheckTime(Integer merInfoCheckTime) {
        this.merInfoCheckTime = merInfoCheckTime;
    }

    public Integer getMerOpenStatus() {
        return merOpenStatus;
    }

    public void setMerOpenStatus(Integer merOpenStatus) {
        this.merOpenStatus = merOpenStatus;
    }

    public String getMerSecretKey() {
        return merSecretKey;
    }

    public void setMerSecretKey(String merSecretKey) {
        this.merSecretKey = merSecretKey == null ? null : merSecretKey.trim();
    }

    public String getMerIpWhitelist() {
        return merIpWhitelist;
    }

    public void setMerIpWhitelist(String merIpWhitelist) {
        this.merIpWhitelist = merIpWhitelist == null ? null : merIpWhitelist.trim();
    }

    public Integer getMerNeedCheckIpWhitelist() {
        return merNeedCheckIpWhitelist;
    }

    public void setMerNeedCheckIpWhitelist(Integer merNeedCheckIpWhitelist) {
        this.merNeedCheckIpWhitelist = merNeedCheckIpWhitelist;
    }

    public Integer getMerOpenTime() {
        return merOpenTime;
    }

    public void setMerOpenTime(Integer merOpenTime) {
        this.merOpenTime = merOpenTime;
    }

    public Integer getMerOpenMailSentTime() {
        return merOpenMailSentTime;
    }

    public void setMerOpenMailSentTime(Integer merOpenMailSentTime) {
        this.merOpenMailSentTime = merOpenMailSentTime;
    }

    public String getDaifuChannelId() {
        return daifuChannelId;
    }

    public void setDaifuChannelId(String daifuChannelId) {
        this.daifuChannelId = daifuChannelId == null ? null : daifuChannelId.trim();
    }

    public String getDaifuChannelMerAbbr() {
        return daifuChannelMerAbbr;
    }

    public void setDaifuChannelMerAbbr(String daifuChannelMerAbbr) {
        this.daifuChannelMerAbbr = daifuChannelMerAbbr == null ? null : daifuChannelMerAbbr.trim();
    }

    public String getDaifuChannelMerId() {
        return daifuChannelMerId;
    }

    public void setDaifuChannelMerId(String daifuChannelMerId) {
        this.daifuChannelMerId = daifuChannelMerId == null ? null : daifuChannelMerId.trim();
    }

    public String getDaifuChannelChannelId() {
        return daifuChannelChannelId;
    }

    public void setDaifuChannelChannelId(String daifuChannelChannelId) {
        this.daifuChannelChannelId = daifuChannelChannelId == null ? null : daifuChannelChannelId.trim();
    }

    public Integer getDaifuChannelT1() {
        return daifuChannelT1;
    }

    public void setDaifuChannelT1(Integer daifuChannelT1) {
        this.daifuChannelT1 = daifuChannelT1;
    }

    public Integer getLimitInMoneyDaily() {
        return limitInMoneyDaily;
    }

    public void setLimitInMoneyDaily(Integer limitInMoneyDaily) {
        this.limitInMoneyDaily = limitInMoneyDaily;
    }

    public Integer getLimitInMoneySingle() {
        return limitInMoneySingle;
    }

    public void setLimitInMoneySingle(Integer limitInMoneySingle) {
        this.limitInMoneySingle = limitInMoneySingle;
    }

    public Integer getLimitOutMoneySingle() {
        return limitOutMoneySingle;
    }

    public void setLimitOutMoneySingle(Integer limitOutMoneySingle) {
        this.limitOutMoneySingle = limitOutMoneySingle;
    }

    public Integer getLimitOutMoneyCardDaily() {
        return limitOutMoneyCardDaily;
    }

    public void setLimitOutMoneyCardDaily(Integer limitOutMoneyCardDaily) {
        this.limitOutMoneyCardDaily = limitOutMoneyCardDaily;
    }

    public Integer getT1() {
        return t1;
    }

    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    public String getKftMerId() {
        return kftMerId;
    }

    public void setKftMerId(String kftMerId) {
        this.kftMerId = kftMerId == null ? null : kftMerId.trim();
    }

    public String getKftSceMerIds() {
        return kftSceMerIds;
    }

    public void setKftSceMerIds(String kftSceMerIds) {
        this.kftSceMerIds = kftSceMerIds == null ? null : kftSceMerIds.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey == null ? null : authkey.trim();
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

	@Override
	public String toString() {
		return "NpayMerInfo [merId=" + merId + ", merShortName=" + merShortName + ", merName=" + merName + ", merRegNo="
				+ merRegNo + ", merRegAddr=" + merRegAddr + ", merTaxNo=" + merTaxNo + ", merOrganizationCertificate="
				+ merOrganizationCertificate + ", merRegCapital=" + merRegCapital + ", merKind=" + merKind
				+ ", merRiskLevel=" + merRiskLevel + ", merLicenseValidDate=" + merLicenseValidDate
				+ ", merBusinessType=" + merBusinessType + ", merProvinceId=" + merProvinceId + ", merCityId="
				+ merCityId + ", merAddress=" + merAddress + ", merLegalPerson=" + merLegalPerson + ", merLegalIdType="
				+ merLegalIdType + ", merLegalIdNumber=" + merLegalIdNumber + ", merLegalIdValidDate="
				+ merLegalIdValidDate + ", openDate=" + openDate + ", contact=" + contact + ", contactPhone="
				+ contactPhone + ", contactEmail=" + contactEmail + ", settBankId=" + settBankId + ", settBankType="
				+ settBankType + ", settBankAccountNo=" + settBankAccountNo + ", settAccountName=" + settAccountName
				+ ", settCertifyId=" + settCertifyId + ", openProductIds=" + openProductIds + ", openGateways="
				+ openGateways + ", merInfoCreateTime=" + merInfoCreateTime + ", merInfoUpdateTime=" + merInfoUpdateTime
				+ ", merCheckStatus=" + merCheckStatus + ", merInfoCheckTime=" + merInfoCheckTime + ", merOpenStatus="
				+ merOpenStatus + ", merSecretKey=" + merSecretKey + ", merIpWhitelist=" + merIpWhitelist
				+ ", merNeedCheckIpWhitelist=" + merNeedCheckIpWhitelist + ", merOpenTime=" + merOpenTime
				+ ", merOpenMailSentTime=" + merOpenMailSentTime + ", daifuChannelId=" + daifuChannelId
				+ ", daifuChannelMerAbbr=" + daifuChannelMerAbbr + ", daifuChannelMerId=" + daifuChannelMerId
				+ ", daifuChannelChannelId=" + daifuChannelChannelId + ", daifuChannelT1=" + daifuChannelT1
				+ ", limitInMoneyDaily=" + limitInMoneyDaily + ", limitInMoneySingle=" + limitInMoneySingle
				+ ", limitOutMoneySingle=" + limitOutMoneySingle + ", limitOutMoneyCardDaily=" + limitOutMoneyCardDaily
				+ ", t1=" + t1 + ", kftMerId=" + kftMerId + ", kftSceMerIds=" + kftSceMerIds + ", isDelete=" + isDelete
				+ ", authkey=" + authkey + ", isHide=" + isHide + "]";
	}
    
    
    
    
}