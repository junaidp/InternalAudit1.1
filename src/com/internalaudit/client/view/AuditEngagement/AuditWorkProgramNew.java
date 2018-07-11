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

public class AuditWorkProgramNew implements IsWidget {

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		VerticalPanel vpn = new VerticalPanel();
		// TODO Auto-generated method stub
		Label lblSerialNo = new Label("Serial No.");
		Label lblReference = new Label("Reference");
		Label lblAuditProg = new Label("Audit Program");
		
		Label lblReviewer = new Label("Reviewer");
		
		
		Label lblSerialNoData = new Label("01");
		
		Label lblReferenceData = new Label("p2p-c1");
	
		TextArea txtAreaAuditProg = new TextArea();
	    txtAreaAuditProg.setWidth("300px");
		txtAreaAuditProg.setHeight("90px");
	    txtAreaAuditProg.setText("hello h listen hey hello hi");
	    
	    Label lblReviewerData = new Label("ABCD");
	    
        
      

       
        
         lblSerialNo.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblAuditProg.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblReviewer.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblReference.getElement().getStyle().setFontWeight(FontWeight.BOLD);
     
        lblAuditProg.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblReference.getElement().getStyle().setMarginLeft(20, Unit.PX);
       lblReviewer.getElement().getStyle().setMarginLeft(20, Unit.PX);
       
  
        lblReferenceData.getElement().getStyle().setMarginLeft(20, Unit.PX);
        txtAreaAuditProg.getElement().getStyle().setMarginLeft(20, Unit.PX);
        lblReviewerData.getElement().getStyle().setMarginLeft(20, Unit.PX);
       
        
        
        FlexTable flex = new FlexTable();
     
        
        flex.setWidget(0,1, lblSerialNo);
        flex.setWidget(1,1,lblSerialNoData);
        
        flex.setWidget(0,2, lblReference);
      flex.setWidget(1,2,lblReferenceData);
      
        flex.setWidget(0,3, lblAuditProg);
      flex.setWidget(1,3,txtAreaAuditProg);
      
        flex.setWidget(0,4, lblReviewer);
      flex.setWidget(1,4,lblReviewerData);
      
		return flex;
	}

}