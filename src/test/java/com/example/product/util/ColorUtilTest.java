package com.example.product.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author birolg, 3/18/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ColorUtilTest {

    @MockBean
    private Map<String, String> colorMap;

    @Autowired
    private ColorUtil colorUtil;

    @Test
    public void getHexCodeByName() {
        String hexOfWhite = "#FFFFFF";
        Mockito.when(colorMap.get("WHITE")).thenReturn(hexOfWhite);
        String whiteHex = colorUtil.getHexCodeByName("white");
        Assert.assertEquals(whiteHex, hexOfWhite);
    }
}