package com.springboot.template.core.entity;

import com.springboot.template.core.entity.base.BaseEntity;
import com.springboot.template.core.entity.id.generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ENT_WITH_STR_ID")
public class TemplateUser extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="template_user_seq")
    @GenericGenerator(name = "template_user_seq",
        strategy="com.springboot.template.core.entity.id.generator.SequenceIdGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INITIAL_PARAM, value = "50")
        }
    )
    private String id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
        this.id = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
