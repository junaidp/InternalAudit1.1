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
import com.googlecode.gwt.crypto.util.Str;
import com.internalaudit.shared.DegreeImportance;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.StrategicDegreeImportance;

public class StrategicDegreeImportanceView extends HorizontalPanel {
	
	private ListBox listBoxDegreeImportance;
	private TextArea txtAreaComment;;
	private TextBox txtWeightage;
	private ListBox listBoxRatings;
	private ArrayList<StrategicDegreeImportance> arrayDegreeImportance;
	private AddDegreeOfImportanceSettingsView addDegreeOfImportanceSettingsView;
	private float resultRatings = 0;
	
	public StrategicDegreeImportanceView(){
		add(layout());
//		changeHandlers(null);
	}

	private Widget layout() {	
		arrayDegreeImportance = new ArrayList<StrategicDegreeImportance>();
		addDegreeOfImportanceSettingsView = new AddDegreeOfImportanceSettingsView();
//		obj.set
		FlexTable flexPanel = addDegreeOfImportanceSettingsView.getFlexPanel();
		addDegreeOfImportanceSettingsView.invisibleFiels();
		
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

	private void changeHandlers(final StrategicDegreeImportance strategicDegreeImportanceNew) {
		txtAreaComment.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> text) {
				strategicDegreeImportanceNew.setComments(text.getValue());
			}
		});
		listBoxDegreeImportance.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent value) {
				strategicDegreeImportanceNew.setRatings(Integer.parseInt((listBoxRatings.getSelectedValue())));
				setDegreeID(strategicDegreeImportanceNew);
			}
		}) ;
		txtWeightage.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> value) {
				strategicDegreeImportanceNew.setWeightage(Integer.parseInt(value.getValue()));
			}
		});
		listBoxRatings.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				strategicDegreeImportanceNew.setRatings(Integer.parseInt(listBoxRatings.getSelectedValue()));
			}
		});
	}
	
	public void setListBoxDegreeImportance(ArrayList<StrategicDegreeImportance> arrayStrategicDegreeImportance) {
		addDegreeOfImportanceSettingsView.getTxtName().setVisible(false);
		listBoxDegreeImportance.setVisible(true);
		arrayDegreeImportance = arrayStrategicDegreeImportance;
		for(StrategicDegreeImportance strategicDegreeImportance : arrayStrategicDegreeImportance)
			if(strategicDegreeImportance.getCheck() == 0)
				listBoxDegreeImportance.addItem(strategicDegreeImportance.getDegreeImportanceID().getDegreeImportanceName(), String.valueOf(strategicDegreeImportance.getDegreeImportanceID()));
		setComments(arrayStrategicDegreeImportance);
	}
	
	private void setComments(final ArrayList<StrategicDegreeImportance> arrayListDegreeImportance) {
		for(StrategicDegreeImportance strategicDegreeImportance : arrayListDegreeImportance) 
			if(listBoxDegreeImportance.getSelectedValue().equalsIgnoreCase(strategicDegreeImportance.getDegreeImportanceID().getDegreeImportanceName()))
				txtAreaComment.setText(strategicDegreeImportance.getComments());
		listBoxDegreeImportance.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				for(StrategicDegreeImportance strategicDegreeImportance: arrayListDegreeImportance) 
					if(listBoxDegreeImportance.getSelectedValue().equalsIgnoreCase(strategicDegreeImportance.getDegreeImportanceID().getDegreeImportanceName()))
						txtAreaComment.setText(strategicDegreeImportance.getComments());
			}
		});
	}
	
	public void setDegreeImportance(StrategicDegreeImportance strategicDegreeImportance) {
//		strategicDegreeImportance.setCompanyID(companyID);
//		if(strategicDegreeImportance.getDegreeImportanceID().getDegreeImportanceName() == null)
//			strategicDegreeImportance.getDegreeImportanceID().setDegreeImportanceName(listBoxDegreeImportance.getSelectedValue());
		changeHandlers(strategicDegreeImportance);
//		degreeImportanceNew.setComments(txtAreaComment.getText());
		setDegreeID(strategicDegreeImportance);
	}
	
	private void setDegreeID(StrategicDegreeImportance strategicDgreeImportanceNew) {
		for(int i=0; i<arrayDegreeImportance.size(); i++) {
			if(listBoxDegreeImportance.getSelectedItemText().equalsIgnoreCase(arrayDegreeImportance.get(i).getDegreeImportanceID().getDegreeImportanceName())) {
				strategicDgreeImportanceNew.setId(arrayDegreeImportance.get(i).getId());
				strategicDgreeImportanceNew.setDegreeImportanceID(arrayDegreeImportance.get(i).getDegreeImportanceID());
				strategicDgreeImportanceNew.setStrategicId(arrayDegreeImportance.get(i).getStrategicId()); 
			}
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
	
	public void enableFields(boolean flag, String status) {
		if(!flag)
			addDegreeOfImportanceSettingsView.invisibleFiels();
		if(!flag && status.equalsIgnoreCase("initiated"))
			addDegreeOfImportanceSettingsView.invisibleAllFiels();
		disableFields(flag);
	}

	public void disableFields(boolean flag) {
		txtAreaComment.setEnabled(flag);
		txtWeightage.setEnabled(flag);
		listBoxDegreeImportance.setEnabled(flag);
		listBoxRatings.setEnabled(flag);
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
