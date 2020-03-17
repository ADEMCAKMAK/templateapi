package com.springboot.template.storage;

import com.exercises.springboot.core.exception.base.BaseException;

public class FileNotFoundException extends BaseException {

    public FileNotFoundException(String message) {
        super(message);
    }
}
