package com.overalt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overalt.model.FriendOrFamily;
import com.overalt.repository.FriendOrFamilyRepository;

@Service
public class FriendOrFamilyService {

    private final FriendOrFamilyRepository friendOrFamilyRepository;

    @Autowired
    public FriendOrFamilyService(FriendOrFamilyRepository friendOrFamilyRepository) {
        this.friendOrFamilyRepository = friendOrFamilyRepository;
    }

    public boolean updateContactNumber(long oldContactNumber, long newContactNumber) {
        FriendOrFamily friendOrFamily = friendOrFamilyRepository.findByContactNumber(oldContactNumber);
        if (friendOrFamily != null) {
            friendOrFamily.setContactNumber(newContactNumber);
            friendOrFamilyRepository.save(friendOrFamily);
            return true;
        }
        return false;
    }
}
