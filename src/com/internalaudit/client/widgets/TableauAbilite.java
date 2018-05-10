package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class TableauAbilite extends Composite  {

	private static TableauAbiliteUiBinder uiBinder = GWT.create(TableauAbiliteUiBinder.class);

	interface TableauAbiliteUiBinder extends UiBinder<Widget, TableauAbilite> {
	}

	 @UiField
	    Frame myFrame;
	 
	
	
	public TableauAbilite() {
		initWidget(uiBinder.createAndBindUi(this));
		
		myFrame.setUrl("tableau/tableauabilite.html");
		myFrame.setSize("1200px", "800px");
	
		
	}

	
}
