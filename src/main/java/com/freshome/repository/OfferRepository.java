package com.freshome.repository;

import com.freshome.entity.Offer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select off from Offer off join Order orr on off.order.id = orr.id where orr.id = :orderId")
    List<Offer> findByOrder_Id(@Param("orderId") Long id, Sort sort);

    @Query(value = """
            SELECT count (o.id) FROM home.offer o
            JOIN home.expert e ON o.expert_id = e.id
            JOIN home."user" u ON e.id = u.id
            WHERE o.expert_id = :expertId AND u.deleted = false AND o.deleted = false
            """, nativeQuery = true)
    int countByExpert_Id(@Param("expertId") Long id);
}
