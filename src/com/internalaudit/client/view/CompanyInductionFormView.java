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
import com.internalaudit.client.view.AuditEngagement.LabelBold;
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
		this.getElement().getStyle().setMarginTop(20, Unit.PX);
	}

	private void layout() {
		Label lblHeading = new Label("Company Induction Form");
		lblHeading.addStyleName("blue");
		
		LabelBold lblCmpanyName = new LabelBold("Company Name");
		LabelBold lblPersonName = new LabelBold("Contact Person Name");
		LabelBold lblPersonEmail = new LabelBold("Contact Person Email");
		LabelBold lblCmpanyPackage = new LabelBold("Package");
		
		
		listPackages.setWidth("130px");
		
		for(UserPackages packages: UserPackages.values())
		listPackages.addItem(packages.name, Integer.toString(packages.id));
		
		setWidget(0, 1, lblHeading);
		setWidget(1,0, lblCmpanyName);
		setWidget(2,0, lblPersonName);
		setWidget(3,0, lblPersonEmail);
		setWidget(4,0, lblCmpanyPackage);

		txtName.setWidth("250px");
		txtContactPeron.setWidth("250px");
		txtContactPersonEmail.setWidth("250px");
		listPackages.setWidth("250px");
		txtBoxUsersAllowed.setWidth("250px");
		txtBoxMngmntUsersAllowed.setWidth("250px");
		
		setWidget(1,1, txtName);
		setWidget(2,1, txtContactPeron);
		setWidget(3,1, txtContactPersonEmail);
		setWidget(4, 1, listPackages);
		
		LabelBold lblIncludeLibrary = new LabelBold("Include Library");
		hpnlLblCheckBox.add(lblIncludeLibrary);
 
		hpnlCheckBox.add(includeLibray);
		
		setWidget(5, 0, hpnlLblCheckBox);
		setWidget(5, 1, hpnlCheckBox);
		// hide check box in default
		goldPackageWithLibrary(false);
		
		LabelBold lblNumOfEmployee = new LabelBold("Number of Users");
		setWidget(6, 0, lblNumOfEmployee);
		setWidget(6, 1, txtBoxUsersAllowed);
		
		LabelBold lblNumOfMngmnt = new LabelBold("Number of Management Users");
		lblNumOfMngmnt.setWidth("210px");
		setWidget(7, 0, lblNumOfMngmnt);
		setWidget(7, 1, txtBoxMngmntUsersAllowed);
		
		HorizontalPanel hpnlButtons =  new HorizontalPanel();
		hpnlButtons.add(btnCancel);
		hpnlButtons.add(btnSubmit);
		hpnlButtons.addStyleName("w3-right");
		setWidget(8,1, hpnlButtons);
		
		lblCmpanyName.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblPersonName.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblPersonEmail.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblCmpanyPackage.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblNumOfEmployee.getElement().getStyle().setPaddingTop(5, Unit.PX);
		lblNumOfMngmnt.getElement().getStyle().setPaddingTop(5, Unit.PX);
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
