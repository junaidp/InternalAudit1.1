package com.internalaudit.client.portal;

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
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class PortableLayoutContainerUi extends VerticalPanel {

  protected static final int MIN_HEIGHT = 1;
  protected static final int MIN_WIDTH = 1280;
  protected static final int PREFERRED_HEIGHT = 1;
  protected static final int PREFERRED_WIDTH = 1;

  private static final StockProperties properties = GWT.create(StockProperties.class);

  private PortalLayoutContainer portal;
  private Stock stock = new Stock();
  List<Stock> stocks = new ArrayList<Stock>();
  private     ListStore<Stock> store = new ListStore<Stock>(properties.key());

  private static Date randomDate() {
	    DateWrapper w = new DateWrapper();
	    int r = (int)(Math.random() * 10) * 10;
	    w = w.addDays(-r);
	    return w.asDate();
	  }

  public PortableLayoutContainerUi() {


      Portlet portletFieldWork = new Portlet();
      portletFieldWork.setHeadingHtml("Field Work");
      portletFieldWork.add(createGridFieldWork());
      configPanel(portletFieldWork);

      Portlet portletPlanning = new Portlet();
      portletPlanning.setHeadingHtml("Planing");
      portletPlanning.add(createGridPlanning());
      configPanel(portletPlanning);

      Portlet portletReporting = new Portlet();
      portletReporting.setHeadingHtml("Reporting");
      portletReporting.add(createGridReporting());
      configPanel(portletReporting);

      portal = new PortalLayoutContainer(3);
      portal.setSpacing(20);
      portal.setColumnWidth(0, .99);
      portal.setColumnWidth(1, .50);
      portal.setColumnWidth(2, .30);
      portal.add(portletFieldWork, 0);
      portal.add(portletPlanning, 0);
      portal.add(portletReporting, 0);
      setData();
      add( portal);
  }
  

  private void setData() {
	 
	// setting data pf fieldwork
	stock.setFieldWorkStep("Completed");
	stock.setName("somenameww");
//	stock.setFieldWorkStep("inComplete");
	//setting data of planning
	stock.setPlanningName("planning Name");
	stock.setPlanningWorkStep("completed");
	stock.setReportingName("reportingname");
	stock.setReportingWorkStep("incomplete");
	stock.setReportingName("reportingname1");
	stock.setReportingWorkStep("incomplete1");
	stocks.add(stock);
	store.addAll(stocks);
}

//
//public Widget createGrid() {
//    final NumberFormat number = NumberFormat.getFormat("0.00");
//    
//    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(properties.name(), 200, "Company");
//    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(properties.symbol(), 75, "Symbol");
//    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(properties.last(), 75, "Last");
//    ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(properties.change(), 75, "Change");
//    ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(properties.lastTrans(), 100, "Last Updated");
//    
//    changeCol.setCell(new AbstractCell<Double>() {
//      @Override
//      public void render(Context context, Double value, SafeHtmlBuilder sb) {
//        String style = "style='color: " + (value < 0 ? "red" : "green") + "'";
//        String v = number.format(value);
//        // The quicktip has to initialized to use the quick tips in this element. 
//        sb.appendHtmlConstant("<span " + style + " qtitle='Change' qtip='" + v + "'>" + v + "</span>");
//      }
//    });
//
//    lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));
//
//    List<ColumnConfig<Stock, ?>> columns = new ArrayList<ColumnConfig<Stock, ?>>();
//    columns.add(nameCol);
//    columns.add(symbolCol);
//    columns.add(lastCol);
//    columns.add(changeCol);
//    columns.add(lastTransCol);
//
//    ColumnModel<Stock> cm = new ColumnModel<Stock>(columns);
//
//    ListStore<Stock> store = new ListStore<Stock>(properties.key());
//   
//    store.addAll(getStocks());
//
//    final Grid<Stock> grid = new Grid<Stock>(store, cm);
//    grid.getView().setAutoExpandColumn(nameCol);
//    grid.getView().setForceFit(true);
//    grid.getView().setStripeRows(true);
//    grid.getView().setColumnLines(true);
//
//    // This is needed to enable quicktips to be displayed in the grid.  
//    // See the cell renderer HTML above with qtip attribute.
//   // QuickTip.of(grid);
//
//    return grid;
//  }

 
  
  public Widget createGridFieldWork() {
	    final NumberFormat number = NumberFormat.getFormat("0.00");
	    
	    ColumnConfig<Stock, String> fieldworkCol = new ColumnConfig<Stock, String>(properties.name(), 150, "FieldWork1");
	    ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(properties.fieldWorkDate(), 100, "Exception1");
	    ColumnConfig<Stock, String> WorkStepsComptCol = new ColumnConfig<Stock, String>(properties.fieldWorkStep(), 150, "WorkSteps Completed1");

	    lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

	    List<ColumnConfig<Stock, ?>> columns = new ArrayList<ColumnConfig<Stock, ?>>();
	    columns.add(fieldworkCol);
	    columns.add(lastTransCol);
	    columns.add(WorkStepsComptCol);
	    
	    ColumnModel<Stock> cm = new ColumnModel<Stock>(columns);

	 //   ListStore<Stock> store = new ListStore<Stock>(properties.key());
	   // store.addAll(getFieldWork());
	  //  store.addAll(stocks);

	    final Grid<Stock> grid = new Grid<Stock>(store, cm);
	    grid.getView().setAutoExpandColumn(fieldworkCol);
	    grid.getView().setForceFit(true);
	    grid.getView().setStripeRows(true);
	    grid.getView().setColumnLines(true);

	    return grid;
	  }

  
  
  public Widget createGridPlanning() {
	    final NumberFormat number = NumberFormat.getFormat("0.00");
	    
	    ColumnConfig<Stock, String> fieldworkCol = new ColumnConfig<Stock, String>(properties.planningName(), 250, "Planning");
	   
	  //  ColumnConfig<Stock, Double> WorkStepsComptCol = new ColumnConfig<Stock, Double>(properties.last(), 150, "WorkSteps Completed");
//	    ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(properties.change(), 75, "Change");
	    ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(properties.planningWorkDate(), 100, "WorkSteps Completed");
	    ColumnConfig<Stock, String> workStepComp = new ColumnConfig<Stock, String>(properties.planningWorkStep(), 250, "");
	  //  ColumnConfig<Stock, String> ExceptionlCol = new ColumnConfig<Stock, String>(properties.planningWorkStep(), 105, "");

	    lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

	    List<ColumnConfig<Stock, ?>> columns = new ArrayList<ColumnConfig<Stock, ?>>();
	    columns.add(fieldworkCol);
	    columns.add(lastTransCol);
	   columns.add(workStepComp);
//	    columns.add(changeCol);
//	    columns.add(lastTransCol);

	    ColumnModel<Stock> cm = new ColumnModel<Stock>(columns);

	    //ListStore<Stock> store = new ListStore<Stock>(properties.key());
	   // store.addAll(getPlanning());

	    final Grid<Stock> grid = new Grid<Stock>(store, cm);
	    grid.getView().setAutoExpandColumn(fieldworkCol);
	    grid.getView().setForceFit(true);
	    grid.getView().setStripeRows(true);
	    grid.getView().setColumnLines(true);

	    return grid;
	  }
  
  public Widget createGridReporting() {
	    final NumberFormat number = NumberFormat.getFormat("0.00");
	    
	    ColumnConfig<Stock, String> ReportingCol = new ColumnConfig<Stock, String>(properties.reportingName(), 150, "Reporting1");
	    ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(properties.planningWorkDate(), 100, "");
	   ColumnConfig<Stock, String> workStepsComptCol = new ColumnConfig<Stock, String>(properties.reportingWorkStep(), 150, "");
	   lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

	    List<ColumnConfig<Stock, ?>> columns = new ArrayList<ColumnConfig<Stock, ?>>();
	    columns.add(ReportingCol);
	    columns.add(lastTransCol);
	    columns.add(workStepsComptCol);
//	    columns.add(changeCol);
//	    columns.add(lastTransCol);

	    ColumnModel<Stock> cm = new ColumnModel<Stock>(columns);

//	    ListStore<Stock> store = new ListStore<Stock>(properties.key());
//	    store.addAll(getReporting());

	    final Grid<Stock> grid = new Grid<Stock>(store, cm);
	    grid.getView().setAutoExpandColumn(ReportingCol);
	    grid.getView().setForceFit(true);
	    grid.getView().setStripeRows(true);
	    grid.getView().setColumnLines(true);


	    return grid;
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

  

//  
//  public static List<Stock> getFieldWork() {
//	    List<Stock> stocks = new ArrayList<Stock>();
//
//	  //  stocks.add(new Stock("Apple Inc..", "AAPL", "aa"));
//	 
//	    stocks.add(new Stock("issues "
//	    		+ "Sopurchase order in system , Inc.", randomDate(),  "completed"));
//	    stocks.add(new Stock("Trace with Doocumentary element, Inc.", randomDate(),  "pending"));
//	    stocks.add(new Stock("Cisco Systems, Inc.", randomDate(),  "completed"));
//	    stocks.add(new Stock("Cisco Systems, Inc.", randomDate(),  "pending"));
//	    stocks.add(new Stock("Cisco Systems, Inc.", randomDate(),  "completed"));
//	    stocks.add(new Stock("Cisco Systems, Inc.", randomDate(),  "completed"));
//	    
//	   // Stock stock = new Stock();
//	   // stock.setName(name);
//	    
//	    
//
////	    
//	  
//		return stocks;
//  }
//  
//  public static List<Stock> getPlanning() {
//	    List<Stock> stocks = new ArrayList<Stock>();
//
//	  //  stocks.add(new Stock("Apple Inc..", "AAPL", "aa"));
//	 
//	    stocks.add(new Stock("audit notificaton ", randomDate(),  "completed"));
//	    stocks.add(new Stock("client kickoff meeting.", randomDate(),  "pending"));
//	    stocks.add(new Stock("key risks", randomDate(),  "completed"));
//	    stocks.add(new Stock("Objectives", randomDate(),  "pending"));
//	    stocks.add(new Stock("Objective Risk Control", randomDate(),  "completed"));
//	    stocks.add(new Stock("Audit work program", randomDate(),  "completed"));
//	    
//	    
//	    
//
////	    
//	  
//		return stocks;
//}
//  public static List<Stock> getReporting() {
//	    List<Stock> stocks = new ArrayList<Stock>();
//
//	  //  stocks.add(new Stock("Apple Inc..", "AAPL", "aa"));
//	 
//	    stocks.add(new Stock("Draft Report approval by head of internalaudit ", randomDate(),  "completed"));
//	    stocks.add(new Stock("Deaft sent to management for comments.", randomDate(),  "pending"));
//	    stocks.add(new Stock("key risks", randomDate(),  "completed"));
//	   
//	    
//	    
//	    
//
////	    
//	  
//		return stocks;
//}
//  
//  
//  public static List<Stock> getStocks() {
//	    List<Stock> stocks = new ArrayList<Stock>();
//
//	    stocks.add(new Stock("Apple Inc.", "AAPL", 125.64, 123.43, randomDate()));
//	    stocks.add(new Stock("Cisco Systems, Inc.", "CSCO", 25.84, 26.3, randomDate()));
//	    stocks.add(new Stock("Google Inc.", "GOOG", 516.2, 512.6, randomDate()));
//	    stocks.add(new Stock("Intel Corporation", "INTC", 21.36, 21.53, randomDate()));
//	    stocks.add(new Stock("Level 3 Communications, Inc.", "LVLT", 5.55, 5.54, randomDate()));
//	    stocks.add(new Stock("Microsoft Corporation", "MSFT", 29.56, 29.72, randomDate()));
//	    stocks.add(new Stock("Nokia Corporation (ADR)", "NOK", 27.83, 27.93, randomDate()));
//	    stocks.add(new Stock("Oracle Corporation", "ORCL", 18.73, 18.98, randomDate()));
//	    stocks.add(new Stock("Starbucks Corporation", "SBUX", 27.33, 27.36, randomDate()));
//	    stocks.add(new Stock("Yahoo! Inc.", "YHOO", 26.97, 27.29, randomDate()));
//	    stocks.add(new Stock("Applied Materials, Inc.", "AMAT", 18.4, 18.66, randomDate()));
//	    stocks.add(new Stock("Comcast Corporation", "CMCSA", 25.9, 26.4, randomDate()));
//	    stocks.add(new Stock("Sirius Satellite", "SIRI", 2.77, 2.74, randomDate()));
//	    stocks.add(new Stock("Tellabs, Inc.", "TLAB", 10.64, 10.75, randomDate()));
//	    stocks.add(new Stock("eBay Inc.", "EBAY", 30.43, 31.21, randomDate()));
//	    stocks.add(new Stock("Broadcom Corporation", "BRCM", 30.88, 30.48, randomDate()));
//	    stocks.add(new Stock("CMGI Inc.", "CMGI", 2.14, 2.13, randomDate()));
//	    stocks.add(new Stock("Amgen, Inc.", "AMGN", 56.22, 57.02, randomDate()));
//	    stocks.add(new Stock("Limelight Networks", "LLNW", 23, 22.11, randomDate()));
//	    stocks.add(new Stock("Amazon.com, Inc.", "AMZN", 72.47, 72.23, randomDate()));
//	    stocks.add(new Stock("E TRADE Financial Corporation", "ETFC", 24.32, 24.58, randomDate()));
//	    stocks.add(new Stock("AVANIR Pharmaceuticals", "AVNR", 3.7, 3.52, randomDate()));
//	    stocks.add(new Stock("Gemstar-TV Guide, Inc.", "GMST", 4.41, 4.55, randomDate()));
//	    stocks.add(new Stock("Akamai Technologies, Inc.", "AKAM", 43.08, 45.32, randomDate()));
//	    stocks.add(new Stock("Motorola, Inc.", "MOT", 17.74, 17.69, randomDate()));
//	    stocks.add(new Stock("Advanced Micro Devices, Inc.", "AMD", 13.77, 13.98, randomDate()));
//	    stocks.add(new Stock("General Electric Company", "GE", 36.8, 36.91, randomDate()));
//	    stocks.add(new Stock("Texas Instruments Incorporated", "TXN", 35.02, 35.7, randomDate()));
//	    stocks.add(new Stock("Qwest Communications", "Q", 9.9, 10.03, randomDate()));
//	    stocks.add(new Stock("Tyco International Ltd.", "TYC", 33.48, 33.26, randomDate()));
//	    stocks.add(new Stock("Pfizer Inc.", "PFE", 26.21, 26.19, randomDate()));
//	    stocks.add(new Stock("Time Warner Inc.", "TWX", 20.3, 20.45, randomDate()));
//	    stocks.add(new Stock("Sprint Nextel Corporation", "S", 21.85, 21.76, randomDate()));
//	    stocks.add(new Stock("Bank of America Corporation", "BAC", 49.92, 49.73, randomDate()));
//	    stocks.add(new Stock("Taiwan Semiconductor", "TSM", 10.4, 10.52, randomDate()));
//	    stocks.add(new Stock("AT&T Inc.", "T", 39.7, 39.66, randomDate()));
//	    stocks.add(new Stock("United States Steel Corporation", "X", 115.81, 114.62, randomDate()));
//	    stocks.add(new Stock("Exxon Mobil Corporation", "XOM", 81.77, 81.86, randomDate()));
//	    stocks.add(new Stock("Valero Energy Corporation", "VLO", 72.46, 72.6, randomDate()));
//	    stocks.add(new Stock("Micron Technology, Inc.", "MU", 12.02, 12.27, randomDate()));
//	    stocks.add(new Stock("Verizon Communications Inc.", "VZ", 42.5, 42.61, randomDate()));
//	    stocks.add(new Stock("Avaya Inc.", "AV", 16.96, 16.96, randomDate()));
//	    stocks.add(new Stock("The Home Depot, Inc.", "HD", 37.66, 37.79, randomDate()));
//	    stocks.add(new Stock("First Data Corporation", "FDC", 32.7, 32.65, randomDate()));
//	    return stocks;
//
//	  }

}
