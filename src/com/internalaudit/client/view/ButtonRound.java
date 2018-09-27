package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.chart.client.chart.Chart.Position;

public class ButtonRound extends FocusPanel{

	Label l = new Label("");
	public ButtonRound(String name) {
		//Label l = new Label();
		l.getElement().getStyle().setFontSize(10, Unit.PX);
		l.setText(name);
		l.getElement().getStyle().setColor("white");
		l.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		l.getElement().getStyle().setTextAlign(TextAlign.CENTER);
		add(l);
		getElement().getStyle().setBackgroundColor("#32CD32");
		setStyleName("w3-button w3-round-xxlarge w3-hover-blue ");
		getElement().getStyle().setMarginLeft(5, Unit.PX);
		setWidth("90px");
		setHeight("30px");
	}
	
	public ButtonRound() {
	
	}
	
	public void setEnabled(boolean b) {
	
		
	}
	public void setText(String string) {
		l.setText(string);
		
	}

	}

