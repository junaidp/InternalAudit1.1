package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.AuditEngagementPresenter.Display;

public class AuditEngagementView extends Composite implements Display {

	private static AuditEngagementViewUiBinder uiBinder = GWT
			.create(AuditEngagementViewUiBinder.class);

	interface AuditEngagementViewUiBinder extends
			UiBinder<Widget, AuditEngagementView> {
	}

	@UiField
	VerticalPanel page;
	@UiField
	VerticalPanel imagePanel;
	
	
	private Image sync = new Image("refresh.png");
	
	public AuditEngagementView() {
		
		initWidget(uiBinder.createAndBindUi(this));
		imagePanel.add(sync);
		sync.setStyleName("pointerStyle");
	}

	public VerticalPanel getPage() {
		return page;
	}

	public void setPage(VerticalPanel page) {
		this.page = page;
	}
	
	
	public Image getSyncBtn()
	{
		return sync;
	}

	}
