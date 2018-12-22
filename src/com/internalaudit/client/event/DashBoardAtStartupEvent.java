package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.internalaudit.shared.Employee;

public class DashBoardAtStartupEvent extends GwtEvent<DashBoardAtStartupEventHandler> {

	Employee user;

	public DashBoardAtStartupEvent(Employee user) {
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

	public Employee getEmployee() {
		return user;
	}

	public void setEmployee(Employee user) {
		this.user = user;
	}

}
