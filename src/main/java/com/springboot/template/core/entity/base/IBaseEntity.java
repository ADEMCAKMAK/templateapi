package com.springboot.template.core.entity.base;

import java.io.Serializable;

public interface IBaseEntity<ID extends Serializable> {

    ID getId();

    void setId(ID id);
}
