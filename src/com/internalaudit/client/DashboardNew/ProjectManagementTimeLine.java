package com.internalaudit.client.DashboardNew;

import com.google.gwt.user.client.ui.RootPanel;  
import org.moxieapps.gwt.highcharts.client.*;  
import org.moxieapps.gwt.highcharts.client.labels.*;  
import org.moxieapps.gwt.highcharts.client.plotOptions.*;  

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class ProjectManagementTimeLine  extends VerticalLayoutContainer{  
  
    public ProjectManagementTimeLine() {
		 
    	 add(createChart()); 
    }
  

    public Chart createChart() {  
    	  
        final Chart chart = new Chart()  
            .setType(Series.Type.BAR) 
            .setWidth(500)
            .setChartTitleText("Stacked bar chart")  
            .setSeriesPlotOptions(new SeriesPlotOptions()  
                .setStacking(PlotOptions.Stacking.NORMAL)  
            )  
            .setLegend(new Legend()  
                .setBackgroundColor("#FFFFFF")  
                .setReversed(true)  
            )  
            .setToolTip(new ToolTip()  
                .setFormatter(new ToolTipFormatter() {  
                    public String format(ToolTipData toolTipData) {  
                        return toolTipData.getSeriesName() + ": " +  
                            toolTipData.getYAsLong() + " " +  
                            toolTipData.getXAsString();  
                    }  
                })  
            );  
        chart.setCredits(new Credits().setText(""));
        chart.getXAxis()  
            .setCategories("Apples", "Oranges", "Pears", "Grapes", "Bananas");  
  
        chart.getYAxis()  
            .setMin(0)  
            .setAxisTitleText("Total fruit consumption");  
  
        chart.addSeries(chart.createSeries()  
            .setName("John")  
            .setPoints(new Number[] { 0, 3  })  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("Jane")  
            .setPoints(new Number[] { 4,  2 })  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("Joe")  
            .setPoints(new Number[] { 0,  5 })  
        );  
  
        return chart;  
    }  
      
    }  
 