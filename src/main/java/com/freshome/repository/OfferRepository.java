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
}
