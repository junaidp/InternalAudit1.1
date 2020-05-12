package com.internalaudit.client.view.ToDo;

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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.upload.AuditWorkProgramUpload;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobCreation;
import com.sencha.gxt.widget.core.client.form.DateField;

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
	DateField dueDate;
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

	// String filepath;
	// Anchor lblfilename;
	private String informationRequestId;
	private String mainFolder;
	private InternalAuditServiceAsync rpcService;

	private static InformationRequestViewUiBinder uiBinder = GWT.create(InformationRequestViewUiBinder.class);

	interface InformationRequestViewUiBinder extends UiBinder<Widget, InformationRequestRaiserView> {
	}

	public InformationRequestRaiserView() {
		initWidget(uiBinder.createAndBindUi(this));
		rpcService = GWT.create(InternalAuditService.class);
		fetchEmployees();
		fetchJobs();
		txtBoxRequestItem.getElement().setPropertyString("placeholder", "Enter text here");
		mainFolder = InternalAuditConstants.PATHINFORMATIONREQUESTUPLOADS;
		informationRequestId = InternalAuditConstants.PATHTOUNSAVEDATTACHMENTS;
		AuditWorkProgramUpload informationUploadAttachments = new AuditWorkProgramUpload(informationRequestId,
				mainFolder);
		clickHandlers(informationRequestId, mainFolder);
		panelInformationUploadAttachments.add(informationUploadAttachments);
		// String dateString =
		// DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
		// dueDate.setFormat((Format) DateTimeFormat.getFormat("MM/dd/yyyy"));
		// dueDate.setFormat(new
		// DateBox.DefaultFormat(DateTimeFormat.getFormat("EEEE, MMMM dd,
		// yyyy")));
		// dueDate.getElement().setPropertyString("placeholder", " dd/mm/yyyy");
		// dueDate.setFormat(new
		// DateBox.DefaultFormat(DateTimeFormat.getFormat("dd MMMM , yyyy")));
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

	private void clickHandlers(final String informationRequestId, final String mainFolder) {
		// btnSave.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// refreshFile(informationRequestId, mainFolder);
		// saveInformationRequest(filepath);
		// }
		//
		// });
		btnCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				deleteUnssavedAttachments();

			}

		});
	}

	// private void saveInformationRequest(String filepath) {
	// InformationRequestEntity informationrequest = new
	// InformationRequestEntity();
	// informationrequest.setRequestItem(txtBoxRequestItem.getText());
	// Employee responsibleContact = new Employee();
	// responsibleContact.setEmployeeId(Integer.parseInt(listBoxContact.getSelectedValue()));
	// JobCreation job = new JobCreation();
	// job.setJobCreationId(Integer.parseInt(listBoxJobs.getSelectedValue()));
	// informationrequest.setContactResponsible(responsibleContact);
	//
	// informationrequest.setJob(job);
	// informationrequest.setContactEmail(txtBoxEmail.getText());
	// informationrequest.setSendNotication(checkBoxNotification.getValue());
	// informationrequest.setSendReminder(checkBoxReminder.getValue());
	// informationrequest.setDueDate(dueDate.getValue());
	// informationrequest.setStatus(listBoxStatus.getSelectedIndex());
	// informationrequest.setRead(false);
	// rpcService.saveinformationRequest(informationrequest, filepath, new
	// AsyncCallback<String>() {
	//
	// @Override
	// public void onSuccess(String result) {
	// new DisplayAlert(result);
	// Window.alert(result + "success");
	//
	// }
	//
	// @Override
	// public void onFailure(Throwable caught) {
	// Window.alert("error in rpc saveInformationRequest" +
	// caught.getLocalizedMessage());
	//
	// }
	// });
	// }
	//
	// private void refreshFile(final String informationRequestId, final String
	// mainFolder) {
	// rpcService.fetchAuditStepsProcerdure(informationRequestId, mainFolder,
	// new AsyncCallback<ArrayList<String>>() {
	//
	// @Override
	// public void onSuccess(ArrayList<String> result) {
	//
	// for (int i = 0; i < result.size(); i++) {
	// lblfilename = new Anchor(result.get(i));
	//
	// lblfilename.addStyleName("pointerStyle");
	// lblfilename.getElement().getStyle().setTextDecoration(TextDecoration.NONE);
	// lblfilename.setHeight("25px");
	//
	// lblfilename.setWordWrap(false);
	// String upperCasedJobLink = lblfilename.getText();
	// lblfilename.setText(upperCasedJobLink);
	//
	// }
	// filepath = mainFolder + "/" + informationRequestId + "/" +
	// lblfilename.getText();
	//
	// }
	//
	// @Override
	// public void onFailure(Throwable caught) {
	//
	// System.out.println("fetchAuditProcedure Failed");
	// }
	//
	// });
	//
	// }

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

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public TextArea getTxtBoxRequestItem() {
		return txtBoxRequestItem;
	}

	public void setTxtBoxRequestItem(TextArea txtBoxRequestItem) {
		this.txtBoxRequestItem = txtBoxRequestItem;
	}

	// public String getFilepath() {
	// return filepath;
	// }
	//
	// public void setFilepath(String filepath) {
	// this.filepath = filepath;
	// }

	// public Anchor getLblfilename() {
	// return lblfilename;
	// }
	//
	// public void setLblfilename(Anchor lblfilename) {
	// this.lblfilename = lblfilename;
	// }

	public String getInformationRequestId() {
		return informationRequestId;
	}

	public void setInformationRequestId(String informationRequestId) {
		this.informationRequestId = informationRequestId;
	}

	public String getMainFolder() {
		return mainFolder;
	}

	public void setMainFolder(String mainFolder) {
		this.mainFolder = mainFolder;
	}

	public ListBox getListBoxContact() {
		return listBoxContact;
	}

	public void setListBoxContact(ListBox listBoxContact) {
		this.listBoxContact = listBoxContact;
	}

	public TextBox getTxtBoxEmail() {
		return txtBoxEmail;
	}

	public void setTxtBoxEmail(TextBox txtBoxEmail) {
		this.txtBoxEmail = txtBoxEmail;
	}

	public DateField getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateField dueDate) {
		this.dueDate = dueDate;
	}

	public ListBox getListBoxJobs() {
		return listBoxJobs;
	}

	public void setListBoxJobs(ListBox listBoxJobs) {
		this.listBoxJobs = listBoxJobs;
	}

	public ListBox getListBoxStatus() {
		return listBoxStatus;
	}

	public void setListBoxStatus(ListBox listBoxStatus) {
		this.listBoxStatus = listBoxStatus;
	}

	public CheckBox getCheckBoxNotification() {
		return checkBoxNotification;
	}

	public void setCheckBoxNotification(CheckBox checkBoxNotification) {
		this.checkBoxNotification = checkBoxNotification;
	}

	public CheckBox getCheckBoxReminder() {
		return checkBoxReminder;
	}

	public void setCheckBoxReminder(CheckBox checkBoxReminder) {
		this.checkBoxReminder = checkBoxReminder;
	}

	public VerticalPanel getPanelInformationUploadAttachments() {
		return panelInformationUploadAttachments;
	}

	public void setPanelInformationUploadAttachments(VerticalPanel panelInformationUploadAttachments) {
		this.panelInformationUploadAttachments = panelInformationUploadAttachments;
	}

}
