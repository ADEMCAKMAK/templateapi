package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.service.base.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ReadOnlyController<E extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>> {

    M findById(ID id);

    List<M> findAll(String query);

    Page<M> findAll(String query, Pageable pageable);
}
