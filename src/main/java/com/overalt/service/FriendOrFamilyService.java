package com.overalt.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.overalt.model.FriendOrFamily;
import com.overalt.repository.FriendOrFamilyRepository;

public class FriendOrFamilyService{
    @Autowired
    public final FriendOrFamilyRepository ffrepo;

    public FriendOrFamilyService(FriendOrFamilyRepository ffrepo){
        this.ffrepo = ffrepo;
    }

    // Method to update the contact number
    public FriendOrFamily updateContactNumber(String newPhoneNumber) {
        return ffrepo.updateContactNumber(newPhoneNumber);
    }

    // Method to update the relationship type
    public FriendOrFamily updateRelationshipType(String newRelationshipType) {
        return ffrepo.updateRelationshipType(newRelationshipType);
    }

}