package com.springboot.template.core.service;

import com.springboot.template.core.entity.Authority;
import com.springboot.template.core.repository.AuthorityRepository;
import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.service.base.IAuthorityService;
import com.springboot.template.core.service.dto.AuthorityDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class AuthorityService extends BaseService<Authority, Long, AuthorityDTO> implements IAuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        super(authorityRepository);
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Optional<AuthorityDTO> findOneByCode(String code) {
        return this.authorityRepository.findOneByCode(code).map(this::convertTo);
    }
}
