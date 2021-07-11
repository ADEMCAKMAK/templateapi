package com.spring.template.core.service.base;

import com.spring.template.core.mapper.EntityModelMapper;
import com.spring.template.core.entity.base.BaseEntity;
import com.spring.template.core.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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

    public EntityModelMapper<T, M> getEntityModelMapper() {
        return entityModelMapper;
    }

    @Override
    public List<M> findAll(final String query) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findAll(query));
    }

    @Override
    public Page<M> findAll(final String query, final Pageable pageable) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findAll(query, pageable));
    }

    @Override
    public Optional<M> optionalFindById(final ID id) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findById(id));
    }

    @Override
    public M findById(final ID id) {
        return optionalFindById(id).orElseThrow(
                ()-> new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+id)
        );
    }

    @Override
    @Transactional
    public M create(M model) {
        T saveEntity = getEntityModelMapper().fromModelToEntity(model);
        return getEntityModelMapper().fromEntityToModel(getRepository().save(saveEntity));
    }

    @Override
    @Transactional
    public M update(M model) {
        Boolean found = getRepository().existsById(model.getId());
        if( Objects.equals(found, Boolean.TRUE) ) {
            T updateEntity = getEntityModelMapper().updateFromModelToEntity(model, getRepository().getOne(model.getId()));
            return getEntityModelMapper().fromEntityToModel(getRepository().saveAndFlush(updateEntity));
        }
        else
            throw new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+model.getId());
    }

    @Override
    @Transactional
    public void delete(final ID id) {
        getRepository().deleteById(id);
    }

    @Override
    @Transactional
    public void softDelete(final ID id) {
        Boolean found = getRepository().existsById(id);
        if( Objects.equals(found, Boolean.TRUE) ) {
            T deleteEntity = getRepository().getOne(id);
            deleteEntity.setDeleted(Boolean.TRUE);
            getRepository().save(deleteEntity);
        }
        else
            throw new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+id);
    }
}
