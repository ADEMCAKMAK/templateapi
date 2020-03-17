package com.springboot.template.core.service.base;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.entity.base.HistoryEntity;
import com.springboot.template.core.service.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface IBaseService<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseDTO<ID>> {

    Page<M> findAll(String query, Pageable pageable);

    List<M> findAll(String query);

    Optional<M> findById(ID id);

    M _findById(ID id);

    M create(M model);

    M update(ID id, M model);

    void delete(ID id);

    List<HistoryEntity<T>> findChanges(ID id);

    Boolean isEntityClassAudited();
}
