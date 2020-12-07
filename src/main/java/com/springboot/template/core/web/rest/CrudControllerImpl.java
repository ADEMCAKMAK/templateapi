package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.service.base.BaseModel;
import com.springboot.template.core.service.base.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.Serializable;

public abstract class CrudControllerImpl<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>>
    extends ReadOnlyControllerImpl<T, ID, M>
    implements CrudController<T, ID, M>, ReadOnlyController<T, ID, M>{

    public CrudControllerImpl(BaseService<T, ID, M> baseService) {
        super(baseService);
    }

    @PostMapping
    @Override
    public ResponseEntity<M> create(M model) {
        return ResponseEntity.ok(getBaseService().create(model));
    }

    @PutMapping(value = "{id:.+}")
    @Override
    public ResponseEntity<M> update(M model) {
        return ResponseEntity.ok(getBaseService().update(model));
    }

    @DeleteMapping(value = "{id:.+}")
    @Override
    public ResponseEntity<?> delete(ID id) {
        getBaseService().delete(id);
        return ResponseEntity.ok().build();
    }
}
