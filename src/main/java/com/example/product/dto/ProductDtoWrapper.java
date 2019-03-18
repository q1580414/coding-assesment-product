package com.example.product.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author birolg, 3/17/2019.
 *
 *
 */
public class ProductDtoWrapper {

    private List<ProductDto> products = new ArrayList<>();

    public ProductDtoWrapper() {
    }

    public ProductDtoWrapper(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
