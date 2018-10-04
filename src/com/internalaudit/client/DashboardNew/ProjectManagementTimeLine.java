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
           .setHeight(230)
            .setWidth(500)
            .setChartTitleText("TIME LINE")  
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
            .setCategories("Expenditures", "Assets", "Revcenue");  
  
        chart.getYAxis()  
            .setMin(0)  
            .setAxisTitleText("");  
  
        chart.addSeries(chart.createSeries()  
            .setName("")  
            .setPoints(new Number[] { 0, 3  })  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("")  
            .setPoints(new Number[] { 4,  2 })  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("")  
            .setPoints(new Number[] { 0,  5 })  
        );  
  
        return chart;  
    }  
      
    }  
 