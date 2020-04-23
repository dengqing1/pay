package com.myd.entity;

public class NpayAinongBank {
    private String bankid;

    private String bankname;

    private String banklogo;

    private Integer status;

    private Boolean creditsupport;

    private Integer creditstatus;

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBanklogo() {
        return banklogo;
    }

    public void setBanklogo(String banklogo) {
        this.banklogo = banklogo == null ? null : banklogo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getCreditsupport() {
        return creditsupport;
    }

    public void setCreditsupport(Boolean creditsupport) {
        this.creditsupport = creditsupport;
    }

    public Integer getCreditstatus() {
        return creditstatus;
    }

    public void setCreditstatus(Integer creditstatus) {
        this.creditstatus = creditstatus;
    }
}