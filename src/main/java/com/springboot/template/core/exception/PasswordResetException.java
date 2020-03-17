package com.springboot.template.core.exception;

import com.springboot.template.core.exception.base.BaseException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PasswordResetException extends BaseException {

    public PasswordResetException(String message) {
        super(message);
    }
}
