package com.spring.template.backend.auth;

import com.spring.template.core.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Group extends BaseEntity<Long> {

    private static final String sequencer = "SEQ_GROUP";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequencer)
    @SequenceGenerator(name = sequencer)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE", unique = true)
    private String reference;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
