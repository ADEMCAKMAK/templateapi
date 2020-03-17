package com.springboot.template.core.authentication.service;

import com.springboot.template.core.authentication.entity.Authority;
import com.springboot.template.core.authentication.service.base.IAuthorityService;
import com.springboot.template.core.authentication.service.dto.AuthorityDTO;
import com.springboot.template.core.repository.AuthorityRepository;
import com.springboot.template.core.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class AuthorityService extends BaseService<Authority, Long, AuthorityDTO> implements IAuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        super(authorityRepository);
        this.authorityRepository = authorityRepository;
    }

}
