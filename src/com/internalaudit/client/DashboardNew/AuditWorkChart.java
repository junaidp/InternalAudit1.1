package com.internalaudit.client.DashboardNew;
  
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import org.moxieapps.gwt.highcharts.client.*; 
import org.moxieapps.gwt.highcharts.client.plotOptions.*;  
  
public class AuditWorkChart extends VerticalLayoutContainer {  
  
   public AuditWorkChart(){
	   
	   add(createChart());
   }  
  
    public Chart createChart() {  
  
        final Chart chart = new Chart()  
            .setType(Series.Type.BAR)  
            .setChartTitleText("Audit Work Statuss")  
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
  
        chart.getXAxis()  
            .setCategories("Fixed Assets", "Expenditures");  
  
        chart.getYAxis()  
            .setMin(0)  
            .setAxisTitleText("Audit Work Statis");  
  
        chart.addSeries(chart.createSeries()  
            .setName("Completed")  
            .setPoints(new Number[] { 5, 3, 4, 7, 2 })  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("open")  
            .setPoints(new Number[] { 2, 2, 3, 2, 1 })  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("UnderReview")  
            .setPoints(new Number[] { 3, 4, 4, 2, 5 })  
        );  
  
        return chart;  
    }  
  
}  