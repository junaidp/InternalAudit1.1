package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AuditUniverseIdentificationView extends Composite {

	private static AuditUniverseIdentificationViewUiBinder uiBinder = GWT
			.create(AuditUniverseIdentificationViewUiBinder.class);

	interface AuditUniverseIdentificationViewUiBinder extends UiBinder<Widget, AuditUniverseIdentificationView> {
	}

	@UiField
	VerticalPanel mainPanel;
	private boolean headingAdded = false;
	private int tab;
//	private VerticalPanel vpnlStrategic;
//	private VerticalPanel vpnlOperation;
//	private VerticalPanel vpnlReporting;
//	private VerticalPanel vpnlCompliance;

	private ArrayList<AuditUniverseStrategicView> strategicList = new ArrayList<AuditUniverseStrategicView>();
	ContentPanel cp;
	private AuditUniverseStrategicView auditUniverseStrategicView;

	public AuditUniverseIdentificationView(ContentPanel cp) {
		this.cp = cp;

		initWidget(uiBinder.createAndBindUi(this));
		bind();

	}

	private void bind() {
		mainPanel.add(flexPanelLayoutStrategic());
		//auditUniverseIdentificationTabs();
		// btnAmend.setTitle("Send back for amendments");
	}

	public void setHandlers() {

	}

	private Widget flexPanelLayoutStrategic() {
		VerticalLayoutContainer strategicPanel = new VerticalLayoutContainer();
		strategicPanel.setScrollMode(ScrollMode.AUTOY);
//		final VerticalPanel vpnlMain = new VerticalPanel();
//		final VerticalPanel vpnlStrategic = new VerticalPanel();
//		strategicPanel.add(vpnlStrategic);
		strategicPanel.setSize("1200px", "300px");

		// final HorizontalPanel hpnlStrategic = new HorizontalPanel();
		// hpnlStrategic.setWidth("700px");
		HorizontalPanel hpnlStrategicHeader = new HorizontalPanel();
		hpnlStrategicHeader.add(new AuditUniverseStrategicViewHeading());

		final Image btnAdd = new Image("images/add.png");
		btnAdd.addStyleName("pointerStyle");
		hpnlStrategicHeader.add(btnAdd);

		strategicPanel.add(hpnlStrategicHeader);
//		strategicPanel.add(strategicPanel);
		final HorizontalPanel hpnlButtonsInitiator = new HorizontalPanel();
		final HorizontalPanel hpnlButtonsApprovar = new HorizontalPanel();
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setWidth("750px");
		hpnlButtonsInitiator.add(hpnlSpace);
		hpnlButtonsInitiator.setSpacing(2);
		hpnlButtonsInitiator.setVisible(false);
		hpnlButtonsApprovar.add(hpnlSpace);
		hpnlButtonsApprovar.setSpacing(2);
		hpnlButtonsApprovar.setVisible(false);
		final VerticalPanel vpnlStrategicData = new VerticalPanel();
		// btnAdd.getElement().getStyle().setPaddingLeft(1154, Unit.PX);
		btnAdd.getElement().getStyle().setMarginTop(10, Unit.PX);
		// vpnlStrategic.add(btnAdd);

		strategicPanel.add(vpnlStrategicData);
		strategicPanel.add(hpnlButtonsInitiator);
		strategicPanel.add(hpnlButtonsApprovar);
		// vpnlStrategicData.add(hpnlStrategic);
		auditUniverseStrategicView = new AuditUniverseStrategicView();
		auditUniverseStrategicView.getAuditUniverseStrategicViewData().setData();
		auditUniverseStrategicView.getAuditUniverseStrategicViewData().setListBoxStrategicData(auditUniverseStrategicView);
//		auditUniverseStrategicView.getAuditUniverseStrategicViewData().fetchDivisions();
//		auditUniverseStrategicView.getAuditUniverseStrategicViewData().fetchObjectiveOwners();

		vpnlStrategicData.clear();
		strategicList.clear();
		auditUniverseStrategicView.getAuditUniverseStrategicViewData().fetchStrategic(vpnlStrategicData,
				hpnlButtonsInitiator, hpnlButtonsApprovar, btnAdd);

		// cp.addBeforeExpandHandler(new BeforeExpandHandler(){
		//
		// @Override
		// public void onBeforeExpand(BeforeExpandEvent event) {
		// vpnlStrategicData.clear();
		// strategicList.clear();
		// auditUniverseStrategicView.getAuditUniverseStrategicViewData().fetchStrategic(vpnlStrategicData,
		// hpnlButtonsInitiator, hpnlButtonsApprovar, btnAdd, tab );
		//
		// }});

		btnAdd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// btnAdd.setEnabled(false);
				auditUniverseStrategicView = new AuditUniverseStrategicView();
				auditUniverseStrategicView.getAuditUniverseStrategicViewData().setListBoxStrategicData(auditUniverseStrategicView);
				auditUniverseStrategicView.getHpnlButtonsApprovar().setVisible(false);
				auditUniverseStrategicView.getHpnlButtonInitiator().setVisible(true);
				auditUniverseStrategicView.getAuditUniverseStrategicViewData().fetchDepartmentsForNewRecord(auditUniverseStrategicView);
//				Window.alert(auditUniverseStrategicView.getListStrategicTabs().getSelectedValue());
//				tab = Integer.parseInt(auditUniverseStrategicView.getListStrategicTabs().getSelectedValue());
				// auditUniverseStrategicView.getAuditUniverseStrategicViewData().fetchObjectiveOwnersForNewRecord(auditUniverseStrategicView);

				vpnlStrategicData.add(auditUniverseStrategicView);
				// vpnlStrategicData.insert(auditUniverseStrategicView, 1);
				// Plus icon was not working in Reoprting and Compliance
				auditUniverseStrategicView.getBtnSave().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						auditUniverseStrategicView.getAuditUniverseStrategicViewData().saveStrategic(
								auditUniverseStrategicView, vpnlStrategicData, hpnlButtonsInitiator,
								hpnlButtonsApprovar, btnAdd, "save", auditUniverseStrategicView.getBtnSave());
					}
				});

				auditUniverseStrategicView.getBtnSubmit().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						auditUniverseStrategicView.getAuditUniverseStrategicViewData().saveStrategic(
								auditUniverseStrategicView, vpnlStrategicData, hpnlButtonsInitiator,
								hpnlButtonsApprovar, btnAdd, "submit", auditUniverseStrategicView.getBtnSubmit());
					}
				});
			}
		});

		return strategicPanel;
	}

