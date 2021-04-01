package com.lcyanxi.utils;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author lichang
 * @date 2021/3/30
 */
@Component
public class MyUtils {
    @PostConstruct
    public void init(){
        System.out.println("初始化init方法...");
    }

}
