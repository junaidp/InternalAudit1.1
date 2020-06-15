package com.internalaudit.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.view.ToDo.InformationRequestRaisePortal;
import com.internalaudit.client.view.ToDo.InformationRequestReceiverPortal;
import com.internalaudit.client.view.ToDo.ToDoRaiserPortal;
import com.internalaudit.client.view.ToDo.ToDoReceiverPortal;
import com.internalaudit.shared.Employee;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SideBarView extends VerticalLayoutContainer {
	// InternalAuditServiceAsync rpcService =
	// GWT.create(InternalAuditService.class);
	final MainViewNew mv = new MainViewNew();

	VerticalPanel panelbar = new VerticalPanel();
	// included
	Image imgIRRaiserGrid;
	// included
	Image imgToDoReceiverGrid;

	// included
	Image imgIRReceiverGrid;
	Image ImgMenu2 = new Image("menu (2).png");
	// included
	Image imgToDoRaiserGrid;
	// included
	Image ImgControls = new Image("Reportsw.png");
	/// Spaces
	Image ImgAnalysis = new Image("analysis.png");

	Image ImgCalendar = new Image("calendar.png");
	Image ImgSpeedoMeter = new Image("speedometer.png");
	Image ImgTab = new Image("tab.png");
	Image ImgPlus = new Image("plus.png");
	Image ImgCloseCircular = new Image("close-circular-button-symbol.png");
	private ToDoRaiserPortal toDoRaiserPortal;
	private InformationRequestRaisePortal informationRequestRaisePortal;
	private ToDoReceiverPortal toDoReceiverPortal;
	private InformationRequestReceiverPortal informationRequestReceiverPortal;

	public SideBarView(final Employee loggedInUser, final HandlerManager eventBus) {
		sideMenuIcons(loggedInUser);
		panelbar.addStyleName("sidebarNew w3-cobalt  w3-bar-block ");
		// panelbar.setWidth("64px");

		imgIRRaiserGrid.addStyleName("w3-bar-item w3-button w3-round w3-hover-blue");
		ImgCloseCircular.addStyleName("w3-bar-item w3-button w3-hover-blue");
		imgToDoReceiverGrid.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgSpeedoMeter.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgTab.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgPlus.addStyleName("w3-bar-item w3-button w3-hover-blue");
		imgIRReceiverGrid.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgMenu2.addStyleName("w3-bar-item w3-button w3-hover-blue");
		imgToDoRaiserGrid.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgAnalysis.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgControls.addStyleName("w3-bar-item w3-button w3-hover-blue");
		ImgCalendar.addStyleName("w3-bar-item w3-button w3-hover-blue");

		imgIRRaiserGrid.setWidth("60px");
		imgIRRaiserGrid.setHeight("45px");
		imgToDoReceiverGrid.setWidth("60px");
		imgToDoReceiverGrid.setHeight("45px");
		imgIRReceiverGrid.setWidth("60px");
		imgIRReceiverGrid.setHeight("45px");
		// ImgPlus.setWidth("60px");
		// ImgPlus.setHeight("50px");
		// ImgCalendar.setWidth("60px");
		// ImgCalendar.setHeight("50px");
		imgToDoRaiserGrid.setWidth("60px");
		imgToDoRaiserGrid.setHeight("45px");
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

		imgToDoReceiverGrid.setSize("40", "40");
		imgIRReceiverGrid.setSize("30", "30");
		ImgCalendar.setSize("25", "25");
		panelbar.setWidth("40");

		panelbar.add(imgToDoRaiserGrid);
		panelbar.add(imgIRRaiserGrid);
		panelbar.add(imgToDoReceiverGrid);
		panelbar.add(imgIRReceiverGrid);
		panelbar.add(ImgControls);

		// panelbar.add(ImgCalendar);
		// panelbar.add(ImgCloseCircular);
		HorizontalPanel v = new HorizontalPanel();
		v.setWidth("35px");
		v.setHeight("650px");
		panelbar.add(v);

		//
		// panelbar.add(ImgAnalysis);
		// panelbar.add(ImgPlus);
		// panelbar.add(ImgSpeedoMeter);

		/*
		 * panelbar.add(ImgMenu2); panelbar.add(ImgTab);
		 */
		add(panelbar);

		imgToDoRaiserGrid.setTitle("To Do Raiser Grid");
		imgToDoRaiserGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// raiserToDoView = true;
				toDoRaiserPortal = new ToDoRaiserPortal(loggedInUser.getUserRaisedToDos());
				new PopupsView(toDoRaiserPortal, "To Do Raiser Grid", "900px", "530px");
				toDoRaiserPortal.fetchAssignedFromToDoReLoad();
				// receiverToDoView = false;
			}
		});
		imgIRRaiserGrid.setTitle("Information Request Raiser Grid");
		imgIRRaiserGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				informationRequestRaisePortal = new InformationRequestRaisePortal(
						loggedInUser.getUserRaisedInformationRequests());
				new PopupsView(informationRequestRaisePortal, "Information Request Raiser", "900px", "530px");
				informationRequestRaisePortal.fetchAssignedFromIRReLoad();
			}
		});
		imgToDoReceiverGrid.setTitle("To Do Receiver Grid");
		imgToDoReceiverGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// receiverToDoView = true;
				toDoReceiverPortal = new ToDoReceiverPortal(loggedInUser.getTodos());
				new PopupsView(toDoReceiverPortal, "To Do Receiver", "650px", "530px");
				toDoReceiverPortal.fetchAssignedToToDos();
				// raiserToDoView = false;
			}

		});
		imgIRReceiverGrid.setTitle("Information Request Receiver Grid");
		imgIRReceiverGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				informationRequestReceiverPortal = new InformationRequestReceiverPortal(
						loggedInUser.getInformationRequests());
				new PopupsView(informationRequestReceiverPortal, "Information Request Receiver", "650px", "530px");
				informationRequestReceiverPortal.fetchAssignedToIRReLoad();
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

	private void sideMenuIcons(Employee loggedInUser) {
		setIconToDoRaiserGrid(loggedInUser);
		setIconIRRaiserGrid(loggedInUser);
		setIconToDoReceiverGrid(loggedInUser);
		setIconIRReceiverGrid(loggedInUser);
	}

	
	private void setIconToDoRaiserGrid(Employee loggedInUser) {
		boolean updateIconAlert = false;
		for (int i = 0; i < loggedInUser.getUserRaisedToDos().size(); i++) {
			if (loggedInUser.getUserRaisedToDos().get(i).getRead() == true)
				updateIconAlert = true;
		}
		if (!updateIconAlert == true)
			imgToDoRaiserGrid = new Image("Icons/iconToDo.png");
		else
			imgToDoRaiserGrid = new Image("sideMenuAlertIcons/iconToDo.png");
	}

	private void setIconIRRaiserGrid(Employee loggedInUser) {
		boolean updateIconAlert = false;
		for (int i = 0; i < loggedInUser.getUserRaisedInformationRequests().size(); i++) {
			if (loggedInUser.getUserRaisedInformationRequests().get(i).getRead() == true)
				updateIconAlert = true;
		}
		if (!updateIconAlert == true)
			imgIRRaiserGrid = new Image("Information Requestw.png");
		else
			imgIRRaiserGrid = new Image("sideMenuAlertIcons/Information Requestw.png");
		updateIconAlert = false;
	}

	private void setIconToDoReceiverGrid(Employee loggedInUser) {
		boolean updateIconAlert = false;
		for (int i = 0; i < loggedInUser.getTodos().size(); i++) {
			if (loggedInUser.getTodos().get(i).getRead() == false)
				updateIconAlert = true;
		}
		if (!updateIconAlert == true)
			imgToDoReceiverGrid = new Image("Taskw.png");
		else
			imgToDoReceiverGrid = new Image("sideMenuAlertIcons/Taskw.png");
	}

	private void setIconIRReceiverGrid(Employee loggedInUser) {
		boolean updateIconAlert = false;
		for (int i = 0; i < loggedInUser.getInformationRequests().size(); i++) {
			if (loggedInUser.getInformationRequests().get(i).getRead() == false)
				updateIconAlert = true;
		}
		if (!updateIconAlert == true)
			imgIRReceiverGrid = new Image("IR receiverw.png");
		else
			imgIRReceiverGrid = new Image("sideMenuAlertIcons/IR receiverw.png");
		updateIconAlert = false;
	}

	public Image getImgMenu() {
		return imgIRRaiserGrid;
	}

	public void setImgMenu(Image imgMenu) {
		imgIRRaiserGrid = imgMenu;
	}

	public Image getImgCloseCircular() {
		return ImgCloseCircular;
	}

	public void setImgCloseCircular(Image imgCloseCircular) {
		ImgCloseCircular = imgCloseCircular;
	}

	public Image getImgSecuirity() {
		return imgToDoReceiverGrid;
	}

	public void setImgSecuirity(Image imgSecuirity) {
		imgToDoReceiverGrid = imgSecuirity;
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
		return imgIRReceiverGrid;
	}

	public void setImgPie(Image imgPie) {
		imgIRReceiverGrid = imgPie;
	}

	public Image getImgMenu2() {
		return ImgMenu2;
	}

	public void setImgMenu2(Image imgMenu2) {
		ImgMenu2 = imgMenu2;
	}

	public Image getImgHome() {
		return imgToDoRaiserGrid;
	}

	public void setImgHome(Image imgHome) {
		imgToDoRaiserGrid = imgHome;
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