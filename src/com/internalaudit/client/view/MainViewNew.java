package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainViewNew extends Composite {

	private static MainViewNewUiBinder uiBinder = GWT.create(MainViewNewUiBinder.class);

	interface MainViewNewUiBinder extends UiBinder<Widget, MainViewNew> {
	}

	public MainViewNew() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
