package com.freshome.repository;

import com.freshome.entity.Customer;
import com.freshome.service.specification.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByUser_Id(Long id);

    boolean existsByPhoneNumber(String phoneNumber);
}
