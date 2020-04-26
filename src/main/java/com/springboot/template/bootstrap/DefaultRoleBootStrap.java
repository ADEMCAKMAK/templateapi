package com.springboot.template.bootstrap;

import com.springboot.template.core.service.AuthorityService;
import com.springboot.template.core.service.RoleService;
import com.springboot.template.core.service.dto.AuthorityDTO;
import com.springboot.template.core.service.dto.RoleDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Order(2)
public class DefaultRoleBootStrap implements CommandLineRunner {

    private final BootstrapProperties bootstrapProperties;
    private final AuthorityService authorityService;
    private final RoleService roleService;

    public DefaultRoleBootStrap(BootstrapProperties bootstrapProperties,
                                AuthorityService authorityService,
                                RoleService roleService) {
        this.bootstrapProperties = bootstrapProperties;
        this.authorityService = authorityService;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void run(String... args) {

        if (!this.bootstrapProperties.getBootstrap().isRoleActive())
            return;

        String[] defaultAuthoritiesCodes = new String[]{"ADMIN", "USER"};
        List<AuthorityDTO> defaultAuthorities = new ArrayList<>();
        RoleDTO[] defaultRoles = new RoleDTO[]{
                new RoleDTO("ADMIN", "ADMIN"),
                new RoleDTO("USER", "USER")};

        for (String defaultAuthoritiesCode : defaultAuthoritiesCodes) {
            Optional<AuthorityDTO> authorityOptional = authorityService.findOneByCode(defaultAuthoritiesCode);
            authorityOptional.ifPresent(defaultAuthorities::add);
            authorityOptional.ifPresent(authorityDTO -> {
                if (authorityDTO.getCode().equals("USER"))
                    defaultRoles[1].setAuthorities(Stream.of(authorityOptional.get()).collect(Collectors.toSet()));
            });
        }

        defaultRoles[0].setAuthorities(new HashSet<>(defaultAuthorities));

        for (RoleDTO defaultRole : defaultRoles) {
            Optional<RoleDTO> roleOptional = roleService.findOneByCode(defaultRole.getCode());
            if (!roleOptional.isPresent())
                roleService.create(defaultRole);
        }
    }

}
