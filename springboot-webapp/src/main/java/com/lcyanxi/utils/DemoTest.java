package com.lcyanxi.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lichang
 * @date 2021/3/26
 */
public class DemoTest {
    public static void main(String[] args) {
        String str = "125630,125660,125660,125660,125678,125811";
        List<Integer> tempIds = Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        Set<Integer> repeatProductIds = tempIds.stream().filter(i -> Collections.frequency(tempIds, i) > 1)
                .collect(Collectors.toSet());
        System.out.println(repeatProductIds);
    }
}
