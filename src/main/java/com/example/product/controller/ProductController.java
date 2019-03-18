package com.example.product.controller;

import com.example.product.dto.ProductDtoWrapper;
import com.example.product.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author birolg, 3/17/2019.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private PriceService priceService;

    /**
     * lists products which has reduction. it is sorted to show highest reduction first
     *
     * @param labelType type to define label.
     * @return ProductDtoWrapper which wraps prodeuct list
     */
    @GetMapping("/reduction")
    public ProductDtoWrapper listProducts(@RequestParam(name = "labelType", defaultValue = "ShowWasNow") String labelType) {
        return new ProductDtoWrapper(priceService.listReductions(labelType));
    }
}
