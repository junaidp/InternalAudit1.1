package com.internalaudit.client.view.dashboard;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Color;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.Labels;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.labels.XAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.DataCount;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.DashBoardNewDTO;

public class ExceptionsImplementationChart extends VerticalPanel {
	
	public ExceptionsImplementationChart(){
		
	}
	
	public void setData(DashBoardNewDTO dashboard){
		add(createChart(dashboard));
		
	}
	public Chart createChart(DashBoardNewDTO dashboard){
		
		 final Chart chart = new Chart()  
         .setType(Series.Type.PIE)  
//         .setChartTitleText("Exceptions Implementation")  
         
//         .setPlotBackgroundColor("#F00")  
         .setPlotBorderWidth(null)  
         .setOption("/title/text", "jhhj")
         .setPlotShadow(false);
         chart.setCredits(
new Credits()
.setText("")

)  			
		
		
         .setPiePlotOptions(new PiePlotOptions()  
             .setAllowPointSelect(true)  
             .setColor("#FFFFFF") 
             
             .setPieDataLabels(new PieDataLabels()  
                 .setConnectorColor("#000000")  
//                 .setOption("connectorColor", "white")
                 

                 .setEnabled(true)  
                 .setColor("#000000")  
                 .setFormatter(new DataLabelsFormatter() {  
                     public String format(DataLabelsData dataLabelsData) {  
                         return "<b>" + dataLabelsData.getPointName() + "</b>: " + dataLabelsData.getYAsLong() + "";  
                     }  
                 })  
             )  
         )  
         .setLegend(new Legend()  
             .setLayout(Legend.Layout.VERTICAL)  
             .setAlign(Legend.Align.RIGHT)  
             .setVerticalAlign(Legend.VerticalAlign.TOP)  
             .setX(-100)  
             .setY(100)  
             
             .setFloating(true)  
             .setBorderWidth(1)  
             .setBackgroundColor("#FFFFFF")  
             .setShadow(true)  
         )  
         .setToolTip(new ToolTip()  
             .setFormatter(new ToolTipFormatter() {  
                 public String format(ToolTipData toolTipData) {  
                     return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsLong();  
                 }  
             })  
         );  

     chart.addSeries(chart.createSeries()  
         .setName("")  
         .setPoints(new Point[]{  
             new Point("Implemented", dashboard.getImplemented()).setColor("#FFD700"),  
             new Point("Not Implemented", dashboard.getNotImplemented()).setColor("#FFE4B5")
             
        
         })  
     );  

     chart.getXAxis().setLabels(new XAxisLabels()  
     .setRotation(-45)  
     .setAlign(Labels.Align.RIGHT)  
    
    		 	);  
  
     
     
    chart.setSize(440, 250);
    chart.setChartTitle(
    	     new ChartTitle()
    	       .setText("Exceptions Implementation")
    	       .setAlign(ChartTitle.Align.CENTER)
//    	       .setOption("/style/fontWeight", "bold")
//    	       .setOption("/style/fontFamily", "Impact")
    	      

    	   );
    chart.setBackgroundColor(new Color()
    		);
//    chart.setOption("/title/text", "jhhj");
//    chart.setOption("/chart/colors", "'#50B432', '#ED561B'");
//    chart.setStyle(new Style()  
//    .setFont("normal 33px Verdana, sans-serif")  
//) ; 
   

    
    return chart;  
	}
	


}
