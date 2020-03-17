package com.springboot.template.storage;

import com.springboot.template.core.exception.base.BaseException;

public class FileNotFoundException extends BaseException {

    public FileNotFoundException(String message) {
        super(message);
    }
}
