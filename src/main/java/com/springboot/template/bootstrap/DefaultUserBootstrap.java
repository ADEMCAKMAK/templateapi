package com.springboot.template.bootstrap;


import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.entity.Role;
import com.exercises.springboot.core.authentication.service.UserService;
import com.exercises.springboot.core.authentication.service.dto.UserDTO;
import com.exercises.springboot.core.repository.AuthorityRepository;
import com.exercises.springboot.core.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(1)
public class DefaultUserBootstrap implements CommandLineRunner {

    private final BootstrapProperties bootstrapProperties;

    private final AuthorityRepository authorityRepository;

    private final RoleRepository roleRepository;

    private final UserService userService;

    public DefaultUserBootstrap(BootstrapProperties bootstrapProperties,
                                AuthorityRepository authorityRepository,
                                RoleRepository roleRepository,
                                UserService userService) {
        this.bootstrapProperties = bootstrapProperties;
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void run(String... args) {

        if (!this.bootstrapProperties.getBootstrap().isUserActive())
            return;

        Authority[] defaultAuthorities = new Authority[]{new Authority("ADMIN", "ADMIN"), new Authority("USER", "USER")};
        Role[] defaultRoles = new Role[]{new Role("ADMIN", "ADMIN"), new Role("USER", "USER")};

        for (int i = 0, defaultAuthoritiesLength = defaultAuthorities.length; i < defaultAuthoritiesLength; i++) {
            Optional<Authority> authorityOptional = authorityRepository.findOneByCode(defaultAuthorities[i].getCode());
            if (!authorityOptional.isPresent())
                authorityRepository.save(defaultAuthorities[i]);
            else
                defaultAuthorities[i] = authorityOptional.get();
        }

        defaultRoles[0].setAuthorities(Arrays.stream(defaultAuthorities).collect(Collectors.toSet()));
        defaultRoles[1].setAuthorities(Stream.of(defaultAuthorities[1]).collect(Collectors.toSet()));

        for (Role defaultRole : defaultRoles) {
            Optional<Role> roleOptional = roleRepository.findOneByCode(defaultRole.getCode());
            if (!roleOptional.isPresent())
                roleRepository.save(defaultRole);
        }

        if (!this.userService.findByUsername("admin").isPresent()) {

            UserDTO managedUserModel = new UserDTO();
            managedUserModel.setUsername("admin");
            managedUserModel.setPassword("admin");
            managedUserModel.setFirstname("ADMIN");
            managedUserModel.setLastname("ADMIN");
            managedUserModel.setEmail("admin@mail.com.tr");
            managedUserModel.setRoleCodes(Stream.of("ADMIN").collect(Collectors.toSet()));
            this.userService.create(managedUserModel);
        }

    }
}
