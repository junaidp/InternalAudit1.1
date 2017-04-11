package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.User;

		public class JobListingEvent extends GwtEvent<JobListingEventHandler> {
			
			User loggedInUser = null;
			private String from;
			
			public JobListingEvent(String from){
				this.from = from;
			}
			
		  public static Type<JobListingEventHandler> TYPE = new Type<JobListingEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<JobListingEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(JobListingEventHandler handler) {
		    handler.onJobListing(this);
			
		}

		public User getLoggedInUser() {
			return loggedInUser;
		}

		public void setLoggedInUser(User loggedInUser) {
			this.loggedInUser = loggedInUser;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		

	}


