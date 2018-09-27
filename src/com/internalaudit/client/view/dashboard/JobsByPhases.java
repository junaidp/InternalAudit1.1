package com.internalaudit.client.view.dashboard;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Color;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.plotOptions.ColumnPlotOptions;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.shared.DashBoardNewDTO;

public class JobsByPhases extends VerticalPanel {
	
	public JobsByPhases(){
		
		
		
		
	}
	
	public void setData(DashBoardNewDTO dashboard){
		add(createChart(dashboard));
	
	}
	public Chart createChart(DashBoardNewDTO dashboard){
		
		final Chart chart = new Chart()  
        .setType(Series.Type.COLUMN)  
        .setChartTitleText("Jobs by phases")  ;
        chart.setCredits(
new Credits()
.setText("")

)
        .setColumnPlotOptions(new ColumnPlotOptions()  
            .setPointPadding(0.2)  
            .setBorderWidth(0)  
        )  
        .setLegend(new Legend()  
            .setLayout(Legend.Layout.VERTICAL)  
            .setAlign(Legend.Align.LEFT)  
            .setVerticalAlign(Legend.VerticalAlign.TOP)  
            .setX(100)  
            .setY(70).setEnabled(false)
            .setFloating(true)  
//            .setBackgroundColor("#ffa500")  
            .setShadow(true)  
        )  
        .setToolTip(new ToolTip()  
            .setFormatter(new ToolTipFormatter() {  
                public String format(ToolTipData toolTipData) {  
                    return toolTipData.getXAsString() + ": " + toolTipData.getYAsLong() + "";  
                }  
            })  
        );  

    chart.getXAxis()  
        .setCategories("Planning", "Execution", "Reporting");  

    chart.getYAxis()  
        .setAxisTitleText("No of jobs")  
        .setMin(0);  

    chart.addSeries(chart.createSeries()  
        .setName("jobs")
        .setPoints(new Number[] { dashboard.getJobsInPlaning(), dashboard.getJobsInExecCount(), dashboard.getJobsInReporting() })  
    );  
    chart.setSize(410, 250);
    chart.setBackgroundColor(new Color()
    		);
    return chart;  
	}
	


}
