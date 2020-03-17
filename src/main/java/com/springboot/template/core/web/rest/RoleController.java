package com.springboot.template.core.web.rest;

import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.authentication.service.RoleService;
import com.exercises.springboot.core.authentication.service.dto.RoleDTO;
import com.exercises.springboot.core.web.rest.base.CrudController;
import com.exercises.springboot.core.web.rest.base.IRoleController;
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
