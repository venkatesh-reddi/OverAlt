package com.overalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.overalt.model.FriendOrFamily;
@Service
public interface  FriendOrFamilyRepository extends JpaRepository<FriendOrFamily, Long> {
    FriendOrFamily updateContactNumber(String newPhoneNumber);
    FriendOrFamily updateRelationshipType(String newRelationshipType);
}
