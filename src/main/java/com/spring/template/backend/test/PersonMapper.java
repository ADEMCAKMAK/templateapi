package com.spring.template.backend.test;

import com.spring.template.core.mapper.EntityModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends EntityModelMapper<Person, PersonModel> {

}
