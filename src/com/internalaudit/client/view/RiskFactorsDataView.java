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

public class RiskFactorsDataView extends VerticalPanel{
	
	private ListBox listBoxRiskFactors;
	private ListBox listBoxProbability;
	private Image imgRiskRating;
	private ArrayList<StrategicRiskFactor> arrayStrategicRiskFactor ;
	private AddRiskFactorsSettingsView riskFactorsSettingsView ;

	public RiskFactorsDataView() {
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
		
		listBoxProbability = new ListBox();
		listBoxProbability.setWidth("50px");
		listBoxProbability.getElement().getStyle().setPaddingLeft(15, Unit.PX);
		for(int i=10; i>0; i--)
			listBoxProbability.addItem(""+i, ""+i);
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
//		riskFactorsSettingsView.getTxtRiskDescription().addValueChangeHandler(new ValueChangeHandler<String>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<String> comments) {
//				riskFactorToSave.getRiskFactorID().setRiskDescription(comments.getValue());
//			}
//		});
		
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
				riskFactorsSettingsView.getTxtRiskDescription().setText(riskFactor.getRiskFactorID().getRiskDescription());
				riskFactorsSettingsView.getTxtRiskDescription().setEnabled(false);
			}
		listBoxRiskFactors.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				for(StrategicRiskFactor riskFactor: arrayStrategicRiskFactor) 
					if(listBoxRiskFactors.getSelectedValue().equalsIgnoreCase(riskFactor.getRiskFactorID().getRiskName())) {
						riskFactorsSettingsView.getTxtRiskDescription().setText(riskFactor.getRiskFactorID().getRiskDescription());
						riskFactorsSettingsView.getTxtRiskDescription().setEnabled(false);
					}
			}
		});
	}
	
	private void setRiskID(StrategicRiskFactor riskFactorToSave) {
		for(int i = 0; i < arrayStrategicRiskFactor.size(); i++) {			
			if(listBoxRiskFactors.getSelectedItemText().equals(arrayStrategicRiskFactor.get(i).getRiskFactorID().getRiskName()))
				riskFactorToSave.getRiskFactorID().setRiskId(arrayStrategicRiskFactor.get(i).getRiskFactorID().getRiskId());
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

	public void setRiskFactors(StrategicRiskFactor riskFactorToSave, int companyID) {
//		riskFactorToSave.setCompanyID(companyID);
//		if(riskFactorToSave.getRiskFactorID().getRiskDescription() == null)
//			riskFactorToSave.getRiskFactorID().setRiskDescription(riskFactorsSettingsView.getTxtRiskDescription().getText());
//		if(riskFactorToSave.getRiskFactorID().getRiskName() == null)
//			riskFactorToSave.getRiskFactorID().setRiskName(listBoxRiskFactors.getSelectedValue());
		changeHandlers(riskFactorToSave);	
		setRiskID(riskFactorToSave);
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
}
