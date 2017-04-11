package com.internalaudit.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class SkillsResources extends Composite {
	
	

	private HorizontalPanel hpnlMain;
	private ListBox skillsList;
	private TextBox noOfResources;
	
	private int availableHours;
	
	public SkillsResources() {
	
		hpnlMain = new HorizontalPanel();
		
		initWidget(hpnlMain);
		
		skillsList = new ListBox();
		
		noOfResources = new TextBox();
		
		hpnlMain.add(skillsList);
		
		hpnlMain.add(noOfResources);
		
	}
	
	
	public HorizontalPanel getHpnlMain() {
		return hpnlMain;
	}

	public void setHpnlMain(HorizontalPanel hpnlMain) {
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
