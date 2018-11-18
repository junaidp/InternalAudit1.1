package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ExceptionRow extends Composite {
	
	private Label exId;
	
	private TextBox exception;
	private FileUpload fileUploadException;
	private HorizontalPanel hpnl;
	private Image removeRow;
	
	
	public ExceptionRow() 
	{
		hpnl = new HorizontalPanel();
		
		initWidget(hpnl);
				
		exId = new Label("0");
		exId.addStyleName("hidden");
		
		exception = new TextBox();
		fileUploadException = new FileUpload();
		removeRow = new Image("images/deleteIcon.png");
		exception.addStyleName("txtExtendedWidth");
	
		hpnl.add(exId);
		hpnl.add(exception);
		hpnl.add(fileUploadException);
		hpnl.add(removeRow);
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

	public Image getRemoveRow() {
		return removeRow;
	}

	public void setRemoveRow(Image removeRow) {
		this.removeRow = removeRow;
	}
	
	public void removeRow() {
		exception.removeFromParent();
		exId.removeFromParent();
		removeRow.removeFromParent();
		
	}

	public void disableFields() {
		exception.setEnabled(false);
		removeRow.setVisible(false);
	}

}
