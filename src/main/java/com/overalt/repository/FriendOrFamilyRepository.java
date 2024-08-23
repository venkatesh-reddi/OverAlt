package com.overalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.overalt.model.Customer;
import com.overalt.model.FriendOrFamily;

@Repository
public interface FriendOrFamilyRepository extends JpaRepository<FriendOrFamily, Long> {
    // Custom method to find FriendOrFamily by contact number
    FriendOrFamily findByContactNumber(long contactNumber);
    List<FriendOrFamily> findByCustomer(Customer customer);
}
