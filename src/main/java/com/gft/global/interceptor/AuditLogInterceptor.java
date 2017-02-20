package com.gft.global.interceptor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.type.Type;

import com.gft.global.interceptor.util.AuditLogUtil;
import com.gft.global.log.model.IAuditLog;

public class AuditLogInterceptor extends EmptyInterceptor {

    private static final String SAVED = "Saved";

    private static final String DELETED = "Deleted";

    private static final String UPDATED = "Updated";

    private static final long serialVersionUID = 1775228354052642640L;

    private Set<IAuditLog> inserts = new HashSet();
    private Set<IAuditLog> updates = new HashSet();
    private Set<IAuditLog> deletes = new HashSet();

    Session session;
    SessionBuilder sessionBuilder;

    public void setSession(Session session) {
	this.session = session;
    }

    public void setSessionBuilder(SessionBuilder sessionBuilder) {
	this.sessionBuilder = sessionBuilder;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	System.out.println("onSave");

	if (entity instanceof IAuditLog) {
	    AuditLogUtil.LogIt(SAVED, (IAuditLog) entity, session);
	    inserts.add((IAuditLog) entity);
	    return false;
	} else
	    return super.onSave(entity, id, state, propertyNames, types);

    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	System.out.println("onDelete");

	super.onDelete(entity, id, state, propertyNames, types);

	if (entity instanceof IAuditLog) {
	    AuditLogUtil.LogIt(DELETED, (IAuditLog) entity, session);
	    deletes.add((IAuditLog) entity);
	}
    }

    @Override
    public void postFlush(Iterator entities) {
	System.out.println("postFlush");

	super.postFlush(entities);

	try {

	    for (IAuditLog iAuditLog : inserts) {
		System.out.println("postFlush S [" + iAuditLog.toString() + "]");
		System.out.println("postFlush - insert");
	    }
	    for (IAuditLog iAuditLog : deletes) {
		System.out.println("postFlush D [" + iAuditLog.toString() + "]");
		System.out.println("postFlush - delete");
	    }
	    for (IAuditLog iAuditLog : updates) {
		System.out.println("postFlush U [" + iAuditLog.toString() + "]");
		System.out.println("postFlush - update");
	    }

	} finally {
	    inserts.clear();
	    updates.clear();
	    deletes.clear();
	}
    }

    @Override
    public void preFlush(Iterator entities) {
	System.out.println("preFlush");
	super.preFlush(entities);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
	System.out.println("onFlushDirty");

	if (entity instanceof IAuditLog) {
	    AuditLogUtil.LogIt(UPDATED, (IAuditLog) entity, session);
	    updates.add((IAuditLog) entity);
	    return false;
	} else
	    return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);

    }

}
