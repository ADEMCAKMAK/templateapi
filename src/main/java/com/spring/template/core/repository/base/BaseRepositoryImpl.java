package com.spring.template.core.repository.base;

import com.spring.template.core.entity.base.BaseEntity;
import com.spring.template.core.repository.support.SQL_JPA_QUERY_SUPPORT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;


public class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(final String query, final Pageable pageable) {
        return StringUtils.isEmpty(query) ?
                this.findAll(pageable) :
                this.findAll(SQL_JPA_QUERY_SUPPORT.toSpecification(query, getEntityManager()), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(final String query) {
        return StringUtils.isEmpty(query) ?
                this.findAll() :
                this.findAll(SQL_JPA_QUERY_SUPPORT.toSpecification(query, getEntityManager()));
    }
}
