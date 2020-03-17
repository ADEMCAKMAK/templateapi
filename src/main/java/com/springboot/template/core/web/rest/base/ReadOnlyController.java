package com.springboot.template.core.web.rest.base;

import com.exercises.springboot.core.entity.base.BaseEntity;
import com.exercises.springboot.core.entity.base.HistoryEntity;
import com.exercises.springboot.core.service.base.BaseService;
import com.exercises.springboot.core.service.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

public abstract class ReadOnlyController<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseDTO<ID>>
        implements IReadOnlyController<T, ID, M> {

    private final BaseService<T, ID, M> service;

    protected ReadOnlyController(BaseService<T, ID, M> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public Page<M> findAll(@RequestParam(defaultValue = "", required = false) String query,
                           @PageableDefault(Integer.MAX_VALUE) Pageable pageable) {
        return service.findAll(query, pageable);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Override
    public M findById(@PathVariable("id") ID id) {
        return service._findById(id);
    }

    @RequestMapping(value = "/audit/{id}", method = RequestMethod.GET)
    @Override
    public List<HistoryEntity<T>> findChanges(@PathVariable("id") ID id) {
        return this.service.findChanges(id);
    }
}
