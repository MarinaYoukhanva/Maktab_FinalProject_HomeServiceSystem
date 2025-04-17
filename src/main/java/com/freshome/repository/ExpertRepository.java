package com.freshome.repository;

import com.freshome.entity.Expert;
import com.freshome.entity.enumeration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long>,
        JpaSpecificationExecutor<Expert> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Expert> findByUser_Id(Long userId);

    Optional<Expert> findByUser_Username(String username);

    List<Expert> findByStatus(UserStatus status);

}
