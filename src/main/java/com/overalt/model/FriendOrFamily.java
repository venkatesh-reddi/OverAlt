package com.overalt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FriendOrFamily")
public class FriendOrFamily{
    @Id
    private long contactNumber;
    @Column(nullable = false)
    private String contactName;
    @Column(nullable = false)
    private String relationshipType;

    public FriendOrFamily(){

    }

    public FriendOrFamily( long contactNumber, String contactName, String relationshipType){
        this.contactNumber = contactNumber;
        this.contactName = contactName;
        this.relationshipType = relationshipType;
    }

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

    public String getRelationType() {
        return relationshipType;
    }

    public void setRelationType(String relationshipType) {
        this.relationshipType = relationshipType;
    }
    @Override
    public String toString() {
        return  "FriendOrFamily{" +
        "Contact Number=" + contactNumber +
        ", Contact Name='" + contactName + '\'' +
        ", Relationship Type ='" + relationshipType + '\'' ;
    }
}