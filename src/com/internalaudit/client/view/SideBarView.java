package com.internalaudit.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.ToDo.InformationRequestRaisePortal;
import com.internalaudit.client.view.ToDo.InformationRequestReceiverPortal;
import com.internalaudit.client.view.ToDo.ToDoRaiserPortal;
import com.internalaudit.client.view.ToDo.ToDoReceiverPortal;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SideBarView extends VerticalLayoutContainer {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	final MainViewNew mv = new MainViewNew();

	VerticalPanel panelbar = new VerticalPanel();
	// included
	Image ImgMenu = new Image("Information Requestw.png");
	// included
	Image ImgSecuirity = new Image("Taskw.png");

	// included
	Image ImgPie = new Image("IR receiverw.png");
	Image ImgMenu2 = new Image("menu (2).png");
	// included
	Image ImgHome = new Image("Icons/iconToDo.png");
	// included
	Image ImgControls = new Image("Reportsw.png");
	/// Spaces
	Image ImgAnalysis = new Image("analysis.png");

	Image ImgCalendar = new Image("calendar.png");
	Image ImgSpeedoMeter = new Image("speedometer.png");
	Image ImgTab = new Image("tab.png");
	Image ImgPlus = new Image("plus.png");
	Image ImgCloseCircular = new Image("close-circular-button-symbol.png");

	public SideBarView(final Employee loggedInUser, final HandlerManager eventBus) {

		panelbar.addStyleName("sidebarNew w3-cobalt  w3-bar-block ");
		// panelbar.setWidth("64px");

		ImgMenu.addStyleName("w3-bar-item w3-button w3-round w3-hover-blue");
		ImgCloseCircular.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgSecuirity.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgSpeedoMeter.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgTab.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgPlus.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgPie.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgMenu2.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgHome.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgAnalysis.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgControls.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgCalendar.addStyleName("w3-bar-item w3-button w3-hover-blue");

		ImgMenu.setWidth("60px");
		ImgMenu.setHeight("45px");
		ImgSecuirity.setWidth("60px");
		ImgSecuirity.setHeight("45px");
		ImgPie.setWidth("60px");
		ImgPie.setHeight("45px");
		// ImgPlus.setWidth("60px");
		// ImgPlus.setHeight("50px");
		// ImgCalendar.setWidth("60px");
		// ImgCalendar.setHeight("50px");
		ImgHome.setWidth("60px");
		ImgHome.setHeight("45px");
		// ImgAnalysis.setWidth("60px");
		// ImgAnalysis.setHeight("50px");
		// ImgCloseCircular.setWidth("60px");
		// ImgCloseCircular.setHeight("50px");
		ImgControls.setWidth("60px");
		ImgControls.setHeight("45px");
		// ImgMenu2.setWidth("60px");
		// ImgMenu2.setHeight("50px");
		// ImgTab.setWidth("60px");
		// ImgTab.setHeight("50px");
		// ImgSpeedoMeter.setWidth("60px");
		// ImgSpeedoMeter.setHeight("50px");

		ImgSecuirity.setSize("40", "40");
		ImgPie.setSize("30", "30");
		ImgCalendar.setSize("25", "25");
		panelbar.setWidth("40");

		panelbar.add(ImgHome);
		panelbar.add(ImgMenu);
		panelbar.add(ImgSecuirity);
		panelbar.add(ImgPie);

		// panelbar.add(ImgCalendar);
		// panelbar.add(ImgCloseCircular);
		HorizontalPanel v = new HorizontalPanel();
		v.setWidth("35px");
		v.setHeight("650px");
		panelbar.add(ImgControls);
		panelbar.add(v);

		//
		// panelbar.add(ImgAnalysis);
		// panelbar.add(ImgPlus);
		// panelbar.add(ImgSpeedoMeter);

		/*
		 * panelbar.add(ImgMenu2); panelbar.add(ImgTab);
		 */
		add(panelbar);

		ImgHome.setTitle("To Do Raiser Grid");
		ImgHome.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ToDoRaiserPortal p = new ToDoRaiserPortal(loggedInUser.getUserRaisedToDos());
				PopupsView pp = new PopupsView(p, "To Do Raiser Grid");

				// pp.getLabelheading().setText("To Do Raiser Grid");
				// pp.getPopup().setHeadingText("To Do Raiser Grid");
				pp.getVpnlMain().setTitle("TaskList");
				pp.getVpnlMain().setWidth("900px");
				pp.getVpnlMain().setHeight("530px");

			}
		});
		ImgMenu.setTitle("Information Request Raiser Grid");
		ImgMenu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				InformationRequestRaisePortal p = new InformationRequestRaisePortal(
						loggedInUser.getUserRaisedInformationRequests());
				PopupsView pp = new PopupsView(p, "Information Request Raiser");
				// pp.getLabelheading().setText("Information Request Raiser");
				// pp.getPopup().setHeadingText("Information Request Raiser");
				pp.getVpnlMain().setTitle("TaskList");
				pp.getVpnlMain().setWidth("800px");
				pp.getVpnlMain().setHeight("530px");

			}
		});

		ImgPie.setTitle("Information Request Receiver Grid");
		ImgPie.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				InformationRequestReceiverPortal p = new InformationRequestReceiverPortal(
						loggedInUser.getInformationRequests());
				PopupsView pp = new PopupsView(p, "Information Request Receiver");

				// pp.getLabelheading().setText("Information Request Receiver");
				// pp.getPopup().setHeadingText("Information Request Receiver");
				pp.getVpnlMain().setWidth("650px");
				pp.getVpnlMain().setHeight("530px");

			}
		});
		ImgSecuirity.setTitle("To Do Receiver Grid");
		ImgSecuirity.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				ToDoReceiverPortal p = new ToDoReceiverPortal(loggedInUser.getTodos());
				PopupsView pp = new PopupsView(p, "To Do Receiver");
				// pp.getLabelheading().setText("To Do Receiver");
				// pp.getPopup().setHeadingText("To Do Receiver");
				pp.getVpnlMain().setTitle("TaskList");
				pp.getVpnlMain().setWidth("650px");
				pp.getVpnlMain().setHeight("530px");
			}

		});
		ImgControls.setTitle("ReportView");
		ImgControls.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				History.newItem("AuditReporting");
				/*
				 * ReportViewMain reportViewMain = new ReportViewMain();
				 * 
				 * ScrollPanel panelScroll = new ScrollPanel();
				 * panelScroll.setWidth("1080px");
				 * panelScroll.setHeight("590px");
				 * panelScroll.add(reportViewMain); PopupsView pp = new
				 * PopupsView(panelScroll, "");
				 * 
				 * pp.getLabelheading().setText("Report View");
				 * pp.getVpnlMain().setTitle("ReportView");
				 * pp.getVpnlMain().setWidth("1100px");
				 * pp.getVpnlMain().setHeight("600px");
				 */

			}
		});

	}

	public Image getImgMenu() {
		return ImgMenu;
	}

	public void setImgMenu(Image imgMenu) {
		ImgMenu = imgMenu;
	}

	public Image getImgCloseCircular() {
		return ImgCloseCircular;
	}

	public void setImgCloseCircular(Image imgCloseCircular) {
		ImgCloseCircular = imgCloseCircular;
	}

	public Image getImgSecuirity() {
		return ImgSecuirity;
	}

	public void setImgSecuirity(Image imgSecuirity) {
		ImgSecuirity = imgSecuirity;
	}

	public Image getImgSpeedoMeter() {
		return ImgSpeedoMeter;
	}

	public void setImgSpeedoMeter(Image imgSpeedoMeter) {
		ImgSpeedoMeter = imgSpeedoMeter;
	}

	public Image getImgTab() {
		return ImgTab;
	}

	public void setImgTab(Image imgTab) {
		ImgTab = imgTab;
	}

	public Image getImgPlus() {
		return ImgPlus;
	}

	public void setImgPlus(Image imgPlus) {
		ImgPlus = imgPlus;
	}

	public Image getImgPie() {
		return ImgPie;
	}

	public void setImgPie(Image imgPie) {
		ImgPie = imgPie;
	}

	public Image getImgMenu2() {
		return ImgMenu2;
	}

	public void setImgMenu2(Image imgMenu2) {
		ImgMenu2 = imgMenu2;
	}

	public Image getImgHome() {
		return ImgHome;
	}

	public void setImgHome(Image imgHome) {
		ImgHome = imgHome;
	}

	public Image getImgAnalysis() {
		return ImgAnalysis;
	}

	public void setImgAnalysis(Image imgAnalysis) {
		ImgAnalysis = imgAnalysis;
	}

	public Image getImgCalendar() {
		return ImgCalendar;
	}

	public void setImgCalendar(Image imgCalendar) {
		ImgCalendar = imgCalendar;
	}

	public Image getImgControls() {
		return ImgControls;
	}

	public void setImgControls(Image imgControls) {
		ImgControls = imgControls;
	}

}