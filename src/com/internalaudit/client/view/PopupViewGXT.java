package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class PopupViewGXT {

	public PopupViewGXT(Widget widget, String heading) {
		final Dialog simple = new Dialog();

		simple.setPixelSize(400, 200);
		simple.setResizable(false);
		simple.setHideOnButtonClick(true);
		simple.getButtonBar().clear();
		VerticalLayoutContainer v = new VerticalLayoutContainer();
		v.add(widget);
		simple.setWidget(v);
		v.setScrollMode(ScrollMode.AUTOY);
		v.getElement().getStyle().setPaddingRight(10, Unit.PX);
		simple.setHeadingText(heading);
		simple.show();
	}
}