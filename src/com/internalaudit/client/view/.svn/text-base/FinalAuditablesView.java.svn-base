package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.view.data.FinalAuditablesViewData;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class FinalAuditablesView extends Composite {

	private static FinalAuditablesViewUiBinder uiBinder = GWT
			.create(FinalAuditablesViewUiBinder.class);
	
	private FinalAuditablesViewData finalAuditablesViewData = new FinalAuditablesViewData();

	interface FinalAuditablesViewUiBinder extends
			UiBinder<Widget, FinalAuditablesView> {
	}

	@UiField VerticalPanel areas;
	
	public FinalAuditablesView(ContentPanel cp, VerticalPanel vpnlFinalAuditable) {
		initWidget(uiBinder.createAndBindUi(this));
		
		finalAuditablesViewData.setData(cp, this, vpnlFinalAuditable);
		
	}

	public VerticalPanel getAreas() {
		return areas;
	}

	public void setAreas(VerticalPanel areas) {
		this.areas = areas;
	}



}
