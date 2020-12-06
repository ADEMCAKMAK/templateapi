package com.springboot.template.core.mapper;

import java.util.List;

public interface EntityModelMapper<E, M> {

    E fromModelToEntity(final M model);

    M fromEntityToModel(final E entity);

    List<E> fromModelToEntity(final List<M> model);

    List<M> fromEntityToModel(final List<E> entity);
}
