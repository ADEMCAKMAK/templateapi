package com.springboot.template.core.authentication.service;

import java.util.*;

import com.exercises.springboot.core.authentication.service.dto.*;
import com.exercises.springboot.core.exception.EntityNotFoundException;
import com.exercises.springboot.core.security.jwt.TokenProvider;
import com.exercises.springboot.mail.MailService;
import com.google.common.collect.Sets;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.authentication.entity.User;
import com.exercises.springboot.core.authentication.service.base.IUserService;
import com.exercises.springboot.core.exception.base.BaseException;
import com.exercises.springboot.core.repository.AuthorityRepository;
import com.exercises.springboot.core.repository.RoleRepository;
import com.exercises.springboot.core.repository.UserRepository;
import com.exercises.springboot.core.service.base.BaseService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@Service
public class UserService extends BaseService<User, String, UserDTO> implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MailService mailService;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AuthorityRepository authorityRepository,
                       PasswordEncoder passwordEncoder,
                       TokenProvider tokenProvider,
                       MailService mailService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
    }

    @Override
    public String generateToken(Authentication authentication) {
        return this.tokenProvider.generateToken(authentication);
    }

    @Override
    @Transactional
    public void register(RegisterModel registerModel) {

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(registerModel.getFirstname());
        userDTO.setLastname(registerModel.getLastname());
        userDTO.setUsername(registerModel.getUsername());
        userDTO.setPassword(registerModel.getPassword());
        userDTO.setEmail(registerModel.getEmail());
        userDTO.setActivated(true);
        userDTO.setRoleCodes(Sets.newHashSet(Collections.singletonList("USER")));
        userDTO.setAuthorityCodes(Sets.newHashSet(Collections.singletonList("USER")));
        this.create(userDTO);
    }

    @Override
    @Transactional
    public UserDTO updateUserInfo(UserUpdateModel userUpdateModel) {
        UserDTO optionalUser = this.userRepository.findById(userUpdateModel.getId())
                .map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName(), "id", userUpdateModel.getId()));
        optionalUser.setFirstname(userUpdateModel.getFirstname());
        optionalUser.setLastname(userUpdateModel.getLastname());
        optionalUser.setUsername(userUpdateModel.getUsername());
        optionalUser.setEmail(userUpdateModel.getEmail());
        optionalUser.setPhoneNumber(userUpdateModel.getPhoneNumber());

        return this.update(optionalUser.getId(), optionalUser);
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO model) {

        Optional<User> optionalUser = this.userRepository.findByUsername(model.getUsername());
        if (optionalUser.isPresent()) {
            throw new BaseException("Username is already in use");
        }
        optionalUser = this.userRepository.findByEmail(model.getEmail());
        if (optionalUser.isPresent()) {
            throw new BaseException("Email is already in use");
        }
        this.setRoleAndAuthority(model);
        return super.create(model);
    }

    @Override
    @Transactional
    public UserDTO update(String id, UserDTO model) {

        Optional<User> optionalUser = this.userRepository.findByUsername(model.getUsername());
        if (optionalUser.isPresent() && !Objects.equals(optionalUser.get().getId(), model.getId())) {
            throw new BaseException("Username is already in use");
        }
        optionalUser = this.userRepository.findByEmail(model.getEmail());
        if (optionalUser.isPresent() && !Objects.equals(optionalUser.get().getId(), model.getId())) {
            throw new BaseException("Email is already in use");
        }
        this.setRoleAndAuthority(model);
        return super.update(id, model);
    }

    @Override
    @Transactional
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordModel forgotPasswordModel) throws MessagingException {
        UserDTO userDTO = this.userRepository.findByEmail(forgotPasswordModel.getEmail())
                .map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName(), "email", forgotPasswordModel.getEmail()));
        // generate token and save
        String resetToken = this.tokenProvider.generateResetToken(userDTO);
        UUID uuid = UUID.randomUUID();
        userDTO.setResetToken(resetToken);
        userDTO.setResetTokenKey(uuid.toString());
        this.mailService.send(forgotPasswordModel.getEmail(), "test mail", "<p>" + this.mailHtmlContent(uuid.toString())+"</p>");
        this.update(userDTO.getId(), userDTO);
    }
    
    private String mailHtmlContent(String token){
        return "<html>" +
                "<body>" +
                "<h>Merhabalar,</h>" +
                "<p>Parola değişikliği için, geçici olarak sizin için bir jeton ürettik.</p>" +
                "<p>Aşağıdaki linki kullanarak geçici jeton ile parolanızı değiştirebilirsiniz.</p>" +
                "<p>token :"+token+"</p>" +
                "<a href=\"https://www.w3schools.com\">Visit W3Schools.com!</a>" +
                "</body>"+
                "</html>";
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordModel resetPasswordModel) {
        UserDTO userDTO = this.userRepository.findByResetTokenKey(resetPasswordModel.getResetTokenKey())
                .map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName(), "reset_token_key", resetPasswordModel.getResetTokenKey()));

        if (this.tokenProvider.validateResetToken(userDTO.getResetToken())) {
            if (Objects.equals(resetPasswordModel.getNewPassword(), resetPasswordModel.getNewPasswordConfirm())){
                userDTO.setPassword(this.passwordEncoder.encode(resetPasswordModel.getNewPassword()));
                userDTO.setResetToken(null);
                userDTO.setResetTokenKey(null);
                this.update(userDTO.getId(), userDTO);
            }
            else{
                throw new BaseException("Check new password!.");
            }
        }
    }

    @Override
    public void updatePassword(ResetPasswordModel resetPasswordModel) {
        UserDTO userDTO = this.userRepository.findById(resetPasswordModel.getId())
                .map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName(), "id", resetPasswordModel.getId()));

        if (this.passwordEncoder.matches(resetPasswordModel.getOldPassword(), userDTO.getPassword())) {
            if (Objects.equals(resetPasswordModel.getNewPassword(), resetPasswordModel.getNewPasswordConfirm())){
                userDTO.setPassword(this.passwordEncoder.encode(resetPasswordModel.getNewPassword()));
                this.update(userDTO.getId(), userDTO);
            }
            else{
                throw new BaseException("Check passwords!.");
            }
        }
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(this::convertTo);
    }

    private void setRoleAndAuthority(UserDTO model) {
        List<Role> roles = this.roleRepository.findAll();
        List<Authority> authorities = this.authorityRepository.findAll();
        model.setRoles(roles.stream()
                .filter(role -> model.getRoleCodes().contains(role.getCode()))
                .collect(Collectors.toSet()));
        model.setAuthorities(authorities.stream()
                .filter(authority -> model.getAuthorityCodes().contains(authority.getCode()))
                .collect(Collectors.toSet()));
    }

}
