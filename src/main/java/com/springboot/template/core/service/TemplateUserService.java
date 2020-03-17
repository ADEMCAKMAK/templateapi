package com.springboot.template.core.service;

import com.springboot.template.core.entity.TemplateUser;
import com.springboot.template.core.repository.TemplateUserRepository;
import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.service.base.ITemplateUserService;
import com.springboot.template.core.service.dto.TemplateUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class TemplateUserService
        extends BaseService<TemplateUser, String, TemplateUserDTO>
        implements ITemplateUserService {

    private final TemplateUserRepository templateUserRepository;

    public TemplateUserService(TemplateUserRepository templateUserRepository) {
        super(templateUserRepository);
        this.templateUserRepository = templateUserRepository;
    }

}
