package com.overalt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table
public class Customer {

    @Id
    private int customerId;

    @OneToMany(mappedBy = "customer")
    private List<FriendOrFamily> friendOrFamily;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 255)
    private String address;

    @JsonBackReference
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    Plan plan;

    @Column(nullable = false)
    private int currentFamilyCount;

    @Column(nullable = false)
    private int currentFriendsCount;

    // Default Constructor
    public Customer() {}

    // Constructor without customerId
    public Customer(int customerId, String firstName, String lastName, String phoneNumber, String email, String address, Plan plan, int currentFamilyCount, int currentFriendsCount) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.plan = plan;
        this.currentFamilyCount = currentFamilyCount;
        this.currentFriendsCount = currentFriendsCount;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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


    public int getCurrentFamilyCount() {
        return currentFamilyCount;
    }

    public void setCurrentFamilyCount(int currentFamilyCount) {
        this.currentFamilyCount = currentFamilyCount;
    }

    public int getCurrentFriendsCount() {
        return currentFriendsCount;
    }

    public void setCurrentFriendsCount(int currentFriendsCount) {
        this.currentFriendsCount = currentFriendsCount;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<FriendOrFamily> getFriendOrFamily() {
        return friendOrFamily;
    }

    public void setCustomers(List<FriendOrFamily> friendOrFamily) {
        this.friendOrFamily = friendOrFamily;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", planId=" + plan +
                ", currentFamilyCount=" + currentFamilyCount +
                ", currentFriendsCount=" + currentFriendsCount +
                '}';
    }
}
