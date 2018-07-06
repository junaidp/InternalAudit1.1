package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class KeyRiskAndExistingControlsView extends Composite {
   @UiField
   ListBox listBoxRisk;
    @UiField
    ListBox listBoxExistingCtrl;
    
		
	
	private static KeyRiskAndExistingControlsViewUiBinder uiBinder = GWT
			.create(KeyRiskAndExistingControlsViewUiBinder.class);

	interface KeyRiskAndExistingControlsViewUiBinder extends UiBinder<Widget, KeyRiskAndExistingControlsView> {
	}

	public KeyRiskAndExistingControlsView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		
		listBoxRisk.setWidth("170px");
	
		listBoxExistingCtrl.setWidth("170px");
		listBoxExistingCtrl.addStyleName("txtShrikedWidth");
		listBoxExistingCtrl.addStyleName("txtShrikedWidth");
	}

	public ListBox getListBoxRisk() {
		return listBoxRisk;
	}

	public void setListBoxRisk(ListBox listBoxRisk) {
		this.listBoxRisk = listBoxRisk;
	}

	public ListBox getListBoxExistingCtrl() {
		return listBoxExistingCtrl;
	}

	public void setListBoxExistingCtrl(ListBox listBoxExistingCtrl) {
		this.listBoxExistingCtrl = listBoxExistingCtrl;
	}

	
}

