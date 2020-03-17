package com.springboot.template.core.service;

import com.exercises.springboot.core.entity.TemplateUser;
import com.exercises.springboot.core.repository.TemplateUserRepository;
import com.exercises.springboot.core.repository.base.IBaseRepository;
import com.exercises.springboot.core.service.base.BaseService;
import com.exercises.springboot.core.service.base.IBaseService;
import com.exercises.springboot.core.service.base.ITemplateUserService;
import com.exercises.springboot.core.service.dto.TemplateUserDTO;
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
