package com.springboot.template.core.mapper;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EntityModelMapper<T, M> {

    T fromModelToEntity(final M model);

    Optional<T> fromModelToEntity(final Optional<M> optModel);

    M fromEntityToModel(final T entity);

    Optional<M> fromEntityToModel(final Optional<T> optEntity);

    List<T> fromModelToEntity(final List<M> models);

    List<M> fromEntityToModel(final List<T> entities);

    Page<M> fromEntityToModel(final Page<T> entities);
}
