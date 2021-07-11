package com.spring.template.core.entity.base;

import java.io.Serializable;

public interface Entity<ID extends Serializable> extends Serializable {

    ID getId();

    void setId(ID id);
}
