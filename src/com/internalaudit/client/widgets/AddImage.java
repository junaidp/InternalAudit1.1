package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;

public class AddImage extends FocusPanel {
	public AddImage() {
		final Image addImage = new Image("images/add.png");
		add(addImage);
		addStyleName("w3-right");
		addStyleName("pointerStyle");
	}
}
