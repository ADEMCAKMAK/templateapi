package com.springboot.template.core.repository.base;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.repository.support.RSQL_JPA_QUERY_SUPPORT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(String query, Pageable pageable) {
        return StringUtils.isEmpty(query) ?
                this.findAll(pageable) :
                this.findAll(RSQL_JPA_QUERY_SUPPORT.toSpecification(query, getEntityManager()), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(String query) {
        return StringUtils.isEmpty(query) ?
                this.findAll() :
                this.findAll(RSQL_JPA_QUERY_SUPPORT.toSpecification(query, getEntityManager()));
    }
}
