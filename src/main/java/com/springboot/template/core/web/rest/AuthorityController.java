package com.springboot.template.core.web.rest;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.service.AuthorityService;
import com.exercises.springboot.core.authentication.service.dto.AuthorityDTO;
import com.exercises.springboot.core.web.rest.base.CrudController;
import com.exercises.springboot.core.web.rest.base.IAuthorityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authority")
public class AuthorityController
        extends CrudController<Authority, Long, AuthorityDTO>
        implements IAuthorityController {

    private AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        super(authorityService);
        this.authorityService = authorityService;
    }
}
