package com.overalt.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.overalt.model.Customer;
import com.overalt.model.FriendOrFamily;
import com.overalt.repository.FriendOrFamilyRepository;

@SpringBootTest
public class FriendOrFamilyServiceTest {

    @Mock
    private FriendOrFamilyRepository friendOrFamilyRepository;

    @InjectMocks
    private FriendOrFamilyService friendOrFamilyService;

    private FriendOrFamily friendOrFamily;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null, 2, 3);
        friendOrFamily = new FriendOrFamily(customer, 9876543210L, "Jane Doe", "Sister");
    }

    @Test
    public void testSaveFriendOrFamily() {
        when(friendOrFamilyRepository.save(any(FriendOrFamily.class))).thenReturn(friendOrFamily);
        FriendOrFamily savedFriendOrFamily = friendOrFamilyService.saveFriendOrFamily(friendOrFamily);
        assertThat(savedFriendOrFamily).isNotNull();
        assertThat(savedFriendOrFamily.getContactNumber()).isEqualTo(9876543210L);
        verify(friendOrFamilyRepository, times(1)).save(friendOrFamily);
    }

    @Test
    public void testGetFriendOrFamilyByContactNumber() {
        when(friendOrFamilyRepository.findByContactNumber(9876543210L)).thenReturn(friendOrFamily);
        FriendOrFamily foundFriendOrFamily = friendOrFamilyService.getFriendOrFamilyByContactNumber(9876543210L);
        assertThat(foundFriendOrFamily).isNotNull();
        assertThat(foundFriendOrFamily.getContactNumber()).isEqualTo(9876543210L);
        verify(friendOrFamilyRepository, times(1)).findByContactNumber(9876543210L);
    }

    @Test
    public void testGetAllFriendsOrFamily() {
        List<FriendOrFamily> friendsOrFamily = Arrays.asList(friendOrFamily);
        when(friendOrFamilyRepository.findAll()).thenReturn(friendsOrFamily);
        List<FriendOrFamily> allFriendsOrFamily = friendOrFamilyService.getAllFriendsOrFamily();
        assertThat(allFriendsOrFamily).isNotEmpty();
        assertThat(allFriendsOrFamily.size()).isEqualTo(1);
        verify(friendOrFamilyRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteFriendOrFamilyByContactNumber() {
        doNothing().when(friendOrFamilyRepository).deleteById(9876543210L);
        friendOrFamilyService.deleteFriendOrFamilyByContactNumber(9876543210L);
        verify(friendOrFamilyRepository, times(1)).deleteById(9876543210L);
    }
}
