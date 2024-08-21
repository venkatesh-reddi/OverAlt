package com.overalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.overalt.model.CallDetails;

@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetails, Integer> {
    List<CallDetails> findByCallerId(Long callerId);
    List<CallDetails> findByReceiverId(String receiverId);
    List<CallDetails> findByCallerIdAndReceiverId(Long callerId, String receiverId);
}
