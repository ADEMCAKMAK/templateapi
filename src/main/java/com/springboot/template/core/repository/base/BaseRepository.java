package com.springboot.template.core.repository.base;

import com.exercises.springboot.core.entity.base.BaseEntity;
import com.exercises.springboot.core.repository.base.support.RSQLQueryUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class BaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements IBaseRepository<T, ID> {

    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ID> entityInformation;

    public BaseRepository(JpaEntityInformation<T, ID> entityInformation,
                          EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    //* https://github.com/jirutka/rsql-parser
    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(String query, Pageable pageable) {
        return StringUtils.isEmpty(query) ?
                this.findAll(pageable) :
                this.findAll(RSQLQueryUtils.toSpecification(query, this.entityManager), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(String query) {
        return StringUtils.isEmpty(query) ?
                this.findAll() :
                this.findAll(RSQLQueryUtils.toSpecification(query, this.entityManager));
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public AuditReader getAuditReader() {
        return AuditReaderFactory.get(this.getEntityManager());
    }
}