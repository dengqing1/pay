package com.myd.entity;

public class NpayProducts {
    private String productId;

    private String productAbbr;

    private String name;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductAbbr() {
        return productAbbr;
    }

    public void setProductAbbr(String productAbbr) {
        this.productAbbr = productAbbr == null ? null : productAbbr.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}