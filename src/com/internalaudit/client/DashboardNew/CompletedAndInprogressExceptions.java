package com.internalaudit.client.DashboardNew;

import java.util.HashMap;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import com.internalaudit.shared.InternalAuditConstants;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class CompletedAndInprogressExceptions extends VerticalLayoutContainer {

	public CompletedAndInprogressExceptions(HashMap<String, Integer> completedAndInprogressExceptions) {
		add(createChart(completedAndInprogressExceptions));
	}

	public Chart createChart(HashMap<String, Integer> completedAndInprogressExceptions) {

		final Chart chart = new Chart().setWidth(320).setHeight(200).setType(Series.Type.PIE).setChartTitleText("")

				.setPlotBackgroundColor((String) null).setPlotBorderWidth(null).setPlotShadow(true)
				.setPiePlotOptions(new PiePlotOptions().setAllowPointSelect(true).setCursor(PlotOptions.Cursor.POINTER)
						.setPieDataLabels(new PieDataLabels().setEnabled(false)).setShowInLegend(true))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";
					}
				}));

		chart.addSeries(chart.createSeries().setName("Exceptions")
				.setPoints(new Point[] {
						new Point("Completed",
								completedAndInprogressExceptions.get(InternalAuditConstants.COMPLETEDEXCEPTIONS)),
						new Point("In Progress",
								completedAndInprogressExceptions.get(InternalAuditConstants.INPROGRESSEXCEPTIONS)),

		}));

		return chart;
	}
}