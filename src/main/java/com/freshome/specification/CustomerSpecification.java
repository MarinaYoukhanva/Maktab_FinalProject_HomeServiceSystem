package com.freshome.specification;

import com.freshome.entity.Customer;
import com.freshome.entity.User;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {

    public static Specification<Customer> searchCustomer(
            List<SingularAttribute<?, ?>> fields, List<Operator> operators, List<String> values
    ) {
        List<Predicate> predicates = new ArrayList<>();
        return (((root, query, criteriaBuilder) -> {
            PredicatesCreator.create(fields,operators, values, root, criteriaBuilder,predicates);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
