package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoadingPopup {
	private DecoratedPopupPanel popupLoading;

	
	public  void display() {
		popupLoading = new DecoratedPopupPanel ();
		popupLoading.setSize("100%", "100%");
		VerticalPanel vpnlLoad = new VerticalPanel();
		vpnlLoad.setSize("20px", "20px");
		vpnlLoad.add(new Image("images/loading.gif"));
//		popupLoading.setWidget(vpnlLoad);
		popupLoading.setWidget(new Image("images/loading.gif"));
		popupLoading.addStyleName("whiteBackground");
//		popupLoading.setGlassEnabled(true);
		popupLoading.center();
	}


	public void remove(){
		popupLoading.removeFromParent();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
