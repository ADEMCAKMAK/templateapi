package com.springboot.template.core.service.base;

import com.springboot.template.core.entity.User;
import com.springboot.template.core.service.dto.*;
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