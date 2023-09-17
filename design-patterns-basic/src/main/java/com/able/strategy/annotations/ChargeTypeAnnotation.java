package com.able.strategy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.able.strategy.enums.ChargeType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ChargeTypeAnnotation {
    ChargeType taxType();
}
