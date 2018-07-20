package com.internalaudit.client.widgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

public class AddIcon extends FocusPanel {

	public AddIcon(){
		
		Label l = new Label("+");
		add(l);
		setStyleName("w3-button w3-small w3-circle w3-green w3-margin");
		getElement().getStyle().setMarginLeft(300, Unit.PX);
	}
}
