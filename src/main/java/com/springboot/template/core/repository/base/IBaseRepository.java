package com.springboot.template.core.repository.base;

import com.springboot.template.core.entity.base.BaseEntity;
import org.hibernate.envers.AuditReader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface IBaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    Page<T> findAll(String query, Pageable pageable);

    List<T> findAll(String query);

    EntityManager getEntityManager();

    AuditReader getAuditReader();
}
