package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.User;

		public class CreateUserEvent extends GwtEvent<CreateUserEventHandler> {
			
			User user = new User();			
		  public static Type<CreateUserEventHandler> TYPE = new Type<CreateUserEventHandler>();

		public CreateUserEvent(User user) {
			this.user = user;
		}

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<CreateUserEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(CreateUserEventHandler handler) {
		    handler.onCreateUser(this);
			
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		

	}


