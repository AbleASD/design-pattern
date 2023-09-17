package com.able.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.able.strategy.annotations.ChargeTypeAnnotation;
import com.able.strategy.chargeport.ChargeStrategy;
import com.able.strategy.enums.ChargeType;

public class AnnotationChargeStrategyFactory {
    static Map<ChargeType, ChargeStrategy> chargeStrategyMap = new HashMap<>();

    public static ChargeStrategy getChargeStrategy(ChargeType type) throws Exception {
        if (chargeStrategyMap.get(type) != null) {
            return chargeStrategyMap.get(type);
        } else {
            throw new Exception(type + "not found!");
        }
    }

    static {
        registerChargeStrategy();
    }

    private static void registerChargeStrategy() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(ChargeStrategy.class.getPackage().getName()))
                .setScanners(Scanners.SubTypes));

        Set<Class<? extends ChargeStrategy>> strategyClassSet = reflections.getSubTypesOf(ChargeStrategy.class);

        if (strategyClassSet != null) {
            for (Class<?> clazz : strategyClassSet) {
                if (clazz.isAnnotationPresent(ChargeTypeAnnotation.class)) {
                    ChargeTypeAnnotation typeAnnotation = clazz.getAnnotation(ChargeTypeAnnotation.class);
                    ChargeType chargeType = typeAnnotation.taxType();

                    try {
                        chargeStrategyMap.put(chargeType,
                                (ChargeStrategy) clazz.getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            }
        }
    }
}
