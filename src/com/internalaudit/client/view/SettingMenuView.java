package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
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
	private Anchor anchorCompanyLogo = new Anchor("Company Logo");
	private Anchor anchorRiskAssesmentFactors = new Anchor("Risk Assesment Factors");
	private VerticalLayoutContainer vpnlMenuDetailedView = new VerticalLayoutContainer(); 
	private Button btnBack = new Button("Back");
	private Image imgLogo = new Image();
	
	public SettingMenuView(Employee loggedInUser){
		this.loggedInUser = loggedInUser;
		add(logoLayout());
		add(layout());
	}
	
	private Widget logoLayout() {
		HorizontalPanel hpnlLogos = new HorizontalPanel();
		hpnlLogos.setWidth("1200px");
		Image imgAbilite = new Image("images/trans.png");
		hpnlLogos.add(imgAbilite);
		
		imgLogo.setSize("240px", "60px");	
		imgLogo.addStyleName("w3-right");
		imgLogo.getElement().getStyle().setMarginTop(20, Unit.PX);
		hpnlLogos.add(imgLogo);
		
		return hpnlLogos;
	} 
	
	private Widget layout() {
		HorizontalPanel hpnlTab = new HorizontalPanel();
		hpnlTab.setWidth("1200px");
		VerticalPanel vpnlSideMenu = new VerticalPanel();
		VerticalPanel vpnlDetailContainerMain = new VerticalPanel();
		
		vpnlDetailContainerMain.addStyleName("w3-border-top w3-border-blue");
		vpnlMenuDetailedView.addStyleName("w3-panel");
		
		vpnlDetailContainerMain.setWidth("1000px");
		hpnlTab.add(vpnlSideMenu);
		hpnlTab.add(vpnlDetailContainerMain);
		vpnlDetailContainerMain.add(vpnlMenuDetailedView);	
		
		sideMenuItems(vpnlSideMenu);
		sideMenuDetailedView(vpnlMenuDetailedView);

		vpnlDetailContainerMain.add(btnBack);
		btnBack.addStyleName("w3-right");

		return hpnlTab;
	}

	private void sideMenuItems(VerticalPanel vpnlSideMenu) {
		vpnlSideMenu.addStyleName("w3-container");
		vpnlSideMenu.setWidth("200px");
		vpnlSideMenu.addStyleName("sidebarNew w3-cobalt  w3-bar-block");
		anchorStyle(anchorChangePassword);
		anchorStyle(anchorDivisionDepartment);
		anchorStyle(anchorCompanyLogo);
		anchorStyle(anchorRiskAssesmentFactors);
		
		vpnlSideMenu.add(anchorChangePassword);
		vpnlSideMenu.add(anchorDivisionDepartment);
		vpnlSideMenu.add(anchorCompanyLogo);
		vpnlSideMenu.add(anchorRiskAssesmentFactors);
		
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setSize("200px", "200px");
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
		anchor.setSize("200px", "30px");
		anchor.addStyleName("w3-bar-item w3-button");
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
		
		anchorCompanyLogo.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				vpnlMenuDetailedView.clear();
				vpnlMenuDetailedView.add(new CompanyLogo(loggedInUser.getCompanyId()));
			}
		});
		
		anchorRiskAssesmentFactors.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				vpnlMenuDetailedView.clear();
				vpnlMenuDetailedView.add(new AddRiskAssesmentFactors(loggedInUser.getCompanyId()));
			}
		});
	}

	public Image getImgLogo() {
		return imgLogo;
	}
	
}
