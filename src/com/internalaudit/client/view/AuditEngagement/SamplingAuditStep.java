 package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SamplingAuditStep extends VerticalPanel {
	Label lblControl = new Label("Control");
	Label lblControlList = new Label("Control List");
	Label lblFrequency = new Label("Frequency");
	Label lblSampleSize = new Label("Sample Size");
	Label lblSamplingMethod = new Label("Sampling Method");
	Label lblPopulationSize = new Label("Population Size");
	
	TextArea txtAreaControl = new TextArea();
	ListBox listBoxControlList = new ListBox();
	ListBox listBoxFrequency = new ListBox();
	TextBox lblSampleSizeData = new TextBox();
	ListBox listBoxSamplingMethod = new ListBox();
	TextBox lblPopulationData  = new TextBox(); 
	 
		
		

	
	
	public SamplingAuditStep() {
		// TODO Auto-generated method stub
	
		// Styling of all the labels
		
		lblControl.addStyleName("w3-panel w3-light-blue");
		lblControlList.addStyleName("w3-panel w3-light-blue");
		lblFrequency.addStyleName("w3-panel w3-light-blue");
		lblSampleSize.addStyleName("w3-panel w3-light-blue");
		lblSamplingMethod.addStyleName("w3-panel w3-light-blue");
		lblPopulationSize.addStyleName("w3-panel w3-light-blue");
		
		lblControl.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblControlList.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblFrequency.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblSampleSize.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblSamplingMethod.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		lblPopulationSize.getElement().getStyle().setFontWeight(FontWeight.BOLD);
		
		// Styling of all the labels data

		
		txtAreaControl.getElement().getStyle().setMarginLeft(50, Unit.PX);
		listBoxControlList.getElement().getStyle().setMarginLeft(50, Unit.PX);
		listBoxFrequency.getElement().getStyle().setMarginLeft(50, Unit.PX);
		lblSampleSizeData.getElement().getStyle().setMarginLeft(50, Unit.PX);
		listBoxSamplingMethod.getElement().getStyle().setMarginLeft(50, Unit.PX);
		lblPopulationData.getElement().getStyle().setMarginLeft(50, Unit.PX);
		
//		lblSampleSizeData.setText("3");
		lblSampleSizeData.setEnabled(false);
		
		
		//lblPopulationData.setText("u00");
		lblPopulationData.setEnabled(false);
		
	
	    txtAreaControl.setWidth("300px");
		txtAreaControl.setHeight("90px");
	    txtAreaControl.setText("Purchase Order Cannot be Issued untill and Unless vendor is selected by the authorized as per the company's approved policy within the procurement module.");
	    
		listBoxControlList.addItem("Low", "0");
		listBoxControlList.addItem("Medium", "1");
		listBoxControlList.addItem("High", "2");
		
		listBoxFrequency.addItem("Anually", "0");
		listBoxFrequency.addItem("Quarterly", "1");
		listBoxFrequency.addItem("Monthly", "2");
		listBoxFrequency.addItem("Weekly", "3");
		listBoxFrequency.addItem("Daily", "4");
		listBoxFrequency.addItem("Recurring", "5");
		
		listBoxSamplingMethod.addItem("Random Selection", "0");
		listBoxSamplingMethod.addItem("Systematic Selection", "1");
		
		listBoxSamplingMethod.addItem("Block Selection", "2");	 
		listBoxFrequency.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				getSampleSize(listBoxControlList.getSelectedValue(), listBoxFrequency.getSelectedValue());
				
			}
		});
		
		listBoxControlList.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				getSampleSize(listBoxControlList.getSelectedValue(), listBoxFrequency.getSelectedValue());
				
			}
		});
		
			
			
	
        
        
        FlexTable flex = new FlexTable();
     
        flex.setWidget(0,0, lblControl);
        flex.setWidget(0,1,txtAreaControl);
        
        flex.setWidget(1,0, lblControlList);
        flex.setWidget(1,1,listBoxControlList);
        
        flex.setWidget(2,0, lblFrequency);
        flex.setWidget(2,1,listBoxFrequency);
        
        flex.setWidget(3,0, lblSampleSize);
        flex.setWidget(3,1,lblSampleSizeData);
        
        flex.setWidget(4,0, lblSamplingMethod);
        flex.setWidget(4,1,listBoxSamplingMethod);
        
        flex.setWidget(5,0, lblPopulationSize);
        flex.setWidget(5,1,lblPopulationData);
      
		add(flex);
	}






	private void getSampleSize(String control, String frequency) {
		
		if (control.equals("0") && frequency.equals("0")){
		
			lblSampleSizeData.setText("1");
		}
		if (control.equals("1") && frequency.equals("0")){
			
			lblSampleSizeData.setText("1");
		}
		if (control.equals("2") && frequency.equals("0")){
			
			lblSampleSizeData.setText("1");
		}
		
		
		if (control.equals("0") && frequency.equals("1")){
			
			lblSampleSizeData.setText("2");
		}
	if (control.equals("1") && frequency.equals("1")){
			
			lblSampleSizeData.setText("2");
		}
	if (control.equals("2") && frequency.equals("1")){
		
		lblSampleSizeData.setText("2");
	}
	
	if (control.equals("0") && frequency.equals("2")){
		
		lblSampleSizeData.setText("2");
	}
	if (control.equals("1") && frequency.equals("2")){
		
		lblSampleSizeData.setText("3");
	}
	if (control.equals("2") && frequency.equals("2")){
		
		lblSampleSizeData.setText("3");
	}
	
	
	if (control.equals("0") && frequency.equals("3")){
		
		lblSampleSizeData.setText("5");
	}
	
	if (control.equals("1") && frequency.equals("3")){
		
		lblSampleSizeData.setText("6");
	}
	
	if (control.equals("2") && frequency.equals("3")){
		
		lblSampleSizeData.setText("8");
	}
	
	

	if (control.equals("0") && frequency.equals("4")){
		
		lblSampleSizeData.setText("15");
	}

	if (control.equals("1") && frequency.equals("4")){
		
		lblSampleSizeData.setText("20");
	}

	if (control.equals("2") && frequency.equals("4")){
		
		lblSampleSizeData.setText("25");
	}
	
	

	if (control.equals("0") && frequency.equals("5")){
		
		lblSampleSizeData.setText("25");
	}

	if (control.equals("1") && frequency.equals("5")){
		
		lblSampleSizeData.setText("30");
	}

	if (control.equals("2") && frequency.equals("5")){
		
		lblSampleSizeData.setText("40");
	}
	
	//for populated
	if ( frequency.equals("0")){
		
		lblPopulationData.setText("1");
	}
	if ( frequency.equals("1")){
		
		lblPopulationData.setText("4");
	}
	if ( frequency.equals("2")){
		
		lblPopulationData.setText("12");
	}
	if ( frequency.equals("3")){
		
		lblPopulationData.setText("52");
	}
	

	
	}
}