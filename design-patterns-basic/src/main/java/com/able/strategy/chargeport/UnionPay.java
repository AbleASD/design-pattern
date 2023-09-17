package com.able.strategy.chargeport;

import com.able.strategy.annotations.ChargeTypeAnnotation;
import com.able.strategy.enums.ChargeType;

@ChargeTypeAnnotation(taxType = ChargeType.UnionPay)
public class UnionPay implements ChargeStrategy {

    @Override
    public double charge(long cost) {
        System.out.println("Union Pay: " + cost);
        return cost;
    }
    
}
