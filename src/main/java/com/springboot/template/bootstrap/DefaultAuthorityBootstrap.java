package com.springboot.template.bootstrap;

import com.springboot.template.core.service.AuthorityService;
import com.springboot.template.core.service.dto.AuthorityDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Order(1)
public class DefaultAuthorityBootstrap implements CommandLineRunner {

    private final BootstrapProperties bootstrapProperties;

    private final AuthorityService authorityService;

    public DefaultAuthorityBootstrap(BootstrapProperties bootstrapProperties, AuthorityService authorityService) {
        this.bootstrapProperties = bootstrapProperties;
        this.authorityService = authorityService;
    }

    @Transactional
    @Override
    public void run(String... args) {

        if (!this.bootstrapProperties.getBootstrap().isAuthorityActive())
            return;

        AuthorityDTO[] defaultAuthorities = new AuthorityDTO[]{
                new AuthorityDTO("ADMIN", "ADMIN"),
                new AuthorityDTO("USER", "USER")};

        for (AuthorityDTO defaultAuthority : defaultAuthorities) {
            Optional<AuthorityDTO> authorityOptional = authorityService.findOneByCode(defaultAuthority.getCode());
            if (!authorityOptional.isPresent())
                authorityService.create(defaultAuthority);
        }
    }
}
