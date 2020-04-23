package com.myd.entity;

public class NpayIquxunBank {
    private String zBankId;

    private String bankId;

    private String bankName;

    public String getzBankId() {
        return zBankId;
    }

    public void setzBankId(String zBankId) {
        this.zBankId = zBankId == null ? null : zBankId.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }
}