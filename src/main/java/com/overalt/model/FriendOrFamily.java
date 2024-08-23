package com.overalt.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class FriendOrFamily {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Id
    @Column(unique = true)
    private long contactNumber;

    @Column(nullable = false, length = 50)
    private String contactName;

    @Column(nullable = false, length = 20)
    private String relationshipType;

    // Default Constructor
    public FriendOrFamily() {}

    // Parameterized Constructor
    public FriendOrFamily(Customer customer, long contactNumber, String contactName, String relationshipType) {
        this.customer = customer;
        this.contactNumber = contactNumber;
        this.contactName = contactName;
        this.relationshipType = relationshipType;
    }

    // Getters and Setters
    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public long getCustomer() {
        return contactNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "FriendOrFamily{" +
                "customerId=" + customer +
                "contactNumber=" + contactNumber +
                ", contactName='" + contactName + '\'' +
                ", relationshipType='" + relationshipType + '\'' +
                '}';
    }
}
