package com.internalaudit.client.view;

import java.util.ArrayList;

import javax.swing.Icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.shared.DegreeImportance;
import com.internalaudit.shared.RiskFactor;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AddRiskAssesmentFactors extends VerticalPanel {
	
	private InternalAuditServiceAsync rpcService;
	private	VerticalPanel vpnlMain = new VerticalPanel();
	private	VerticalPanel vpnlDegreeOfImportance = new VerticalPanel();
	private VerticalPanel vpnlRiskFactors = new VerticalPanel();
	private Image imgAddDegree;
	private Image imgAddRiskFactor;
	private Button btnSaveForms;
	private ArrayList<DegreeImportance> arrayListDegreeImportance;
	private ArrayList<RiskFactor> arrayListRiskFacrors;
	private int companyID;
	
	public AddRiskAssesmentFactors(int companyID) {
		rpcService = GWT.create(InternalAuditService.class);
		arrayListDegreeImportance = new ArrayList<DegreeImportance>();
		arrayListRiskFacrors = new ArrayList<RiskFactor>();
		this.companyID = companyID;
		add(layoutMain());
		handlers();
		fetchDegreeImportance();
		fetchRiskFactors();
	}
	
	private Widget layoutMain() {
		
		Label lblSetting = new Label("Setting Page");
		vpnlMain.add(lblSetting);

		LabelBold lblRiskAssesmentFactors = new LabelBold("Risk Assesment Factors");
		vpnlMain.add(lblRiskAssesmentFactors);
		
		Label lblMsg = new Label("Define your risk here");
		vpnlMain.add(lblMsg);
		
//		vpnlDegreeOfImportance.addStyleName("w3-container");
		btnSaveForms = new Button("Save");		
		btnSaveForms.setStyleName("w3-right");
		
		vpnlMain.add(layoutHeaderDegreeOfImportance());
		vpnlMain.add(vpnlDegreeOfImportance);
		vpnlMain.add(layoutHeaderRiskFactors());
		vpnlMain.add(vpnlRiskFactors);
		vpnlMain.add(btnSaveForms);

		return vpnlMain;
	}

	private Widget layoutHeaderDegreeOfImportance() {
		HorizontalPanel hpnlHeader = new HorizontalPanel();
		hpnlHeader.setWidth("1000px");
		
		LabelHeading lblDegreeImportance = new LabelHeading("Degree of importance");
		hpnlHeader.add(lblDegreeImportance);
		lblDegreeImportance.setWidth("600px");
		
		imgAddDegree = new Image("images/add.png"); 
		imgAddDegree.addStyleName("w3-right");
		hpnlHeader.add(imgAddDegree);
	
		return hpnlHeader;
	}
	
	private Widget layoutHeaderRiskFactors() {
		HorizontalPanel hpnlHeader = new HorizontalPanel();		
		hpnlHeader.setWidth("1000px");
		
		LabelHeading lblDegreeImportance = new LabelHeading("Risk Factors");
		hpnlHeader.add(lblDegreeImportance);
		lblDegreeImportance.setWidth("380px");
		
//		LabelHeading lblDegreeDescription = new LabelHeading("Risk Description");
//		hpnlHeader.add(lblDegreeDescription);
//		lblDegreeDescription.setWidth("400px");
		
		imgAddRiskFactor = new Image("images/add.png"); 
		imgAddRiskFactor.addStyleName("w3-right");
		hpnlHeader.add(imgAddRiskFactor);
	
		return hpnlHeader;
	}
	
	private void handlers() {

		imgAddDegree.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				AddDegreeOfImportanceSettingsView degreeImportanceData = new AddDegreeOfImportanceSettingsView();
				vpnlDegreeOfImportance.add(degreeImportanceData);
				degreeViewHandlers(degreeImportanceData, 0);
				setArrayListDegreeImportance(degreeImportanceData, 0); 
			}
		});
		
		imgAddRiskFactor.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent arg0) {
						AddRiskFactorsSettingsView riskFactor = new AddRiskFactorsSettingsView();
						vpnlRiskFactors.add(riskFactor);
						riskFactorViewHandlers(riskFactor, 0);
						setArrayListRiskFactor(riskFactor, 0);
					}
				});
		btnSaveForms.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				saveDegreeImportance();
				saveRiskFactor();
			}
		});
	}

	private void degreeViewHandlers(final AddDegreeOfImportanceSettingsView degreeImportanceData, final int degreeID) {
		degreeImportanceData.getImgDelete().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
//				vpnlDegreeOfImportance.remove(degreeImportanceData);
				boolean confirmToDeleteDegreeImportance = Window.confirm("Do you want to delete " + degreeImportanceData.getTxtName().getValue());
				if(confirmToDeleteDegreeImportance && companyID != 0)
					deleteDegreeImportance(degreeID);
			}
		});
	}
	
	private void riskFactorViewHandlers(final AddRiskFactorsSettingsView riskFactor, final int riskID) {
		riskFactor.getImgDelete().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
//				vpnlRiskFactors.remove(riskFactor);
				boolean confirmToDeleteRiskFactor = Window.confirm("Do you want to delete " + riskFactor.getTxtRiskFactors().getValue());
				if(confirmToDeleteRiskFactor && riskID != 0)
					deleteRiskFactor(riskID);
			}
		});
	}
	
	private void fetchRiskFactors() {
		rpcService.fetchRiskFactors(companyID, new AsyncCallback<ArrayList<RiskFactor>>() {
			
			@Override
			public void onSuccess(ArrayList<RiskFactor> arrayRiskFactors) {
				addDefaultRiskFactors(arrayRiskFactors);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Unable to fetch Default Risk Factors");
			}
		});
	} 
	
	private void addDefaultRiskFactors(ArrayList<RiskFactor> arrayRiskFactors) {
		for(RiskFactor riskFactor : arrayRiskFactors) {
		AddRiskFactorsSettingsView riskFactorView = new AddRiskFactorsSettingsView();
		vpnlRiskFactors.add(riskFactorView);
		riskFactorView.getTxtRiskFactors().setText(riskFactor.getRiskName());
//		riskFactorView.getTxtRiskDescription().setText(riskFactor.getRiskDescription());
		if(riskFactor.getChecked() == 1)
			riskFactorView.getCheckBox().setValue(true);			
		else
			riskFactorView.getCheckBox().setValue(false);	
		riskFactorViewHandlers(riskFactorView, riskFactor.getRiskId());
		setArrayListRiskFactor(riskFactorView, riskFactor.getRiskId());
		}
	}
	
	private void setArrayListRiskFactor(final AddRiskFactorsSettingsView riskFactorView, int riskId) {
		final RiskFactor riskFactorsObj = new RiskFactor();
		riskFactorsObj.setRiskName(riskFactorView.getTxtRiskFactors().getValue());
		riskFactorView.getTxtRiskFactors().addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				riskFactorsObj.setRiskName(arg0.getValue());
			}
			
		});
		
