package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AddRiskFactorsSettingsView extends VerticalPanel {

	private TextBox txtRiskFactors;
//	private TextArea txtRiskDescription;
	private Image imgDelete;
	private CheckBox checkBox;
	private FlexTable flexPanel ;
	
	public AddRiskFactorsSettingsView() {
		this.addStyleName("w3-panel");
		add(layout());
	}

	private Widget layout() {
		flexPanel = new FlexTable();
		txtRiskFactors = new TextBox();
		txtRiskFactors.getElement().setPropertyString("placeholder", "Enter risk factor");
		txtRiskFactors.setWidth("430px");
		flexPanel.setWidget(0, 1, txtRiskFactors);
		
//		txtRiskDescription = new TextArea();
//		txtRiskDescription.setWidth("430px");
//		txtRiskDescription.getElement().setPropertyString("placeholder", "Enter description here");
//		txtRiskDescription.getElement().getStyle().setPaddingLeft(30, Unit.PX);
//		flexPanel.setWidget(0, 2, txtRiskDescription);
		
		checkBox = new CheckBox();
		checkBox.getElement().getStyle().setPaddingLeft(20, Unit.PX);
		checkBox.setChecked(true);
		flexPanel.setWidget(0, 3, checkBox);
				
		imgDelete = new Image("images/deleteIcon.png");
		flexPanel.setWidget(0, 4, imgDelete);
		
		return flexPanel;
	}
	
	public void invisibleIcons() {
		checkBox.setVisible(false);
		txtRiskFactors.setEnabled(false);
//		txtRiskDescription.setEnabled(false); 
	}
	
	public void invisibleAllFields() {
		imgDelete.setVisible(false);
		checkBox.setVisible(false);
		txtRiskFactors.setEnabled(false);
//		txtRiskDescription.setEnabled(false); 
	}

	public TextBox getTxtRiskFactors() {
		return txtRiskFactors;
	}

	public void setTxtRiskFactors(TextBox txtRiskFactors) {
		this.txtRiskFactors = txtRiskFactors;
	}

//	public TextArea getTxtRiskDescription() {
//		return txtRiskDescription;
//	}
//
//	public void setTxtRiskDescription(TextArea txtRiskDescription) {
//		this.txtRiskDescription = txtRiskDescription;
//	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public Image getImgDelete() {
		return imgDelete;
	}

	public FlexTable getFlexPanel() {
		return flexPanel;
	}
	
}
