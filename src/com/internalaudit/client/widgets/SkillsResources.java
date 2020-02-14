package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SkillsResources extends Composite {

	private VerticalPanel hpnlMain;
	private ListBox skillsList;
	private TextBox noOfResources;

	private int availableHours;

	public SkillsResources() {

		hpnlMain = new VerticalPanel();

		initWidget(hpnlMain);

		skillsList = new ListBox();

		noOfResources = new TextBox();
		noOfResources.addStyleName("smallTEXTBOX");
		hpnlMain.add(skillsList);

		hpnlMain.add(noOfResources);

	}

	public VerticalPanel getHpnlMain() {
		return hpnlMain;
	}

	public void setHpnlMain(VerticalPanel hpnlMain) {
		this.hpnlMain = hpnlMain;
	}

	public ListBox getSkillsList() {
		return skillsList;
	}

	public void setSkillsList(ListBox skillsList) {
		this.skillsList = skillsList;
	}

	public TextBox getNoOfResources() {
		return noOfResources;
	}

	public void setNoOfResources(TextBox noOfResources) {
		this.noOfResources = noOfResources;
	}

	public int getAvailableHours() {
		return availableHours;
	}

	public void setAvailableHours(int availableHours) {
		this.availableHours = availableHours;
	}

}
