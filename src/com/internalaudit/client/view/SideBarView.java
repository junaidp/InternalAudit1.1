package com.internalaudit.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.DashboardNew.PortalInformationRequest;
import com.internalaudit.shared.User;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SideBarView extends VerticalLayoutContainer {
	
	 final MainViewNew mv = new MainViewNew();
	 
	 
	VerticalPanel panelbar = new VerticalPanel();
	Image ImgMenu = new Image("menu.png");
	Image ImgCloseCircular = new Image("close-circular-button-symbol.png");
	Image ImgSecuirity = new Image("security.png");
	Image ImgSpeedoMeter = new Image("speedometer.png");
	Image ImgTab = new Image("tab.png");
	Image ImgPlus = new Image("plus.png");
	Image ImgPie = new Image(
			"pie-circular-graphic-with-bars-in-the-center-part-thin-symbol-outline-inside-a-circle.png");
	Image ImgMenu2 = new Image("menu (2).png");
	Image ImgHome = new Image("home.png");
	Image ImgAnalysis = new Image("analysis.png");

	Image ImgCalendar = new Image("calendar.png");
	Image ImgControls = new Image("controls.png");
	
	private User loggedInUser = null;
	
	public SideBarView(final User loggedInUser) {

		panelbar.addStyleName("w3-sidebar w3-cobalt  w3-bar-block ");
		panelbar.setWidth("64px");
		
		this.loggedInUser = loggedInUser;

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
		ImgMenu.setHeight("50px");
		ImgSecuirity.setWidth("60px");
		ImgSecuirity.setHeight("50px");
		ImgPie.setWidth("60px");
		ImgPie.setHeight("50px");
		ImgPlus.setWidth("60px");
		ImgPlus.setHeight("50px");
		ImgCalendar.setWidth("60px");
		ImgCalendar.setHeight("50px");
		ImgHome.setWidth("60px");
		ImgHome.setHeight("50px");
		ImgAnalysis.setWidth("60px");
		ImgAnalysis.setHeight("50px");
		ImgCloseCircular.setWidth("60px");
		ImgCloseCircular.setHeight("50px");
		ImgControls.setWidth("60px");
		ImgControls.setHeight("50px");
		ImgMenu2.setWidth("60px");
		ImgMenu2.setHeight("50px");
		ImgTab.setWidth("60px");
		ImgTab.setHeight("50px");
		ImgSpeedoMeter.setWidth("60px");
		ImgSpeedoMeter.setHeight("50px");

		// ImgMenu.setSize("50", "50");
		ImgSecuirity.setSize("40", "40");
		ImgPie.setSize("30", "30");
		ImgCalendar.setSize("25", "25");

		panelbar.add(ImgHome);
		panelbar.add(ImgMenu);
		panelbar.add(ImgSecuirity);
		panelbar.add(ImgPie);
		panelbar.add(ImgCalendar);
		panelbar.add(ImgCloseCircular);
		panelbar.add(ImgControls);

		 panelbar.add(ImgAnalysis);
		 panelbar.add(ImgPlus);
		  panelbar.add(ImgSpeedoMeter);

			/*panelbar.add(ImgMenu2);
		 * panelbar.add(ImgTab);
		 */
		add(panelbar);

		ImgHome.setTitle("To DO");
		 ImgHome.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					ToDoView todoview = new ToDoView();
					
					PopupsView pp = new PopupsView(todoview, "");
					pp.getLabelheading().setText("To Do");
					pp.getVpnlMain().setWidth("320px");
					pp.getHpnlSPace().setWidth("320px");
					pp.getVpnlMain().setHeight("320px");
					}
			});
			 ImgMenu.setTitle("Information Request");
			 ImgMenu.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						final InformationRequestView informationreq = new InformationRequestView();
						PopupsView pp = new PopupsView(informationreq, "");
						pp.getLabelheading().setText("Information Request");
						pp.getVpnlMain().setTitle("Information Request");
						pp.getVpnlMain().setWidth("400px");
						pp.getHpnlSPace().setWidth("400px");
						pp.getVpnlMain().setHeight("530px");

					}
				});
			 ImgSecuirity.setTitle("EmailView");
			 ImgCalendar .addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						final EmailView emailView = new EmailView(loggedInUser.getTodos().get(loggedInUser.getTodos().size()-1));
						PopupsView pp = new PopupsView(emailView, "");
						pp.getLabelheading().setText("Email View");
						pp.getVpnlMain().setTitle("Email View");
						pp.getVpnlMain().setWidth("600px");
						pp.getHpnlSPace().setWidth("600px");
						pp.getVpnlMain().setHeight("530px");
						
					}
				});
				 ImgPie.setTitle("Task List");
				ImgPie .addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						PortalTaskList p = new PortalTaskList();
						PopupsView pp = new PopupsView(p, "");
						 pp = new PopupsView(p, "");
						pp.getLabelheading().setText("Task List");
						pp.getVpnlMain().setTitle("TaskList");
						pp.getVpnlMain().setWidth("800px");
					//	pp.getHpnlSPace().setWidth("400px");
						pp.getVpnlMain().setHeight("530px");
						
					}
				});
				 ImgSecuirity.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						final TodosList toDo = new TodosList(loggedInUser.getTodos());
						PopupsView pp = new PopupsView(toDo, "");
						pp.getLabelheading().setText("Todos");
						pp.getVpnlMain().setTitle("Todos");
						pp.getVpnlMain().setWidth("600px");
						pp.getHpnlSPace().setWidth("600px");
						pp.getVpnlMain().setHeight("530px");
						
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