package com.freshome.service.specification;

import com.freshome.entity.Customer;
import com.freshome.entity.User;
import jakarta.persistence.criteria.Join;
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
            PredicatesCreator.create(fields, operators, values, root, criteriaBuilder, predicates);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }

    public static Specification<Customer> searchCustomer(
            List<String> fields, List<String> values
    ) {
        List<Predicate> predicates = new ArrayList<>();
        return (((root, query, criteriaBuilder) -> {
            if (fields == null || fields.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Customer, User> customerUserJoin = root.join("user");
            for (int i = 0; i < fields.size(); i++) {
                String field = fields.get(i);
                String value = values.get(i);
                if (isUserField(field))
                    predicates.add(criteriaBuilder.like(
                            customerUserJoin.get(field), "%" + value + "%"));
                else
                    predicates.add(criteriaBuilder.like(
                            root.get(field), "%" +value + "%"));
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
