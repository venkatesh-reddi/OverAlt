package com.overalt.controller;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overalt.exception.friendorfamily.FriendOrFamilyNotFoundException;
import com.overalt.exception.friendorfamily.InvalidFriendOrFamilyDataException;
import com.overalt.model.Customer;
import com.overalt.model.FriendOrFamily;
import com.overalt.repository.CustomerRepository;
import com.overalt.repository.FriendOrFamilyRepository;

@ExtendWith(MockitoExtension.class)
public class FriendOrFamilyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FriendOrFamilyRepository friendOrFamilyRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private FriendOrFamilyController friendOrFamilyController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateFriendOrFamily() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        FriendOrFamily friendOrFamily = new FriendOrFamily();
        friendOrFamily.setContactNumber(123456789L);
        friendOrFamily.setContactName("Alice");
        friendOrFamily.setRelationshipType("Friend");

        when(friendOrFamilyRepository.save(any(FriendOrFamily.class))).thenReturn(friendOrFamily);

        mockMvc.perform(post("/friendorfamily")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(friendOrFamily)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contactNumber").value(123456789L))
                .andExpect(jsonPath("$.contactName").value("Alice"))
                .andExpect(jsonPath("$.relationshipType").value("Friend"));
    }

    @Test
    void testCreateFriendOrFamily_Invalid() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        mockMvc.perform(post("/friendorfamily")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"contactNumber\": -1 }")) // Invalid contact number
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidFriendOrFamilyDataException.class)
                        .hasMessage("Friend or Family data is invalid. Contact number must be positive.");
                });
    }

    @Test
    void testGetFriendOrFamilyByContactNumber() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        FriendOrFamily friendOrFamily = new FriendOrFamily();
        friendOrFamily.setContactNumber(123456789L);
        friendOrFamily.setContactName("Alice");
        friendOrFamily.setRelationshipType("Friend");

        when(friendOrFamilyRepository.findByContactNumber(anyLong())).thenReturn(friendOrFamily);

        mockMvc.perform(get("/friendorfamily/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactNumber").value(123456789L))
                .andExpect(jsonPath("$.contactName").value("Alice"))
                .andExpect(jsonPath("$.relationshipType").value("Friend"));
    }

    @Test
    void testGetFriendOrFamilyByContactNumber_NotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        when(friendOrFamilyRepository.findByContactNumber(anyLong())).thenReturn(null);

        mockMvc.perform(get("/friendorfamily/123456789"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(FriendOrFamilyNotFoundException.class)
                        .hasMessage("Friend or Family with contact number 123456789 not found");
                });
    }

    @Test
    void testGetFriendsOrFamilyByCustomerId() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");

        FriendOrFamily friendOrFamily = new FriendOrFamily();
        friendOrFamily.setContactNumber(123456789L);
        friendOrFamily.setContactName("Alice");
        friendOrFamily.setRelationshipType("Friend");

        List<FriendOrFamily> friendsOrFamily = Arrays.asList(friendOrFamily);
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        when(friendOrFamilyRepository.findByCustomer(any(Customer.class))).thenReturn(friendsOrFamily);

        mockMvc.perform(get("/friendorfamily/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].contactNumber").value(123456789L))
                .andExpect(jsonPath("$[0].contactName").value("Alice"))
                .andExpect(jsonPath("$[0].relationshipType").value("Friend"));
    }

    @Test
    void testGetFriendsOrFamilyByCustomerId_NotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/friendorfamily/customer/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFriendOrFamily() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        FriendOrFamily existingFriendOrFamily = new FriendOrFamily();
        existingFriendOrFamily.setContactNumber(123456789L);
        existingFriendOrFamily.setContactName("Alice");
        existingFriendOrFamily.setRelationshipType("Friend");

        FriendOrFamily updatedFriendOrFamily = new FriendOrFamily();
        updatedFriendOrFamily.setContactName("Bob");
        updatedFriendOrFamily.setRelationshipType("Family");

        when(friendOrFamilyRepository.findByContactNumber(anyLong())).thenReturn(existingFriendOrFamily);
        when(friendOrFamilyRepository.save(any(FriendOrFamily.class))).thenReturn(updatedFriendOrFamily);

        mockMvc.perform(put("/friendorfamily/123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFriendOrFamily)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactNumber").value(123456789L))
                .andExpect(jsonPath("$.contactName").value("Bob"))
                .andExpect(jsonPath("$.relationshipType").value("Family"));
    }

    @Test
    void testUpdateFriendOrFamily_NotFound() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        FriendOrFamily updatedFriendOrFamily = new FriendOrFamily();
        updatedFriendOrFamily.setContactName("Bob");
        updatedFriendOrFamily.setRelationshipType("Family");

        when(friendOrFamilyRepository.findByContactNumber(anyLong())).thenReturn(null);

        mockMvc.perform(put("/friendorfamily/123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFriendOrFamily)))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                        .isInstanceOf(FriendOrFamilyNotFoundException.class)
                        .hasMessage("Friend or Family with contact number 123456789 not found");
                });
    }

    @Test
    void testDeleteFriendOrFamily() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        doNothing().when(friendOrFamilyRepository).deleteById(anyLong());

        mockMvc.perform(delete("/friendorfamily/123456789"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteFriendOrFamily_InternalServerError() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(friendOrFamilyController).build();

        doThrow(new RuntimeException()).when(friendOrFamilyRepository).deleteById(anyLong());

        mockMvc.perform(delete("/friendorfamily/123456789"))
                .andExpect(status().isInternalServerError());
    }
}
