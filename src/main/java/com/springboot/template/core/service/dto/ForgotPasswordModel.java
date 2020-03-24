package com.springboot.template.core.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ForgotPasswordModel {

    @Email
    @Size(max = 100)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
