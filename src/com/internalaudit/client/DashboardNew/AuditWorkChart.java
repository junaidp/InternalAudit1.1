package com.internalaudit.client.DashboardNew;

import java.util.ArrayList;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;

import com.internalaudit.shared.AuditWorkStatusDTO;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AuditWorkChart extends VerticalLayoutContainer {

	public AuditWorkChart(ArrayList<AuditWorkStatusDTO> auditWorkStatusDTO) {

		add(createChart(auditWorkStatusDTO));
	}

	public Chart createChart(ArrayList<AuditWorkStatusDTO> auditWorkStatusDTO) {

		final Chart chart = new Chart().setWidth(700).setHeight(270).setType(Series.Type.BAR).setChartTitleText("Audit Work Status")
				.setSeriesPlotOptions(new SeriesPlotOptions().setStacking(PlotOptions.Stacking.NORMAL))
				.setLegend(new Legend().setBackgroundColor("#FFFFFF").setReversed(true))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return toolTipData.getSeriesName() + ": " + toolTipData.getYAsLong() + " "
								+ toolTipData.getXAsString();
					}
				}));
	 
		chart.getYAxis().setMin(0).setAxisTitleText(null);
		chart.setCredits(new Credits().setText(""));
		
		chart.getYAxis().setTickInterval(1);

		String[] listCat = new String[auditWorkStatusDTO.size()];
		Point[] pointsUnderReview = new Point[auditWorkStatusDTO.size()];
		Point[] pointsOpen = new Point[auditWorkStatusDTO.size()];
		Point[] pointsCompleted = new Point[auditWorkStatusDTO.size()];
		Series seriesUnderReview = chart.createSeries().setName("Under Review");

		Series seriesOpen = chart.createSeries().setName("Open");
		Series seriesCompleted = chart.createSeries().setName("Completed");
		chart.addSeries(seriesUnderReview);
		chart.addSeries(seriesOpen);
		chart.addSeries(seriesCompleted);

		for (int i = 0; i < auditWorkStatusDTO.size(); i++) {

			listCat[i] = auditWorkStatusDTO.get(i).getJobName();

			chart.getXAxis().setCategories(listCat);

			Point pUnderReview = new Point("review", auditWorkStatusDTO.get(i).getUnderReview()).setColor("#FFD700");
			pointsUnderReview[i] = pUnderReview;

			Point pOpen = new Point("open", auditWorkStatusDTO.get(i).getOpen()).setColor("#4169E1");
			pointsOpen[i] = pOpen;

			Point pCompleted = new Point("completed", auditWorkStatusDTO.get(i).getCompleted()).setColor("#32CD32");
			pointsCompleted[i] = pCompleted;

			
		}
	

		seriesUnderReview.setPoints(pointsUnderReview);
		seriesOpen.setPoints(pointsOpen);
		seriesCompleted.setPoints(pointsCompleted);

		return chart;
	}

}