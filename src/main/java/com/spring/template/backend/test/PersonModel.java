package com.spring.template.backend.test;

import com.spring.template.core.service.base.BaseModel;

public class PersonModel extends BaseModel<Long> {

    private Long id;
    private String name;
    private String surname;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
