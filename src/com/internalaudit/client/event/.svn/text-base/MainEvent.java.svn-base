package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.internalaudit.shared.User;

		public class MainEvent extends GwtEvent<MainEventHandler> {
			
			User loggedInUser = null;
			int selectedYear;
			
			public MainEvent(User loggedInUser){
				this.loggedInUser = loggedInUser;
			}
			
		  public MainEvent(User loggedInUser, int year) {
			  this.loggedInUser = loggedInUser;
			  this.selectedYear = year;
			}

		public static Type<MainEventHandler> TYPE = new Type<MainEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<MainEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(MainEventHandler handler) {
		    handler.onMain(this);
			
		}

		public User getLoggedInUser() {
			return loggedInUser;
		}

		public void setLoggedInUser(User loggedInUser) {
			this.loggedInUser = loggedInUser;
		}

		public int getSelectedYear() {
			return selectedYear;
		}

		public void setSelectedYear(int selectedYear) {
			this.selectedYear = selectedYear;
		}

	}


