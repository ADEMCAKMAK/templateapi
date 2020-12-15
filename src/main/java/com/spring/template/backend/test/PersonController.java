package com.spring.template.backend.test;

import com.spring.template.core.web.rest.CrudController;
import com.spring.template.core.web.rest.CrudControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController
        extends CrudControllerImpl<Person, Long, PersonModel>
        implements CrudController<Person, Long, PersonModel> {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        super(personService);
        this.personService = personService;
    }
}
