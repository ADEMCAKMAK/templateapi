package com.springboot.template.core.entity.id.base;

import java.io.Serializable;

public interface CompoundKey extends Serializable {

    String KEY_DELIMITER = "_";

    String getCompoundKey();

    void resolveCompoundKey(String source);
}
