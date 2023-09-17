package com.able.builder;

import com.able.builder.domain.Member;
import com.able.builder.domain.Order;
import com.able.builder.domain.SelectedPlans;
import com.able.builder.domain.UserProfile;

public interface IntegrationBuilder {
    
    UserProfile buildUserProfile();

    SelectedPlans buildSelectedPlans();

    Order buildOrder();
    
    Member buildMember();

    IntegrationData buildIntegrationData();
}
