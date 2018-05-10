package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TableauReports extends Composite  {

	private static TableauReportsUiBinder uiBinder = GWT.create(TableauReportsUiBinder.class);

	interface TableauReportsUiBinder extends UiBinder<Widget, TableauReports> {
	}

	public TableauReports() {
		initWidget(uiBinder.createAndBindUi(this));
		myFrame.setUrl("tableau/tableau.html");
		myFrame.setSize("1200px", "800px");
	}

	 @UiField
	    Frame myFrame;
	 
	public TableauReports(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		myFrame.setUrl("tableau/tableau.html");
		myFrame.setSize("1200px", "800px");
		
	}

	

}
