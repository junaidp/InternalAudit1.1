package com.internalaudit.client.DashboardNew;

import org.moxieapps.gwt.highcharts.client.AxisTitle;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.BarPlotOptions;

import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class ExceptionReportingStatusChart extends VerticalLayoutContainer {

	public ExceptionReportingStatusChart(DashBoardNewDTO dashboardDTO) {

		add(createChart(dashboardDTO));
	}

	public Chart createChart(DashBoardNewDTO dashboardDTO) {

		final Chart chart = new Chart()

				.setWidth(320).setHeight(250).setType(Series.Type.BAR).setChartTitleText("Exception Reporting Status")

				// .setChartSubtitleText("Source: Wikipedia.org")
				.setBarPlotOptions(new BarPlotOptions().setDataLabels(new DataLabels().setEnabled(true)))
				.setLegend(new Legend().setLayout(Legend.Layout.VERTICAL).setAlign(Legend.Align.RIGHT)
						.setVerticalAlign(Legend.VerticalAlign.TOP).setX(-100).setY(100).setFloating(true)
						.setBorderWidth(1).setBackgroundColor("#FFFFFF").setShadow(true))
				.setCredits(new Credits().setEnabled(false))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return toolTipData.getSeriesName() + ": " + toolTipData.getYAsLong() + " million";
					}
				}));

		chart.getXAxis().setCategories("Agreed By MNGMNT", "Communicated To MNGMNT", "Approved By IA Head");

		chart.getYAxis().setAxisTitle(new AxisTitle().setText("Population (millions)").setAlign(AxisTitle.Align.HIGH));

		chart.addSeries(chart.createSeries().setName("Year 1800").setPoints(new Number[] { 107, 31, 635, 203, 2 }));
		// chart.addSeries(chart.createSeries()
		// .setName("Year 1900")
		// .setPoints(new Number[] { 133, 156, 947, 408, 6 })
		// );
		// chart.addSeries(chart.createSeries()
		// .setName("Year 2008")
		// .setPoints(new Number[] { 973, 914, 4054, 732, 34 })
		// );

		return chart;
	}

}