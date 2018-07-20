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

public class AuditWorkProgramNew implements IsWidget {
	Label lblSerialNo = new Label("Serial No.");
	Label lblReference = new Label("Reference");
	Label lblAuditProg = new Label("Audit Program");
	Label lblReviewer = new Label("Reviewer");
	TextArea txtAreaAuditProg = new TextArea();
	 Label lblReviewerData = new Label("");
	 Label lblSerialNoData = new Label("");
		
		Label lblReferenceData = new Label("");
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		VerticalPanel vpn = new VerticalPanel();
		// TODO Auto-generated method stub
		
		lblSerialNo.addStyleName("w3-panel w3-light-blue");
		
		lblReference.addStyleName("w3-panel w3-light-blue");
		
		lblAuditProg.addStyleName("w3-panel w3-light-blue");
		
		lblReviewer.addStyleName("w3-panel w3-light-blue");
		
		
	
		
	    txtAreaAuditProg.setWidth("300px");
		txtAreaAuditProg.setHeight("90px");
	    txtAreaAuditProg.setText("");
	    
	   
	    
        
      

       
        
         lblSerialNo.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblAuditProg.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblReviewer.getElement().getStyle().setFontWeight(FontWeight.BOLD);
        lblReference.getElement().getStyle().setFontWeight(FontWeight.BOLD);
     
        lblAuditProg.getElement().getStyle().setMarginLeft(1, Unit.PX);
        lblReference.getElement().getStyle().setMarginLeft(10, Unit.PX);
       lblReviewer.getElement().getStyle().setMarginLeft(10, Unit.PX);
       lblSerialNoData.setWidth("135px");
       lblSerialNoData.addStyleName("w3-panel");
       lblReferenceData.addStyleName("w3-panel");
       txtAreaAuditProg.addStyleName("w3-panel");
       lblReviewerData.addStyleName("w3-panel");
       
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

	public void hideElemetns(){
		lblSerialNo.setVisible(false);
		lblReviewer.setVisible(false);
		lblAuditProg.setVisible(false);
		lblReference.setVisible(false);
		
	}

	public void setData(AuditProgramme auditProgramme) {
		txtAreaAuditProg.setText(auditProgramme.getAuditProgrammeName());
		lblReviewerData.setText(auditProgramme.getReviewer().getEmployeeName());
		lblSerialNoData.setText(auditProgramme.getAuditProgrammeId()+"");
		//TODO populate other , refDate = auditProgramme.getSuggestedControlsId().getRefNo(); (Add refNo colun in suggestcontrols Table , data is in auditeng PDF)
		//
		
	}
}