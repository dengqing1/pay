package com.myd.entity;

import java.math.BigDecimal;

/**
 * 功能描述: 商户信息实体类
 *
 * @auther: loujiawei
 * @date: 2018/12/4 11:39
 */
public class MerchantInformation {

    //商户ID
    private String merchant_id;
    //商户名称
    private String merchant_name;
    //产品
    private String product;
    //信息更新时间
    private String information_update_time;
    //风险等级
    private String risk_level;
    //信息审核状态
    private String information_audit_status;
    //审核时间
    private String audit_time;
    //开通状态
    private String opening_status;
    //开通时间
    private String opening_time;
    //商户账户余额
    private BigDecimal merchant_account_money;
    //冻结资金
    private BigDecimal freeze_funds;
    //在途金额
    private BigDecimal on_the_amount;
    //当日入金
    private BigDecimal day_into_golden;
    //当日出金
    private BigDecimal day_out_golden;
    //交易金额
    private BigDecimal transaction_amount;
    //手续费
    private BigDecimal poundage;
    //笔数
    private Integer number;
    //渠道类型
    private String qdchannel_type;
    //产品类型
    private String product_type;
    //商户执照名称
    private String merchant_license_name;
    //商户执照号
    private String merchant_license_id;
    //税务登记号
    private String tax_registration_id;
    //组织机构证
    private String organization_certificate;
    //工商注册地址
    private String registered_business_address;
    //商户注册资金（万元）
    private BigDecimal merchant_registered_money;
    //公司性质
    private String company_nature;
    //执照有效期
    private String licence_validity;
    //MCC类型
    private String mcc_type;
    //省份
    private String province;
    //城市
    private String city;
    //办公地址
    private String office_address;
    //法人名称
    private String legalperson_name;
    //证件类型
    private String document_type;
    //证件号码
    private String certificate_id;
    //证件有效期
    private String certificate_validity;
    //联系人名称
    private String contact_name;
    //联系人电话
    private String contact_phone;
    //联系人Email
    private String contact_email;
    //开户行
    private String deposit_bank;
    //账户类型
    private String account_type;
    //开户账号
    private String deposit_account;
    //开户名
    private String deposit_name;
    //开户身份证号
    private String deposit_id_number;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getInformation_update_time() {
        return information_update_time;
    }

    public void setInformation_update_time(String information_update_time) {
        this.information_update_time = information_update_time;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public String getInformation_audit_status() {
        return information_audit_status;
    }

    public void setInformation_audit_status(String information_audit_status) {
        this.information_audit_status = information_audit_status;
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }

    public String getOpening_status() {
        return opening_status;
    }

    public void setOpening_status(String opening_status) {
        this.opening_status = opening_status;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public BigDecimal getMerchant_account_money() {
        return merchant_account_money;
    }

    public void setMerchant_account_money(BigDecimal merchant_account_money) {
        this.merchant_account_money = merchant_account_money;
    }

    public BigDecimal getFreeze_funds() {
        return freeze_funds;
    }

    public void setFreeze_funds(BigDecimal freeze_funds) {
        this.freeze_funds = freeze_funds;
    }

    public BigDecimal getOn_the_amount() {
        return on_the_amount;
    }

    public void setOn_the_amount(BigDecimal on_the_amount) {
        this.on_the_amount = on_the_amount;
    }

    public BigDecimal getDay_into_golden() {
        return day_into_golden;
    }

    public void setDay_into_golden(BigDecimal day_into_golden) {
        this.day_into_golden = day_into_golden;
    }

    public BigDecimal getDay_out_golden() {
        return day_out_golden;
    }

    public void setDay_out_golden(BigDecimal day_out_golden) {
        this.day_out_golden = day_out_golden;
    }

    public BigDecimal getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(BigDecimal transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getQdchannel_type() {
        return qdchannel_type;
    }

    public void setQdchannel_type(String qdchannel_type) {
        this.qdchannel_type = qdchannel_type;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getMerchant_license_name() {
        return merchant_license_name;
    }

    public void setMerchant_license_name(String merchant_license_name) {
        this.merchant_license_name = merchant_license_name;
    }

    public String getMerchant_license_id() {
        return merchant_license_id;
    }

    public void setMerchant_license_id(String merchant_license_id) {
        this.merchant_license_id = merchant_license_id;
    }

    public String getTax_registration_id() {
        return tax_registration_id;
    }

    public void setTax_registration_id(String tax_registration_id) {
        this.tax_registration_id = tax_registration_id;
    }

    public String getOrganization_certificate() {
        return organization_certificate;
    }

    public void setOrganization_certificate(String organization_certificate) {
        this.organization_certificate = organization_certificate;
    }

    public String getRegistered_business_address() {
        return registered_business_address;
    }

    public void setRegistered_business_address(String registered_business_address) {
        this.registered_business_address = registered_business_address;
    }

    public BigDecimal getMerchant_registered_money() {
        return merchant_registered_money;
    }

    public void setMerchant_registered_money(BigDecimal merchant_registered_money) {
        this.merchant_registered_money = merchant_registered_money;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }

    public String getLicence_validity() {
        return licence_validity;
    }

    public void setLicence_validity(String licence_validity) {
        this.licence_validity = licence_validity;
    }

    public String getMcc_type() {
        return mcc_type;
    }

    public void setMcc_type(String mcc_type) {
        this.mcc_type = mcc_type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getLegalperson_name() {
        return legalperson_name;
    }

    public void setLegalperson_name(String legalperson_name) {
        this.legalperson_name = legalperson_name;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(String certificate_id) {
        this.certificate_id = certificate_id;
    }

    public String getCertificate_validity() {
        return certificate_validity;
    }

    public void setCertificate_validity(String certificate_validity) {
        this.certificate_validity = certificate_validity;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getDeposit_bank() {
        return deposit_bank;
    }

    public void setDeposit_bank(String deposit_bank) {
        this.deposit_bank = deposit_bank;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getDeposit_account() {
        return deposit_account;
    }

    public void setDeposit_account(String deposit_account) {
        this.deposit_account = deposit_account;
    }

    public String getDeposit_name() {
        return deposit_name;
    }

    public void setDeposit_name(String deposit_name) {
        this.deposit_name = deposit_name;
    }

    public String getDeposit_id_number() {
        return deposit_id_number;
    }

    public void setDeposit_id_number(String deposit_id_number) {
        this.deposit_id_number = deposit_id_number;
    }
}
