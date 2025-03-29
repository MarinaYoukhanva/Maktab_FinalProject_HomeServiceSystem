package com.freshome.repository;

import com.freshome.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long>,
        JpaSpecificationExecutor<Expert> {

    boolean existsByEmail(String email);

}
