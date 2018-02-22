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
import com.internalaudit.shared.StrategicDepartments;

public class AuditJobsByFunction extends VerticalPanel{

	public AuditJobsByFunction(){

	}

	public void setData(ArrayList<StrategicDepartments> arrayList){
		add(createFunctionChart(arrayList));
		setSpacing(5);
//		addStyleName("grayBackground");
	}


	public Chart createFunctionChart(ArrayList<StrategicDepartments> arrayList) {  

		final DataCount cout = new DataCount();
		cout.getDivisionCount(arrayList);

		final Chart chart = new Chart().setWidth(350).setHeight(350)
				.setType(Series.Type.PIE)  
				.setChartTitleText("Audit jobs by functions	")  
//				.setPlotBackgroundColor((String) null)  
				.setPlotBorderWidth(null)  
				.setPlotShadow(false) ;
		chart.setCredits(
				new Credits()
				.setText("")

				)
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
						.setLegend(new Legend()  
						.setEnabled(true)

								)  
								.setToolTip(new ToolTip()
								.setFormatter(new ToolTipFormatter() {  
									public String format(ToolTipData toolTipData) {  
										return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";  
									}  
								})  
										);  



		int total =  cout.div.it + cout.div.comm + cout.div.business + cout.div.finance + cout.div.hr + cout.div.reg + cout.div.strategy + cout.div.pl ;
		if(total>0){
		Point pointIT = new Point("IT", 100*cout.div.it / total).setColor("#0000CD");
		Point pointIBusiness =  new Point("Business", 100* cout.div.business / total ).setColor("#191970"); 
		Point pointFinance = new Point("Finance", 100*cout.div.finance / total).setColor("#48D1CC");
		Point pointCommercial =new Point("Commercial", 100* cout.div.comm / total).setColor("#20B2AA");
		Point pointHr =new Point("Hr", 100* cout.div.hr / total);
		Point pointStrategy =new Point("Strategy", 100* cout.div.strategy / total);
		Point pointProc =new Point("Proc & Logistics", 100* cout.div.pl / total).setColor("#FFD700");
		Point pointReg =new Point("Regulatory", 100* cout.div.reg / total).setColor("#FFE4B5");

				Series seri = chart.createSeries() ;
		seri.setName("Risk Assessment") ;
		if(cout.div.it!=0){
			seri.addPoint(pointIT);
		}
		if(cout.div.business!=0){
			seri.addPoint(pointIBusiness);
		}
		if(cout.div.finance!=0){
			seri.addPoint(pointFinance);
		}
		if(cout.div.comm!=0){
			seri.addPoint(pointCommercial);
		}
		if(cout.div.hr!=0){
			seri.addPoint(pointHr);
		}
		if(cout.div.strategy!=0){
			seri.addPoint(pointStrategy);
		}
		if(cout.div.pl!=0){
			seri.addPoint(pointProc);
		}
		if(cout.div.reg!=0){
			seri.addPoint(pointReg);
		}
		
		chart.addSeries(seri);
		
		}
		
		
		
		//	                .setPoints(new Point[]{  
		//	                    point
		//	                	new Point("IT", 100*cout.div.it / total),  
		//	                    new Point("Business", 100* cout.div.business / total ),  
		//	                    new Point("Finance", 100*cout.div.finance / total),
		//	                    new Point("Commercial", 100* cout.div.comm / total),
		//	                    new Point("Hr", 100* cout.div.hr / total),
		//	                    new Point("Strategy", 100* cout.div.strategy / total),
		//	                    new Point("Proc & Logistics", 100* cout.div.pl / total),
		//	                    new Point("Regulatory", 100* cout.div.reg / total),

		//	                })  

		//	            ); 
		chart.setBackgroundColor(new Color()
				);
		return  chart.setSize(410, 250);

	}


}
