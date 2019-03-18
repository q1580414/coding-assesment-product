package com.example.product.api;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<ProductsItem> products = new ArrayList<>();

    public void setProducts(List<ProductsItem> products) {
        this.products = products;
    }

    public List<ProductsItem> getProducts() {
        return products;
    }

}