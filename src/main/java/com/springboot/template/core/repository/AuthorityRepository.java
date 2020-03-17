package com.springboot.template.core.repository;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.repository.base.IBaseRepository;

import java.util.Optional;

public interface AuthorityRepository extends IBaseRepository<Authority, Long> {

    Optional<Authority> findOneByCode(String code);
}
