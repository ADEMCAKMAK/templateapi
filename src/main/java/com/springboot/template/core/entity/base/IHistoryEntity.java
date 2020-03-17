package com.springboot.template.core.entity.base;

import java.io.Serializable;

public interface IHistoryEntity<T extends Serializable>  {

    T getEntity();

    void setEntity(T entity);
}
