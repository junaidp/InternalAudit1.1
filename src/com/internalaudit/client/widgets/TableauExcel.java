package com.internalaudit.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;

public class TableauExcel extends Composite  {

	private static TableauExcelUiBinder uiBinder = GWT.create(TableauExcelUiBinder.class);

	interface TableauExcelUiBinder extends UiBinder<Widget, TableauExcel> {
	}

	public TableauExcel() {
		initWidget(uiBinder.createAndBindUi(this));
		myFrame.setUrl("tableau/tableauexcel.html");
		myFrame.setSize("1200px", "800px");
	}

	 @UiField
	    Frame myFrame;
}
