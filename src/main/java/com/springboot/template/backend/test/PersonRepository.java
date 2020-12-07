package com.springboot.template.backend.test;

import com.springboot.template.core.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {
}
