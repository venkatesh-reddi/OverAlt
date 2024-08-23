package com.overalt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.overalt.model.Customer;
import com.overalt.model.FriendOrFamily;
import com.overalt.repository.FriendOrFamilyRepository;
import com.overalt.repository.CustomerRepository;

@RestController
@RequestMapping("/friendorfamily")
public class FriendOrFamilyController {

    @Autowired
    private FriendOrFamilyRepository friendOrFamilyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new friend or family member
    @PostMapping
    public ResponseEntity<FriendOrFamily> createFriendOrFamily(@RequestBody FriendOrFamily friendOrFamily) {
        FriendOrFamily savedFriendOrFamily = friendOrFamilyRepository.save(friendOrFamily);
        return new ResponseEntity<>(savedFriendOrFamily, HttpStatus.CREATED);
    }

    // Get a friend or family member by contact number
    @GetMapping("/{contactNumber}")
    public ResponseEntity<FriendOrFamily> getFriendOrFamilyByContactNumber(@PathVariable long contactNumber) {
        FriendOrFamily friendOrFamily = friendOrFamilyRepository.findByContactNumber(contactNumber);
        if (friendOrFamily != null) {
            return new ResponseEntity<>(friendOrFamily, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all friends or family members for a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FriendOrFamily>> getFriendsOrFamilyByCustomerId(@PathVariable int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            List<FriendOrFamily> friendsOrFamily = friendOrFamilyRepository.findByCustomer(customer.get());
            return new ResponseEntity<>(friendsOrFamily, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing friend or family member
    @PutMapping("/{contactNumber}")
    public ResponseEntity<FriendOrFamily> updateFriendOrFamily(@PathVariable long contactNumber, @RequestBody FriendOrFamily friendOrFamilyDetails) {
        FriendOrFamily friendOrFamily = friendOrFamilyRepository.findByContactNumber(contactNumber);
        if (friendOrFamily != null) {
            friendOrFamily.setContactName(friendOrFamilyDetails.getContactName());
            friendOrFamily.setRelationshipType(friendOrFamilyDetails.getRelationshipType());
            FriendOrFamily updatedFriendOrFamily = friendOrFamilyRepository.save(friendOrFamily);
            return new ResponseEntity<>(updatedFriendOrFamily, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a friend or family member
    @DeleteMapping("/{contactNumber}")
    public ResponseEntity<HttpStatus> deleteFriendOrFamily(@PathVariable long contactNumber) {
        try {
            friendOrFamilyRepository.deleteById(contactNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
