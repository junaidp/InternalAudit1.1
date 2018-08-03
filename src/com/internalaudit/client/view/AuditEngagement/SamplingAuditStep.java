package com.internalaudit.client.view.AuditEngagement;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.shared.AuditProgramme;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.SuggestedControls;

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
	Label lblSampleSizeData = new Label("2");
	ListBox listBoxSamplingMethod = new ListBox();
	Label lblPopulationData  = new Label("600"); 
	 
		
		

	
	
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
}