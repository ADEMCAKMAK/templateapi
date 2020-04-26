package com.springboot.template.core.service;

import com.springboot.template.core.entity.User;
import com.springboot.template.core.exception.EntityNotFoundException;
import com.springboot.template.core.exception.base.BaseException;
import com.springboot.template.core.repository.UserRepository;
import com.springboot.template.core.security.jwt.TokenProvider;
import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.service.base.IUserService;
import com.springboot.template.core.service.dto.AuthorityDTO;
import com.springboot.template.core.service.dto.RoleDTO;
import com.springboot.template.core.service.dto.UserDTO;
import com.springboot.template.core.service.helper.MapperHelper;
import com.springboot.template.core.web.rest.model.ForgotPasswordModel;
import com.springboot.template.core.web.rest.model.RegisterModel;
import com.springboot.template.core.web.rest.model.ResetPasswordModel;
import com.springboot.template.core.web.rest.model.UserUpdateModel;
import com.springboot.template.mail.MailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Stream;


@Transactional(readOnly = true)
@Service
public class UserService extends BaseService<User, String, UserDTO> implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MailService mailService;
    private final AuthorityService authorityService;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       TokenProvider tokenProvider,
                       MailService mailService,
                       AuthorityService authorityService,
                       RoleService roleService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
        this.authorityService = authorityService;
        this.roleService = roleService;
    }

    @Override
    public String generateToken(Authentication authentication) {
        return this.tokenProvider.generateToken(authentication);
    }

    @Override
    @Transactional
    public void register(RegisterModel registerModel) {
        String[] defaultCodes = new String[]{"USER"};
        Set<RoleDTO> roleDTOS = new HashSet<>();
        Set<AuthorityDTO> authorityDTOS = new HashSet<>();

        for (String defaultCode : defaultCodes) {
            Optional<AuthorityDTO> authorityOptional = authorityService.findOneByCode(defaultCode);
            Optional<RoleDTO> roleOptional = roleService.findOneByCode(defaultCode);
            authorityOptional.ifPresent(authorityDTOS::add);
            roleOptional.ifPresent(roleDTOS::add);
        }
        registerModel.setRoles(roleDTOS);
        registerModel.setAuthorities(authorityDTOS);
        registerModel.setPassword(this.passwordEncoder.encode(registerModel.getPassword()));
        this.create(MapperHelper.getMapper().map(registerModel, UserDTO.class));
    }

    @Override
    @Transactional
    public UserDTO updateUserInfo(UserUpdateModel userUpdateModel) {
        UserDTO optionalUser = this.userRepository.findById(userUpdateModel.getId())
                .map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName(), "id", userUpdateModel.getId()));
        MapperHelper.getMapper().map(userUpdateModel, optionalUser);
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
        userDTO.setResetUUID(uuid.toString());
        this.mailService.send(forgotPasswordModel.getEmail(), "test mail", "<p>" + this.mailHtmlContent(uuid.toString()) + "</p>");
        this.update(userDTO.getId(), userDTO);
    }

    private String mailHtmlContent(String token) {
        return "<html>" +
                "<body>" +
                "<h>Merhabalar,</h>" +
                "<p>Parola değişikliği için, geçici olarak sizin için bir jeton ürettik.</p>" +
                "<p>Aşağıdaki linki kullanarak geçici jeton ile parolanızı değiştirebilirsiniz.</p>" +
                "<p>token :" + token + "</p>" +
                "<a href=\"https://www.w3schools.com\">Visit W3Schools.com!</a>" +
                "</body>" +
                "</html>";
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordModel resetPasswordModel) {
        UserDTO userDTO = this.userRepository.findByResetUUID(resetPasswordModel.getResetUUID())
                .map(this::convertTo)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName(), "reset_token_key", resetPasswordModel.getResetUUID()));

        if (this.tokenProvider.validateResetToken(userDTO.getResetToken())) {
            if (Objects.equals(resetPasswordModel.getNewPassword(), resetPasswordModel.getNewPasswordConfirm())) {
                userDTO.setPassword(this.passwordEncoder.encode(resetPasswordModel.getNewPassword()));
                userDTO.setResetToken(null);
                userDTO.setResetUUID(null);
                this.update(userDTO.getId(), userDTO);
            } else {
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
            if (Objects.equals(resetPasswordModel.getNewPassword(), resetPasswordModel.getNewPasswordConfirm())) {
                userDTO.setPassword(this.passwordEncoder.encode(resetPasswordModel.getNewPassword()));
                this.update(userDTO.getId(), userDTO);
            } else {
                throw new BaseException("Check passwords!.");
            }
        }
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(this::convertTo);
    }

}
