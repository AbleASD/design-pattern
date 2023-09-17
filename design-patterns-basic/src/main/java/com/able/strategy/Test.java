package com.able.strategy;

import com.able.strategy.chargeport.ChargeStrategy;
import com.able.strategy.enums.ChargeType;

public class Test {
    public static void main(String[] args) {
        try {
            ChargeStrategy strategy = AnnotationChargeStrategyFactory.getChargeStrategy(ChargeType.AliPay);
            System.out.println(strategy.charge(100));

            strategy = AnnotationChargeStrategyFactory.getChargeStrategy(ChargeType.WechatPay);
            System.out.println(strategy.charge(50));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
