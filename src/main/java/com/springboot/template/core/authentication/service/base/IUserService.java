package com.springboot.template.core.authentication.service.base;

import com.exercises.springboot.core.authentication.entity.User;
import com.exercises.springboot.core.authentication.service.dto.*;
import com.exercises.springboot.core.service.base.IBaseService;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.util.Optional;

public interface IUserService extends IBaseService<User, String, UserDTO> {

    String generateToken(Authentication authentication);
    Optional<UserDTO> findByUsername(String username);
    void register(RegisterModel registerModel);
    UserDTO updateUserInfo(UserUpdateModel userUpdateModel);
    void resetPassword(ResetPasswordModel resetPasswordModel);
    void updatePassword(ResetPasswordModel resetPasswordModel);
    void forgotPassword(ForgotPasswordModel forgotPasswordModel) throws MessagingException;
}