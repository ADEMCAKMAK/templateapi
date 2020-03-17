package com.springboot.template.core.authentication.service.base;

import com.springboot.template.core.authentication.entity.Role;
import com.springboot.template.core.authentication.service.dto.RoleDTO;
import com.springboot.template.core.service.base.IBaseService;

public interface IRoleService extends IBaseService<Role, Long, RoleDTO> {
}
