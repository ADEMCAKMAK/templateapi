package com.springboot.template.core.service.dto;

public class ValueDTO extends SimpleBaseDTO {

    private String code;

    private String text;

    public ValueDTO() {
    }

    public ValueDTO(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

}
