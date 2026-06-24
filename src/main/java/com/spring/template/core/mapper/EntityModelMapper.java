package com.spring.template.core.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface EntityModelMapper<T,M> {

    M fromEntityToModel(final T entity);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    T updateFromModelToEntity(M model, @MappingTarget T entity);

    @InheritInverseConfiguration
    T fromModelToEntity(final M model);
}