//		riskFactorsObj.setRiskDescription(riskFactorView.getTxtRiskDescription().getValue());
//		riskFactorView.getTxtRiskDescription().addValueChangeHandler(new ValueChangeHandler<String>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<String> arg0) {
//				riskFactorsObj.setRiskDescription(arg0.getValue());
//			}
//		});
		
		riskFactorsObj.setChecked(1);
		riskFactorView.getCheckBox().addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> arg0) {
				if(riskFactorView.getCheckBox().isChecked())
					riskFactorsObj.setChecked(1);
				else
					riskFactorsObj.setChecked(0);
			}
		});
		
		riskFactorsObj.setCompanyID(companyID);
//		if(riskId > 6) //1st 5 Default valuse in Table Risk Factor
		riskFactorsObj.setRiskId(riskId);
		arrayListRiskFacrors.add(riskFactorsObj);
	}

	private void fetchDegreeImportance() {
		rpcService.fetchDegreeImportance(companyID ,new AsyncCallback<ArrayList<DegreeImportance>>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Unable to fetch Default Degree Importance");
			}

			@Override
			public void onSuccess(ArrayList<DegreeImportance> arrayDegreeImportance) {
				addDefaultDegreeImportance(arrayDegreeImportance);
			}
		});
	}
	
	private void addDefaultDegreeImportance(ArrayList<DegreeImportance> arrayDegreeImportance) {
		for(DegreeImportance degreeImportance : arrayDegreeImportance) {
			AddDegreeOfImportanceSettingsView degreeImportanceViewData = new AddDegreeOfImportanceSettingsView();
			vpnlDegreeOfImportance.add(degreeImportanceViewData);
			degreeImportanceViewData.getTxtName().setText(degreeImportance.getDegreeImportanceName());
			if(degreeImportance.getChecked() == 1)
				degreeImportanceViewData.getCheckBox().setValue(true);			
			else
				degreeImportanceViewData.getCheckBox().setValue(false);	
			degreeViewHandlers(degreeImportanceViewData, degreeImportance.getDegreeImportanceID());
			setArrayListDegreeImportance(degreeImportanceViewData, degreeImportance.getDegreeImportanceID());
		}
	}
	
	private void setArrayListDegreeImportance(final AddDegreeOfImportanceSettingsView degreeImportanceData, int degreeImportanceID) {
		final DegreeImportance degreeImportanceObj = new DegreeImportance();
		degreeImportanceObj.setDegreeImportanceName(degreeImportanceData.getTxtName().getValue());
		degreeImportanceData.getTxtName().addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				degreeImportanceObj.setDegreeImportanceName(arg0.getValue());
			}
			
		});

		degreeImportanceObj.setChecked(1);
		degreeImportanceData.getCheckBox().addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> arg0) {
				if(degreeImportanceData.getCheckBox().isChecked())
					degreeImportanceObj.setChecked(1);
				else
					degreeImportanceObj.setChecked(0);
			}
		});
		
		degreeImportanceObj.setCompanyID(companyID);
