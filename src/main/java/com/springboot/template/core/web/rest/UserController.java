package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.User;
import com.springboot.template.core.service.UserService;
import com.springboot.template.core.service.dto.UserDTO;
import com.springboot.template.core.web.rest.base.CrudController;
import com.springboot.template.core.web.rest.base.IUserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController extends CrudController<User, String, UserDTO> implements IUserController {

    private UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @Override
    public UserDTO create(@Valid UserDTO model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDTO update(String s, @Valid UserDTO model) {
        throw new UnsupportedOperationException();
    }
}
