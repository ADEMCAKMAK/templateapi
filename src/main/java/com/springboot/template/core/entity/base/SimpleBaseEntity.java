package com.springboot.template.core.entity.base;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@MappedSuperclass
public class SimpleBaseEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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
