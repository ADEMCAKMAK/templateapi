package com.spring.template.core.service.base;

import com.spring.template.core.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    void delete(final ID id, boolean force);

    default void delete(final ID id){
        delete(id, false);
    }

}
