package com.spring.template.core.repository.base;

import com.spring.template.core.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepositoryImplementation<T, ID> {

    EntityManager getEntityManager();

    Page<T> findAll(String query, Pageable pageable);

    List<T> findAll(String query);

}
