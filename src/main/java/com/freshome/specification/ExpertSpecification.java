package com.freshome.specification;

import com.freshome.entity.Expert;
import com.freshome.entity.ServiceCategory;
import com.freshome.entity.ServiceCategory_;
import com.freshome.entity.SubService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
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
}
