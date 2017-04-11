package com.internalaudit.client.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.presenter.MainPresenter.Display;
import com.internalaudit.client.view.Reporting.ReportingView;
import com.internalaudit.client.view.Scheduling.AuditSchedulingView;
import com.internalaudit.shared.User;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.info.Info;

public class MainView extends Composite implements Display {

	private AuditPlanningView auditPlanningView ;
	private User loggedInUser;
	private Anchor logOut = new Anchor("Logout");
	private Anchor createCompany = new Anchor("Add Company");
	private Anchor createUser = new Anchor("Add User");
	private ListBox listYears = new ListBox();
	private Label welcome = new Label("");
	private VerticalPanel vpnlAuditScheduing = new VerticalPanel();
	private VerticalPanel vpnlAuditEngagement = new VerticalPanel();
	private VerticalPanel vpnlDashBoard = new VerticalPanel();
	private VerticalPanel reportingView = new VerticalPanel();
	private VerticalPanel reportsView = new VerticalPanel();
	
	PlainTabPanel panel = new PlainTabPanel();

	public MainView(User loggedInUser){

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
//		hpnlHeader.setStyleName("blueBackground");
		hpnlMain.setWidth(Window.getClientWidth()-10+"px");
		hpnlHeader.setWidth(Window.getClientWidth()-imgHeader.getWidth()+"px");
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

//		logOut.setStyleName("logout");

//		panel.setWidth(Window.getClientWidth()-100+"px");
		panel.setWidth("1200px");
		panel.setResizeTabs(true);
		if(loggedInUser.getEmployeeId().getFromInternalAuditDept().equalsIgnoreCase("yes")){
			panel.add(auditPlanningView, "Audit Planning");
			panel.add(vpnlAuditScheduing, "Audit Scheduling");
			panel.add(vpnlAuditEngagement, "Audit Engagement");
			panel.add(reportingView, "Reporting");
			
			TabItemConfig config = new TabItemConfig("");
			config.setEnabled(false); 
			
//			panel.add(new Label(""), config);
//			panel.add(new Label(""), config);
			panel.add(vpnlDashBoard, "DashBoard");
			panel.add(new EmployeeDashBoardView(), "WorkItems");
			panel.add(reportsView, "Reports");
			
			
		}else{
			panel.add(reportingView, "Reporting");
			panel.setActiveWidget(reportingView);
		}

		TabItemConfig config = new TabItemConfig("Reporting");
		
//		panel.insert(reportingView, 3, config);
		VerticalPanel vpnlTabPanel = new VerticalPanel();
		vpnlTabPanel.setStyleName("centerPanel");
		vp.setWidth("100%");
		vp.add(vpnlTabPanel);
		vpnlTabPanel.add(panel);
		hpnl.add(selectYear());
		hpnl.add(welcome); // Welcome <name>
		welcome.setStyleName("blue");
		welcome.setWordWrap(false);
		hpnlHeader.add(hpnlSpace);
		hpnlSpace.setWidth("65%");
		hpnlHeader.add(hpnl);
		if(loggedInUser.getEmployeeId().getEmployeeName().equalsIgnoreCase("Muhammad Faheem Piracha") && loggedInUser.getEmployeeId().getEmployeeId() ==1){
		hpnl.add(createCompany); 
		hpnl.add(createUser); 
		}
		hpnl.add(logOut); // logout link
		hpnl.setSpacing(2);
		hpnl.setWidth("100%");
		hpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		initWidget(vp);
	}
	
	public Widget selectYear(){
		HorizontalPanel hpnlYear = new HorizontalPanel();
		VerticalPanel vpnlYear = new VerticalPanel();
		Label lblSelectYear = new Label("Year");
//		vpnlYear.add(lblSelectYear);
		listYears.setStyleName("yearList");
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



}
