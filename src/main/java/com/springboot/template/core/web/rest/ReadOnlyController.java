package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.service.base.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface ReadOnlyController<E extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>> {

    ResponseEntity<M> findById(ID id);

    ResponseEntity<List<M>> findAll(String query);

    ResponseEntity<Page<M>> findAll(String query, Pageable pageable);
}