//		if(degreeImportanceID > 3) //1st 3 Default valuse in Table DegreeImportance
		degreeImportanceObj.setDegreeImportanceID(degreeImportanceID);
		arrayListDegreeImportance.add(degreeImportanceObj);
	}
	
	private void saveDegreeImportance() {
		rpcService.saveDegreeImportance(arrayListDegreeImportance, new AsyncCallback<ArrayList<DegreeImportance>>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Unable to save DegreeImportance");
			}

			@Override
			public void onSuccess(ArrayList<DegreeImportance> arrayDegreeImportance) {
				vpnlDegreeOfImportance.clear();
				new DisplayAlert("DegreeImportance saved");
				arrayListDegreeImportance.clear();
				addDefaultDegreeImportance(arrayDegreeImportance);
			}
		});
	}
	
	private void saveRiskFactor() {
		rpcService.saveRiskFactor(arrayListRiskFacrors, new AsyncCallback<ArrayList<RiskFactor>>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Unable to save DegreeImportance");
			}

			@Override
			public void onSuccess(ArrayList<RiskFactor> resultRiskFactor) {
				vpnlRiskFactors.clear();
				new DisplayAlert("Risk Factors saved");
				arrayListRiskFacrors.clear();
				addDefaultRiskFactors(resultRiskFactor);
			}
		});
	}
	
	private void deleteDegreeImportance(int degreeID) {
		rpcService.deleteDegreeImportance(degreeID, new AsyncCallback<ArrayList<DegreeImportance>>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Unable to delete DegreeImportance");
			}

			@Override
			public void onSuccess(ArrayList<DegreeImportance> arrayDegreeImportance) {
				vpnlDegreeOfImportance.clear();
				new DisplayAlert("DegreeImportance deleted");
				arrayListDegreeImportance.clear();
				addDefaultDegreeImportance(arrayDegreeImportance);
			}
		});
	}
	
	private void deleteRiskFactor(int riskID) {
		rpcService.deleteRiskFactor(riskID, new AsyncCallback<ArrayList<RiskFactor>>() {

			@Override
			public void onFailure(Throwable arg0) {
				new DisplayAlert("Unable to delete RiskFactor");
			}

			@Override
			public void onSuccess(ArrayList<RiskFactor> resultArrayDegreeImportance) {
				vpnlRiskFactors.clear();
				new DisplayAlert("RiskFactor deleted");
				arrayListRiskFacrors.clear();
				addDefaultRiskFactors(resultArrayDegreeImportance);
			}
		});
	}
}
