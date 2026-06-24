package com.spring.template.backend.test;

import com.spring.template.core.service.base.BaseService;
import com.spring.template.core.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonService extends BaseServiceImpl<Person, Long>
        implements BaseService<Person, Long> {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }
}
