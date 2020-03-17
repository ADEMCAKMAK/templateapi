package com.springboot.template.core.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;

import java.io.Serializable;

public class HistoryEntity<T extends Serializable> implements IHistoryEntity<T>, Serializable {

    private T entity;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DefaultRevisionEntity defaultRevisionEntity;

    private RevisionType revisionType;

    public HistoryEntity(T entity, DefaultRevisionEntity defaultRevisionEntity, RevisionType revisionType) {
        this.entity = entity;
        this.defaultRevisionEntity = defaultRevisionEntity;
        this.revisionType = revisionType;
    }


    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void setEntity(T entity) {
        this.entity = entity;
    }

    public DefaultRevisionEntity getDefaultRevisionEntity() {
        return defaultRevisionEntity;
    }

    public void setDefaultRevisionEntity(DefaultRevisionEntity defaultRevisionEntity) {
        this.defaultRevisionEntity = defaultRevisionEntity;
    }

    public RevisionType getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(RevisionType revisionType) {
        this.revisionType = revisionType;
    }

}
