package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddDegreeOfImportanceSettingsView extends HorizontalPanel {
	
	private TextBox txtName;
	private Image imgDelete;
	private CheckBox checkBox;
	private FlexTable flexPanel;
	
	public AddDegreeOfImportanceSettingsView() {
		this.addStyleName("w3-panel");
		add(layout());
	}

	private Widget layout() {
		flexPanel = new FlexTable();
		txtName = new TextBox();
		txtName.setWidth("400px");
		txtName.getElement().setPropertyString("placeholder", "Enter degree of importance");
		flexPanel.setWidget(0, 1, txtName);

		
		checkBox = new CheckBox();
		checkBox.setChecked(true);
		checkBox.getElement().getStyle().setPaddingLeft(20, Unit.PX);
		flexPanel.setWidget(0, 3, checkBox);
				
		imgDelete = new Image("images/deleteIcon.png");
		flexPanel.setWidget(0, 4, imgDelete);
				
		return flexPanel;
	}
	
	public void invisibleAllFiels() {
		imgDelete.setVisible(false);
		checkBox.setVisible(false);
		txtName.setEnabled(false); 
	}
	
	public void invisibleFiels() {
		checkBox.setVisible(false);
		txtName.setEnabled(false); 
	}
	
	public TextBox getTxtName() {
		return txtName;
	}

	public void setTxtName(TextBox txtName) {
		this.txtName = txtName;
	}

	public Image getImgDelete() {
		return imgDelete;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public FlexTable getFlexPanel() {
		return flexPanel;
	}

	public void setFlexPanel(FlexTable flexPanel) {
		this.flexPanel = flexPanel;
	}

	public void setImgDelete(Image imgDelete) {
		this.imgDelete = imgDelete;
	}

}
