package com.springboot.template.core.web.rest.base;


import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.service.dto.BaseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.Serializable;


public abstract class CrudController<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseDTO<ID>>
        extends ReadOnlyController<T, ID, M> implements ICrudController<T, ID, M> {

    private final BaseService<T, ID, M> service;

    public CrudController(BaseService<T, ID, M> service) {
        super(service);
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public M create(@RequestBody @Valid M model) {
        return service.create(model);
    }

    @RequestMapping(value = "{id:.+}", method = RequestMethod.PUT)
    @Override
    public M update(@PathVariable("id") ID id, @RequestBody @Valid M model) {
        return service.update(id, model);
    }

    @RequestMapping(value = "{id:.+}", method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable("id") ID id) {
        service.delete(id);
    }


}
