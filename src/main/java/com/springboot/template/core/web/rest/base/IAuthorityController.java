package com.springboot.template.core.web.rest.base;

import com.springboot.template.core.authentication.entity.Authority;
import com.springboot.template.core.authentication.service.dto.AuthorityDTO;

public interface IAuthorityController extends ICrudController<Authority, Long, AuthorityDTO> {
}
