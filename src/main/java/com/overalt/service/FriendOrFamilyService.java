package com.overalt.service;

import com.overalt.model.FriendOrFamily;
import com.overalt.repository.FriendOrFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendOrFamilyService {

    @Autowired
    private FriendOrFamilyRepository friendOrFamilyRepository;

    public FriendOrFamily saveFriendOrFamily(FriendOrFamily friendOrFamily) {
        return friendOrFamilyRepository.save(friendOrFamily);
    }

    public FriendOrFamily getFriendOrFamilyByContactNumber(long contactNumber) {
        return friendOrFamilyRepository.findByContactNumber(contactNumber);
    }

    public List<FriendOrFamily> getAllFriendsOrFamily() {
        return friendOrFamilyRepository.findAll();
    }

    public void deleteFriendOrFamilyByContactNumber(long contactNumber) {
        friendOrFamilyRepository.deleteById(contactNumber);
    }
}
