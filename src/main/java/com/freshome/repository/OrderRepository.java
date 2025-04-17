package com.freshome.repository;

import com.freshome.entity.Order;
import com.freshome.entity.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    List<Order> findAllByCustomer_Id(@Param("customerId") Long id);

    List<Order> findAllByStatusInAndCustomer_Id (List<OrderStatus> status, Long id);

    int countOrdersByCustomer_Id( Long id);

    int countOrdersByStatusInAndCustomer_Id (List<OrderStatus> status, Long id);


    List<Order> findAllByExpert_Id(@Param("expertId") Long id);

    List<Order> findAllByStatusInAndExpert_Id (List<OrderStatus> status,  Long id);

    @Query(value = """
            SELECT count(o.id) FROM home.order o
            JOIN home.expert e ON o.expert_id = e.id
            JOIN home."user" u ON e.id = u.id
            WHERE o.expert_id = :expertId AND u.deleted = false AND o.deleted = false
            """, nativeQuery = true)
    int countOrderByExpert_Id(@Param("expertId") Long id);

    @Query(value = """
            SELECT count(o.id) FROM home.order o
            JOIN home.expert e ON o.expert_id = e.id
            JOIN home."user" u ON e.id = u.id
            WHERE o.expert_id = :expertId AND u.deleted = false AND o.deleted = false
            AND o.status IN ('COMPLETED', 'PAID')
            """, nativeQuery = true)
    int countDoneOrderByExpertId (@Param("expertId") Long id);

    List<Order> findBySubService_IdIn(List<Long> ids);
}
