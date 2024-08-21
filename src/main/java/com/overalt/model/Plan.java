package com.overalt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Plans")
public class Plan {
    @Id
    private int plan_id;
    @Column(nullable = false)
    private String plan_name;
    @Column(nullable = false)
    private double  monthly_cost;
    @Column(nullable = false)
    private int data_limit;
    @Column(nullable = false)
    private int call_minutes;
    @Column(nullable = false)
    private int max_family_members;
    @Column(nullable = false)
    private int max_friends;

    public Plans(){

    }
       
    public Plans(int plan_id, String plan_name, int call_minutes, int data_limit, double monthly_cost, int max_family_members, int max_friends) {
        this.plan_id = plan_id;
        this.plan_name = plan_name;
        this.call_minutes = call_minutes;
        this.data_limit = data_limit;
        this.monthly_cost = monthly_cost ;
        this.max_family_members = max_family_members;
        this.max_friends = max_friends;
    }
    
    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public int getCall_minutes() {
        return call_minutes;
    }

    public void setCall_minutes(int call_minutes) {
        this.call_minutes = call_minutes;
    }

    public int getData_limit() {
        return data_limit;
    }

    public void setData_limit(int data_limit) {
        this.data_limit = data_limit;
    }

    public double getMonthly_cost() {
        return monthly_cost;
    }

    public void setMonthly_cost(double monthly_cost) {
        this.monthly_cost = monthly_cost;
    }

    public int getMax_family_members() {
        return max_family_members;
    }

    public void setMax_family_members(int max_family_members) {
        this.max_family_members = max_family_members;
    }

    public int getMax_friends() {
        return max_friends;
    }

    public void setMax_friends(int max_friends) {
        this.max_friends = max_friends;
    }

    // Method to display plan details
    @Override
    public String toString() {
        return "Plans{" +
        "Plan Id =" + plan_id +
        ", Plan Name ='" + plan_name + '\'' +
        ", Call Minutes ='" + call_minutes + '\'' +
        ", Data Limit ='" + data_limit + '\'' +
        ", Monthly Cost ='" + monthly_cost + '\'' +
        ", Max Family Members ='" + max_family_members + '\'' +
        ", Max Friends =" + max_friends +
        '}';
    }
}


