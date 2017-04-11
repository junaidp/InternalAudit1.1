package com.internalaudit.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface CreateUserEventHandler extends EventHandler {
	void onCreateUser(CreateUserEvent event);
}
