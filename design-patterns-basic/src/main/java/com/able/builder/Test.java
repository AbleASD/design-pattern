package com.able.builder;

public class Test {
    public static void main(String[] args) {
        IntegrationBuilder builder = new XHFIntegrationBuilder();

        IntegrationDirector director = new IntegrationDirector();

        IntegrationData integrationData = director.builIntegrationData(builder);

        System.out.println(integrationData);
    }
}
