package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.User;

		public class DashBoardAtStartupEvent extends GwtEvent<DashBoardAtStartupEventHandler> {
			
			User user ;
			
			public DashBoardAtStartupEvent(User  user){
				this.user = user;
			}
			
		  public static Type<DashBoardAtStartupEventHandler> TYPE = new Type<DashBoardAtStartupEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<DashBoardAtStartupEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(DashBoardAtStartupEventHandler handler) {
		    handler.onDashBoardAtStartup(this);
			
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		
	}


