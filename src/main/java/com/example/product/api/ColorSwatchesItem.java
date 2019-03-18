package com.example.product.api;

public class ColorSwatchesItem {

    private String basicColor;
    private boolean isAvailable;
    private String colorSwatchUrl;
    private String color;
    private String imageUrl;
    private String skuId;

    public ColorSwatchesItem() {
    }

    public ColorSwatchesItem(String basicColor) {
        this.basicColor = basicColor;
    }

    public void setBasicColor(String basicColor) {
        this.basicColor = basicColor;
    }

    public String getBasicColor() {
        return basicColor;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setColorSwatchUrl(String colorSwatchUrl) {
        this.colorSwatchUrl = colorSwatchUrl;
    }

    public String getColorSwatchUrl() {
        return colorSwatchUrl;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuId() {
        return skuId;
    }
}
