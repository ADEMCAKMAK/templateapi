package com.spring.template.core.web.rest;

import com.spring.template.core.entity.base.BaseEntity;
import com.spring.template.core.service.base.BaseModel;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface CrudController<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>>
        extends ReadOnlyController<T, ID, M>{

    ResponseEntity<M> create(M model);

    ResponseEntity<M> update(M model);

    ResponseEntity<?> delete(ID id, Boolean force);
}
