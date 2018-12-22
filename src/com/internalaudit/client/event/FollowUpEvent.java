package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FollowUpEvent extends GwtEvent<FollowUpEventHandler> {

	VerticalPanel centerPanel;

	public FollowUpEvent(VerticalPanel centerPanel) {
		this.centerPanel = centerPanel;

	}

	public static Type<FollowUpEventHandler> TYPE = new Type<FollowUpEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FollowUpEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FollowUpEventHandler handler) {
		handler.onFollowUp(this);

	}

	public VerticalPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(VerticalPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

}
