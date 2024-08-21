package com.overalt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
	@Id
    private int customer_id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(unique = true)
    private String phone_number;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int plan_id;
    @Column(nullable = false)
    private int current_family_count;
    @Column(nullable = false)
    private int current_friends_count;

    public Customer() {
    }

    public Customer(int customer_id, String first_name, String last_name, String phone_number, String email, String address, int plan_id, int current_family_count, int current_friends_count) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.plan_id = plan_id;
        this.current_family_count = current_family_count;
        this.current_friends_count = current_friends_count;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getCurrent_family_count() {
        return current_family_count;
    }

    public void setCurrent_family_count(int current_family_count) {
        this.current_family_count = current_family_count;
    }

    public int getCurrent_friends_count() {
        return current_friends_count;
    }

    public void setCurrent_friends_count(int current_friends_count) {
        this.current_friends_count = current_friends_count;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", plan_id=" + plan_id +
                ", current_family_count=" + current_family_count +
                ", current_friends_count=" + current_friends_count +
                '}';
    }
}
