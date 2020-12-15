package com.spring.template.backend.test;

import com.spring.template.core.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {
}
