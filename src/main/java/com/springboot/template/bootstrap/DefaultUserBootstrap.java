package com.springboot.template.bootstrap;

import com.springboot.template.core.service.AuthorityService;
import com.springboot.template.core.service.RoleService;
import com.springboot.template.core.service.UserService;
import com.springboot.template.core.service.dto.AuthorityDTO;
import com.springboot.template.core.service.dto.RoleDTO;
import com.springboot.template.core.web.rest.model.RegisterModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Order(3)
public class DefaultUserBootstrap implements CommandLineRunner {

    private final BootstrapProperties bootstrapProperties;
    private final AuthorityService authorityService;
    private final RoleService roleService;
    private final UserService userService;

    public DefaultUserBootstrap(BootstrapProperties bootstrapProperties,
                                AuthorityService authorityService,
                                RoleService roleService,
                                UserService userService) {
        this.bootstrapProperties = bootstrapProperties;
        this.authorityService = authorityService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void run(String... args) {

        if (!this.bootstrapProperties.getBootstrap().isUserActive())
            return;

        String[] defaultCodes = new String[]{"ADMIN", "USER"};
        List<AuthorityDTO> defaultAuthorities = new ArrayList<>();
        List<RoleDTO> defaultRoles = new ArrayList<>();

        for (String defaultCode : defaultCodes) {
            Optional<AuthorityDTO> authorityOptional = authorityService.findOneByCode(defaultCode);
            Optional<RoleDTO> roleOptional = roleService.findOneByCode(defaultCode);
            authorityOptional.ifPresent(defaultAuthorities::add);
            roleOptional.ifPresent(defaultRoles::add);
        }

        if (!this.userService.findByUsername("admin").isPresent()) {
            RegisterModel registerModel = new RegisterModel();
            registerModel.setUsername("admin");
            registerModel.setPassword("admin");
            registerModel.setFirstname("ADMIN");
            registerModel.setLastname("ADMIN");
            registerModel.setEmail("admin@mail.com.tr");
            registerModel.setRoles(new HashSet<>(defaultRoles));
            registerModel.setAuthorities(new HashSet<>(defaultAuthorities));
            this.userService.register(registerModel);
        }

        if (!this.userService.findByUsername("user").isPresent()) {
            RegisterModel registerModel = new RegisterModel();
            registerModel.setUsername("user");
            registerModel.setPassword("user");
            registerModel.setFirstname("USER");
            registerModel.setLastname("USER");
            registerModel.setEmail("user@mail.com.tr");
            registerModel.setRoles(defaultRoles.stream().filter(roleDTO -> roleDTO.getCode().equals("USER")).collect(Collectors.toSet()));
            registerModel.setAuthorities(defaultAuthorities.stream().filter(authorityDTO -> authorityDTO.getCode().equals("USER")).collect(Collectors.toSet()));
            this.userService.register(registerModel);
        }

    }
}
