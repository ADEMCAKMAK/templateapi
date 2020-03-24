package com.springboot.template.core.web.rest.base;

import com.springboot.template.core.entity.User;
import com.springboot.template.core.service.dto.UserDTO;

public interface IUserController extends ICrudController<User, String, UserDTO> {
}
