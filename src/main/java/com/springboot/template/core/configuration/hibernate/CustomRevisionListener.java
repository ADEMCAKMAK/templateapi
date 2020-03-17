package com.springboot.template.core.configuration.hibernate;

import com.exercises.springboot.core.security.SecurityContexts;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity rev = (CustomRevisionEntity) revisionEntity;
        rev.setUserId(SecurityContexts.getCurrentUserId());
    }

}
