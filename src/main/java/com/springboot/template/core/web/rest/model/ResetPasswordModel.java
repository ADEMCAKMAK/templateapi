package com.springboot.template.core.web.rest.model;

public class ResetPasswordModel {

    private String id;

    private String resetUUID;

    private String oldPassword;

    private String newPassword;

    private String newPasswordConfirm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResetUUID() {
        return resetUUID;
    }

    public void setResetUUID(String resetUUID) {
        this.resetUUID = resetUUID;
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
