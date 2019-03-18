package com.example.product.util;

import com.example.product.api.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;

/**
 * helper class for price operations
 *
 * @author birolg, 3/17/2019.
 */
@Component
public class PriceUtil {

    private static final double PRICE_THRESHOLD = 10.0;

    @Autowired
    @Qualifier("highPriceTL")
    private ThreadLocal<NumberFormat> highPriceTL;

    @Autowired
    @Qualifier("lowPriceTL")
    private ThreadLocal<NumberFormat> lowPriceTL;

    /**
     * formats currency. if it is less then 10, it returns decimal representation otherwise shows integer
     *
     * @param price price info
     * @return formatted price representation like £9.8 or £15
     */
    public String formatPrice(Double price) {
        if (price == null) {
            return "";
        }
        NumberFormat format = price < PRICE_THRESHOLD ? lowPriceTL.get() : highPriceTL.get();
        return format.format(price);
    }

    /**
     * calculates the reduction of price object
     *
     * @param price price info
     * @return returns was - now
     */
    public Double calculateReduction(Price price) {
        return price.getWas() != null ? price.getWas() - price.getNow().getNowOrTo() : 0;
    }

    /**
     * calculates discount of price object
     *
     * @param price price info
     * @return returns (was-now)/was*100
     */
    public Double calculateDiscount(Price price) {
        return (price != null && price.getWas() != null) ? (price.getWas() - price.getNow().getNowOrTo()) / price.getWas() * 100 : 0;
    }
}
