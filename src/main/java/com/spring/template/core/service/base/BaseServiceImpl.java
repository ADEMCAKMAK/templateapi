package com.spring.template.core.service.base;

import com.spring.template.core.entity.base.BaseEntity;
import com.spring.template.core.repository.base.BaseRepository;
import java.io.Serializable;
import java.util.Objects;


public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseService<T, ID> {

    private final BaseRepository<T, ID> repository;

    protected BaseServiceImpl(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public BaseRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    public void delete(final ID id, boolean force) {
        Boolean found = getRepository().existsById(id);
        if( Objects.equals(found, Boolean.TRUE) && Objects.equals(force, Boolean.FALSE) ) {
            T entity = getRepository().getReferenceById(id);
            entity.setDeleted(Boolean.TRUE);
            getRepository().save(entity);
        } else if (Objects.equals(found, Boolean.TRUE) && Objects.equals(force, Boolean.TRUE)) {
            getRepository().deleteById(id);
        }
    }
}
