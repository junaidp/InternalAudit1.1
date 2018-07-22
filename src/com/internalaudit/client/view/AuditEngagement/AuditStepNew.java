package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AmendmentPopup;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.widgets.ExceptionRow;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.TimeOutException;

public class AuditStepNew extends Composite {

	private static AuditStepNewUiBinder uiBinder = GWT.create(AuditStepNewUiBinder.class);

	interface AuditStepNewUiBinder extends UiBinder<Widget, AuditStepNew> {
	}
	
	@UiField
	Button submit;
    @UiField
    Button reject;
    @UiField
    Button approve;
    @UiField
    HorizontalPanel approvalButtonsPanel;
    @UiField
    HorizontalPanel initiationButtonsPanel;
    @UiField
    Label auditStepId;

    @UiField
    Button addException;

    @UiField
    VerticalPanel exceptions;
    int selectedJobId; Employee loggedInEmployee;
    
    
    InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
    

	public AuditStepNew(int selectedJobId, Employee loggedInEmployee) {
		initWidget(uiBinder.createAndBindUi(this));
		layout();
	}


	private void layout() {
		
		addException.addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent arg0) {

			final ExceptionRow row = new ExceptionRow();

			exceptions.add(row);
		

			row.getRemoveRow().addClickHandler(new ClickHandler() {

			    @Override
			    public void onClick(ClickEvent event) {
				row.removeRow();
				for (int i = 0; i < exceptions.getWidgetCount(); i++) {
				    if (exceptions.getWidget(i) == row) {
					exceptions.remove(i);
				    }
				}
			    }
			});

		    }
		});
		
		
		setHandlers(null, selectedJobId);
    }

    private void setHandlers(final AuditWork auditWork, final int selectedJobId) {

	/*save.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent arg0) {
		// disableFields(exceptions);
		saveAuditStep(auditWork, selectedJobId, InternalAuditConstants.SAVED, "");

	    }

	});
*/
	approve.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent arg0) {
		
		saveAuditStep(auditWork, selectedJobId, InternalAuditConstants.APPROVED, "");

	    }

	});

	reject.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent arg0) {
		final AmendmentPopup amendmentPopup = new AmendmentPopup();
		amendmentPopup.popupAmendment();

		amendmentPopup.getBtnSubmit().addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			
			saveAuditStep(auditWork, selectedJobId, InternalAuditConstants.REJECTED,
				amendmentPopup.getComments().getText());
			amendmentPopup.getPopupComments().removeFromParent();
		    }
		});

	    }
	});
	submit.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent arg0) {
		boolean confirm = Window.confirm("Are you done with this Audit Step ?");
		if (confirm) {
		  
		    saveAuditStep(auditWork, selectedJobId, InternalAuditConstants.SUBMIT, "");
		}
	    }

	});
    }

    private void saveAuditStep(final AuditWork auditWork, final int selectedJobId, int status, String feedback) {
	// disableFields(exceptions);

	AuditStep step = new AuditStep();

	ArrayList<Exceptions> exs = new ArrayList<Exceptions>();

	step.setFeedback(feedback);
	step.setPopulation("pop");
	step.setProceducePerformance("performance");
	step.setSampleSelected("5");
	step.setSelectionBasis("sample");
	step.setConclusion("this is our concolusion");
	step.setJobId(selectedJobId);
	step.setAuditStepId(Integer.parseInt(auditStepId.getText()));
	step.setAuditWork(auditWork);
	step.setStatus(status);

	if (status == InternalAuditConstants.SUBMIT || status == InternalAuditConstants.SAVED) {

	    Employee initiatedBy = new Employee();
	    initiatedBy = loggedInEmployee;
	    step.setInitiatedBy(initiatedBy);

	    Employee approvedBy = new Employee();
	    approvedBy.setEmployeeId(0);
	    step.setApprovedBy(approvedBy);
	} else if (status == InternalAuditConstants.APPROVED || status == InternalAuditConstants.REJECTED) {
	    Employee approvedBy = new Employee();
	    approvedBy = loggedInEmployee;
	    step.setApprovedBy(approvedBy);
	}

	for (int i = 0; i < exceptions.getWidgetCount(); i++) {
	    Exceptions exception = new Exceptions();
	    exception.setDetail(((ExceptionRow) exceptions.getWidget(i)).getException().getText());
	    exception.setJobCreationId(selectedJobId);
	    exception.setExceptionId(Integer.parseInt(((ExceptionRow) exceptions.getWidget(i)).getExId().getText()));
	    exception.setAuditStep(step.getAuditStepId());
	    exs.add(exception);
	}
	//saveAuditStepAndException(step, exs, status, null);

    }
    
   

		
}
