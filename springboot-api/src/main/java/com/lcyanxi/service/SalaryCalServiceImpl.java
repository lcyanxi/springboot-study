package com.lcyanxi.service;

/**
 * @author lichang
 * @date 2021/1/25
 */
public class SalaryCalServiceImpl implements ISalaryCalService {

    @Override
    public Double cal(Double money) {
        System.out.println("discount Service");
        return money * 0.8;
    }
}
