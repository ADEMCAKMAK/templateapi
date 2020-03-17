package com.springboot.template.core.service.base;


import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.entity.base.HistoryEntity;
import com.springboot.template.core.exception.EntityNotFoundException;
import com.springboot.template.core.repository.base.IBaseRepository;
import com.springboot.template.core.service.dto.BaseDTO;
import com.springboot.template.core.service.helper.MapperHelper;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable, M extends BaseDTO<ID>>
        implements IBaseService<T, ID, M> {

    private final IBaseRepository<T, ID> repository;
    private final Class<? extends T> entityClass;
    private final Class<? extends ID> primaryKeyClass;
    private final Class<? extends M> modelClass;
    private final String entityClassName;

    @SuppressWarnings("unchecked")
    public BaseService(IBaseRepository<T, ID> repository) {
        this.repository = repository;
        Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        this.entityClass = (Class<T>) actualTypeArguments[0];
        this.primaryKeyClass = (Class<ID>) actualTypeArguments[1];
        this.modelClass = (Class<M>) actualTypeArguments[2];
        this.entityClassName = this.entityClass.getSimpleName();
    }

    @Override
    public Page<M> findAll(String query, Pageable pageable) {

        Page<? extends T> entities = this.repository.findAll(query, pageable);
        return entities.map(this::convertTo);
    }

    @Override
    public List<M> findAll(String query) {
        return this.convertTo(this.repository.findAll(query));
    }

    @Override
    public Optional<M> findById(ID id) {
        return this.repository.findById(id).map(this::convertTo);
    }


    @Override
    public M _findById(ID id) {
        return this.repository.findById(id).map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.entityClassName, "id", id.toString()));
    }

    @Override
    @Transactional
    public M create(M model) {
        T created = this.convertFrom(model);
        created = this.repository.save(created);
        return this.convertTo(created);
    }

    @Override
    @Transactional
    public M update(ID id, M model) {
        T updated = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.entityClassName, "id", id.toString()));
        MapperHelper.getMapper().map(model, updated);
        updated = this.repository.save(updated);
        return this.convertTo(updated);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Boolean isEntityClassAudited() {
        return this.repository.getAuditReader().isEntityClassAudited(this.entityClass);
    }

    @Override
    public List<HistoryEntity<T>> findChanges(ID id) {

        if( !this.isEntityClassAudited() )
            return Collections.emptyList();

        AuditQuery auditQuery = this.repository.getAuditReader().createQuery()
                .forRevisionsOfEntity(this.entityClass, false, true);

        return this.convertToHistoryEntity((List<Object[]>) auditQuery.add(AuditEntity.id().eq(id)).getResultList());
    }

    private List<HistoryEntity<T>> convertToHistoryEntity(List<Object[]> revisionEntities){

        List<HistoryEntity<T>> historyEntities = new ArrayList<>();

        for( Object[] objects : revisionEntities){
            historyEntities.add(
                    new HistoryEntity<>((T) objects[0], (DefaultRevisionEntity) objects[1], (RevisionType) objects[2])
            );
        }

        return historyEntities;
    }

    public M convertTo(T entity) {
        return MapperHelper.getMapper().map(entity, this.modelClass);
    }

    public T convertFrom(M model) {
        return MapperHelper.getMapper().map(model, this.entityClass);
    }

    public List<M> convertTo(List<T> entities) {
        return entities.parallelStream().map(this::convertTo).collect(Collectors.toList());
    }

    public List<T> convertFrom(List<M> models) {
        return models.parallelStream().map(this::convertFrom).collect(Collectors.toList());
    }

}
