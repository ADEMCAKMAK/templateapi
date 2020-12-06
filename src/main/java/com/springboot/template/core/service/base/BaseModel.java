package com.springboot.template.core.service.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel<ID extends Serializable>
        implements Serializable {

    private static final long serialVersionUID = 6841028096770761004L;

    @JsonIgnore
    private int version;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private String lastModifiedBy;
    @JsonIgnore
    private Date lastModifiedDate;

    abstract public ID getId();

    abstract public void setId(ID id);

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}