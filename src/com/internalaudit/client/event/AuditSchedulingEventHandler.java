package com.internalaudit.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AuditSchedulingEventHandler extends EventHandler {
	void onAuditScheduling(AuditSchedulingEvent event);
}
