package com.springboot.template.core.configuration;

import com.springboot.template.core.entity.id.base.StringToPrimaryKeyConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToPrimaryKeyConverterFactory());
    }

}
