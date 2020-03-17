package com.springboot.template.core.configuration;

import com.springboot.template.core.repository.base.BaseRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.springboot.template.core.repository",
        repositoryBaseClass = BaseRepository.class)
@EnableJpaAuditing
public class PersistenceConfiguration {

}
