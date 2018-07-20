package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InformationRequestView extends Composite {

	private static InformationRequestViewUiBinder uiBinder = GWT.create(InformationRequestViewUiBinder.class);

	interface InformationRequestViewUiBinder extends UiBinder<Widget, InformationRequestView> {
	}

	public InformationRequestView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
