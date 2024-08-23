package com.overalt.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Plan {

    @Id
    @Column(name = "plan_id")
    private int planId;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @JsonManagedReference
    @OneToMany(mappedBy = "plan")
    private List<Customer> customers;

    @Column(name = "monthly_cost", nullable = false)
    private BigDecimal monthlyCost;

    @Column(name = "data_limit", nullable = false)
    private int dataLimit;

    @Column(name = "call_minutes", nullable = false)
    private int callMinutes;

    @Column(name = "max_family_members", nullable = false)
    private int maxFamilyMembers;

    @Column(name = "max_friends", nullable = false)
    private int maxFriends;

    // Default Constructor
    public Plan() {}

    // Parameterized Constructor
    public Plan(int planId, String planName, int callMinutes, int dataLimit, BigDecimal monthlyCost, int maxFamilyMembers, int maxFriends) {
        this.planId = planId;
        this.planName = planName;
        this.callMinutes = callMinutes;
        this.dataLimit = dataLimit;
        this.monthlyCost = monthlyCost;
        this.maxFamilyMembers = maxFamilyMembers;
        this.maxFriends = maxFriends;
    }

    // Getters and Setters
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(BigDecimal monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public int getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(int dataLimit) {
        this.dataLimit = dataLimit;
    }

    public int getCallMinutes() {
        return callMinutes;
    }

    public void setCallMinutes(int callMinutes) {
        this.callMinutes = callMinutes;
    }

    public int getMaxFamilyMembers() {
        return maxFamilyMembers;
    }

    public void setMaxFamilyMembers(int maxFamilyMembers) {
        this.maxFamilyMembers = maxFamilyMembers;
    }

    public int getMaxFriends() {
        return maxFriends;
    }

    public void setMaxFriends(int maxFriends) {
        this.maxFriends = maxFriends;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    // Method to display plan details
    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                ", monthlyCost=" + monthlyCost +
                ", dataLimit=" + dataLimit +
                ", callMinutes=" + callMinutes +
                ", maxFamilyMembers=" + maxFamilyMembers +
                ", maxFriends=" + maxFriends +
                '}';
    }
}
