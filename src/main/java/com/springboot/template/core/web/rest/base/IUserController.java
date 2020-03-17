package com.springboot.template.core.web.rest.base;

import com.exercises.springboot.core.authentication.entity.User;
import com.exercises.springboot.core.authentication.service.dto.UserDTO;

public interface IUserController extends ICrudController<User, String, UserDTO> {
}
