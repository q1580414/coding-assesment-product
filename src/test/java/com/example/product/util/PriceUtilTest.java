package com.example.product.util;

import com.example.product.api.Now;
import com.example.product.api.Price;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author birolg, 3/18/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceUtilTest {

    @Autowired
    private PriceUtil priceUtil;

    @Test
    public void formatPrice() {
        String formattedPrice = priceUtil.formatPrice(null);
        Assert.assertEquals(formattedPrice, "");

        formattedPrice = priceUtil.formatPrice(8.5697);
        Assert.assertEquals(formattedPrice, "£8.57");

        formattedPrice = priceUtil.formatPrice(8.5688);
        Assert.assertEquals(formattedPrice, "£8.569");

        formattedPrice = priceUtil.formatPrice(10.0);
        Assert.assertEquals(formattedPrice, "£10");

        formattedPrice = priceUtil.formatPrice(12.9);
        Assert.assertEquals(formattedPrice, "£12");
    }

    @Test
    public void calculateReduction() {
        Price price = new Price();
        Now now = new Now();
        now.setNow(1.2);
        price.setNow(now);

        Double reduction = priceUtil.calculateReduction(price);
        Assert.assertEquals(0.0, reduction, 0.0);

        price.setWas(12.1);
        reduction = priceUtil.calculateReduction(price);
        Assert.assertEquals(10.9, reduction, 0.0);
    }

    @Test
    public void calculateDiscount() {
        Price price = new Price();
        Now now = new Now();
        now.setNow(1.2);
        price.setNow(now);

        Double discount = priceUtil.calculateReduction(price);
        Assert.assertEquals(0.0, discount, 0.0);

        price.setWas(12.1);
        discount = priceUtil.calculateDiscount(price);
        Assert.assertEquals(90.08, discount, 0.1);
    }
}