package com.internalaudit.client.view.dashboard;

import java.util.ArrayList;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Color;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.DataCount;
import com.internalaudit.shared.Strategic;

public class AuditJobsByRisk extends VerticalPanel{
	
	public AuditJobsByRisk(){

	}
	
	public void setData(ArrayList<Strategic> strategicList){
		add(createChart(strategicList));
		setSpacing(5);
//		addStyleName("grayBackground");
	}

		
		public Chart createChart(ArrayList<Strategic> strategicList) {  
			  
			 DataCount cout = new DataCount();
		        cout.getDataCount(strategicList);
		        
				final Chart chart = new Chart().setWidth(350).setHeight(350)
		                .setType(Series.Type.PIE)  
		                
		                .setChartTitleText("Audit jobs by risk")  
		                .setPlotBackgroundColor((String) null)  
		                .setPlotBorderWidth(null)  
		                .setPlotShadow(false)  ;
			              chart.setCredits(
			            		     new Credits()
			            		       .setText("")
			            		      
			            		   )
				                .setPiePlotOptions(new PiePlotOptions()  
				                    .setAllowPointSelect(true)  
				                   // .setCursor(PlotOptions.Cursor.POINTER)  
				                    .setPieDataLabels(new PieDataLabels() 
				                    


				                    .setConnectorColor("#000000")  
				                    .setEnabled(true)  
				                    .setColor("#000000")  
				                    .setFormatter(new DataLabelsFormatter() {  
				                        public String format(DataLabelsData dataLabelsData) {  
				                            return  dataLabelsData.getPointName() + " " + dataLabelsData.getYAsDouble() + " %";  
				                        }  
				                    })  
				                )  
				                    
				                    
				                    
		                )  
		                .setLegend(new Legend()  
		                	.setEnabled(true)

		                )  
		                .setToolTip(new ToolTip()
		                    .setFormatter(new ToolTipFormatter() {  
		                        public String format(ToolTipData toolTipData) {  
		                            return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";  
		                        }  
		                    })  
		                );  
		  

		        
		        int total =  cout.risk.hi + cout.risk.low + cout.risk.mid;
		        
		        
		        chart.addSeries(chart.createSeries()  
		            .setName("Risk Assessment")  
		            .setPoints(new Point[]{  
		                new Point("High", 100*cout.risk.hi / total).setColor("#0000CD"),  
		                new Point("Low", 100*cout.risk.low / total ).setColor("#191970"),  
		                new Point("Medium", 100*cout.risk.mid / total).setColor("#48D1CC")
		            })  
		        ); 
		        
		        chart.setSize(420, 250);
		        chart.setBackgroundColor(new Color()
		        		);
		        return chart;  
	    }
		

}
