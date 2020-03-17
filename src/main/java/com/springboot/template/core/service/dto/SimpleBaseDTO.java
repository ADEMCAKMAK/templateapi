package com.springboot.template.core.service.dto;

public class SimpleBaseDTO extends BaseDTO<Long> {

    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
