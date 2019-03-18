package com.example.product.config;

import com.example.product.util.PriceLabelFunction;
import com.example.product.util.PriceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Config class for ProductApplication
 *
 * @author birolg, 3/17/2019.
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean("highPriceTL")
    public ThreadLocal<NumberFormat> getHighPriceTL() {
        return ThreadLocal.withInitial(() -> {
            final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
            format.setMaximumFractionDigits(0);
            format.setRoundingMode(RoundingMode.DOWN);
            return format;
        });
    }

    @Bean("lowPriceTL")
    public ThreadLocal<NumberFormat> getLowPriceTL() {
        return ThreadLocal.withInitial(() -> {
            final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
            format.setMaximumFractionDigits(3);
            return format;
        });
    }

    @Bean("colorMap")
    public Map<String, String> getColorMap() {

        Map<String, String> colorMap = new HashMap<>();

        colorMap.put("BLACK", "000000");
        colorMap.put("WHITE", "FFFFFF");
        colorMap.put("RED", "FF0000");
        colorMap.put("BLUE", "0000FF");
        colorMap.put("YELLOW", "FFFF00");
        colorMap.put("GRAY", "808080");
        colorMap.put("GREY", "808080");
        colorMap.put("GREEN", "008000");
        colorMap.put("PURPLE", "800080");
        colorMap.put("PINK", "FFC0CB");
        colorMap.put("ORANGE", "FFA500");

        return colorMap;
    }

    @Bean
    public Map<String, PriceLabelFunction> getPriceFunctionsByLabelType(PriceUtil priceUtil) {
        Map<String, PriceLabelFunction> functionMap = new HashMap<>();

        functionMap.put("ShowWasNow", t -> String.format("Was %s, now %s",
                priceUtil.formatPrice(t.getWas()),
                priceUtil.formatPrice(t.getNow().getNowOrTo())));

        functionMap.put("ShowWasThenNow", t -> {
            String formatted;
            if (t.getThen() != null) {
                formatted = String.format("Was %s, then %s, now %s",
                        priceUtil.formatPrice(t.getWas()),
                        priceUtil.formatPrice(t.getThen()),
                        priceUtil.formatPrice(t.getNow().getNowOrTo()));
            } else {
                formatted = String.format("Was %s, now %s",
                        priceUtil.formatPrice(t.getWas()),
                        priceUtil.formatPrice(t.getNow().getNowOrTo()));
            }
            return formatted;
        });

        functionMap.put("ShowPercDscount", t -> String.format("%.1f%% off - now %s",
                priceUtil.calculateDiscount(t),
                priceUtil.formatPrice(t.getNow().getNowOrTo())));

        return functionMap;
    }
}
