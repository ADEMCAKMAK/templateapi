package com.springboot.template.core.web.rest.config;

public class RestSubError {

    private String object;
    private String field;
    private Object rejected;
    private String message;

    public RestSubError(String object, String field, Object rejected, String message) {
        this.object = object;
        this.field = field;
        this.rejected = rejected;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejected() {
        return rejected;
    }

    public void setRejected(Object rejected) {
        this.rejected = rejected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
