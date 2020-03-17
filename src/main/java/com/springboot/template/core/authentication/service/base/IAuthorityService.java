package com.springboot.template.core.authentication.service.base;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.service.dto.AuthorityDTO;
import com.exercises.springboot.core.service.base.IBaseService;

public interface IAuthorityService extends IBaseService<Authority, Long, AuthorityDTO> {
}
