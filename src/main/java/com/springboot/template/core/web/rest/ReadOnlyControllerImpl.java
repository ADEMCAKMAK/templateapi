package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.service.base.BaseModel;
import com.springboot.template.core.service.base.BaseService;
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

    public ReadOnlyControllerImpl(BaseService<T, ID, M> baseService) {
        this.baseService = baseService;
    }

    public BaseService<T, ID, M> getBaseService() {
        return baseService;
    }

    @GetMapping(value = "{id}")
    @Override
    public ResponseEntity<M> findById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(getBaseService().findByID(id));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<M>> findAll(@RequestParam(defaultValue = "", required = false) String query) {
        return ResponseEntity.ok(getBaseService().findAll(query));
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<M>> findAll(@RequestParam(defaultValue = "", required = false) String query,
                                           @PageableDefault(Integer.MAX_VALUE) Pageable pageable) {
        return ResponseEntity.ok(getBaseService().findAll(query, pageable));
    }
}
