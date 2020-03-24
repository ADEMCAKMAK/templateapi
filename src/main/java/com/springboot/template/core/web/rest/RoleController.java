package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.Role;
import com.springboot.template.core.service.RoleService;
import com.springboot.template.core.service.dto.RoleDTO;
import com.springboot.template.core.web.rest.base.CrudController;
import com.springboot.template.core.web.rest.base.IRoleController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends CrudController<Role, Long, RoleDTO> implements IRoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }
}
