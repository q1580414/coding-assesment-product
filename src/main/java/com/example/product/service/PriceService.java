package com.example.product.service;

import com.example.product.api.Price;
import com.example.product.dto.ProductDto;

import java.util.List;

/**
 * @author birolg, 3/17/2019.
 */
public interface PriceService {

    /**
     * lists products which has reduction. it is sorted to show highest reduction first
     *
     * @param labelType type to define label.
     * @return list of products
     */
    List<ProductDto> listReductions(String labelType);

    /**
     * formats price regarding to predefined labelType
     *
     * @param price     contains all price information
     * @param labelType predefined label type
     * @return formatted price label
     */
    String generatePriceLabel(Price price, String labelType);
}
