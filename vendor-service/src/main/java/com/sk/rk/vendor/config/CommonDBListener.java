package com.sk.rk.config;

import com.sk.rk.repository.CommonPojo;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import com.sk.rk.util.CommonUtil;

import java.sql.Timestamp;

public class CommonDBListener implements PreInsertEventListener, PreUpdateEventListener {

    public static final CommonDBListener INSTANCE = new CommonDBListener();

    @Override
    public boolean onPreInsert (PreInsertEvent preInsertEvent) {
        onInsert(preInsertEvent.getEntity(), preInsertEvent.getState(), preInsertEvent.getPersister().getPropertyNames());
        return false;
    }

    @Override
    public boolean onPreUpdate (PreUpdateEvent preUpdateEvent) {
        onUpdate(preUpdateEvent.getEntity(), preUpdateEvent.getState(), preUpdateEvent.getPersister().getPropertyNames());
        return false;
    }

    private void onInsert(Object entity, Object[] state, String[] propertyNames) {
        Timestamp timestamp = CommonUtil.getCurrentTimestamp();
        if (entity instanceof CommonPojo) {
            CommonPojo defaultAttribute = (CommonPojo) entity;
            defaultAttribute.setDateCreated(timestamp);
            defaultAttribute.setDateModified(timestamp);
        }
        setPropertyState(state, propertyNames, "dateCreated", timestamp);
        setPropertyState(state, propertyNames, "dateModified", timestamp);
    }

    private void onUpdate(Object entity, Object[] state, String[] propertyNames) {
        if (entity instanceof CommonPojo) {
            CommonPojo defaultImport = (CommonPojo) entity;
            Timestamp timestamp = CommonUtil.getCurrentTimestamp();
            setPropertyState(state, propertyNames, "createdDate", defaultImport.getDateCreated());
            defaultImport.setDateModified(timestamp);
            setPropertyState(state, propertyNames, "modifiedDate", timestamp);
        }
    }

    private void setPropertyState(Object[] propertyStates, String[] propertyNames, String propertyName, Object propertyState) {
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyName.equals(propertyNames[i])) {
                propertyStates[i] = propertyState;
                return;
            }
        }
    }
}
