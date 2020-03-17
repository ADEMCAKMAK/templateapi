package com.springboot.template.core.repository;

import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.repository.base.IBaseRepository;

import java.util.Optional;

public interface RoleRepository extends IBaseRepository<Role, Long> {

    Optional<Role> findOneByCode(String code);
}
