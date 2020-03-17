package com.springboot.template.core.web.rest.base;

import com.exercises.springboot.core.entity.base.BaseEntity;
import com.exercises.springboot.core.entity.base.HistoryEntity;
import com.exercises.springboot.core.service.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IReadOnlyController<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseDTO<ID>> {

    Page<M> findAll(String query, Pageable pageable);

    M findById(ID id);

    List<HistoryEntity<T>> findChanges(ID id);

}
