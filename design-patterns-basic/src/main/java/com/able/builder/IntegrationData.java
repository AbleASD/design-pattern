package com.able.builder;

import com.able.builder.domain.Member;
import com.able.builder.domain.Order;
import com.able.builder.domain.SelectedPlans;
import com.able.builder.domain.UserProfile;

public class IntegrationData {
    private UserProfile userProfile;
    private SelectedPlans selectedPlans;
    private Order order;
    private Member member;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public SelectedPlans getSelectedPlans() {
        return selectedPlans;
    }

    public void setSelectedPlans(SelectedPlans selectedPlans) {
        this.selectedPlans = selectedPlans;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return userProfile.toString() + "\n" + selectedPlans.toString() + "\n"
                + order.toString() + "\n" + member.toString();
    }

}
