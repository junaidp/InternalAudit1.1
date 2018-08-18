package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.util.DataStorage;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class PortalIssue extends VerticalLayoutContainer {

  protected static final int MIN_HEIGHT = 1;
  protected static final int MIN_WIDTH = 1280;
  protected static final int PREFERRED_HEIGHT = 1;
  protected static final int PREFERRED_WIDTH = 1;

  private static final IssuesProperties properties = GWT.create(IssuesProperties.class);

  private PortalLayoutContainer portal;
  private Issues Issues = new Issues();
  List<Issues> stocks = new ArrayList<Issues>();
  private     ListStore<Issues> store = new ListStore<Issues>(properties.key());



  public PortalIssue() {

add(createGridFieldWork());
//      Portlet portletFieldWork = new Portlet();
//      portletFieldWork.setHeadingHtml("Field Work");
//      portletFieldWork.add(createGridFieldWork());
//      configPanel(portletFieldWork);

   

//
//      portal = new PortalLayoutContainer(3);
//      portal.setSpacing(20);
//      portal.setColumnWidth(0, .99);
//      portal.setColumnWidth(1, .50);
//      portal.setColumnWidth(2, .30);
//      portal.add(portletFieldWork, 0);
//     // setData();
//      add( portal);
  }
  



 
  
  public Widget createGridFieldWork() {
	    final NumberFormat number = NumberFormat.getFormat("0.00");

	    ColumnConfig<Issues, Integer> issueId = new ColumnConfig<Issues, Integer>(properties.id(), 150, "Id");
	    ColumnConfig<Issues, String> issueTitle = new ColumnConfig<Issues, String>(properties.issueTitle(), 150, "Issue Title");
	    ColumnConfig<Issues, String> issueManagement = new ColumnConfig<Issues, String>(properties.managementResponce(), 100, "Management Response");
	    ColumnConfig<Issues, String> issueStatus = new ColumnConfig<Issues, String>(properties.status(), 150, "Status");

	    List<ColumnConfig<Issues, ?>> columns = new ArrayList<ColumnConfig<Issues, ?>>();
	    columns.add(issueId); 
	    columns.add(issueTitle);
	    columns.add(issueManagement);
	    columns.add(issueStatus);
	    
	    ColumnModel<Issues> cm = new ColumnModel<Issues>(columns);

	 //   ListStore<Issues> store = new ListStore<Issues>(properties.key());
	   // store.addAll(getFieldWork());
	  //  store.addAll(stocks);

	    final Grid<Issues> grid = new Grid<Issues>(store, cm);
	    grid.getView().setAutoExpandColumn(issueId);
	    grid.getView().setForceFit(true);
	    grid.getView().setStripeRows(true);
	    grid.getView().setColumnLines(true);

	    return grid;
	  }

  private void setData() {
		 
	// setting data pf fieldwork

	Issues.setIssueTitle("titile");
	Issues.setManagementResponce("management");
	Issues.setStatus("inComplete");
	stocks.add(Issues);
	store.addAll(stocks);
}



  private void configPanel(final Portlet portlet) {
    portlet.setCollapsible(true);
    portlet.setAnimCollapse(false);
    portlet.getHeader().addTool(new ToolButton(ToolButton.GEAR));
    portlet.getHeader().addTool(new ToolButton(ToolButton.CLOSE, new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        portlet.removeFromParent();
      }
    }));
  }

  


}