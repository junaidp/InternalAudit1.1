package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.user.client.ui.Label;

public class LabelHeading extends Label {

	public LabelHeading() {

		// addStyleName("w3-panel");
		addStyleName("labelDesign form-row");

	}

	public LabelHeading(String text) {
		setText(text);
		addStyleName("labelDesign form-row");
	}
}
