package com.example.product.service;

import com.example.product.api.*;
import com.example.product.dto.ProductDto;
import com.example.product.util.ColorUtil;
import com.example.product.util.PriceLabelFunction;
import com.example.product.util.PriceUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author birolg, 3/18/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {

    @MockBean
    private Map<String, PriceLabelFunction> priceLabelFunctions;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private PriceUtil priceUtil;

    @MockBean
    private ColorUtil colorUtil;

    @Autowired
    private PriceService priceService;

    @Test
    public void generatePriceLabel() {
        String labelType = "ShowWasNow";
        String priceLabel = "Was £15.50, now £8.265";

        Mockito.when(priceLabelFunctions.get(labelType)).thenReturn(t -> priceLabel);

        String showWasNow = priceService.generatePriceLabel(new Price(), labelType);
        Assert.assertEquals(showWasNow, priceLabel);

        String empty = priceService.generatePriceLabel(new Price(), "test");
        Assert.assertEquals(empty, "");
    }

    @Test
    public void listReductions() {
        Response response = new Response();

        ProductsItem item1 = new ProductsItem("id1", "title1", new Price(3.4, 3.3, 3.2, new Now(3.1)));
        item1.getColorSwatches().add(new ColorSwatchesItem("BLACK"));
        item1.getColorSwatches().add(new ColorSwatchesItem("BLACK"));

        ProductsItem item2 = new ProductsItem("id2", "title2", new Price(40.4, 30.0, 20.1, new Now(15.0)));
        item2.getColorSwatches().add(new ColorSwatchesItem("BLUE"));

        ProductsItem item3 = new ProductsItem("id3", "title3", new Price(null, null, null, new Now(11.5)));
        item3.getColorSwatches().add(new ColorSwatchesItem("BLUE"));

        response.getProducts().add(item1);
        response.getProducts().add(item2);
        response.getProducts().add(item3);

        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any(Class.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        Mockito.when(priceUtil.calculateReduction(item1.getPrice())).thenReturn(0.3);
        Mockito.when(priceUtil.calculateReduction(item2.getPrice())).thenReturn(25.4);

        Mockito.when(priceUtil.formatPrice(3.1)).thenReturn("£3.1");
        Mockito.when(priceUtil.formatPrice(15.0)).thenReturn("£15");

        Mockito.when(colorUtil.getHexCodeByName("BLACK")).thenReturn("#000000");
        Mockito.when(colorUtil.getHexCodeByName("BLUE")).thenReturn("#0000FF");

        String labelType = "ShowWasNow";
        String priceLabel = "Was £15.50, now £8.265";

        Mockito.when(priceLabelFunctions.get(labelType)).thenReturn(t -> priceLabel);

        List<ProductDto> products = priceService.listReductions(labelType);

        Assert.assertFalse(products.isEmpty());
        Assert.assertEquals(2, products.size());//test reduction filter

        ProductDto dto = products.get(0);

        Assert.assertEquals(dto.getProductId(), "id2"); //test sort by reduction

        Assert.assertFalse(dto.getColorSwatches().isEmpty());
        Assert.assertEquals(dto.getColorSwatches().get(0).getRgbColor(), "#0000FF"); //test color
    }
}