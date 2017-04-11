package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.User;

		public class AuditEngagementEvent extends GwtEvent<AuditEngagementEventHandler> {
			
			VerticalPanel centerPanel;
			
			public AuditEngagementEvent(VerticalPanel centerPanel){
				this.centerPanel = centerPanel;
			}
			
		  public static Type<AuditEngagementEventHandler> TYPE = new Type<AuditEngagementEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<AuditEngagementEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(AuditEngagementEventHandler handler) {
		    handler.onAuditEngagement(this);
			
		}

		public VerticalPanel getCenterPanel() {
			return centerPanel;
		}

		public void setCenterPanel(VerticalPanel centerPanel) {
			this.centerPanel = centerPanel;
		}

		

	}


