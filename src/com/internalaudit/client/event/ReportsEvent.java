package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;


		public class ReportsEvent extends GwtEvent<ReportsEventHandler> {
			
			VerticalPanel centerPanel;
			
			public ReportsEvent(VerticalPanel centerPanel){
				this.centerPanel = centerPanel;
			}
			
		  public static Type<ReportsEventHandler> TYPE = new Type<ReportsEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<ReportsEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(ReportsEventHandler handler) {
		    handler.onReports(this);
			
		}

		public VerticalPanel getCenterPanel() {
			return centerPanel;
		}

		public void setCenterPanel(VerticalPanel centerPanel) {
			this.centerPanel = centerPanel;
		}

		

	}


