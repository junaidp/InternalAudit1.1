package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.DegreeImportance;
import com.internalaudit.shared.RiskFactor;

public class AddDegreeOfImportanceView extends HorizontalPanel {
	
	private ListBox listBoxDegreeImportance;
	private TextArea txtAreaComment;;
	private TextBox txtWeightage;
	private ListBox listBoxRatings;
	private ArrayList<DegreeImportance> arrayDegreeImportance;
	private AddDegreeOfImportanceSettingsView addDegreeOfImportanceSettingsView;
	private float resultRatings = 0;
	
	public AddDegreeOfImportanceView(){
		add(layout());
//		changeHandlers(null);
	}

	private Widget layout() {	
		arrayDegreeImportance = new ArrayList<DegreeImportance>();
		addDegreeOfImportanceSettingsView = new AddDegreeOfImportanceSettingsView();
//		obj.set
		FlexTable flexPanel = addDegreeOfImportanceSettingsView.getFlexPanel();
		addDegreeOfImportanceSettingsView.invisibleIcons();
		
		listBoxDegreeImportance = new ListBox();
		listBoxDegreeImportance.setVisible(false);
		listBoxDegreeImportance.setWidth("400px");
		flexPanel.setWidget(0, 0, listBoxDegreeImportance);
		
		txtAreaComment = new TextArea();
		txtAreaComment.setWidth("450px");
		txtAreaComment.getElement().setPropertyString("placeholder", "Enter comment here");
		flexPanel.setWidget(0, 2, txtAreaComment);
		
		txtWeightage = new TextBox();
		txtWeightage.setWidth("100px");
		txtWeightage.setValue("0");
		txtWeightage.getElement().setPropertyString("placeholder", "Input 100%");
		flexPanel.setWidget(0, 5, txtWeightage);
		
		listBoxRatings = new ListBox();
		listBoxRatings.setWidth("50px");
		listBoxRatings.getElement().getStyle().setPaddingLeft(15, Unit.PX);
		for(int i=10; i>0; i--)
			listBoxRatings.addItem(""+i, ""+i);
		flexPanel.setWidget(0, 6, listBoxRatings);
		
		return flexPanel;
	}

	private void changeHandlers(final DegreeImportance degreeImportanceNew) {
		txtAreaComment.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> text) {
				degreeImportanceNew.setComments(text.getValue());
			}
		});
		listBoxDegreeImportance.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent value) {
				degreeImportanceNew.setRating(Integer.parseInt((listBoxRatings.getSelectedValue())));
				setDegreeID(degreeImportanceNew);
			}
		}) ;
		txtWeightage.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> value) {
				degreeImportanceNew.setWeightage(Integer.parseInt(value.getValue()));
			}
		});
	}
	
	public void setListBoxDegreeImportance(ArrayList<DegreeImportance> arrayListDegreeImportance) {
		addDegreeOfImportanceSettingsView.getTxtName().setVisible(false);
		listBoxDegreeImportance.setVisible(true);
		arrayDegreeImportance = arrayListDegreeImportance;
		for(DegreeImportance degreeImportance : arrayListDegreeImportance)
			if(degreeImportance.getChecked() == 1)
				listBoxDegreeImportance.addItem(degreeImportance.getDegreeImportanceName(), String.valueOf(degreeImportance.getDegreeImportanceID()));
		setComments( arrayListDegreeImportance);
	}
	
	private void setComments(final ArrayList<DegreeImportance> arrayListDegreeImportance) {
		for(DegreeImportance degreeImportance: arrayListDegreeImportance) 
			if(listBoxDegreeImportance.getSelectedValue().equalsIgnoreCase(degreeImportance.getDegreeImportanceName()))
				txtAreaComment.setText(degreeImportance.getComments());
		listBoxDegreeImportance.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				for(DegreeImportance degreeImportance: arrayListDegreeImportance) 
					if(listBoxDegreeImportance.getSelectedValue().equalsIgnoreCase(degreeImportance.getDegreeImportanceName()))
						txtAreaComment.setText(degreeImportance.getComments());
			}
		});
	}
	
	public void setDegreeImportance(DegreeImportance degreeImportanceNew, int companyID) {
		degreeImportanceNew.setCompanyID(companyID);
		if(degreeImportanceNew.getDegreeImportanceName() == null)
			degreeImportanceNew.setDegreeImportanceName(listBoxDegreeImportance.getSelectedValue());
		changeHandlers(degreeImportanceNew);
		degreeImportanceNew.setComments(txtAreaComment.getText());
		setDegreeID(degreeImportanceNew);
	}
	
	private void setDegreeID(DegreeImportance degreeImportanceNew) {
		for(int i=0; i<arrayDegreeImportance.size(); i++) {
			if(Integer.parseInt(listBoxDegreeImportance.getSelectedValue()) == arrayDegreeImportance.get(i).getDegreeImportanceID())
				degreeImportanceNew.setDegreeImportanceID(arrayDegreeImportance.get(i).getDegreeImportanceID());
		}
	}
	
	public float calculateImpactRatings() {	
		if(txtWeightage.getValue().length()>0 && txtWeightage.getValue() != null)
			resultRatings = (Integer.parseInt(listBoxRatings.getSelectedItemText()) * Integer.parseInt(txtWeightage.getText())) / 100;
		
		listBoxRatings.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				resultRatings = (Integer.parseInt(listBoxRatings.getSelectedItemText()) * Integer.parseInt(txtWeightage.getText())) / 100;
			}
		});
		
		txtWeightage.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				resultRatings = (Integer.parseInt(listBoxRatings.getSelectedItemText()) * Integer.parseInt(txtWeightage.getText())) / 100;
			}
		});
		
		return resultRatings;
	}

	public AddDegreeOfImportanceSettingsView getAddDegreeOfImportanceSettingsView() {
		return addDegreeOfImportanceSettingsView;
	}

	public TextArea getTxtAreaComment() {
		return txtAreaComment;
	}

	public void setTxtAreaComment(TextArea txtAreaComment) {
		this.txtAreaComment = txtAreaComment;
	}

	public TextBox getTxtWeightage() {
		return txtWeightage;
	}

	public void setTxtWeightage(TextBox txtWeightage) {
		this.txtWeightage = txtWeightage;
	}

	public ListBox getListBoxRatings() {
		return listBoxRatings;
	}

	public void setListBoxRatings(ListBox listBoxRatings) {
		this.listBoxRatings = listBoxRatings;
	}

	public ListBox getListBoxDegreeImportance() {
		return listBoxDegreeImportance;
	}
		
}
