package com.springboot.template.core.repository;

import com.springboot.template.core.authentication.entity.Role;
import com.springboot.template.core.repository.base.IBaseRepository;

import java.util.Optional;

public interface RoleRepository extends IBaseRepository<Role, Long> {

    Optional<Role> findOneByCode(String code);
}
