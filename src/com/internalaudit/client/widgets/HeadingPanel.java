package com.internalaudit.client.widgets;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class HeadingPanel extends Composite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9054749442958414530L;
	
	private HorizontalPanel headingsContainer;
	
	public HeadingPanel() {
		
		headingsContainer = new HorizontalPanel();
		initWidget(headingsContainer);
		//headingsContainer.setStyleName("headingsPanel");
		
	}

	public HorizontalPanel getHeadingsContainer() {
		return headingsContainer;
	}

	public void setHeadingsContainer(HorizontalPanel headingsContainer) {
		this.headingsContainer = headingsContainer;
	}
	
	
	
}
