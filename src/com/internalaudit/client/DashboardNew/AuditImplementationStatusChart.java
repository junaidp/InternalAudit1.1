package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.labels.StackLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.ColumnPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import com.internalaudit.shared.JobNamesWithExceptionsImplementationStatus;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AuditImplementationStatusChart extends VerticalLayoutContainer {

	public AuditImplementationStatusChart(ArrayList<JobNamesWithExceptionsImplementationStatus> implementationStatus) {

		add(createChart(implementationStatus));
	}

	public Chart createChart(ArrayList<JobNamesWithExceptionsImplementationStatus> implementationStatus) {

		final Chart chart = new Chart().setWidth(320).setHeight(200).setType(Series.Type.COLUMN)
				.setChartTitleText("Audit Issues Implementation Status")
				.setColumnPlotOptions(new ColumnPlotOptions().setStacking(PlotOptions.Stacking.NORMAL)
						.setDataLabels(new DataLabels().setEnabled(true).setColor("#FFFFFF")))
				.setLegend(new Legend().setAlign(Legend.Align.RIGHT).setVerticalAlign(Legend.VerticalAlign.TOP)
						.setX(-100).setY(20).setFloating(true).setBackgroundColor("#FFFFFF").setBorderColor("#CCC")
						.setBorderWidth(1).setShadow(false))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getXAsString() + "</b><br/>" + toolTipData.getSeriesName() + ": "
								+ toolTipData.getYAsLong() + "<br/>" + "Total: " + toolTipData.getTotal();
					}
				}));

		String[] listCat = new String[implementationStatus.size()];
		Point[] pointsImplemented = new Point[implementationStatus.size()];
		Point[] pointsNotImplemented = new Point[implementationStatus.size()];
		Series seriesImplemented = chart.createSeries().setName("Implemented");
		Series seriesNotImplemented = chart.createSeries().setName("Not Implemented");
		chart.addSeries(seriesImplemented);
		chart.addSeries(seriesNotImplemented);

		for (int i = 0; i < implementationStatus.size(); i++) {

			listCat[i] = implementationStatus.get(i).getJobName();

			Point pImp = new Point("Implem", implementationStatus.get(i).getImplemented()).setColor("#FFD700");
			pointsImplemented[i] = pImp;

			Point pNotImpl = new Point("not Implem", implementationStatus.get(i).getNotImplemented())
					.setColor("#FFE4B5");
			pointsNotImplemented[i] = pNotImpl;

		}

		chart.getXAxis().setCategories(listCat);

		chart.getYAxis().setMin(0).setAxisTitleText("").setStackLabels(
				new StackLabels().setEnabled(true).setStyle(new Style().setFontWeight("bold").setColor("gray")));

		seriesImplemented.setPoints(pointsImplemented);
		seriesNotImplemented.setPoints(pointsNotImplemented);

		return chart;
	}

}