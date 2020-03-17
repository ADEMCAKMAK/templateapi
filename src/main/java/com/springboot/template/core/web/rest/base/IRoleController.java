package com.springboot.template.core.web.rest.base;

import com.springboot.template.core.authentication.entity.Role;
import com.springboot.template.core.authentication.service.dto.RoleDTO;

public interface IRoleController extends ICrudController<Role, Long, RoleDTO> {


}
