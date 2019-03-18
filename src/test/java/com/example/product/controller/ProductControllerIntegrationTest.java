package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.dto.ProductDtoWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author birolg, 3/17/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testReductionService() {

        ProductDtoWrapper wrapper = restTemplate.getForObject("/product/reduction/", ProductDtoWrapper.class);

        List<ProductDto> products = wrapper.getProducts();

        Assert.assertFalse(products.isEmpty());
        Assert.assertEquals(products.size(), 6);
        Assert.assertTrue(products.get(0).getPriceLabel().startsWith("Was"));//test price label
    }

    @Test
    public void testReductionPriceLabel() {

        ProductDtoWrapper wrapper = restTemplate.getForObject("/product/reduction/?labelType=ShowWasThenNow", ProductDtoWrapper.class);

        List<ProductDto> products = wrapper.getProducts();

        Assert.assertTrue(products.get(0).getPriceLabel().contains("then"));
    }

}