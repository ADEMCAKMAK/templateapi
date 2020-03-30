package com.springboot.template.core.web.rest;

import com.springboot.template.core.security.jwt.JWTConfigurer;
import com.springboot.template.core.service.UserService;
import com.springboot.template.core.web.rest.model.JWTToken;
import com.springboot.template.core.web.rest.model.LoginModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;


@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager,
                           UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginModel loginModel,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginModel.getUsername(), loginModel.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String authToken = this.userService.generateToken(authentication);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, authToken);
            return ResponseEntity.ok(new JWTToken(authToken));
        } catch (AuthenticationException ae) {
            return new ResponseEntity<>(
                    Collections.singletonMap("AuthenticationException", ae.getLocalizedMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
