package com.spring.template.core.service.base;

import com.spring.template.core.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>> {

    Page<M> findAll(final String query, final Pageable pageable);

    List<M> findAll(final String query);

    Optional<M> optionalFindById(final ID id);

    M findById(final ID id);

    M create(M model);

    M update(M model);

    void delete(final ID id);

    void softDelete(final ID id);
}
