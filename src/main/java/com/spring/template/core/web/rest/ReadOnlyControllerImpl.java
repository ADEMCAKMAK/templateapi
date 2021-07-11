package com.spring.template.core.web.rest;

import com.spring.template.core.entity.base.BaseEntity;
import com.spring.template.core.service.base.BaseService;
import com.spring.template.core.service.base.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

public abstract class ReadOnlyControllerImpl<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>>
    implements ReadOnlyController<T, ID, M> {
    
    private final BaseService<T, ID, M> baseService;

    protected ReadOnlyControllerImpl(BaseService<T, ID, M> baseService) {
        this.baseService = baseService;
    }

    public BaseService<T, ID, M> getBaseService() {
        return baseService;
    }

    @GetMapping(value = "{id}")
    @Override
    public ResponseEntity<M> findById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(getBaseService().findById(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<M>> findAll(@RequestParam(defaultValue = "", required = false) String query) {
        return ResponseEntity.ok(getBaseService().findAll(query));
    }

    @GetMapping("/page")
    @Override
    public ResponseEntity<Page<M>> findAll(@RequestParam(defaultValue = "", required = false) String query,
                                           @PageableDefault(Integer.MAX_VALUE) Pageable pageable) {
        return ResponseEntity.ok(getBaseService().findAll(query, pageable));
    }
}
