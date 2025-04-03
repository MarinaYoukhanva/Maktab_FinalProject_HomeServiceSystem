package com.freshome.specification;

import com.freshome.entity.*;
import com.freshome.entity.Order;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExpertSpecification {

    public static Specification<Expert> searchExpert(
            List<SingularAttribute<?, ?>> fields, List<Operator> operators, List<String> values,
            String expertise
    ) {
        List<Predicate> predicates = new ArrayList<>();
        return (((root, query, criteriaBuilder) -> {

            if (expertise != null) {
                Join<Expert, SubService> subServiceExpert = root.join("subServices");
                Join<SubService, ServiceCategory> subServiceExpertCategory = subServiceExpert
                        .join("category");

                predicates.add(criteriaBuilder.like(
                        subServiceExpertCategory.get(ServiceCategory_.name), "%" + expertise + "%"));
            }
            PredicatesCreator.create(fields, operators, values, root, criteriaBuilder, predicates);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }

    public static Specification<Expert> searchExpert(
            List<String> fields, List<String> values,
            String expertise
            , Double minScore, Double maxScore
    ) {
        List<Predicate> predicates = new ArrayList<>();
        return (((root, query, criteriaBuilder) -> {
            if (fields != null && !fields.isEmpty()) {
                for (int i = 0; i < fields.size(); i++) {
                    predicates.add(criteriaBuilder.like(
                            root.get(fields.get(i)), "%" + values.get(i) + "%"
                    ));
                }
            }
            if (expertise != null && !expertise.isEmpty()) {
                Join<Expert, SubService> subServiceExpert = root.join("subServices", JoinType.LEFT);

                predicates.add(criteriaBuilder.like(
                        subServiceExpert.get(SubService_.name), "%" + expertise + "%"));
            }
            if (minScore != null || maxScore != null) {
                Subquery<Double> subquery = query.subquery(Double.class);
                Root<Review> reviewRoot = subquery.from(Review.class);
                Join<Review, Order> orderJoin = reviewRoot.join("order", JoinType.LEFT);

                subquery.select(criteriaBuilder.avg(reviewRoot.get(Review_.RATING)))
                        .where(criteriaBuilder.equal(orderJoin.get("expert"), root));

                Expression<Double> avgScore = criteriaBuilder.coalesce(subquery, 0.0);

                if (minScore != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(avgScore, minScore));
                }
                if (maxScore != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(avgScore, maxScore));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
