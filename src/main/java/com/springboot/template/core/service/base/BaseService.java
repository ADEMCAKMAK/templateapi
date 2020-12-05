package com.springboot.template.core.service.base;

import com.springboot.template.core.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    Page<T> findAll(String query, Pageable pageable);

    List<T> findAll(String query);

    Optional<T> findById(ID id);

    T create(T model);

    T update(T model);

    T update(T model, Boolean created);

    void delete(ID id);
    
}
