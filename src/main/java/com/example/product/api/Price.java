package com.example.product.api;

public class Price {

    private String uom;
    private Double then2;
    private Double then1;
    private Now now;
    private Double was;
    private String currency;

    public Price() {
    }

    public Price(Double was, Double then1, Double then2, Now now) {
        this.was = was;
        this.then1 = then1;
        this.then2 = then2;
        this.now = now;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getThen2() {
        return then2;
    }

    public void setThen2(Double then2) {
        this.then2 = then2;
    }

    public Double getThen1() {
        return then1;
    }

    public Double getThen() {
        return then2 != null ? then2 : then1;
    }

    public void setThen1(Double then1) {
        this.then1 = then1;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public Double getWas() {
        return was;
    }

    public void setWas(Double was) {
        this.was = was;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
