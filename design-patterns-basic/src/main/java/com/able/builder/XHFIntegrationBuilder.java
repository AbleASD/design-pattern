package com.able.builder;

import com.able.builder.domain.Member;
import com.able.builder.domain.Order;
import com.able.builder.domain.SelectedPlans;
import com.able.builder.domain.UserProfile;

public class XHFIntegrationBuilder implements IntegrationBuilder {
    private IntegrationData integrationData;

    public XHFIntegrationBuilder() {
        this.integrationData = new IntegrationData();
    }

    @Override
    public UserProfile buildUserProfile() {
        System.out.println("构建用户信息");
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId("userId");
        userProfile.setUserName("userName");
        integrationData.setUserProfile(userProfile);
        return userProfile;
    }

    @Override
    public SelectedPlans buildSelectedPlans() {
        System.out.println("构建计划信息");
        SelectedPlans selectedPlans = new SelectedPlans();
        selectedPlans.setPlanId("planId");
        selectedPlans.setPlanName("planName");
        integrationData.setSelectedPlans(selectedPlans);
        return selectedPlans;
    }

    @Override
    public Order buildOrder() {
        System.out.println("构建订单信息");
        Order order = new Order();
        order.setOrderId("orderId");
        order.setOrderName("orderName");
        integrationData.setOrder(order);
        return order;
    }

    @Override
    public Member buildMember() {
        System.out.println("构建订单成员信息");
        Member member = new Member();
        member.setMemberId("memberId");
        member.setMemberName("memberName");
        integrationData.setMember(member);
        return member;
    }

    @Override
    public IntegrationData buildIntegrationData() {
        return integrationData;
    }
    
}
