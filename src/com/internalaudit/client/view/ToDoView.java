package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ToDoView extends Composite {

	private static ToDoViewUiBinder uiBinder = GWT.create(ToDoViewUiBinder.class);

	interface ToDoViewUiBinder extends UiBinder<Widget, ToDoView> {
	}

	public ToDoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
