package com.overalt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.overalt.model.CallDetails;

@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetails, Integer> {
    List<CallDetails> findByCallerId(Long callerId);
    List<CallDetails> findByReceiverId(Long receiverId);
    List<CallDetails> findByCallerIdAndReceiverId(Long callerId, Long receiverId);
    List<CallDetails> findByCallStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    static void deleteByCallerId(Long callerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByCallerId'");
    }
    void deleteByReceiverId(Long receiverId);
    void deleteByCallerId(long anyLong);
    void deleteByCallId(long anyLong);

   

    
}
