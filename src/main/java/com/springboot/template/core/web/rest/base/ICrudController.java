package com.springboot.template.core.web.rest.base;


import com.exercises.springboot.core.entity.base.BaseEntity;
import com.exercises.springboot.core.service.dto.BaseDTO;

import java.io.Serializable;

public interface ICrudController<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseDTO<ID>>
        extends IReadOnlyController<T, ID, M> {

    M create(M model);

    M update(ID id, M model);

    void delete(ID id);
}
