package com.springboot.template.core.service.base;

import com.springboot.template.core.entity.Authority;
import com.springboot.template.core.service.dto.AuthorityDTO;

import java.util.Optional;

public interface IAuthorityService extends IBaseService<Authority, Long, AuthorityDTO> {

    Optional<AuthorityDTO> findOneByCode(String code);
}
