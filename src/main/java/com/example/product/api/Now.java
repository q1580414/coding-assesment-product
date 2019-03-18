package com.example.product.api;

import com.example.product.api.deserializer.NowDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = NowDeserializer.class)
public class Now {

    private Double from;
    private Double to;
    private Double now;

    public Now() {
    }

    public Now(Double now) {
        this.now = now;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getFrom() {
        return from;
    }

    public void setTo(Double to) {
        this.to = to;
    }

    public Double getTo() {
        return to;
    }

    public Double getNow() {
        return now;
    }

    public Double getNowOrTo() {
        return now != null ? now : to;
    }

    public void setNow(Double now) {
        this.now = now;
    }
}
