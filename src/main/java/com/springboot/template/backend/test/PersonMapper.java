package com.springboot.template.backend.test;

import com.springboot.template.core.mapper.EntityModelMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper extends EntityModelMapper<Person,PersonModel> {

}
