package com.springboot.template.backend.test;

import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.web.rest.CrudController;
import com.springboot.template.core.web.rest.CrudControllerImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "person")
public class PersonController
        extends CrudControllerImpl<Person, Long, PersonModel>
        implements CrudController<Person, Long, PersonModel> {
    public PersonController(BaseService<Person, Long, PersonModel> baseService) {
        super(baseService);
    }
}
