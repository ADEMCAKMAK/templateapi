package com.spring.template.backend.test;

import com.spring.template.core.service.base.BaseService;
import com.spring.template.core.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonService
        extends BaseServiceImpl<Person, Long, PersonModel>
        implements BaseService<Person, Long, PersonModel> {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        super(personRepository, personMapper);
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }
}
