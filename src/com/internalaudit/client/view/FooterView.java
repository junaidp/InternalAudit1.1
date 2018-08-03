package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FooterView implements IsWidget  {
	HorizontalPanel panelFooer = new HorizontalPanel();
	
	
	public FooterView() {
		// TODO Auto-generated constructor stub
		panelFooer.addStyleName("w3-container w3-blue w3-display-bottomleft" );
		panelFooer.setWidth("100%");
	
		
		HorizontalPanel hpnl = new HorizontalPanel();
		Label lblFooter = new Label("Copy right reserverd");
		lblFooter.getElement().getStyle().setColor("white");
		hpnl.getElement().getStyle().setPaddingLeft(Window.getClientWidth()-300, Unit.PX);
		hpnl.getElement().getStyle().setPaddingTop(5, Unit.PX);
		Image img = new Image("images/copyright.png");
		img.setSize("20px", "20px");
		hpnl.add(img);
		hpnl.add(lblFooter);
		hpnl.setSpacing(3);
		hpnl.setHeight("50px");
		
		
		panelFooer.add(hpnl);
		
		
	}
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return panelFooer;
	}

}
