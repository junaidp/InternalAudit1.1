package com.internalaudit.client.view;

import org.apache.poi.hssf.util.HSSFColor.BLUE;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupsView {

    DecoratedPopupPanel popup;
    VerticalPanel vpnlMain;
    private HorizontalPanel hpnlSPace;

    public HorizontalPanel getHpnlSPace() {
	return hpnlSPace;
    }

    public void setHpnlSPace(HorizontalPanel hpnlSPace) {
	this.hpnlSPace = hpnlSPace;
    }

    Image close = new Image("close.jpg");
    Label labelheading = new Label();
    

    public PopupsView(Widget widget) {
    	
    	//labelheading.getElement().getStyle().setBackgroundColor("BLUE");
    	labelheading.getElement().getStyle().setFontSize(18, Unit.PX);
    	labelheading.getElement().getStyle().setFontWeight(FontWeight.BOLD);
    	labelheading.addStyleName(" w3-container w3-pale-green");
    
	HorizontalPanel hpnlClose = new HorizontalPanel ();
	hpnlSPace = new HorizontalPanel();
	hpnlSPace.setWidth("800px");
	hpnlClose.add(hpnlSPace);
	hpnlClose.add(close);
	close.addStyleName("pointerStyle");
	popup = new DecoratedPopupPanel();
	vpnlMain = new VerticalPanel();
	vpnlMain.add(hpnlClose);
	vpnlMain.add(labelheading);
	vpnlMain.add(widget);
	vpnlMain.setSize("800px", "425px");
	// vpnlMain.
	popup.setWidget(vpnlMain);
	hpnlSPace.addStyleName("w3-panel w3-red");
	//popup.addStyleName("w3-panel w3-green");
	vpnlMain.addStyleName("w3-panel w3-sand");

	popup.setGlassEnabled(true);
	popup.center();

	close.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent arg0) {
		popup.removeFromParent();
	    }
	});
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

	public Label getLabelheading() {
		return labelheading;
	}

	public void setLabelheading(Label labelheading) {
		this.labelheading = labelheading;
	}

}
