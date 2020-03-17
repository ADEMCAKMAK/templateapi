package com.springboot.template.core.web.rest.base;

import com.springboot.template.core.entity.TemplateUser;
import com.springboot.template.core.service.dto.TemplateUserDTO;

public interface ITemplateUserController extends ICrudController<TemplateUser, String, TemplateUserDTO> {
}
