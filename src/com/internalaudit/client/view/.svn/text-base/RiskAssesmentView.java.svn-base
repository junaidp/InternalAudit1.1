package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.data.RiskAssesmentStrategicViewData;
import com.internalaudit.shared.InternalAuditConstants;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent;
import com.sencha.gxt.widget.core.client.event.BeforeExpandEvent.BeforeExpandHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class RiskAssesmentView extends Composite {

	private static RiskAssesmentViewUiBinder uiBinder = GWT
			.create(RiskAssesmentViewUiBinder.class);

	interface RiskAssesmentViewUiBinder extends
			UiBinder<Widget, RiskAssesmentView> {
	}
	
	@UiField VerticalPanel mainPanel;
	private Button saveRiskAssesment = new Button("Submit");

	
	public RiskAssesmentView(ContentPanel cp) {
		initWidget(uiBinder.createAndBindUi(this));
		saveRiskAssesment.setVisible(false);
		
		cp.addBeforeExpandHandler(new BeforeExpandHandler(){

			@Override
			public void onBeforeExpand(BeforeExpandEvent event) {
				auditUniverseIdentificationTabs();
			}});
	}
	
	public void auditUniverseIdentificationTabs(){

		VerticalPanel vp = new VerticalPanel();

		SelectionHandler<Widget> handler = new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				TabPanel panel = (TabPanel) event.getSource();
				Widget w = event.getSelectedItem();
				TabItemConfig config = panel.getConfig(w);
				Info.display("Message", "'" + config.getText() + "' Selected");
			}
		};

		final PlainTabPanel panel = new PlainTabPanel();
		panel.setWidth("1000px");
		final VerticalPanel vpnlStrategic = new VerticalPanel();
		final VerticalPanel vpnlOperation = new VerticalPanel();
		final VerticalPanel vpnlReporting = new VerticalPanel();
		final VerticalPanel vpnlCompliance = new VerticalPanel();
		
		panel.add(vpnlStrategic, "Strategic");
		panel.add(vpnlOperation, "Operations");
		panel.add(vpnlReporting, "Reporting");
		panel.add(vpnlCompliance, "Compliance");
		vpnlStrategic.add(flexPanelLayoutStrategic(0));
		
		panel.addSelectionHandler(new SelectionHandler<Widget>(){

			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				 TabPanel panel = (TabPanel) event.getSource();
			        Widget w = event.getSelectedItem();
			        TabItemConfig config = panel.getConfig(w);
			        if(config.getText().equalsIgnoreCase("strategic")){
			        	vpnlStrategic.clear();
			        	vpnlStrategic.add(flexPanelLayoutStrategic(0));
			        }
			        else if(config.getText().equalsIgnoreCase("operations")){
			        	vpnlOperation.clear();
			        	vpnlOperation.add(flexPanelLayoutStrategic(1));
				        }
			        else if(config.getText().equalsIgnoreCase("Reporting")){
			        	vpnlReporting.clear();
			        	vpnlReporting.add(flexPanelLayoutStrategic(2));
			        }
			        else if(config.getText().equalsIgnoreCase("Compliance")){
			        	vpnlCompliance.clear();
			        	vpnlCompliance.add(flexPanelLayoutStrategic(3));
				        }
					
			        
			}});
		
		vp.add(panel);
		mainPanel.clear();
		mainPanel.add(vp);
	}
	
	private Widget flexPanelLayoutStrategic(int tab) {
		
		
		VerticalPanel strategicPanel = new VerticalPanel();
		strategicPanel.add(saveRiskAssesment);

		strategicPanel.setHeight("100%");
		HorizontalPanel hpnlStrategic = new HorizontalPanel();
		Label lblStrategicObjective = new Label(InternalAuditConstants.STRATEGICOBJECTIVE);
		Label lblRiskFactor = new Label(InternalAuditConstants.RISKFACTORS);
		Label lblRating = new Label(InternalAuditConstants.RATING);
		lblStrategicObjective.setStyleName("blue");
		lblRiskFactor.setStyleName("blue");
		lblRating.setStyleName("blue");
		
		hpnlStrategic.add(lblStrategicObjective);
		hpnlStrategic.add(lblRiskFactor);
		hpnlStrategic.add(lblRating);
		
		hpnlStrategic.setWidth("800px");
		lblStrategicObjective.setWidth("110px");
		lblStrategicObjective.setWordWrap(false);
		lblRiskFactor.setWidth("142px");
		lblRating.setWidth("180px");
//		strategicPanel.add(hpnlStrategic);
		RiskAssesmentStrategicViewData riskAssesmentStrategicViewData = new RiskAssesmentStrategicViewData();
		
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setHeight("400px");
		scrollPanel.setWidget(strategicPanel);
		riskAssesmentStrategicViewData.setData(strategicPanel,this, tab);
	
		return scrollPanel;
	}


	
	public Button getSaveRiskAssesment() {
		return saveRiskAssesment;
	}

	public void setSaveRiskAssesment(Button saveRiskAssesment) {
		this.saveRiskAssesment = saveRiskAssesment;
	}


}
