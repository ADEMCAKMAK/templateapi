package com.springboot.template.core.authentication.service;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.service.base.IAuthorityService;
import com.exercises.springboot.core.authentication.service.dto.AuthorityDTO;
import com.exercises.springboot.core.repository.AuthorityRepository;
import com.exercises.springboot.core.service.base.BaseService;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class AuthorityService extends BaseService<Authority, Long, AuthorityDTO> implements IAuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        super(authorityRepository);
        this.authorityRepository = authorityRepository;
    }

}
