package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;

public class PopupsView {

	Dialog popup;
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

	public PopupsView(Widget widget, String heading) {

		// labelheading.getElement().getStyle().setBackgroundColor("BLUE");
		labelheading.getElement().getStyle().setFontSize(18, Unit.PX);
		labelheading.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		labelheading.addStyleName(" w3-container ");

		HorizontalPanel hpnlClose = new HorizontalPanel();
		hpnlSPace = new HorizontalPanel();
		// hpnlSPace.setWidth("800px");
		// hpnlClose.add(hpnlSPace);
		// hpnlClose.add(close);
		close.addStyleName("pointerStyle");
		popup = new Dialog();
		vpnlMain = new VerticalPanel();
		vpnlMain.add(hpnlClose);
		vpnlMain.add(labelheading);
		vpnlMain.add(widget);
		popup.setWidget(vpnlMain);
		hpnlSPace.addStyleName("w3-panel w3-white ");
		vpnlMain.addStyleName("w3-panel w3-white");
		popup.setHeadingText(heading);
		// popup.setGlassEnabled(true);
		popup.show();
		popup.center();

		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				popup.removeFromParent();
				vpnlMain.removeFromParent();
				hpnlSPace.removeFromParent();

			}
		});
	}

	public Dialog getPopup() {
		return popup;
	}

	public void setPopup(Dialog popup) {
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
