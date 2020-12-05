package com.springboot.template.core.service.base;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseService<T, ID> {

    private final BaseRepository<T, ID> repository;

    protected BaseServiceImpl(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Page<T> findAll(String query, Pageable pageable) {

        if( StringUtils.hasLength(query) )
            return repository.findAll(pageable); // TODO added RsqlUtils or alternatives.
        else
            return repository.findAll(pageable);
    }

    @Override
    public List<T> findAll(String query) {

        if( StringUtils.hasLength(query) )
            return Collections.emptyList(); // TODO added RsqlUtils or alternatives.
        else
            return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T create(T model) {
        return repository.save(model);
    }

    @Override
    public T update(T model) {
        return update(model, false);
    }

    @Override
    public T update(T model, Boolean created) {

        if( created ) return create(model);

        Boolean found = repository.existsById(model.getId());

        if( found )
            return repository.save(model);
        else
           throw new EntityNotFoundException(this.getClass().getSimpleName()+" id: "+model.getId().toString());
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
