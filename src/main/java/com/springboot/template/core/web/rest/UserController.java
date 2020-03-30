package com.springboot.template.core.web.rest;

import com.springboot.template.core.entity.User;
import com.springboot.template.core.service.UserService;
import com.springboot.template.core.service.dto.UserDTO;
import com.springboot.template.core.web.rest.base.IUserController;
import com.springboot.template.core.web.rest.base.ReadOnlyController;
import com.springboot.template.core.web.rest.model.ForgotPasswordModel;
import com.springboot.template.core.web.rest.model.RegisterModel;
import com.springboot.template.core.web.rest.model.ResetPasswordModel;
import com.springboot.template.core.web.rest.model.UserUpdateModel;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController extends ReadOnlyController<User, String, UserDTO> implements IUserController {

    private UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterModel registerModel,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        this.userService.register(registerModel);
    }

    @PutMapping("/account/update")
    public UserDTO updateUserInfo(@Valid @RequestBody UserUpdateModel userUpdateModel,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        return this.userService.updateUserInfo(userUpdateModel);
    }

    @PutMapping("/account/forgot-password")
    public void forgotPassword(@Valid @RequestBody ForgotPasswordModel forgotPasswordModel,
                               HttpServletRequest request,
                               HttpServletResponse response) throws MessagingException {
        this.userService.forgotPassword(forgotPasswordModel);
    }

    @PutMapping("/account/reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordModel resetPasswordModel,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        this.userService.resetPassword(resetPasswordModel);
    }

    @PutMapping("/account/update-password")
    public void updatePassword(@Valid @RequestBody ResetPasswordModel resetPasswordModel,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        this.userService.resetPassword(resetPasswordModel);
    }

}
