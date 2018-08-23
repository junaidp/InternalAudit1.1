package com.internalaudit.client.view;


import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SideBarView extends VerticalLayoutContainer{

	
	VerticalPanel panelbar = new VerticalPanel();
	

public SideBarView(){
	
	panelbar.addStyleName("w3-sidebar w3-metro-dark-blue w3-bar-block");
	panelbar.setWidth("60px");
	 Image ImgMenu = new Image("menu.png");
	 Image ImgCloseCircular = new Image("close-circular-button-symbol.png");
	 Image ImgSecuirity = new Image("security.png");
	 Image ImgSpeedoMeter = new Image("speedometer.png");
	 Image ImgTab = new Image("tab.png");
	 Image ImgPlus = new Image("plus.png");
	 Image ImgPie = new Image("pie-circular-graphic-with-bars-in-the-center-part-thin-symbol-outline-inside-a-circle.png");
	 Image ImgMenu2 = new Image("menu (2).png");
	 Image ImgHome = new Image("home.png");
	 Image ImgAnalysis = new Image("analysis.png");
	 
	 Image ImgCalendar = new Image("calendar.png");
	 Image ImgControls = new Image("controls.png");
	 
	 ImgMenu.addStyleName("w3-bar-item w3-button");
	 ImgCloseCircular.addStyleName("w3-bar-item w3-button");
	 ImgSecuirity.addStyleName("w3-bar-item w3-button");
	 ImgSpeedoMeter.addStyleName("w3-bar-item w3-button");
	 ImgTab.addStyleName("w3-bar-item w3-button");
	 ImgPlus.addStyleName("w3-bar-item w3-button");
	 ImgPie.addStyleName("w3-bar-item w3-button");
	 ImgMenu2.addStyleName("w3-bar-item w3-button");
	 ImgHome.addStyleName("w3-bar-item w3-button");
	 ImgAnalysis.addStyleName("w3-bar-item w3-button");
	 ImgControls.addStyleName("w3-bar-item w3-button");
	 ImgCalendar.addStyleName("w3-bar-item w3-button");
	 
	 
	 
	 
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

	//ImgMenu.setSize("50", "50");
	ImgSecuirity.setSize("40", "40");
	ImgPie.setSize("30", "30");
	ImgCalendar.setSize("25", "25");
	
	panelbar.add(ImgMenu);
	panelbar.add(ImgSecuirity);
	panelbar.add(ImgPie);
	panelbar.add(ImgCalendar);
	panelbar.add(ImgCloseCircular);
	panelbar.add(ImgControls);
	panelbar.add(ImgHome);
	panelbar.add(ImgAnalysis);
	panelbar.add(ImgPlus);
	panelbar.add(ImgSpeedoMeter);
	panelbar.add(ImgMenu2);
	panelbar.add(ImgTab);
	
add(panelbar);

}
}