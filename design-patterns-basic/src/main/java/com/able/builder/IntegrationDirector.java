package com.able.builder;

public class IntegrationDirector {
    public IntegrationData builIntegrationData(IntegrationBuilder builder) {
        System.out.println("开始集成数据到业务系统....");

        builder.buildMember();
        builder.buildOrder();
        builder.buildSelectedPlans();
        builder.buildUserProfile();

        IntegrationData data = builder.buildIntegrationData();
        return data;
    }
}
