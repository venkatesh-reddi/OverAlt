package com.overalt.repository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.overalt.model.Customer;
import com.overalt.model.FriendOrFamily;

@ExtendWith(MockitoExtension.class)
public class FriendOrFamilyRepositoryTest {

    @Mock
    private FriendOrFamilyRepository friendOrFamilyRepository;

    private FriendOrFamily friendOrFamily;
    private Customer customer;

    @BeforeEach
    void setUp() {
        // Initialize test data
        customer = new Customer();
        friendOrFamily = new FriendOrFamily();
        friendOrFamily.setContactNumber(1234567890L);
        friendOrFamily.setCustomer(customer);
    }

    @Test
    void testFindByContactNumber() {
        when(friendOrFamilyRepository.findByContactNumber(anyLong())).thenReturn(friendOrFamily);

        FriendOrFamily result = friendOrFamilyRepository.findByContactNumber(1234567890L);
        verify(friendOrFamilyRepository, times(1)).findByContactNumber(1234567890L);
        assertThat(result).isNotNull().isEqualTo(friendOrFamily);
    }

    @Test
    void testFindByCustomer() {
        List<FriendOrFamily> friendsOrFamilyList = new ArrayList<>();
        friendsOrFamilyList.add(friendOrFamily);

        when(friendOrFamilyRepository.findByCustomer(any(Customer.class))).thenReturn(friendsOrFamilyList);

        List<FriendOrFamily> result = friendOrFamilyRepository.findByCustomer(customer);
        verify(friendOrFamilyRepository, times(1)).findByCustomer(customer);
        assertThat(result).hasSize(1).contains(friendOrFamily);
    }
}
