package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.CompanyInductionFormPresenter.Display;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;

public class CompanyInductionFormView extends FlexTable implements Display {
	
	private TextBox txtName = new TextBox();
	private TextBox txtContactPeron= new TextBox();
	private TextBox txtContactPersonEmail = new TextBox();
	private	TextBox txtBoxUsersAllowed = new TextBox();
	private	TextBox txtBoxMngmntUsersAllowed = new TextBox();
	private ListBox listPackages = new ListBox();
	private Button btnCancel = new Button("Cancel");
	private Button btnSubmit = new Button("Submit");
	private HorizontalPanel hpnlLblCheckBox = new HorizontalPanel();
	private HorizontalPanel hpnlCheckBox = new HorizontalPanel();
	private CheckBox includeLibray =  new CheckBox(); 
	
	public CompanyInductionFormView() {
		
		layout();
		keyHandler();
		this.addStyleName("w3-display-topmiddle w3-border");
	}

	private void layout() {
		Label lblHeading = new Label("User Induction Form");
		lblHeading.addStyleName("labelHeading");
		
		Label lblCmpanyName = new Label("Company Name");
		Label lblPersonName = new Label("Contact Person Name");
		Label lblPersonEmail = new Label("Contact Person Email");
		Label lblCmpanyPackage = new Label("Package");
		
		lblCmpanyName.addStyleName("labelDesign");
		lblPersonName.addStyleName("labelDesign");
		lblPersonEmail.addStyleName("labelDesign");
		lblCmpanyPackage.addStyleName("labelDesign");
		
		listPackages.setWidth("130px");
		
		for(UserPackages packages: UserPackages.values())
		listPackages.addItem(packages.name, Integer.toString(packages.id));
		
		setWidget(0, 1, lblHeading);
		setWidget(1,0, lblCmpanyName);
		setWidget(2,0, lblPersonName);
		setWidget(3,0, lblPersonEmail);
		setWidget(4,0, lblCmpanyPackage);

		setWidget(1,2, txtName);
		setWidget(2,2, txtContactPeron);
		setWidget(3,2, txtContactPersonEmail);
		setWidget(4, 2, listPackages);
		
		Label lblIncludeLibrary = new Label("Include Library");
		lblIncludeLibrary.addStyleName("labelDesign");
		hpnlLblCheckBox.add(lblIncludeLibrary);
 
		hpnlCheckBox.add(includeLibray);
		
		setWidget(5, 0, hpnlLblCheckBox);
		setWidget(5, 2, hpnlCheckBox);
		// hide check box in default
		goldPackageWithLibrary(false);
		
		Label lblNumOfEmployee = new Label("Number of Users");
		lblNumOfEmployee.addStyleName("labelDesign");
		setWidget(6, 0, lblNumOfEmployee);
		setWidget(6, 2, txtBoxUsersAllowed);
		
		Label lblNumOfMngmnt = new Label("Number of Management Users");
		lblNumOfMngmnt.addStyleName("labelDesign");
		setWidget(7, 0, lblNumOfMngmnt);
		setWidget(7, 2, txtBoxMngmntUsersAllowed);
		
		HorizontalPanel hpnlButtons =  new HorizontalPanel();
		hpnlButtons.add(btnCancel);
		hpnlButtons.add(btnSubmit);
		hpnlButtons.addStyleName("w3-right");
		setWidget(8,2, hpnlButtons);

	}
	
	private void keyHandler() {
		listPackages.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				if(listPackages.getSelectedValue().equals("2")) 
					goldPackageWithLibrary(true);
				else
					goldPackageWithLibrary(false);
			}
		});
	}
	
	private void goldPackageWithLibrary(boolean flag) {
		hpnlCheckBox.setVisible(flag);
		hpnlLblCheckBox.setVisible(flag);
	}
	
	@Override
	public TextBox getTxtName() {
		
		return txtName;
	}
	
	public ListBox getListPackages() {
		return listPackages;
	}
	
	public TextBox getTxtContactPerson() {
		
		return txtContactPeron;
	}

	public TextBox getTxtContactPersonEmail() {
		return txtContactPersonEmail;
	}
	
	public Button getBtnCancel(){
		return btnCancel;
	}
	
	public Button getBtnSubmit(){
		return btnSubmit;
	}

	public CheckBox getIncludeLibray() {
		return includeLibray;
	}

	public void setIncludeLibray(CheckBox includeLibray) {
		this.includeLibray = includeLibray;
	}

	public TextBox getTxtBoxUsersAllowed() {
		return txtBoxUsersAllowed;
	}

	public void setTxtBoxUsersAllowed(TextBox txtBoxUsersAllowed) {
		this.txtBoxUsersAllowed = txtBoxUsersAllowed;
	}

	public TextBox getTxtBoxMngmntUsersAllowed() {
		return txtBoxMngmntUsersAllowed;
	}

	public void setTxtBoxMngmntUsersAllowed(TextBox txtBoxMngmntUsersAllowed) {
		this.txtBoxMngmntUsersAllowed = txtBoxMngmntUsersAllowed;
	}

}
