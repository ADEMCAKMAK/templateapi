package com.spring.template.core.service.base;

import com.spring.template.core.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>> {

    Page<M> findAll(String query, Pageable pageable);

    List<M> findAll(String query);

    Optional<M> findById(ID id);

    M findByID(ID id);

    M create(M model);

    M update(M model);

    M update(M model, Boolean created);

    void delete(ID id);
    
}
