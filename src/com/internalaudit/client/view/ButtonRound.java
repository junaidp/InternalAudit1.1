package com.internalaudit.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public class ButtonRound extends Button {

	Label l = new Label("");

	public ButtonRound(String name) {
		// Label l = new Label();
		l.setText(name);

		// add(l);
		setButtonText(name);
		setStyleName("buttonDesign w3-hover-blue");
	}

	public ButtonRound() {
	}

	public void setEnabled(boolean b) {

	}

	public void setButtonText(String string) {
		l.setText(string);
		setText(string);

	}

}
