package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EngagementPopup extends Composite {
	
	
	 PopupPanel popup = new PopupPanel();
	 VerticalPanel container = new VerticalPanel();
	 
	 Label heading = new Label();
	 
	 public EngagementPopup() {
		 
		 popup.setWidget(container);
		 container.addStyleName("engagePopupWidget");
		 container.setWidth("600px");
		 container.setHeight("200px");
		 
		 heading.addStyleName("popupHeading");
		 
		 container.add(heading);
	 }
	 
	 
	 
	 VerticalPanel getContainer() {
		 
		 return this.container;
	 }
	 
	 Label getHeading() {
		 
		 return this.heading;
	 }
	 public void show() {
		 
		 this.popup.show();
		 this.popup.center();
	 }
	 

	 public void hide() {
		 
		 this.popup.hide();
	 }
	 
	 
}
