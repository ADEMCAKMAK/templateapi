package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.service.base.BaseModel;

import java.io.Serializable;

public interface CrudController<E extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>>
        extends ReadOnlyController<E, ID, M>{

    M create(M model);

    M update(M model);

    void delete(ID id);
}
