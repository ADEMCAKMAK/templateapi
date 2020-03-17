package com.springboot.template.core.authentication.service;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.authentication.service.base.IRoleService;
import com.exercises.springboot.core.authentication.service.dto.RoleDTO;
import com.exercises.springboot.core.repository.AuthorityRepository;
import com.exercises.springboot.core.repository.RoleRepository;
import com.exercises.springboot.core.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class RoleService extends BaseService<Role, Long, RoleDTO> implements IRoleService {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    public RoleService(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public RoleDTO create(RoleDTO model) {
        List<Authority> authorities = this.authorityRepository.findAll();
        model.setAuthorities(authorities.stream()
                .filter(authority -> model.getAuthorityCodes().contains(authority.getCode()))
                .collect(Collectors.toSet()));
        return super.create(model);
    }

    @Override
    public RoleDTO update(Long aLong, RoleDTO model) {
        List<Authority> authorities = this.authorityRepository.findAll();
        model.setAuthorities(authorities.stream()
                .filter(authority -> model.getAuthorityCodes().contains(authority.getCode()))
                .collect(Collectors.toSet()));
        return super.update(aLong, model);
    }
}
