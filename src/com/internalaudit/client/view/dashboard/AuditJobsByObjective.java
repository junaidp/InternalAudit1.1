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

public class AuditJobsByObjective extends VerticalPanel{

	public AuditJobsByObjective(){

	}

	public void setData(ArrayList<Strategic> strategicList){
		add(createDomainChart(strategicList));
		setSpacing(5);
		//		addStyleName("grayBackground");
	}


	public Chart createDomainChart(ArrayList<Strategic> strategicList) {  


		DataCount cout = new DataCount();
		cout.getDataCount(strategicList);

		final Chart chart = new Chart().setWidth(350).setHeight(350)
				.setType(Series.Type.PIE)  
				.setChartTitleText("Audit Jobs by objective") 
				.setPlotBackgroundColor((String) null)  

				.setPlotBorderWidth(null);
		chart.setCredits(
				new Credits()
				.setText("")

				)  
				.setPlotShadow(false)  
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

						.setToolTip(new ToolTip()
						.setFormatter(new ToolTipFormatter() {  
							public String format(ToolTipData toolTipData) {  
								return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";  
							}  
						})  
								);  



		int total =  cout.domain.compliance + cout.domain.operations + cout.domain.reporting + cout.domain.strategic;


		chart.addSeries(chart.createSeries()  
				.setName("Risk Assessment")  
				.setPoints(new Point[]{  
						new Point("compliance", 100*cout.domain.compliance / total).setColor("#0000CD"),  
						new Point("operations", 100*cout.domain.operations  / total ).setColor("#191970"),  
						new Point("reporting", 100*cout.domain.reporting / total).setColor("#48D1CC"),
						new Point("strategic", 100*cout.domain.strategic / total).setColor("#20B2AA"),
				})  
				);

		chart.setBackgroundColor(new Color()
		);


		chart.setSize(410, 250);
		chart.setBackgroundColor(new Color()
				);
		return chart;  
	}


}