//	public void auditUniverseIdentificationTabs() {
//
//		VerticalPanel vp = new VerticalPanel();
//
//		final PlainTabPanel panel = new PlainTabPanel();
//		vpnlStrategic = new VerticalPanel();
//		vpnlOperation = new VerticalPanel();
//		vpnlReporting = new VerticalPanel();
//		vpnlCompliance = new VerticalPanel();
//		panel.setResizeTabs(true);
//
//		panel.add(vpnlStrategic, "Strategic");
//		panel.add(vpnlOperation, "Operations");
//		panel.add(vpnlReporting, "Reporting");
//		panel.add(vpnlCompliance, "Compliance");
//
//		vpnlStrategic.add(flexPanelLayoutStrategic(0));
//
//		panel.addSelectionHandler(new SelectionHandler<Widget>() {
//
//			@Override
//			public void onSelection(SelectionEvent<Widget> event) {
//				TabPanel panel = (TabPanel) event.getSource();
//				Widget w = event.getSelectedItem();
//				TabItemConfig config = panel.getConfig(w);
//				if (config.getText().equalsIgnoreCase("strategic")) {
//					vpnlStrategic.clear();
//					vpnlStrategic.add(flexPanelLayoutStrategic(0));
//				} else if (config.getText().equalsIgnoreCase("operations")) {
//					vpnlOperation.clear();
//					vpnlOperation.add(flexPanelLayoutStrategic(1));
//				} else if (config.getText().equalsIgnoreCase("Reporting")) {
//					vpnlReporting.clear();
//					vpnlReporting.add(flexPanelLayoutStrategic(2));
//				} else if (config.getText().equalsIgnoreCase("Compliance")) {
//					vpnlCompliance.clear();
//					vpnlCompliance.add(flexPanelLayoutStrategic(3));
//				}
//
//			}
//		});
//		vp.add(panel);
//		mainPanel.add(vp);
//	}

}
