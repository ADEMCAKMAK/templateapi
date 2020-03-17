package com.springboot.template.core.web.rest.base;

import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.authentication.service.dto.RoleDTO;

public interface IRoleController extends ICrudController<Role, Long, RoleDTO> {


}
