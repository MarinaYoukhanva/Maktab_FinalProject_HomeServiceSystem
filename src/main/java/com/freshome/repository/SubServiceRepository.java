package com.freshome.repository;

import com.freshome.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Long> {

    boolean existsByName(String name);

    List<SubService> findByCategory_Id(Long categoryId);
}
