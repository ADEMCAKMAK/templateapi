package com.springboot.template.core.authentication.service.base;

import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.authentication.service.dto.RoleDTO;
import com.exercises.springboot.core.service.base.IBaseService;

public interface IRoleService extends IBaseService<Role, Long, RoleDTO> {
}
