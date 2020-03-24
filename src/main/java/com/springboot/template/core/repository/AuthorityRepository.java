package com.springboot.template.core.repository;

import com.springboot.template.core.entity.Authority;
import com.springboot.template.core.repository.base.IBaseRepository;

import java.util.Optional;

public interface AuthorityRepository extends IBaseRepository<Authority, Long> {

    Optional<Authority> findOneByCode(String code);
}
