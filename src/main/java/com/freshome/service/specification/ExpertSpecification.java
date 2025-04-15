package com.freshome.service.specification;

import com.freshome.entity.*;
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
                Join<Expert, User> userExpert = root.join("user");
                for (int i = 0; i < fields.size(); i++) {
                    String field = fields.get(i);
                    String value = values.get(i);
                    if (isUserField(field))
                        predicates.add(criteriaBuilder.like(
                                userExpert.get(field), "%" + value + "%"));
                    else
                        predicates.add(criteriaBuilder.like(
                                root.get(field), "%" + value + "%"));
                }
            }
            if (expertise != null && !expertise.isEmpty()) {
                Join<Expert, SubService> subServiceExpert = root.join("subServices", JoinType.LEFT);

                predicates.add(criteriaBuilder.like(
                        subServiceExpert.get(SubService_.name), "%" + expertise + "%"));
            }
            if (minScore != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Expert_.score), minScore));
            }
            if (maxScore != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Expert_.score), maxScore));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }

    private static boolean isUserField(String field) {
        return field.equals("firstname")
                || field.equals("lastname")
                || field.equals("username")
                || field.equals("email");
    }
}
