package com.example.product.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * for basic color operations
 *
 * @author birolg, 3/17/2019.
 */
@Component
public class ColorUtil {

    @Autowired
    @Qualifier("colorMap")
    private Map<String, String> colorMap;

    /**
     * helps to fetch hexa definition of a main color such as blues, black, white...
     *
     * @param name name of basic color
     * @return hex definition of color
     */
    public String getHexCodeByName(String name) {
        return colorMap.get(name.toUpperCase());
    }
}
