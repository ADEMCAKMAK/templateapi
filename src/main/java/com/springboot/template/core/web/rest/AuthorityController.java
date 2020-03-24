package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.Authority;
import com.springboot.template.core.service.AuthorityService;
import com.springboot.template.core.service.dto.AuthorityDTO;
import com.springboot.template.core.web.rest.base.CrudController;
import com.springboot.template.core.web.rest.base.IAuthorityController;
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
