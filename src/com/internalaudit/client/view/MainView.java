package com.internalaudit.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.DashboardNew.AuditWorkChart;
import com.internalaudit.client.DashboardNew.DashboardNew;
import com.internalaudit.client.presenter.MainPresenter.Display;
import com.internalaudit.client.widgets.TableauAbilite;
import com.internalaudit.client.widgets.TableauExcel;
import com.internalaudit.client.widgets.TableauReports;
import com.internalaudit.shared.User;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class MainView extends Composite implements Display {

	private AuditPlanningView auditPlanningView ;
	private User loggedInUser;
	private Anchor logOut = new Anchor("Logout");
	private Anchor feedBack = new Anchor("Help/Feedback");
	private Anchor createCompany = new Anchor("Add Company");
	private Anchor createUser = new Anchor("Add User");
	private ListBox listYears = new ListBox();
	private Label welcome = new Label("");
	private VerticalPanel vpnlAuditScheduing = new VerticalPanel();
	private VerticalPanel vpnlAuditEngagement = new VerticalPanel();
	private VerticalPanel vpnlDashBoard = new VerticalPanel();
	private VerticalPanel reportingView = new VerticalPanel();
	private VerticalPanel reportsView = new VerticalPanel();
	private HorizontalPanel footer = new HorizontalPanel();
	private VerticalLayoutContainer vpnlDashBoardNew = new VerticalLayoutContainer();
	

	PlainTabPanel panel = new PlainTabPanel();
	HorizontalPanel checkpanel = new HorizontalPanel();
	VerticalPanel panelImages = new VerticalPanel();
	VerticalPanel panelSideBar = new VerticalPanel();
	

	public MainView(User loggedInUser){
	// new code	
		panel.getElement().getStyle().setMarginLeft(50, Unit.PX);
		panelImages.setWidth("110px");
		panelImages.setHeight("200px");
//		 panelImages.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
//		panelImages.getElement().getStyle().setBorderWidth(2, Unit.PX);	
//		 Button btn = new Button("Hello its visible");
//		//	panelImages.add(btn);
//		Label dash = new Label("DASHBOARD");
//		panelImages.add(dash);
//		 Image dashboard = new Image("dashboard.png");
//		 dashboard.setWidth("80px");
//		 dashboard.setHeight("40px");
//		 panelImages.add(dashboard);
//		 Label rep = new Label("REPORTS");
//		 panelImages.add(rep);?
//		 Image reports = new Image("Reports.png");
//		 reports.setHeight("40px");
//		 reports.setWidth("80px");
//		 panelImages.add(reports);
//		 Label lblwrk = new Label("WORK ITEM");
//		 panelImages.add(lblwrk);
//		 Image workItem = new Image("work items.jpg");
//		 workItem.setHeight("40px");
//		 workItem.setWidth("80px");
//		 panelImages.add(workItem);
		//VerticalPanel panelTeam = new VerticalPanel();
		//panelTeam.addStyleName("w3-margin");
		//VerticalPanel panelClient = new VerticalPanel();
		//panelClient.addStyleName("w3-margin");
		//2018 
		SideBarView sideBarView = new SideBarView();
		panelSideBar.add(sideBarView);
	
		
		 
		
		 final ToDoView todoview = new ToDoView();
		 final MainViewNew mv = new MainViewNew();
		 final InformationRequestView informationreq = new InformationRequestView();
		 
		 FocusPanel vpTeamMgm = putImageInCard("TEAM MANAGEMENT", "team management.jpg");
		 FocusPanel vpClientMgm = putImageInCard("CLIENT MANAGEMENT", "client mang.png");
		 
		 panelImages.add(vpTeamMgm);
		 panelImages.add(vpClientMgm);
		 
		 vpTeamMgm.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				PopupsView pp = new PopupsView(todoview, "");
				pp.getLabelheading().setText("To Do");
				pp.getVpnlMain().setWidth("320px");
				pp.getHpnlSPace().setWidth("320px");
				pp.getVpnlMain().setHeight("320px");
				}
		});
		 
		 vpClientMgm.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					PopupsView pp = new PopupsView(informationreq, "");
					pp.getLabelheading().setText("Information Request");
					pp.getVpnlMain().setTitle("Information Request");
					pp.getVpnlMain().setWidth("400px");
					pp.getHpnlSPace().setWidth("400px");
					pp.getVpnlMain().setHeight("530px");
	
				}
			});
			 
		 
		//endshere 
		this.loggedInUser = loggedInUser;
		auditPlanningView = new AuditPlanningView(loggedInUser);

		HorizontalPanel hpnlMain = new HorizontalPanel();
		Image imgHeader = new Image("images/trans.png");

		VerticalPanel vp = new VerticalPanel();
		VerticalPanel hpnl = new VerticalPanel();
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		VerticalPanel hpnlHeader = new VerticalPanel();
		vp.add(hpnlMain);
		
		hpnlMain.add(imgHeader);
		hpnlMain.add(hpnlHeader);
		//		hpnlHeader.addStyleName("blueBackground");
		hpnlHeader.setWidth(Window.getClientWidth()-imgHeader.getWidth()-70+"px");
		hpnlHeader.setHeight("91px");
		hpnlHeader.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		//	 SelectionHandler<Widget> handler = new SelectionHandler<Widget>() {
		//	      @Override
		//	      public void onSelection(SelectionEvent<Widget> event) {
		//	        TabPanel panel = (TabPanel) event.getSource();
		//	        Widget w = event.getSelectedItem();
		//	        TabItemConfig config = panel.getConfig(w);
		//	        Info.display("Message", "'" + config.getText() + "' Selected");
		//	      }
		//	    };

		//		logOut.addStyleName("logout");

		//		panel.setWidth(Window.getClientWidth()-100+"px");
		//panel.setWidth("880px");
		panel.setWidth("1200px");
		panel.setResizeTabs(true);
	
		if(loggedInUser.getEmployeeId().getFromInternalAuditDept().equalsIgnoreCase("yes")){
			panel.add(auditPlanningView, "Audit Planning");
			panel.add(vpnlAuditScheduing, "Audit Scheduling");
			panel.add(vpnlAuditEngagement, "Audit Engagement");
			panel.add(reportingView, "Reporting");

			TabItemConfig config = new TabItemConfig("");
			config.setEnabled(false); 

			panel.add(vpnlDashBoard, "DashBoard");
			panel.add(new EmployeeDashBoardView(), "WorkItems");
			panel.add(reportsView, "Reports");
			
			//2018 new
			vpnlDashBoardNew.add( new DashboardNew());
			panel.add(vpnlDashBoardNew, "DashBoardNew");
			// panel.add((IsWidget) new Dashboard(),"NewDashboard");

		}else{
			panel.add(reportingView, "Reporting");
			panel.setActiveWidget(reportingView);
		}
		if(loggedInUser.getEmployeeId().getUserId().getUserId() == 1){
			addTableauTabs();
		}

		TabItemConfig config = new TabItemConfig("Reporting");

		//		panel.insert(reportingView, 3, config);
		VerticalPanel vpnlTabPanel = new VerticalPanel();
		vpnlTabPanel.addStyleName("centerPanel");
		vp.setWidth("100%");
		vp.add(vpnlTabPanel);
		//checkpanel.add(panel);
		//checkpanel.add(panelImages);
		//vpnlTabPanel.add(panel);
		checkpanel.add(panelSideBar);
	//	checkpanel.add(panelImages);
		checkpanel.add(panel);
		
		vpnlTabPanel.add(checkpanel);
		//vpnlTabPanel.add(panelImages);

		hpnl.add(selectYear());
		hpnl.add(welcome); // Welcome <name>
		welcome.addStyleName("blue");
		welcome.setWordWrap(false);
		hpnlHeader.add(hpnlSpace);
		hpnlSpace.setWidth("65%");
		hpnlHeader.add(hpnl);
		if(loggedInUser.getEmployeeId().getEmployeeName().equalsIgnoreCase("Muhammad Faheem Piracha") && loggedInUser.getEmployeeId().getEmployeeId() ==1){
			hpnl.add(createCompany); 
			hpnl.add(createUser); 
		}
		hpnl.add(feedBack);
		hpnl.add(logOut); // logout link
		hpnl.setSpacing(2);
		hpnl.setWidth("100%");
		hpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		initWidget(vp);
	}

	private FocusPanel putImageInCard(String lblName, String imgSource) {
		 Label lbl = new Label(lblName);
		 lbl.setWidth("125px");
		 lbl.getElement().getStyle().setColor("White");
		 Image img = new Image(imgSource);
		 lbl.setWordWrap(false);
		 //lbl.getElement().getStyle().setPaddingLeft(5, Unit.PX);
		 img.getElement().getStyle().setMarginLeft(30,Unit.PX);
		 img.addStyleName("point");
		 lbl.addStyleName("point");
		 img.setHeight("60px");
		 img.setWidth("80px");
		VerticalPanel vpTeamMgm = new VerticalPanel();
		 vpTeamMgm.addStyleName("w3-card-4");
		 vpTeamMgm.add(img);
		 VerticalPanel vpTeamMgmLbl = new VerticalPanel();
		 vpTeamMgmLbl.add(lbl);
		 vpTeamMgmLbl.addStyleName("w3-container w3-center w3-blue");
		 vpTeamMgm.add(vpTeamMgmLbl);
		 FocusPanel focusPanel = new FocusPanel();
		 focusPanel.add(vpTeamMgm);
		 return focusPanel;
	}

	private void addTableauTabs() {
		final VerticalPanel vpTab = new VerticalPanel();
		final VerticalPanel vpTabAb = new VerticalPanel();
		final VerticalPanel vpTabEx = new VerticalPanel();

		vpTabAb.setWidth("100%");
		vpTab.setWidth("100%");
		vpTabEx.setWidth("100%");

		panel.add(vpTab, "Tableau");
		panel.add(vpTabAb, "Tableau Abilite");
		panel.add(vpTabEx, "Tableau Excel");

		panel.addSelectionHandler(new SelectionHandler<Widget>() {

			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				if(event.getSelectedItem().equals(vpTab)){
					vpTab.add(new TableauReports());
				}
				else if(event.getSelectedItem().equals(vpTabAb)){
					vpTabAb.add(new TableauAbilite());
				}
				else if(event.getSelectedItem().equals(vpTabEx)){
					vpTabEx.add(new TableauExcel());
				}
			}
		});
	}

	public Widget selectYear(){
		HorizontalPanel hpnlYear = new HorizontalPanel();
		VerticalPanel vpnlYear = new VerticalPanel();
		Label lblSelectYear = new Label("Year");
		//		vpnlYear.add(lblSelectYear);
		listYears.addStyleName("yearList");
		vpnlYear.add(listYears);
		hpnlYear.add(vpnlYear);
		hpnlYear.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		vpnlYear.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		//		listYears.addItem("2014");

		//		listYears.addItem("2015");
		//		listYears.addItem("2016");
		//		listYears.addItem("2017");
		//		listYears.addItem("2018");
		//		listYears.addItem("2019");
		//		listYears.addItem("2020");
		//		if(selectedYear!=0){
		//			for(int i=0; i< listYears.getItemCount(); i++){
		//				if(Integer.parseInt(listYears.getValue(i)) == selectedYear){
		//					listYears.setSelectedIndex(i);
		//				}
		//			}
		//		}
		return hpnlYear;


	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public Anchor getLogOut() {
		return logOut;
	}

	public void setLogOut(Anchor logOut) {
		this.logOut = logOut;
	}

	public Label getWelcome() {
		return welcome;
	}

	public void setWelcome(Label welcome) {
		this.welcome = welcome;
	}

	public VerticalPanel getVpnlAuditScheduing() {
		return vpnlAuditScheduing;
	}

	public void setVpnlAuditScheduing(VerticalPanel vpnlAuditScheduing) {
		this.vpnlAuditScheduing = vpnlAuditScheduing;
	}

	public PlainTabPanel getPanel() {
		return panel;
	}

	public void setPanel(PlainTabPanel panel) {
		this.panel = panel;
	}

	public VerticalPanel getVpnlAuditEngagement() {
		return vpnlAuditEngagement;
	}

	public void setVpnlAuditEngagement(VerticalPanel vpnlAuditEngagement) {
		this.vpnlAuditEngagement = vpnlAuditEngagement;
	}

	public VerticalPanel getReportingView() {
		return reportingView;
	}

	public void setReportingView(VerticalPanel reportingView) {
		this.reportingView = reportingView;
	}

	public ListBox getListYears() {
		return listYears;
	}

	public void setListYears(ListBox listYears) {
		this.listYears = listYears;
	}

	public VerticalPanel getVpnlDashBoard() {
		return vpnlDashBoard;
	}

	public void setVpnlDashBoard(VerticalPanel vpnlDashBoard) {
		this.vpnlDashBoard = vpnlDashBoard;
	}

	public VerticalPanel getReportsView() {
		return reportsView;
	}

	public void setReportsView(VerticalPanel reportsView) {
		this.reportsView = reportsView;
	}

	public Anchor getCreateCompany() {
		return createCompany;
	}

	public void setCreateCompany(Anchor createCompany) {
		this.createCompany = createCompany;
	}

	public Anchor getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Anchor createUser) {
		this.createUser = createUser;
	}

	public Anchor getFeedBack() {
		return feedBack;
	}



}
