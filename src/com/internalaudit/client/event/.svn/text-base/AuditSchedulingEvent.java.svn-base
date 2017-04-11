package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.User;

		public class AuditSchedulingEvent extends GwtEvent<AuditSchedulingEventHandler> {
			
			VerticalPanel centerPanel;
			
			public AuditSchedulingEvent(VerticalPanel centerPanel){
				this.centerPanel = centerPanel;
			}
			
		  public static Type<AuditSchedulingEventHandler> TYPE = new Type<AuditSchedulingEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<AuditSchedulingEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(AuditSchedulingEventHandler handler) {
		    handler.onAuditScheduling(this);
			
		}

		public VerticalPanel getCenterPanel() {
			return centerPanel;
		}

		public void setCenterPanel(VerticalPanel centerPanel) {
			this.centerPanel = centerPanel;
		}

		

	}


