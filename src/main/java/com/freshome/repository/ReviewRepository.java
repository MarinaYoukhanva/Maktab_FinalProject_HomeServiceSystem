package com.freshome.repository;

import com.freshome.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.order.expert.id = :expertId")
    Double expertScoreFromRatingsAverage(@Param("expertId") Long expertId);


    Optional<Review> findByOrder_Id(Long orderId);
}
