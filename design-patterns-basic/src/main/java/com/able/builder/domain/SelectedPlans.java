package com.able.builder.domain;

public class SelectedPlans {
    private String planId;
    private String planName;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Override
    public String toString() {
        return "计划信息: " + planId + "---" + planName;
    }

    
}
