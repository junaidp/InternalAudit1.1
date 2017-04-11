package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;

public class AuditPlanningView extends Composite {

	private static AuditPlanningViewUiBinder uiBinder = GWT
			.create(AuditPlanningViewUiBinder.class);

	interface AuditPlanningViewUiBinder extends
			UiBinder<Widget, AuditPlanningView> {
	}
	 private ContentPanel panel;
	 @UiField VerticalPanel mainPanel;
	public AuditPlanningView() {
		initWidget(uiBinder.createAndBindUi(this));
		bind();
	}

	private void bind() {
		if (panel == null) {
		      panel = new ContentPanel();
		      panel.setWidth("1000px");
		      panel.setHeadingText("Audit Planning");
		      panel.setBodyBorder(false);
		      AccordionLayoutContainer con = new AccordionLayoutContainer();
		      panel.add(con);
		      
		      AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);
		 
		      ContentPanel cp = new ContentPanel(appearance);
		      cp.setAnimCollapse(false);
		      cp.setHeadingText("Audit Universe Identification");
//		      cp.add(new AuditUniverseIdentificationView(cp));
		      VerticalPanel vpnlIdentification = new VerticalPanel();
		      vpnlIdentification.setHeight("400px");
		      vpnlIdentification.add(new AuditUniverseIdentificationView(cp));
		      cp.add(vpnlIdentification);
		      con.add(cp);
		 
		      cp = new ContentPanel(appearance);
		      cp.setAnimCollapse(false);
		      cp.setBodyStyleName("pad-text");
		      cp.setHeadingText("Risk Assessment");
		     
//		      cp.add(new RiskAssesmentView(cp));
		      VerticalPanel vpnlRiskAssesment = new VerticalPanel();
		      vpnlRiskAssesment.setHeight("370px");
		      vpnlRiskAssesment.add(new RiskAssesmentView(cp));
		      cp.add(vpnlRiskAssesment);
		      con.add(cp);
		     
		      cp = new ContentPanel(appearance);
		      cp.setAnimCollapse(false);
		      cp.setBodyStyleName("pad-text");
		      cp.setHeadingText("Consolidation into Audtitable Units");
		      VerticalPanel vpnl = new VerticalPanel();
		      vpnl.setHeight("400px");
		      vpnl.add(new ConsolidationHeadingView(cp));
		      cp.add(vpnl);
		      con.add(cp);
		 
		      cp = new ContentPanel(appearance);
		      cp.setAnimCollapse(false);
		      cp.setBodyStyleName("pad-text");
		      cp.setHeadingText("Risk Based Prioritization And Finalization");
		      VerticalPanel vpnlPriority = new VerticalPanel();
		      vpnlPriority.setHeight("400px");
		      vpnlPriority.add(new PrioritizationHeadingView(cp));
		      cp.add(vpnlPriority);
		      con.add(cp);
		      
		      cp = new ContentPanel(appearance);
		      cp.setAnimCollapse(false);
		      cp.setBodyStyleName("pad-text");
		      cp.setHeadingText("Final Auditables");
		      VerticalPanel vpnlFinalAuditable = new VerticalPanel();
		      vpnlFinalAuditable.setHeight("400px");
		      vpnlFinalAuditable.add(new FinalAuditablesView(cp, vpnlFinalAuditable));
		      cp.add(vpnlFinalAuditable);
		      con.add(cp);
		      
		     
		}
					
		mainPanel.add(panel);
		   
	}
	
	

}
