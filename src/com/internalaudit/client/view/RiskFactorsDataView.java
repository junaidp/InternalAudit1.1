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

public class RiskFactorsDataView extends VerticalPanel{
	
	private ListBox listBoxRiskFactors;
	private ListBox listBoxProbability;
	private Image imgRiskRating;
	private ArrayList<RiskFactor> arrayRiskFactor ;
	private AddRiskFactorsSettingsView riskFactorsSettingsView ;

	public RiskFactorsDataView() {
		add(layoutMain());
//		updateProbilityImage();
	}

	private Widget layoutMain() {
		arrayRiskFactor = new ArrayList<RiskFactor>();
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
	
	private void changeHandlers(final RiskFactor riskFactorToSave) {
//		riskFactorsSettingsView.getTxtRiskFactors().addValueChangeHandler(new ValueChangeHandler<String>() {
//			
//			@Override
//			public void onValueChange(ValueChangeEvent<String> riskName) {
//				riskFactorToSave.setRiskName(riskName.getValue());
//			}
//		});
		riskFactorsSettingsView.getTxtRiskDescription().addValueChangeHandler(new ValueChangeHandler<String>() {

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
	}
	
	private void setDescription(String listBoxValue) {
		for(RiskFactor riskFactor: arrayRiskFactor) 
			if(listBoxValue.equalsIgnoreCase(riskFactor.getRiskName()))
				riskFactorsSettingsView.getTxtRiskDescription().setText(riskFactor.getRiskDescription());
		listBoxRiskFactors.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				for(RiskFactor riskFactor: arrayRiskFactor) 
					if(listBoxRiskFactors.getSelectedValue().equalsIgnoreCase(riskFactor.getRiskName()))
						riskFactorsSettingsView.getTxtRiskDescription().setText(riskFactor.getRiskDescription());
			}
		});
	}
	
	private void setRiskID(RiskFactor riskFactorToSave) {
		for(int i = 0; i < arrayRiskFactor.size(); i++) {			
			if(listBoxRiskFactors.getSelectedItemText().equals(arrayRiskFactor.get(i).getRiskName()))
				riskFactorToSave.setRiskId(arrayRiskFactor.get(i).getRiskId());
		}
	}
	
	public void setLisBoxRiskFactors(ArrayList<RiskFactor> arrayListRiskFactors) {
		listBoxRiskFactors.setVisible(true);
		riskFactorsSettingsView.getTxtRiskFactors().setVisible(false);
		arrayRiskFactor = arrayListRiskFactors;
		for(RiskFactor riskFactor : arrayListRiskFactors)
			if(riskFactor.getChecked() != 0)
				listBoxRiskFactors.addItem(riskFactor.getRiskName());
		setDescription(listBoxRiskFactors.getSelectedValue());
	}

	public void setRiskFactors(RiskFactor riskFactorToSave, int companyID) {
		riskFactorToSave.setCompanyID(companyID);
		if(riskFactorToSave.getRiskDescription() == null)
			riskFactorToSave.setRiskDescription(riskFactorsSettingsView.getTxtRiskDescription().getText());
		if(riskFactorToSave.getRiskName() == null)
			riskFactorToSave.setRiskName(listBoxRiskFactors.getSelectedValue());
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
