package com.springboot.template.core.service.base;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.mapper.EntityModelMapper;
import com.springboot.template.core.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseModel<ID>>
        implements BaseService<T, ID, M> {

    private final BaseRepository<T, ID> repository;
    private final EntityModelMapper<T, M> entityModelMapper;

    protected BaseServiceImpl(BaseRepository<T, ID> repository, EntityModelMapper<T, M> entityModelMapper) {
        this.repository = repository;
        this.entityModelMapper = entityModelMapper;
    }

    public BaseRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    public List<T> findAll(String query) {
        return getRepository().findAll(query);
    }

    @Override
    public Page<T> findAll(String query, Pageable pageable) {
        return getRepository().findAll(query, pageable);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T create(T model) {
        return getRepository().save(model);
    }

    @Override
    public T update(T model) {
        return update(model, false);
    }

    @Override
    public T update(T model, Boolean created) {

        if( created ) return create(model);

        Boolean found = getRepository().existsById(model.getId());

        if( found )
            return getRepository().save(model);
        else
           throw new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+model.getId().toString());
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
