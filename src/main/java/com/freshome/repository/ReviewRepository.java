package com.freshome.repository;

import com.freshome.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    //@Query("SELECT COALESCE(AVG(r.rating), 0.0) FROM Review r WHERE r.expert.id = :expertId")
//    @Query(value = "SELECT COALESCE(AVG(r.rating), 0.0) FROM review r JOIN expert e ON r.expert_id = e.id " +
//            "WHERE e.id = :expertId AND e.deleted = false AND r.deleted = false",
//            nativeQuery = true)
    @Query(value = """
            SELECT COALESCE(AVG(r.rating), 0.0) FROM home.review r
            JOIN home.expert e ON r.expert_id = e.id
            JOIN home."user" u ON e.id = u.id
            WHERE e.id = :expertId AND u.deleted = false AND r.deleted = false
            """,
            nativeQuery = true)
Double expertScoreFromRatingsAverage(@Param("expertId") Long expertId);
}
