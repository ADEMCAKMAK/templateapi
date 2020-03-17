package com.springboot.template.core.repository.base.support;

import com.github.tennaito.rsql.jpa.JpaPredicateVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

public class RSQLQueryUtils {

    public static <E> Specification<E> toSpecification(final String query, final EntityManager entityManager) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            @SuppressWarnings("unchecked")
            RSQLVisitor<Predicate, EntityManager> visitor = new JpaPredicateVisitor<E>().defineRoot(root);

            final Node rootNode = new RSQLParser().parse(query);

            return rootNode.accept(visitor, entityManager);

        };
    }

}
