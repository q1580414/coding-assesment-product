package com.example.product.api;

import java.util.ArrayList;
import java.util.List;

public class ProductsItem {

    private String productId;
    private String title;
    private Price price;
    private List<ColorSwatchesItem> colorSwatches = new ArrayList<>();

    public ProductsItem() {
    }

    public ProductsItem(String productId, String title, Price price) {
        this.productId = productId;
        this.title = title;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<ColorSwatchesItem> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorSwatchesItem> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }
}