package com.internalaudit.client.view.Scheduling;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.internalaudit.client.presenter.AuditAreasPresenter.Display;


public class AuditAreasView extends Composite implements Display {

	private static AuditAreasViewUiBinder uiBinder = GWT
			.create(AuditAreasViewUiBinder.class);

	interface AuditAreasViewUiBinder extends UiBinder<Widget, AuditAreasView> {
		
	}
	
	@UiField VerticalPanel mainPanel;
	@UiField Button backButton;
	
	public AuditAreasView() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}


	public VerticalPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(VerticalPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public Button getBackButton() {
		return backButton;
	}


	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}


	
	
}
