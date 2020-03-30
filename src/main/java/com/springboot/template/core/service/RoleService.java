package com.springboot.template.core.service;

import com.springboot.template.core.entity.Role;
import com.springboot.template.core.repository.RoleRepository;
import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.service.base.IRoleService;
import com.springboot.template.core.service.dto.RoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class RoleService extends BaseService<Role, Long, RoleDTO> implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<RoleDTO> findOneByCode(String code) {
        return this.roleRepository.findOneByCode(code).map(this::convertTo);
    }
}
