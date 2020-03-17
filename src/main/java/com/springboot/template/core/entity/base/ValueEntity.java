package com.springboot.template.core.entity.base;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Audited
@MappedSuperclass
public class ValueEntity extends SimpleBaseEntity {

    public static final int MIN_CODE_LENGTH = 2;
    public static final int MAX_CODE_LENGTH = 100;
    public static final int MIN_TEXT_LENGTH = 2;
    public static final int MAX_TEXT_LENGTH = 200;
    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(min = MIN_CODE_LENGTH, max = MAX_CODE_LENGTH)
    @Column(name = "CODE", length = MAX_CODE_LENGTH, unique = true)
    private String code;
    @NotNull
    @Size(min = MIN_TEXT_LENGTH, max = MAX_TEXT_LENGTH)
    @Column(name = "TEXT", length = MAX_TEXT_LENGTH)
    private String text;

    public ValueEntity() {
    }

    public ValueEntity(String code, String text) {
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

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueEntity that = (ValueEntity) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}