package com.freshome.service.specification;

import com.freshome.entity.Order;
import com.freshome.entity.Order_;
import com.freshome.entity.ServiceCategory_;
import com.freshome.entity.SubService_;
import com.freshome.entity.enumeration.OrderStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order> searchOrder(
            LocalDateTime fromDate, LocalDateTime toDate, OrderStatus orderStatus,
            String serviceCategory, String subService
    ) {
        List<Predicate> predicates = new ArrayList<>();
        return ((((root, query, criteriaBuilder) -> {
            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get(Order_.orderPlacementDateTime), fromDate
                ));
            }
            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(Order_.orderPlacementDateTime), toDate
                ));
            }
            if (orderStatus != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get(Order_.status), orderStatus
                ));
            }
            if (serviceCategory != null && !serviceCategory.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get(Order_.subService).get(SubService_.category).get(ServiceCategory_.name),
                        "%" + serviceCategory + "%"
                ));
            }
            if (subService != null && !subService.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get(Order_.subService).get(SubService_.name),
                        "%" + subService + "%"
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        })));
    }
}
