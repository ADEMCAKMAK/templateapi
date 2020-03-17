package com.springboot.template.core.web.rest.base;

import com.exercises.springboot.core.entity.TemplateUser;
import com.exercises.springboot.core.service.dto.TemplateUserDTO;

public interface ITemplateUserController extends ICrudController<TemplateUser, String, TemplateUserDTO> {
}
