package com.internalaudit.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface JobListingEventHandler extends EventHandler {
	void onJobListing(JobListingEvent event);
}
