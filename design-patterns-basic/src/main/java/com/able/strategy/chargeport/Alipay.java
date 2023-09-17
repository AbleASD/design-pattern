package com.able.strategy.chargeport;

import com.able.strategy.annotations.ChargeTypeAnnotation;
import com.able.strategy.enums.ChargeType;

@ChargeTypeAnnotation(taxType = ChargeType.AliPay)
public class Alipay implements ChargeStrategy {

    @Override
    public double charge(long cost) {
        System.out.println("AliPay: " + cost);
        return cost;
    }
    
}
