package com.internalaudit.client.DashboardNew;

import java.util.HashMap;

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

import com.internalaudit.shared.InternalAuditConstants;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class ExceptionReportingStatusChart extends VerticalLayoutContainer {

	public ExceptionReportingStatusChart(HashMap<String, Integer> reportStatus) {

		add(createChart(reportStatus));
	}

	public Chart createChart(HashMap<String, Integer> reportStatus) {

		final Chart chart = new Chart().setWidth(500).setHeight(300).setType(Series.Type.BAR)
				.setChartTitleText("Exception Reporting Status")

				// .setChartSubtitleText("Source: Wikipedia.org")
				.setBarPlotOptions(new BarPlotOptions().setDataLabels(new DataLabels().setEnabled(true)))
				.setLegend(new Legend().setLayout(Legend.Layout.VERTICAL).setAlign(Legend.Align.RIGHT)
						.setVerticalAlign(Legend.VerticalAlign.TOP).setX(-100).setY(100).setFloating(true)
						.setBorderWidth(1).setBackgroundColor("#FFFFFF").setShadow(true))
				.setCredits(new Credits().setEnabled(false))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return toolTipData.getSeriesName();
						//+ ": " + toolTipData.getYAsLong() + " million";
					}
				}));
		/*
		 * Point[] pointexceptionsToSent = new Point[1]; Point[]
		 * pointawaitingComments = new Point[1]; Point[] pointcommentsReceived =
		 * new Point[1]; Point[] pointreportIssued = new Point[1]; Point[]
		 * pointfinalReportIssued = new Point[1];
		 */
		chart.getXAxis().setCategories(InternalAuditConstants.EXCEPTIONSTOSENT, InternalAuditConstants.AWAITINGCOMMENTS,
				InternalAuditConstants.COMMENTSRECEIVED, InternalAuditConstants.REPORTISSUED,
				InternalAuditConstants.FINALREPORTISSUED);

		//chart.getYAxis().setAxisTitle(new AxisTitle().setText("Population (millions)").setAlign(AxisTitle.Align.HIGH));

		// Point p1 = new Point("Implem", reportStatus.get("exceptionsToSent"));
		// pointexceptionsToSent[0] = p1;

		chart.addSeries(chart.createSeries().setName("")
				.setPoints(new Number[] { reportStatus.get(InternalAuditConstants.EXCEPTIONSTOSENT),
						
						reportStatus.get(InternalAuditConstants.AWAITINGCOMMENTS),
						reportStatus.get(InternalAuditConstants.COMMENTSRECEIVED),
						reportStatus.get(InternalAuditConstants.REPORTISSUED),
						reportStatus.get(InternalAuditConstants.FINALREPORTISSUED) })).setColors("#4169E1") ;

		// chart.addSeries(chart.createSeries().setName("Year
		// 1900").setPoints(new Number[] { 133, 156, 947, 408, 6 }));

		// chart.addSeries(chart.createSeries().setName("Year 1900")
		// .setPoints(new Number[] { reportStatus.get("exceptionsToSent"), 156,
		// 947, 408, 6 }));

		// chart.addSeries(chart.createSeries()
		// .setName("Year 2008")
		// .setPoints(new Number[] { 973, 914, 4054, 732, 34 })
		// );

		return chart;
	}

}