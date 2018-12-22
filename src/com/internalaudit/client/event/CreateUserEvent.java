package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.Employee;


		public class CreateUserEvent extends GwtEvent<CreateUserEventHandler> {
			
			Employee user = new Employee();			
		  public static Type<CreateUserEventHandler> TYPE = new Type<CreateUserEventHandler>();

		public CreateUserEvent(Employee user) {
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

		public Employee getEmployee() {
			return user;
		}

		public void setEmployee(Employee user) {
			this.user = user;
		}

		

	}


