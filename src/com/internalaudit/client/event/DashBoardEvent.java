package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.User;

		public class DashBoardEvent extends GwtEvent<DashBoardEventHandler> {
			
			VerticalPanel centerPanel;
			
			public DashBoardEvent(VerticalPanel centerPanel){
				this.centerPanel = centerPanel;
			}
			
		  public static Type<DashBoardEventHandler> TYPE = new Type<DashBoardEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<DashBoardEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(DashBoardEventHandler handler) {
		    handler.onDashBoard(this);
			
		}

		public VerticalPanel getCenterPanel() {
			return centerPanel;
		}

		public void setCenterPanel(VerticalPanel centerPanel) {
			this.centerPanel = centerPanel;
		}

		

	}


