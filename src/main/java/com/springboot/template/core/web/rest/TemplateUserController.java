package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.TemplateUser;
import com.springboot.template.core.service.TemplateUserService;
import com.springboot.template.core.service.dto.TemplateUserDTO;
import com.springboot.template.core.web.rest.base.CrudController;
import com.springboot.template.core.web.rest.base.ITemplateUserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template-user")
public class TemplateUserController
        extends CrudController<TemplateUser, String, TemplateUserDTO>
        implements ITemplateUserController {

    private final TemplateUserService templateUserService;

    public TemplateUserController(TemplateUserService templateUserService) {
        super(templateUserService);
        this.templateUserService = templateUserService;
    }
}
