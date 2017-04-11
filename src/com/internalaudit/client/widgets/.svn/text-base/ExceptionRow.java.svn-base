package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ExceptionRow extends Composite {
	
	private Label exId;
	
	private TextBox exception;
	
	private HorizontalPanel hpnl;
	
	
	public ExceptionRow() 
	{
		hpnl = new HorizontalPanel();
		
		initWidget(hpnl);
				
		exId = new Label("0");
		exId.setStyleName("hidden");
		
		exception = new TextBox();
		exception.setStyleName("txtExtendedWidth");
	
		hpnl.add(exId);
		hpnl.add(exception);
		exId.setVisible(false);
	}

	public Label getExId() {
		return exId;
	}

	public void setExId(Label exId) {
		this.exId = exId;
	}

	public TextBox getException() {
		return exception;
	}

	public void setException(TextBox exception) {
		this.exception = exception;
	}

}
