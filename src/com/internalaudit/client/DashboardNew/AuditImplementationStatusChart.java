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

import com.internalaudit.shared.DashBoardNewDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AuditImplementationStatusChart extends VerticalLayoutContainer {

	public AuditImplementationStatusChart(DashBoardNewDTO dashboardDTO) {

		add(createChart(dashboardDTO));
	}

	public Chart createChart(DashBoardNewDTO dashboardDTO) {

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
		ArrayList<String> list = new ArrayList<String>();
		list.add("fixtures");
		String[] listCat = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			listCat[i] = list.get(i);
		}
		// listCat.
		chart.getXAxis().setCategories(listCat);

		chart.getYAxis().setMin(0).setAxisTitleText("").setStackLabels(
				new StackLabels().setEnabled(true).setStyle(new Style().setFontWeight("bold").setColor("gray")));

		chart.addSeries(chart.createSeries().setName("Implemented")
				// .setPoints(new Number[] {5, 3, 4, 7, 2})

				.setPoints(new Point[] { new Point("Implem", dashboardDTO.getImplemented()).setColor("#FFD700"),
						new Point("N-", dashboardDTO.getNotImplemented()).setColor("#FFE4B5")

		})

		);
		// chart.addSeries(chart.createSeries().setName("Not
		// Implemented").setPoints(new Number[] { 2, 2, 3, 2, 1 }));

		return chart;
	}

}