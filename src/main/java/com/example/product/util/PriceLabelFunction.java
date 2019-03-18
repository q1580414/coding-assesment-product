package com.example.product.util;

import com.example.product.api.Price;

import java.util.function.Function;

/**
 * @author birolg, 3/17/2019.
 */
@FunctionalInterface
public interface PriceLabelFunction extends Function<Price, String> {
}
