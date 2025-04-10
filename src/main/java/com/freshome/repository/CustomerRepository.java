package com.freshome.repository;

import com.freshome.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
