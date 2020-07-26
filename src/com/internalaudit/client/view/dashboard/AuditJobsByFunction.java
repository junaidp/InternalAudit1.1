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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.DataCount;
import com.internalaudit.shared.Division;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDepartments;

public class AuditJobsByFunction extends VerticalPanel{

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private ArrayList<Division> listDivision;
	public AuditJobsByFunction(){

	}

	public void setData(ArrayList<Strategic> listStrategic){
//		fetchDivisions();
		setListDivisions(listStrategic);
		add(createFunctionChart(listDivision));
		setSpacing(5);
//		addStyleName("grayBackground");
	}

	private void setListDivisions(ArrayList<Strategic> listStrategic) {
		listDivision = new ArrayList<Division>();
		for(Strategic it: listStrategic) {
			listDivision.add(it.getDivision());
		}
	}

	public Chart createFunctionChart(ArrayList<Division> arrayList) {  

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



		int total = cout.div.HeadOffice + cout.div.AGHKhoba + cout.div.AGHDammam + cout.div.AGHHofuf + cout.div.AGHJubail
				+ cout.div.AMCRakkah + cout.div.AMCJubail + cout.div.AGHAzzizia + cout.div.AMCDammam + cout.div.EMATradingDivision + cout.div.MACHSCollege;
		if(total>0){
		Point pointHeadOffc = new Point("Head Office", 100 * cout.div.HeadOffice / total).setColor("#0000CD");
		Point pointAGHKkhoba =  new Point("AGH Khoba", 100 * cout.div.HeadOffice / total).setColor("#191970"); 
		Point poinAGHDammam = new Point("AGH Dammam", 100 * cout.div.AGHDammam / total).setColor("#48D1CC");
		Point pointAGHHofuf =new Point("AGH Hofuf", 100*cout.div.AGHHofuf / total).setColor("#20B2AA");
		Point poinAGHJubail =new Point("AGH Jubail", 100* cout.div.AGHJubail / total);
		Point poinAMCRakkah = new Point("AMC Rakkah", 100* cout.div.AMCRakkah /total);
		Point poinAMCJuba =new Point("AMC Jubail", 100* cout.div.AMCJubail /total);
		Point poinAGHAzzizia =new Point("AGH Azzizia", 100* cout.div.AGHAzzizia / total).setColor("#FFD700");
		Point poinAMCDammam =new Point("AMC Dammam", 100* cout.div.AMCDammam / total).setColor("#FFE4B5");
		Point poinEMATradingDivision =new Point("EMA-Trading Division", 100* cout.div.HeadOffice / total);
		Point poinMACHSCollege =new Point("MACHS College", 100* cout.div.MACHSCollege / total);

		Series seri = chart.createSeries() ;
		seri.setName("Risk Assessment") ;
		
		if(cout.div.HeadOffice!=0){
			seri.addPoint(pointHeadOffc);
		}
		if(cout.div.AGHKhoba!=0){
			seri.addPoint(pointAGHKkhoba);
		}
		if(cout.div.AGHDammam!=0){
			seri.addPoint(poinAGHDammam);
		}
		if(cout.div.AGHHofuf!=0){
			seri.addPoint(pointAGHHofuf);
		}
		if(cout.div.AGHJubail!=0){
			seri.addPoint(poinAGHJubail);
		}
		if(cout.div.AMCRakkah!=0){
			seri.addPoint(poinAMCRakkah);
		}
		if(cout.div.AMCJubail != 0){
			seri.addPoint(poinAMCJuba);
		}
		if(cout.div.AGHAzzizia!=0){
			seri.addPoint(poinAGHAzzizia);
		}
		if(cout.div.AMCDammam!=0){
			seri.addPoint(poinAMCDammam);
		}
		if(cout.div.EMATradingDivision!=0){
			seri.addPoint(poinEMATradingDivision);
		}
		if(cout.div.MACHSCollege!=0){
			seri.addPoint(poinMACHSCollege);
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
	private void fetchDivisions( ) {
		rpcService.fetchDivision(new AsyncCallback<ArrayList<Division>>() {
			
			@Override
			public void onSuccess(ArrayList<Division> divisions) {
				add(createFunctionChart(divisions));
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Failed fetching Divisions " + arg0.getLocalizedMessage());
				
			}
		});
		
	}
}
