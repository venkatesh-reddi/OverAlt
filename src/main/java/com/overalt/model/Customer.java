package com.overalt.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming customer_id is auto-generated
    private int customerId;

    @Column(nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String lastName;

    @Column(unique = true, nullable = false, length = 15)
    @NotNull
    @Size(max = 15)
    private String phoneNumber;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @Column(nullable = false, length = 255)
    @NotNull
    private String address;

    @Column(nullable = false)
    private int planId;

    @Column(nullable = false)
    private int currentFamilyCount;

    @Column(nullable = false)
    private int currentFriendsCount;

    // Default Constructor
    public Customer() {}

    // Constructor without customerId
    public Customer(String firstName, String lastName, String phoneNumber, String email, String address, int planId, int currentFamilyCount, int currentFriendsCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.planId = planId;
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

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", planId=" + planId +
                ", currentFamilyCount=" + currentFamilyCount +
                ", currentFriendsCount=" + currentFriendsCount +
                '}';
    }
}
