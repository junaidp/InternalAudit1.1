package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.DegreeImportance;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.StrategicRiskFactor;

public class StrategicRiskFactorView extends VerticalPanel{
	
	private ListBox listBoxRiskFactors;
	private ListBox listBoxProbability;	
	private Image imgRiskRating;
	private ArrayList<StrategicRiskFactor> arrayStrategicRiskFactor ;
	private AddRiskFactorsSettingsView riskFactorsSettingsView ;
	private TextArea txtRiskDescription;

	public StrategicRiskFactorView() {
		add(layoutMain());
//		updateProbilityImage();
	}

	private Widget layoutMain() {
		arrayStrategicRiskFactor = new ArrayList<StrategicRiskFactor>();
		riskFactorsSettingsView = new AddRiskFactorsSettingsView();
		FlexTable flexTable = riskFactorsSettingsView.getFlexPanel();
		
		listBoxRiskFactors = new ListBox();
		listBoxRiskFactors.setWidth("430px");
		listBoxRiskFactors.setVisible(false);
		flexTable.setWidget(0, 0, listBoxRiskFactors);
		
		txtRiskDescription = new TextArea();
		txtRiskDescription.setWidth("430px");
		txtRiskDescription.getElement().setPropertyString("placeholder", "Enter description here");
		txtRiskDescription.getElement().getStyle().setPaddingLeft(30, Unit.PX);
		flexTable.setWidget(0, 2, txtRiskDescription);
		
		listBoxProbability = new ListBox();
		listBoxProbability.setWidth("50px");
		listBoxProbability.getElement().getStyle().setPaddingLeft(15, Unit.PX);
		for(float i=10; i>0; i--)
			listBoxProbability.addItem(String.valueOf(i/10), ""+i);
		flexTable.setWidget(0, 5, listBoxProbability);
		
		imgRiskRating = new Image("redcircle.png");
//		imgRiskRating.setVisible(false);
		flexTable.setWidget(0, 6, imgRiskRating);
		
		return flexTable;
	}
	
	public void updateProbilityImage() {
		if(Integer.parseInt(listBoxProbability.getSelectedValue()) <= 10 && Integer.parseInt(listBoxProbability.getSelectedValue()) > 7)
			imgRiskRating.setUrl("redcircle.png");
		else if(Integer.parseInt(listBoxProbability.getSelectedValue()) <= 7 && Integer.parseInt(listBoxProbability.getSelectedValue()) > 3)	
			imgRiskRating.setUrl("yellowcircle.png");
		else 
			imgRiskRating.setUrl("greencircle.png");
	}
	
	private void changeHandlers(final StrategicRiskFactor riskFactorToSave) {
//		riskFactorsSettingsView.getTxtRiskFactors().addValueChangeHandler(new ValueChangeHandler<String>() {
//			
//			@Override
//			public void onValueChange(ValueChangeEvent<String> riskName) {
//				riskFactorToSave.setRiskName(riskName.getValue());
//			}
//		});
		
		txtRiskDescription.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> comments) {
				riskFactorToSave.setRiskDescription(comments.getValue());
			}
		});
		
		listBoxRiskFactors.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				setRiskID(riskFactorToSave);
			}
		});
		listBoxProbability.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				riskFactorToSave.setProbability(Integer.parseInt(listBoxProbability.getSelectedValue()));
			}
		});
	}
	
	private void setDescription(String listBoxValue) {
		for(StrategicRiskFactor riskFactor: arrayStrategicRiskFactor) 
			if(listBoxValue.equalsIgnoreCase(riskFactor.getRiskFactorID().getRiskName())) {
				txtRiskDescription.setText(riskFactor.getRiskDescription());
//				txtRiskDescription.setEnabled(false);
			}
		listBoxRiskFactors.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				for(StrategicRiskFactor riskFactor: arrayStrategicRiskFactor) 
					if(listBoxRiskFactors.getSelectedValue().equalsIgnoreCase(riskFactor.getRiskFactorID().getRiskName())) {
						txtRiskDescription.setText(riskFactor.getRiskDescription());
//						txtRiskDescription.setEnabled(false);
					}
			}
		});
	}
	
	private void setRiskID(StrategicRiskFactor riskFactorToSave) {
		for(int i = 0; i < arrayStrategicRiskFactor.size(); i++) {			
			if(listBoxRiskFactors.getSelectedItemText().equals(arrayStrategicRiskFactor.get(i).getRiskFactorID().getRiskName())) {
				riskFactorToSave.setId(arrayStrategicRiskFactor.get(i).getId());
				riskFactorToSave.setRiskFactorID(arrayStrategicRiskFactor.get(i).getRiskFactorID());
				riskFactorToSave.setStrategicID(arrayStrategicRiskFactor.get(i).getStrategicID());
			}
		}
	}
	
	public void setLisBoxRiskFactors(ArrayList<StrategicRiskFactor> arrayListStrategicRiskFactors) {
		listBoxRiskFactors.setVisible(true);
		riskFactorsSettingsView.getTxtRiskFactors().setVisible(false);
		arrayStrategicRiskFactor = arrayListStrategicRiskFactors;
		for(StrategicRiskFactor StrategicRiskFactor : arrayListStrategicRiskFactors)
			if(StrategicRiskFactor.getCheck() == 0)
				listBoxRiskFactors.addItem(StrategicRiskFactor.getRiskFactorID().getRiskName());
		setDescription(listBoxRiskFactors.getSelectedValue());
	}

	public void setRiskFactors(StrategicRiskFactor riskFactorToSave) {
//		riskFactorToSave.setCompanyID(companyID);
//		if(riskFactorToSave.getRiskFactorID().getRiskDescription() == null)
//			riskFactorToSave.getRiskFactorID().setRiskDescription(riskFactorsSettingsView.getTxtRiskDescription().getText());
//		if(riskFactorToSave.getRiskFactorID().getRiskName() == null)
//			riskFactorToSave.getRiskFactorID().setRiskName(listBoxRiskFactors.getSelectedValue());
		changeHandlers(riskFactorToSave);	
		setRiskID(riskFactorToSave);
		riskFactorToSave.setCheck(1); 
	}
	
	public void enableFields(boolean flag, String status) {
		if(!flag)
			riskFactorsSettingsView.invisibleIcons();
		if(!flag && status.equalsIgnoreCase("initiated") )
			riskFactorsSettingsView.invisibleAllFields();
		listBoxProbability.setEnabled(flag);
		listBoxRiskFactors.setEnabled(flag);
	}

	public AddRiskFactorsSettingsView getRiskFactorsSettingsView() {
		return riskFactorsSettingsView;
	}

	public void setRiskFactorsSettingsView(AddRiskFactorsSettingsView riskFactorsSettingsView) {
		this.riskFactorsSettingsView = riskFactorsSettingsView;
	}

	public ListBox getListBoxProbability() {
		return listBoxProbability;
	}

	public void setListBoxProbability(ListBox listBoxProbability) {
		this.listBoxProbability = listBoxProbability;
	}

	public Image getImgRiskRating() {
		return imgRiskRating;
	}

	public TextArea getTxtRiskDescription() {
		return txtRiskDescription;
	}

	public void setTxtRiskDescription(TextArea txtRiskDescription) {
		this.txtRiskDescription = txtRiskDescription;
	}
}
