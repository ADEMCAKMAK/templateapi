package com.springboot.template.core.entity;


import com.springboot.template.core.entity.base.ValueEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Audited
@Entity
@Table(name = "Z_AUTHORITY")
public class Authority extends ValueEntity {

    public Authority() {
    }

    public Authority(String code, String text) {
        super(code, text);
    }

}
