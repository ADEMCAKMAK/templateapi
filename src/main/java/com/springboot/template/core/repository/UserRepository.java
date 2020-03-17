package com.springboot.template.core.repository;

import com.exercises.springboot.core.authentication.entity.User;
import com.exercises.springboot.core.repository.base.IBaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends IBaseRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetTokenKey(String resetTokenKey);
}
