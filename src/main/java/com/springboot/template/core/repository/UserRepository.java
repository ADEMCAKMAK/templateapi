package com.springboot.template.core.repository;

import com.springboot.template.core.entity.User;
import com.springboot.template.core.repository.base.IBaseRepository;

import java.util.Optional;

public interface UserRepository extends IBaseRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByResetUUID(String resetUUID);
}
