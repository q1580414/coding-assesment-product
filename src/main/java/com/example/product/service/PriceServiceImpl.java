package com.example.product.service;

import com.example.product.api.Now;
import com.example.product.api.Price;
import com.example.product.api.ProductsItem;
import com.example.product.api.Response;
import com.example.product.dto.ColorSwatchDto;
import com.example.product.dto.ProductDto;
import com.example.product.util.ColorUtil;
import com.example.product.util.PriceLabelFunction;
import com.example.product.util.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author birolg, 3/17/2019.
 */
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private Map<String, PriceLabelFunction> priceLabelFunctions;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PriceUtil priceUtil;

    @Autowired
    private ColorUtil colorUtil;

    @Value("${api.product.url}")
    private String apiUrl;

    @Override
    public String generatePriceLabel(Price price, String labelType) {
        PriceLabelFunction function = priceLabelFunctions.get(labelType);
        return function != null ? function.apply(price) : "";
    }

    @Override
    @Validated
    public List<ProductDto> listReductions(@NotNull String labelType) {
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(apiUrl, Response.class);
        Response response = responseEntity.getBody();

        List<ProductsItem> products = response.getProducts();

        List<ProductDto> result = products.stream()
                .filter(t -> priceUtil.calculateReduction(t.getPrice()) > 0)
                .sorted((o1, o2) -> {
                    Double r1 = priceUtil.calculateReduction(o1.getPrice());
                    Double r2 = priceUtil.calculateReduction(o2.getPrice());
                    return r2.compareTo(r1);
                })
                .map(t -> {
                    ProductDto dto = new ProductDto();
                    dto.setProductId(t.getProductId());
                    dto.setTitle(t.getTitle());

                    dto.getColorSwatches().addAll(
                            t.getColorSwatches().stream().map(c -> {
                                ColorSwatchDto colorSwatchDto = new ColorSwatchDto();
                                colorSwatchDto.setColor(c.getColor());
                                colorSwatchDto.setSkuid(c.getSkuId());
                                String hexCodeByName = colorUtil.getHexCodeByName(c.getBasicColor());
                                colorSwatchDto.setRgbColor(hexCodeByName != null ? hexCodeByName : "");
                                return colorSwatchDto;
                            }).collect(Collectors.toList())
                    );

                    Double nowPrice = null;
                    Price price = t.getPrice();
                    if (price != null) {
                        Now now = price.getNow();
                        if (now != null) {
                            nowPrice = now.getNowOrTo();
                        }
                    }
                    dto.setNowPrice(priceUtil.formatPrice(nowPrice));

                    dto.setPriceLabel(generatePriceLabel(t.getPrice(), labelType));

                    return dto;
                }).collect(Collectors.toList());

        return result;
    }
}
