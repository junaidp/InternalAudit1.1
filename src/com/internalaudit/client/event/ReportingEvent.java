package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.User;

		public class ReportingEvent extends GwtEvent<ReportingEventHandler> {
			
			VerticalPanel centerPanel;
			
			public ReportingEvent(VerticalPanel centerPanel){
				this.centerPanel = centerPanel;
			}
			
		  public static Type<ReportingEventHandler> TYPE = new Type<ReportingEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<ReportingEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(ReportingEventHandler handler) {
		    handler.onReporting(this);
			
		}

		public VerticalPanel getCenterPanel() {
			return centerPanel;
		}

		public void setCenterPanel(VerticalPanel centerPanel) {
			this.centerPanel = centerPanel;
		}

		

	}


