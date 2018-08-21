package com.internalaudit.client.DashboardNew;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import org.moxieapps.gwt.highcharts.client.*;  
import org.moxieapps.gwt.highcharts.client.labels.*;  
import org.moxieapps.gwt.highcharts.client.plotOptions.*;  
  
public class AuditPieChart extends VerticalLayoutContainer {  
  

    public AuditPieChart() {
    	add(createChart());
    }

    public Chart createChart() {  
  
        final Chart chart = new Chart() 
        	.setWidth(320)
        	.setHeight(200)
            .setType(Series.Type.PIE)  
            .setChartTitleText("")
  
            .setPlotBackgroundColor((String) null)  
            .setPlotBorderWidth(null)  
            .setPlotShadow(true)  
            .setPiePlotOptions(new PiePlotOptions()  
                .setAllowPointSelect(true)  
                .setCursor(PlotOptions.Cursor.POINTER)  
                .setPieDataLabels(new PieDataLabels()  
                    .setEnabled(false)  
                )  
                .setShowInLegend(true)  
            )  
            .setToolTip(new ToolTip()  
                .setFormatter(new ToolTipFormatter() {  
                    public String format(ToolTipData toolTipData) {  
                        return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";  
                    }  
                })  
            );  
  
        chart.addSeries(chart.createSeries()  
            .setName("Browser share")  
            .setPoints(new Point[]{  
                new Point("Completed", 45.0),  
                new Point("In Progress", 26.8),  
                 
            })  
        );  
  
        return chart;  
    }  
}  