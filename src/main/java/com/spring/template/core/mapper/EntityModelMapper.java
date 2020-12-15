package com.spring.template.core.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface EntityModelMapper<T,M> {

    M fromEntityToModel(final T entity);

    @InheritInverseConfiguration
    T fromModelToEntity(final M model);

    default Optional<T> fromModelToEntity(final Optional<M> optModel){
        return optModel.isPresent() ? optModel.map(this::fromModelToEntity) : Optional.empty();
    }

    default Optional<M> fromEntityToModel(final Optional<T> optEntity){
        return optEntity.isPresent() ? optEntity.map(this::fromEntityToModel) : Optional.empty();
    }

    default List<T> fromModelToEntity(final List<M> models){
        return models.parallelStream().map(this::fromModelToEntity).collect(Collectors.toList());
    }

    default List<M> fromEntityToModel(final List<T> entities){
        return entities.parallelStream().map(this::fromEntityToModel).collect(Collectors.toList());
    }

    default Page<M> fromEntityToModel(final Page<T> entities){
        return entities.map(this::fromEntityToModel);
    }
}
