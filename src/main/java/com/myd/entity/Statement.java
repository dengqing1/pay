package com.myd.entity;

public class Statement {
    private Integer staId;

    private String staMerchantId;
    
    private String golden;

    private String withdraw;

    private String unpaid;

    private String nothing;

    private String date;

    public Integer getStaId() {
        return staId;
    }

    public void setStaId(Integer staId) {
        this.staId = staId;
    }
    
    public String getStaMerchantId() {
		return staMerchantId;
	}

	public void setStaMerchantId(String staMerchantId) {
		this.staMerchantId = staMerchantId;
	}

	public String getGolden() {
        return golden;
    }

    public void setGolden(String golden) {
        this.golden = golden == null ? null : golden.trim();
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw == null ? null : withdraw.trim();
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid == null ? null : unpaid.trim();
    }

    public String getNothing() {
        return nothing;
    }

    public void setNothing(String nothing) {
        this.nothing = nothing == null ? null : nothing.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

	@Override
	public String toString() {
		return "Statement [staId=" + staId + ", golden=" + golden + ", withdraw=" + withdraw + ", unpaid=" + unpaid
				+ ", nothing=" + nothing + ", date=" + date + "]\n";
	}
    
    
    
    
    
}