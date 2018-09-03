package com.internalaudit.client.DashboardNew;
import org.moxieapps.gwt.highcharts.client.*;  
import org.moxieapps.gwt.highcharts.client.labels.*;  
import org.moxieapps.gwt.highcharts.client.plotOptions.*;


import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class ProjectManagementActualHours  extends VerticalLayoutContainer {

	
	public  ProjectManagementActualHours(){
		
		add(createChart());
	} 
	   public Chart createChart() {  
		   
	        final Chart chart = new Chart()  
//	            .setChartTitleText("Average Monthly Temperature and Rainfall in Tokyo")  
//	            .setChartSubtitleText("Source: WorldClimate.com")  
	            .setWidth(500)
	            .setToolTip(new ToolTip()  
	                .setFormatter(new ToolTipFormatter() {  
	                    public String format(ToolTipData toolTipData) {  
	                        return toolTipData.getXAsString() + ": " + toolTipData.getYAsDouble() +  
	                            ("Actual Hour".equals(toolTipData.getSeriesName()) ? " mm" : "°C");  
	                    }  
	                })  
	            )  
	            .setLegend(new Legend()  
	                .setLayout(Legend.Layout.VERTICAL)  
	                .setAlign(Legend.Align.LEFT)  
	                .setVerticalAlign(Legend.VerticalAlign.TOP)  
	                .setX(120)  
	                .setY(100)  
	                .setFloating(true)  
	                .setBackgroundColor("#FFFFFF")  
	            );  
	        chart.setCredits(new Credits().setText(""));
	        chart.getXAxis()  
	            .setCategories(  
	                "Jan", "Feb", "Mar", "Apr", "May", "Jun",  
	                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"  
	            );  
	  
	        // Primary yAxis  
	        chart.getYAxis(0)  
	            .setAxisTitle(new AxisTitle()  
	                .setText("Budget")  
	            )  
	            .setLabels(new YAxisLabels()  
	                .setStyle(new Style()  
	                    .setColor("#89A54E")  
	                )  
	                .setFormatter(new AxisLabelsFormatter() {  
	                    public String format(AxisLabelsData axisLabelsData) {  
	                        return axisLabelsData.getValueAsLong() + "°C";  
	                    }  
	                })  
	            );  
	  
	        // Secondary yAxis  
	        chart.getYAxis(1)  
	            .setAxisTitle(new AxisTitle()  
	                .setText("Actual Hour")  
	            )  
	            .setOpposite(true)  
	            .setLabels(new YAxisLabels()  
	                .setStyle(new Style()  
	                    .setColor("#4572A7")  
	                )  
	                .setFormatter(new AxisLabelsFormatter() {  
	                    public String format(AxisLabelsData axisLabelsData) {  
	                        return axisLabelsData.getValueAsLong() + " mm";  
	                    }  
	                })  
	            );  
	  
	        chart.addSeries(chart.createSeries()  
	            .setName("Actual Hours")  
	            .setType(Series.Type.COLUMN)  
	            .setPlotOptions(new ColumnPlotOptions()  
	            		.setColor("#4169E1") 
	            )  
	            .setYAxis(1)  
	            .setPoints(new Number[]{  
	                49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4  
	            })  
	        );  
	        chart.addSeries(chart.createSeries()  
	            .setName("Budget Hours")  
	            .setType(Series.Type.SPLINE)  
	            .setPlotOptions(new SplinePlotOptions()  
	            		.setColor("#FF6347")  
	            )  
	            .setPoints(new Number[]{  
	                7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6  
	            })  
	        );  
	  
	        return chart;  
	    }  
}
