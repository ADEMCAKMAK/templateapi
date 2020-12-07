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

    public EntityModelMapper<T, M> getEntityModelMapper() {
        return entityModelMapper;
    }

    @Override
    public List<M> findAll(String query) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findAll(query));
    }

    @Override
    public Page<M> findAll(String query, Pageable pageable) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findAll(query, pageable));
    }

    @Override
    public Optional<M> findById(ID id) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findById(id));
    }

    @Override
    public M findByID(ID id) {
        return findById(id).orElseThrow(
                ()-> new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+id)
        );
    }

    @Override
    public M create(M model) {
        T saveEntity = getEntityModelMapper().fromModelToEntity(model);
        return getEntityModelMapper().fromEntityToModel(getRepository().save(saveEntity));
    }

    @Override
    public M update(M model) {
        return update(model, false);
    }

    @Override
    public M update(M model, Boolean created) {

        if( created ) return create(model);

        Boolean found = getRepository().existsById(model.getId());

        if( found )
            return create(model);
        else
           throw new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+model.getId());
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
