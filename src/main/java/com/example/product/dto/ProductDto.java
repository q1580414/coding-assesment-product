package com.example.product.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author birolg, 3/17/2019.
 */
public class ProductDto implements Serializable {

    private String productId;
    private String title;
    private String nowPrice;
    private String priceLabel;
    private List<ColorSwatchDto> colorSwatches = new ArrayList<>();

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

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    public List<ColorSwatchDto> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorSwatchDto> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }
}
