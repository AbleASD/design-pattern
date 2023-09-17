package com.able.strategy.chargeport;

import com.able.strategy.annotations.ChargeTypeAnnotation;
import com.able.strategy.enums.ChargeType;

@ChargeTypeAnnotation(taxType = ChargeType.WechatPay)
public class WechatPay implements ChargeStrategy {

    @Override
    public double charge(long cost) {
        System.out.println("WeChat Pay: " + cost);
        return cost;
    }
    
}
