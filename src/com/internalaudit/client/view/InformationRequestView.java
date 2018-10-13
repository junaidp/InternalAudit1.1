package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.JobCreation;
import com.sencha.gxt.chart.client.draw.engine.SVG.TextBBox;

public class InformationRequestView extends Composite {
	@UiField
	TextBox txtBoxRequestItem;
	@UiField
	ListBox listBoxContact;
	@UiField
	TextBox txtBoxEmail;
	@UiField
	CheckBox checkBoxNotification;
	@UiField
	CheckBox checkBoxReminder;
	@UiField
	DateBox dueDate;
	@UiField
	ListBox listBoxJobs;
	
	@UiField
	ListBox listBoxStatus;
	
	@UiField
	Button btnSave;
	private InternalAuditServiceAsync rpcService;
	

	private static InformationRequestViewUiBinder uiBinder = GWT.create(InformationRequestViewUiBinder.class);

	interface InformationRequestViewUiBinder extends UiBinder<Widget, InformationRequestView> {
	}

	public InformationRequestView() {
		initWidget(uiBinder.createAndBindUi(this));
		rpcService = GWT.create(InternalAuditService.class);
		fetchEmployees();
		fetchJobs();
		btnSave.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			saveInformationRequest();
			}

	
		});
	} 
	
	private void saveInformationRequest() {
		InformationRequestEntity informationrequest = new InformationRequestEntity();
		informationrequest.setRequestItem(txtBoxRequestItem.getText());
		Employee responsibleContact = new Employee();
		responsibleContact.setEmployeeId(Integer.parseInt(listBoxContact.getSelectedValue()));
		JobCreation job = new JobCreation();
		job.setJobCreationId(Integer.parseInt(listBoxJobs.getSelectedValue()));
		informationrequest.setContactResponsible(responsibleContact);
		informationrequest.setJob(job);
		informationrequest.setContactEmail(txtBoxEmail.getText());
		informationrequest.setSendNotication(checkBoxNotification.getValue());
		informationrequest.setSendReminder(checkBoxReminder.getValue());
		informationrequest.setDueDate(dueDate.getValue());
		informationrequest.setStatus(listBoxStatus.getSelectedIndex());
			rpcService.saveinformationRequest(informationrequest, new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
				new DisplayAlert(result);
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("error in rpc saveInformationRequest" + caught.getLocalizedMessage());
					
				}
			});
	}
	private void fetchEmployees(){
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Employee> result) {
				
				for(int i=0; i< result.size(); i++){
				
						listBoxContact.addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId()+"");
						//display.getListEmployees().addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId()+"");
						
					}
				}
			
		});
	}
private void fetchJobs(){
		
		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs");
				
			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {
				for(int i=0; i< result.size(); i++){
				listBoxJobs.addItem(result.get(i).getJobName(),result.get(i).getJobCreationId()+"");
				}
			}
		});
		
	}

}
