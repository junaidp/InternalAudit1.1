package com.internalaudit.client.DashboardNew;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

import org.moxieapps.gwt.highcharts.client.*;  
import org.moxieapps.gwt.highcharts.client.labels.*;  
import org.moxieapps.gwt.highcharts.client.plotOptions.*;  
  
public class AuditImplementationStatusChart extends VerticalLayoutContainer {  
  
public AuditImplementationStatusChart(){
	
	add(createChart());
}
  
    public Chart createChart() {  
  
        final Chart chart = new Chart() 
        		.setWidth(320)
            	.setHeight(200)
            .setType(Series.Type.COLUMN)  
            .setChartTitleText("Audit Issues Implementation Status")  
            .setColumnPlotOptions(new ColumnPlotOptions()  
                .setStacking(PlotOptions.Stacking.NORMAL)  
                .setDataLabels(new DataLabels()  
                    .setEnabled(true)  
                    .setColor("#FFFFFF")  
                )  
            )  
            .setLegend(new Legend()  
                .setAlign(Legend.Align.RIGHT)  
                .setVerticalAlign(Legend.VerticalAlign.TOP)  
                .setX(-100)  
                .setY(20)  
                .setFloating(true)  
                .setBackgroundColor("#FFFFFF")  
                .setBorderColor("#CCC")  
                .setBorderWidth(1)  
                .setShadow(false)  
            )  
            .setToolTip(new ToolTip()  
                .setFormatter(new ToolTipFormatter() {  
                    public String format(ToolTipData toolTipData) {  
                        return "<b>" + toolTipData.getXAsString() + "</b><br/>" +  
                            toolTipData.getSeriesName() + ": " + toolTipData.getYAsLong() + "<br/>" +  
                            "Total: " + toolTipData.getTotal();  
                    }  
                })  
            );  
  
        chart.getXAxis()  
            .setCategories("Expenditures", "Fixed Assetts", "Current Liabilities");  
  
        chart.getYAxis()  
            .setMin(0)  
            .setAxisTitleText("")  
            .setStackLabels(new StackLabels()  
                .setEnabled(true)  
                .setStyle(new Style()  
                    .setFontWeight("bold")  
                    .setColor("gray")  
                )  
            );  
  
        chart.addSeries(chart.createSeries()  
            .setName("Implemented")  
            .setPoints(new Number[] {5, 3, 4, 7, 2})  
        );  
        chart.addSeries(chart.createSeries()  
            .setName("not Implemented")  
            .setPoints(new Number[] {2, 2, 3, 2, 1})  
        );  
  
  
        return chart;  
    }  
  
}  