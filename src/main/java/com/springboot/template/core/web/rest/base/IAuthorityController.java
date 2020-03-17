package com.springboot.template.core.web.rest.base;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.service.dto.AuthorityDTO;

public interface IAuthorityController extends ICrudController<Authority, Long, AuthorityDTO> {
}
