package com.internalaudit.client.view.ToDo;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobCreation;

public class InformationRequestRaiserView extends Composite {
	@UiField
	TextArea txtBoxRequestItem;
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
	@UiField
	Button btnCancel;
	@UiField
	VerticalPanel panelInformationUploadAttachments;
	private InternalAuditServiceAsync rpcService;

	private static InformationRequestViewUiBinder uiBinder = GWT.create(InformationRequestViewUiBinder.class);

	interface InformationRequestViewUiBinder extends UiBinder<Widget, InformationRequestRaiserView> {
	}

	public InformationRequestRaiserView() {
		initWidget(uiBinder.createAndBindUi(this));
		rpcService = GWT.create(InternalAuditService.class);
		fetchEmployees();
		clickHandlers();
		fetchJobs();
		txtBoxRequestItem.getElement().setPropertyString("placeholder", "Enter text here");
		String mainFolder = InternalAuditConstants.PATHINFORMATIONREQUESTUPLOADS;
		String informationRequestId = InternalAuditConstants.PATHTOUNSAVEDATTACHMENTS;
		AuditWorkProgramUpload informationUploadAttachments = new AuditWorkProgramUpload(informationRequestId,
				mainFolder);
		panelInformationUploadAttachments.add(informationUploadAttachments);
		// String dateString =
		// DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
		// dueDate.setFormat((Format) DateTimeFormat.getFormat("MM/dd/yyyy"));
		// dueDate.setFormat(new
		// DateBox.DefaultFormat(DateTimeFormat.getFormat("EEEE, MMMM dd,
		// yyyy")));
		dueDate.getElement().setPropertyString("placeholder", " dd/mm/yyyy");
		dueDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd MMMM , yyyy")));
	}

	public void deleteUnssavedAttachments() {
		rpcService.deleteUnsavedAttachemnts(InternalAuditConstants.PATHINFORMATIONREQUESTUPLOADS,
				new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("fail deleteUnsavedAttachments" + caught.getCause());

					}

					@Override
					public void onSuccess(String result) {
						System.out.println(result);

					}
				});
	}

	private void clickHandlers() {
		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveInformationRequest();
			}

		});
		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				deleteUnssavedAttachments();

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
		informationrequest.setRead(false);
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

	private void fetchEmployees() {
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch Employees");
			}

			@Override
			public void onSuccess(ArrayList<Employee> result) {

				for (int i = 0; i < result.size(); i++) {

					listBoxContact.addItem(result.get(i).getEmployeeName(), result.get(i).getEmployeeId() + "");
					// display.getListEmployees().addItem(result.get(i).getEmployeeName(),
					// result.get(i).getEmployeeId()+"");

				}
			}

		});
	}

	private void fetchJobs() {

		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs");

			}

			@Override
			public void onSuccess(ArrayList<JobCreation> result) {
				for (int i = 0; i < result.size(); i++) {
					listBoxJobs.addItem(result.get(i).getJobName(), result.get(i).getJobCreationId() + "");
				}
			}
		});

	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

}
