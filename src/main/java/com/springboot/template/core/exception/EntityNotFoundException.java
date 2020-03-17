package com.springboot.template.core.exception;


import com.springboot.template.core.exception.base.BaseException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EntityNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String entityName, String field, String data) {
        super(String.format("No %s data found with %s: [%s]", entityName, field, data));
    }

}
