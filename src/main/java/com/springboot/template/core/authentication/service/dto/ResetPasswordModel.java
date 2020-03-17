package com.springboot.template.core.authentication.service.dto;

import java.util.UUID;

public class ResetPasswordModel {

    private String id;

    private String resetTokenKey;

    private String oldPassword;

    private String newPassword;

    private String newPasswordConfirm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResetTokenKey() {
        return resetTokenKey;
    }

    public void setResetTokenKey(String resetTokenKey) {
        this.resetTokenKey = resetTokenKey;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}