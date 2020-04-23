package com.myd.entity;

public class NpayCardBin {
    private String bankName;

    private String cardName;

    private Byte atm;

    private Byte pos;

    private String accountNo;

    private Integer length;

    private String accountValue;

    private String currency;

    private String increRate;

    private String bankCode;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public Byte getAtm() {
        return atm;
    }

    public void setAtm(Byte atm) {
        this.atm = atm;
    }

    public Byte getPos() {
        return pos;
    }

    public void setPos(Byte pos) {
        this.pos = pos;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(String accountValue) {
        this.accountValue = accountValue == null ? null : accountValue.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getIncreRate() {
        return increRate;
    }

    public void setIncreRate(String increRate) {
        this.increRate = increRate == null ? null : increRate.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }
}