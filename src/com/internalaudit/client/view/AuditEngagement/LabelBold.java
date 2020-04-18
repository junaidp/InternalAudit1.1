package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.user.client.ui.Label;

public class LabelBold extends Label {
	public LabelBold(String text) {
		addStyleName("labelDesign");
		setText(text);
	}

	public LabelBold() {
		addStyleName("labelDesign");
	}
}
