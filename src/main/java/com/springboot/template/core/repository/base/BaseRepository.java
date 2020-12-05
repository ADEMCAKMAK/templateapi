package com.springboot.template.core.repository.base;

import com.springboot.template.core.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepositoryImplementation<T, ID> {

    EntityManager getEntityManager();

}
