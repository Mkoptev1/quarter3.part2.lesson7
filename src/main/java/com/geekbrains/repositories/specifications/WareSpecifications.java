package com.geekbrains.repositories.specifications;

import com.geekbrains.entities.Ware;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WareSpecifications {
    public static Specification<Ware> getWareByName(String filterWareName) {
        return new Specification<Ware>() {
            @Override
            public Predicate toPredicate(Root<Ware> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if (filterWareName != null && !filterWareName.trim().isEmpty()) {
                    return criteriaBuilder.like(root.get("name"), "%"+ filterWareName + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };
    }

    public static Specification<Ware> priceGreaterThanOrEq(Long filterPrice) {
        return new Specification<Ware>() {
            @Override
            public Predicate toPredicate(Root<Ware> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if (filterPrice != null && filterPrice > 0) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), filterPrice);
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };
    }

    public static Specification<Ware> priceLesserThanOrEq(Long filterPrice) {
        return new Specification<Ware>() {
            @Override
            public Predicate toPredicate(Root<Ware> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                if (filterPrice != null && filterPrice > 0) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), filterPrice);
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };
    }
}