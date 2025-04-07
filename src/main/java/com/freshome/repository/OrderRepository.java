package com.freshome.repository;

import com.freshome.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    @Query(value = """
            SELECT o.* FROM home.order o
            JOIN home.customer c ON o.customer_id = c.id
            JOIN home."user" u ON c.id = u.id
            WHERE o.customer_id = :customerId AND u.deleted = false AND o.deleted = false
            """, nativeQuery = true)
    List<Order> findByCustomer_Id(@Param("customerId") Long id);

    @Query(value = """
            SELECT o.* FROM home.order o
            JOIN home.expert e ON o.expert_id = e.id
            JOIN home."user" u ON e.id = u.id
            WHERE o.expert_id = :expertId AND u.deleted = false AND o.deleted = false
            """, nativeQuery = true)
    List<Order> findByExpert_Id(@Param("expertId") Long id);

    List<Order> findBySubService_IdIn(List<Long> ids);
}
