package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.data.FinalAuditablesViewData;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class FinalAuditablesView extends Composite {

	private static FinalAuditablesViewUiBinder uiBinder = GWT.create(FinalAuditablesViewUiBinder.class);

	private FinalAuditablesViewData finalAuditablesViewData = new FinalAuditablesViewData();

	interface FinalAuditablesViewUiBinder extends UiBinder<Widget, FinalAuditablesView> {
	}

	@UiField
	HorizontalPanel hpnlHeading;
	@UiField
	VerticalPanel areas;

	public FinalAuditablesView(ContentPanel cp, VerticalPanel vpnlFinalAuditable, Employee loggedInUser) {
		initWidget(uiBinder.createAndBindUi(this));
		hpnlHeading.setSpacing(5);
		LabelHeading lblSerialNo = new LabelHeading();
		lblSerialNo.setWidth("50px");
		lblSerialNo.setText("Sr#");
		LabelHeading lblStatus = new LabelHeading();
		lblStatus.setWidth("100px");
		lblStatus.setText("Status");
		LabelHeading lblUnitHeading = new LabelHeading();
		lblUnitHeading.setText("Auditable Unit");
		//Label lblObjHeading = new Label("Objective");
		lblUnitHeading.setWidth("600px");
		//lblObjHeading.setWidth("250px");
		
		LabelHeading lblDivision = new LabelHeading(InternalAuditConstants.STRATEGICLOCATION);
		lblDivision.setWidth("180px");
		
		LabelHeading lblDepartments = new LabelHeading(InternalAuditConstants.STRATEGICDEPT);
		lblDepartments.setWidth("180px");
		
		hpnlHeading.add(lblSerialNo);
		hpnlHeading.add(lblUnitHeading);
		hpnlHeading.add(lblDivision);
		hpnlHeading.add(lblDepartments);
		hpnlHeading.add(lblStatus);
		//lblObjHeading.addStyleName("labelHeading");

		finalAuditablesViewData.setData(cp, this, vpnlFinalAuditable, loggedInUser);
	}

	public VerticalPanel getAreas() {
		return areas;
	}

	public void setAreas(VerticalPanel areas) {
		this.areas = areas;
	}

}
