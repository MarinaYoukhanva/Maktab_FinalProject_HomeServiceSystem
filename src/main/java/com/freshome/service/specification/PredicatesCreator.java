package com.freshome.service.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

import java.util.List;

public class PredicatesCreator {

    public static <T> List<Predicate> create(
            List<SingularAttribute<?, ?>> fields, List<Operator> operators, List<String> values,
            Root<T> root, CriteriaBuilder criteriaBuilder,List<Predicate> predicates
    ){
        for (int i = 0; i < fields.size(); i++) {

            switch (operators.get(i)) {
                case GREATER_THAN -> predicates
                        .add(criteriaBuilder.greaterThan(
                                root.get(fields.get(i).getName()), values.get(i)));
                case LESS_THAN -> predicates
                        .add(criteriaBuilder.lessThan(
                                root.get(fields.get(i).getName()), values.get(i)));
                case GREATER_EQUAL -> predicates
                        .add(criteriaBuilder.greaterThanOrEqualTo(
                                root.get(fields.get(i).getName()), values.get(i)));
                case LESS_EQUAL -> predicates
                        .add(criteriaBuilder.lessThanOrEqualTo(
                                root.get(fields.get(i).getName()), values.get(i)));
                case EQUAL -> predicates
                        .add(criteriaBuilder.equal(
                                root.get(fields.get(i).getName()), values.get(i)));
                case STARTS_WITH -> predicates
                        .add(criteriaBuilder.like(
                                root.get(fields.get(i).getName()), values.get(i) + "%"));
                case ENDS_WITH -> predicates
                        .add(criteriaBuilder.like(
                                root.get(fields.get(i).getName()), "%" + values.get(i)));
                case CONTAINS -> predicates
                        .add(criteriaBuilder.like(
                                root.get(fields.get(i).getName()), "%" + values.get(i) + "%"));
            }
        }
        return predicates;
    }
}
