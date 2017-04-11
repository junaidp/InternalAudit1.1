package com.internalaudit.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupsViewWhite {
	
	DecoratedPopupPanel popup ;
	VerticalPanel vpnlMain;
	private HorizontalPanel hpnlSPace;
	
	public HorizontalPanel getHpnlSPace() {
		return hpnlSPace;
	}

	public void setHpnlSPace(HorizontalPanel hpnlSPace) {
		this.hpnlSPace = hpnlSPace;
	}

	Image close = new Image("close.jpg");
	
	public PopupsViewWhite(Widget widget) {
		HorizontalPanel hpnlClose = new HorizontalPanel();
		hpnlSPace = new HorizontalPanel();
		hpnlSPace.setWidth("1310px");
		hpnlClose.add(hpnlSPace);
		hpnlClose.add(close);
		close.setStyleName("pointerStyle");
		popup = new DecoratedPopupPanel();
		vpnlMain = new VerticalPanel();
		vpnlMain.add(hpnlClose);
		vpnlMain.add(widget);
//		vpnlMain.setSize("800px","425px");
		popup.setWidget(vpnlMain);
		popup.setPopupPosition(10, 100);
		popup.setStyleName("headerStyle");
		popup.setGlassEnabled(true);
		popup.show();
		
		close.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent arg0) {
				popup.removeFromParent();
			}});
	}

	public DecoratedPopupPanel getPopup() {
		return popup;
	}


	public void setPopup(DecoratedPopupPanel popup) {
		this.popup = popup;
	}


	public Image getClose() {
		return close;
	}


	public void setClose(Image close) {
		this.close = close;
	}

	public VerticalPanel getVpnlMain() {
		return vpnlMain;
	}

	public void setVpnlMain(VerticalPanel vpnlMain) {
		this.vpnlMain = vpnlMain;
	}

	
	


}
