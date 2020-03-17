package com.springboot.template.core.web.rest.config;

import java.util.Date;
import java.util.List;

public class RestError {

    private Integer status;
    private Date timestamp;
    private String error;
    private String exception;
    private String path;
    private List<RestSubError> restSubErrors;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RestSubError> getRestSubErrors() {
        return restSubErrors;
    }

    public void setRestSubErrors(List<RestSubError> restSubErrors) {
        this.restSubErrors = restSubErrors;
    }
}
