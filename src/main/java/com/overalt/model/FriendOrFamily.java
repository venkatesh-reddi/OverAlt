package com.overalt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class FriendOrFamily {

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
    public FriendOrFamily(long contactNumber, String contactName, String relationshipType) {
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

    @Override
    public String toString() {
        return "FriendOrFamily{" +
                "contactNumber=" + contactNumber +
                ", contactName='" + contactName + '\'' +
                ", relationshipType='" + relationshipType + '\'' +
                '}';
    }
}
