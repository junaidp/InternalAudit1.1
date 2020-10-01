package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.SettingMenuPresenter.Display;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SettingMenuView extends VerticalLayoutContainer implements Display {
	
	private Employee loggedInUser;
	private Anchor anchorChangePassword = new Anchor("Change Password");
	private Anchor anchorDivisionDepartment = new Anchor("Division | Department ");
	private VerticalLayoutContainer vpnlMenuDetailedView = new VerticalLayoutContainer(); 
	private Button btnBack = new Button("Back");
	
	public SettingMenuView(Employee loggedInUser){
		this.loggedInUser = loggedInUser;
		add(layout());
		//this.setSize("320px", "200px");
	}
	
	private Widget layout() {
		HorizontalPanel hpnlTab = new HorizontalPanel();
		hpnlTab.setWidth("1200px");
		VerticalPanel vpnlSideMenu = new VerticalPanel();
		VerticalPanel vpnlDetailContainerMain = new VerticalPanel();
		vpnlSideMenu.setWidth("150px");
		vpnlMenuDetailedView.setWidth("1040px");
		vpnlDetailContainerMain.setWidth("1040px");
		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setWidth("1040px");
		contentPanel.add(vpnlDetailContainerMain);
		hpnlTab.add(vpnlSideMenu);
		hpnlTab.add(contentPanel);
		vpnlDetailContainerMain.add(vpnlMenuDetailedView);	
		
		sideMenuItems(vpnlSideMenu);
		sideMenuDetailedView(vpnlMenuDetailedView);

		vpnlDetailContainerMain.add(btnBack);
		btnBack.getElement().getStyle().setMarginLeft(820, Unit.PX);

		return hpnlTab;
	}

	private void sideMenuItems(VerticalPanel vpnlSideMenu) {
		vpnlSideMenu.addStyleName("sidebarNew w3-cobalt  w3-bar-block");
		anchorStyle(anchorChangePassword);
		anchorStyle(anchorDivisionDepartment);
		vpnlSideMenu.add(anchorChangePassword);
		vpnlSideMenu.add(anchorDivisionDepartment);
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setSize("35px", "650px");
		vpnlSideMenu.setWidth("100%");
		vpnlSideMenu.add(hpnlSpace);
	}

	private void sideMenuDetailedView(VerticalLayoutContainer vpnlMenuDetailedView) {
//		vpnlMenuDetailedView.addStyleName("w3-border-bottom");
		vpnlMenuDetailedView.getElement().getStyle().setMarginLeft(20, Unit.PX);
		vpnlMenuDetailedView.add(new ChangePassword(loggedInUser));
		sideMenuHandlers();
	}
	
	private void anchorStyle(Anchor anchor) {
		anchor.getElement().getStyle().setColor("#FFFFFF");
		anchor.setSize("150px", "20px");
	}
	
	private void sideMenuHandlers() {
		anchorChangePassword.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				vpnlMenuDetailedView.clear();
				vpnlMenuDetailedView.add(new ChangePassword(loggedInUser));
			}
		});
		
		anchorDivisionDepartment.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				vpnlMenuDetailedView.clear();
				vpnlMenuDetailedView.add(new AddDivisionDepartmentView());
			}
		});
		
		btnBack.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				History.newItem("main");
			}
		});
	}
	
}
