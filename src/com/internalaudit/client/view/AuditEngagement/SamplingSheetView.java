package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.SamplingExcelSheetEntity;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SamplingSheetView extends VerticalPanel {

	VerticalLayoutContainer mainContainer = new VerticalLayoutContainer();
	SamplingInputGrid sampligGrid;
	Button btnSubmit = new Button("Submit");

	public SamplingSheetView(ArrayList<SamplingExcelSheetEntity> result, TextBox lblPopulationData,
			TextBox lblSamplingSizeData, ListBox listBoxSamplingMethod) {

		sampligGrid = new SamplingInputGrid(result, lblPopulationData, lblSamplingSizeData, listBoxSamplingMethod);
		add(sampligGrid);
		// add(btnSubmit);
	}

}