package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.widgets.SkillsResources;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobSkillRelation;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.Softskills;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.TimeOutException;

public class JobCreationPresenter implements Presenter {

	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private StrategicDTO DTO;
	private JobCreationDTO jobDTO = null;
	private Logger logger = Logger.getLogger("AuditUniverStrategicViewData");
	private ArrayList<StrategicDepartments> selectedStrategicDepartments;

	public interface Display {
		Widget asWidget();

		Object getHtmlErrorMessage = null;

		TextBox getEstimatedWeeks();

		VerticalPanel getSkillResourceContainer();

		ListBox getProposedResources();

		TextBox getRiskRating();

		StrategicDTO getStrategicDTO();

		ListBox getRelevantDept();

		TextBox getDomainText();

		TextBox getTechSkill();

		Label getHeading();

		Button getSaveJobCreation();

		Button getBackButton();

		ListBox getSoftSkill();

		ListBox getAuditHead();

		void disableFields();

		TextBox getJobCreationId();

	}

	public JobCreationPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view,
			StrategicDTO strategicDTO) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;

		this.DTO = strategicDTO;

		fetchCreatedJob(rpcService, strategicDTO);
		fetchDepartments();
	}

	private void fetchCreatedJob(InternalAuditServiceAsync rpcService, StrategicDTO strategicDTO) {
		rpcService.fetchCreatedJob(strategicDTO.getStrategicId(), true, true, "strategicId",
				new AsyncCallback<JobCreationDTO>() {

					@Override
					public void onFailure(Throwable caught) {

						logger.log(Level.INFO, "FAIL: fetchCreatedJob .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: fetchCreatedJob .Inside AuditAreaspresenter");
							Window.alert("FAIL: fetchCreatedJob");// After FAIL
																	// ... write
																	// RPC Name
																	// NOT
																	// Method
																	// Name..
						}

					}

					@Override
					public void onSuccess(JobCreationDTO jobCreationDTO) {

						if (jobCreationDTO == null) {
							System.out.println("no previouis job creation recrd found for this job");

						} else {
							if (jobCreationDTO.getJob().isApproved()) {

								display.getSaveJobCreation().setVisible(false);

							}
							System.out.println(
									" Job creation found with id : " + jobCreationDTO.getJob().getJobCreationId());
							jobDTO = jobCreationDTO;
							display.getHeading().setText(jobDTO.getJob().getJobName());
							display.getTechSkill().setText(jobDTO.getJob().getTechnical());
							display.getRiskRating().setText(jobDTO.getJob().getRiskRating());
							display.getDomainText().setText(jobDTO.getJob().getDomainText());
							display.getEstimatedWeeks().setText(String.valueOf(jobDTO.getJob().getEstimatedWeeks()));
							display.getJobCreationId().setText(String.valueOf(jobDTO.getJob().getJobCreationId()));

							// fill audit head listbox and select correct emo
							fetchEmployees(jobDTO.getJob().getAuditHead());

						}
					}

				});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();

	}

	private void bind() {

		// redaundant call :(
		fetchEmployees(0); // 0 means no emp was selected

		display.getSaveJobCreation().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				JobCreationDTO dto = new JobCreationDTO();
				dto.getJob().setJobCreationId(Integer.parseInt(display.getJobCreationId().getText()));
				dto.getJob().setDomainText(display.getDomainText().getText().toString());
				try {
					dto.getJob().setEstimatedWeeks(Integer.parseInt(display.getEstimatedWeeks().getText().toString()));
				} catch (Exception ex) {
					dto.getJob().setEstimatedWeeks(0);

				}

				// change 18
				Strategic strategic = new Strategic();
				strategic.setId(display.getStrategicDTO().getStrategicId());
				dto.getJob().setStrategicId(strategic);
				// dto.getJob().setJobId(
				// display.getStrategicDTO().getStrategicId());

				// dto.getJob().setJobName(
				// display.getStrategicDTO().getStrategicObjective());
				dto.getJob().setJobName(display.getStrategicDTO().getAuditableUnit());// to
																						// unit
				// dto.getJob().setProposedResources();
				// dto.getJob().setRelevantDept(
				// display.getRelevantDept().getText().toString() );
				dto.getJob().setRiskRating(display.getRiskRating().getText().toString());
				dto.getJob().setTechnical(display.getTechSkill().getText().toString());

				dto.getJob().setAuditHead(
						Integer.parseInt(display.getAuditHead().getValue(display.getAuditHead().getSelectedIndex())));
				// new Employee().setEmployeeId(2);

				ArrayList<JobEmployeeRelation> relations = new ArrayList<JobEmployeeRelation>();

				for (int i = 0; i < display.getProposedResources().getItemCount(); ++i) {
					if (display.getProposedResources().isItemSelected(i)) {

						JobEmployeeRelation r = new JobEmployeeRelation();

						JobCreation j = new JobCreation();
						// j.setJobId(display.getStrategicDTO().getStrategicId());
						// change 18
						Strategic strate = new Strategic();
						strate.setId(display.getStrategicDTO().getStrategicId());

						j.setStrategicId(strate);

						j.setJobCreationId(dto.getJob().getJobCreationId());
						r.setJobCreationId(j);

						Employee e = new Employee();
						e.setEmployeeId(Integer.parseInt(display.getProposedResources().getValue(i).split("-")[0]));

						r.setEmployee(e);
						try {
							r.setEmployee(e);
							// r.setId(
							// Integer.parseInt(display.getProposedResources().getValue(i).split("-")[0]
							// ));
						} catch (Exception ex) {
							new DisplayAlert("No Resource Selected");
						}
						relations.add(r);
					}

				}

				///////////////// For saving Audit Head
				if (display.getAuditHead().getSelectedIndex() != 0) {

					JobEmployeeRelation r = new JobEmployeeRelation();

					JobCreation j = new JobCreation();
					// j.setJobId(display.getStrategicDTO().getStrategicId());
					// change 18
					Strategic strate = new Strategic();
					strate.setId(display.getStrategicDTO().getStrategicId());
					j.setStrategicId(strate);

					j.setJobCreationId(dto.getJob().getJobCreationId());
					r.setJobCreationId(j);

					Employee head = new Employee();
					head.setEmployeeId(Integer
							.parseInt(display.getAuditHead().getValue(display.getAuditHead().getSelectedIndex())));

					r.setEmployee(head);
					try {
						r.setEmployee(head);
						// r.setId(
						// Integer.parseInt(display.getProposedResources().getValue(i).split("-")[0]
						// ));
					} catch (Exception ex) {
						new DisplayAlert("No Audit Head Selected");
					}
					relations.add(r);
				}

				// For saving audit head end///

				ArrayList<JobSkillRelation> skillrelations = new ArrayList<JobSkillRelation>();
				for (int i = 0; i < display.getSoftSkill().getItemCount(); i++) {
					if (display.getSoftSkill().isItemSelected(i)) {

						JobSkillRelation r = new JobSkillRelation();

						r.setJobId(display.getStrategicDTO().getStrategicId());

						Softskills s = new Softskills();
						try {
							s.setSoftSkillId(Integer.parseInt(display.getSoftSkill().getValue(i)));
						} catch (Exception ex) {
							new DisplayAlert("No Resources available with these skill set");
						}
						r.setSoftskills(s);

						skillrelations.add(r);
					}

				}

				dto.setRelation(relations);
				dto.setJobSkillRelation(skillrelations);
				dto.setDepartments(selectedStrategicDepartments);

				rpcService.saveCreatedJob(dto, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						new DisplayAlert("Saved");
						display.disableFields();
						History.newItem("auditScheduling");
						//
					}

					@Override
					public void onFailure(Throwable caught) {
						new DisplayAlert("There was an error in saving " + caught.getMessage());

						logger.log(Level.INFO, "FAIL: saveCreatedJob .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: saveCreatedJob .Inside AuditAreaspresenter");
							Window.alert("FAIL: saveCreatedJob");// After FAIL
																	// ... write
																	// RPC Name
																	// NOT
																	// Method
																	// Name..
						}

					}
				});

			}
		});

		display.getBackButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				History.newItem("auditScheduling");
			}
		});

		StrategicDTO dto = display.getStrategicDTO();

		if (jobDTO != null) {

		}

		else {

			// fetchRelatedEmpAndSetSelected();

			// display.getRelevantDept().setText(dto.getDepartmentName());
			display.getHeading().setText(dto.getAuditableUnit());
			display.getTechSkill().setText(dto.getDepartmentName());
			display.getRiskRating().setText(dto.getRiskRating());
			display.getDomainText().setText(dto.getDomainBasedOnTab());

			// fetchEmployees();

			// select 'None'
			display.getAuditHead().setSelectedIndex(0);

			rpcService.fetchJobTime(dto.getStrategicId(), new AsyncCallback<JobTimeEstimationDTO>() {

				@Override
				public void onSuccess(JobTimeEstimationDTO result) {

					if (result != null)
						display.getEstimatedWeeks()
								.setText(String.valueOf(result.getJobTimeEstimation().getEstimatedWeeks()));

				}

				@Override
				public void onFailure(Throwable caught) {
					//

					logger.log(Level.INFO, "FAIL: declineStrategic .Inside Audit AuditAreaspresenter");
					if (caught instanceof TimeOutException) {
						History.newItem("login");
					} else {
						System.out.println("FAIL: declineStrategic .Inside AuditAreaspresenter");
						Window.alert("FAIL: declineStrategic");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
					}
				}
			});

		}

		// fetches employees list
		// Here I'll need to pass Id, of dept...
		//

		rpcService.fetchResourceUseFor(dto.getStrategicId(), new AsyncCallback<ArrayList<ResourceUse>>() {

			@Override
			public void onSuccess(ArrayList<ResourceUse> result) {

				// go through all the resources, one by one
				// and the the name of resource and number of this resources
				// reqd
				// add individual items in `result` to the container widget

				for (int i = 0; i < result.size(); ++i) {// where u r updating
															// those textboxes
															// add resource info
															// widget to the
															// container

					SkillsResources sr = new SkillsResources();

					sr.getSkillsList().addItem(result.get(i).getSkill().getSkillName(),
							String.valueOf(result.get(i).getSkill().getSkillId()));

					sr.getNoOfResources().setText(String.valueOf(result.get(i).getNoOfResources()));

					sr.getNoOfResources().setEnabled(false);
					sr.getSkillsList().setEnabled(false);

					// adding widgets one by one

					display.getSkillResourceContainer().add(sr);

					// some style to set the padding and margin, this prevents
					// the unusual collapse of widgets into each other

					display.getSkillResourceContainer().addStyleName("spacer");

					sr.getSkillsList().addStyleName("lstSkillResources");

					sr.getNoOfResources().addStyleName("spacer");
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				new DisplayAlert(caught.getMessage());

				logger.log(Level.INFO, "FAIL: fetchResourceUseFor .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchResourceUseFor .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchResourceUseFor");// After FAIL ...
																// write RPC
																// Name NOT
																// Method Name..
				}

			}
		});
	}

	private void fetchDepartments() {
		rpcService.fetchStrategicDepartments(DTO.getStrategicId(),
				new AsyncCallback<ArrayList<StrategicDepartments>>() {

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("fetching strategic depts failed");
					}

					@Override
					public void onSuccess(ArrayList<StrategicDepartments> strategicDepartments) {
						ArrayList<Integer> depIds = new ArrayList<Integer>();
						for (int i = 0; i < strategicDepartments.size(); i++) {
							display.getRelevantDept().addItem(
									strategicDepartments.get(i).getDepartment().getDepartmentName(),
									strategicDepartments.get(i).getDepartment().getDepartmentId() + "");
							depIds.add(strategicDepartments.get(i).getDepartment().getDepartmentId());

						}
						selectedStrategicDepartments = strategicDepartments;
						fetchRelatedEmpAndSetSelected(depIds,
								strategicDepartments.get(strategicDepartments.size() - 1).getResourcesIds());
					}
				});

	}

	// private void fetchRelatedEmpAndSetSelected(ArrayList<Integer> depIds,
	// final ArrayList<Integer> resourcesSelectedForthisJob) {
	// rpcService.fetchEmployeesByDeptId( depIds, new
	// AsyncCallback<ArrayList<Employee>>() {
	//
	// @Override
	// public void onSuccess(ArrayList<Employee> result) {
	//
	// if ( display.getProposedResources().getItemCount() == 1 )
	// {
	// for(int i = 0; i < result.size(); ++i)
	// {
	//
	// display.getProposedResources().addItem(
	// result.get(i).getEmployeeName(),
	// String.valueOf(result.get(i).getEmployeeId()) +"" //relation id 0 for
	// first time
	// );
	// }
	// }
	//
	//
	// fetchReouceIds();
	//
	// }
	//
	//
	//
	// @Override
	// public void onFailure(Throwable caught) {
	//
	//
	//
	// logger.log(Level.INFO, "FAIL: fetchEmployeesByDeptId .Inside Audit
	// AuditAreaspresenter");
	// if(caught instanceof TimeOutException){
	// History.newItem("login");
	// }else{
	// System.out.println("FAIL: fetchEmployeesByDeptId .Inside
	// AuditAreaspresenter");
	// Window.alert("FAIL: fetchEmployeesByDeptId");// After FAIL ... write RPC
	// Name NOT Method Name..
	// }
	//
	//
	// }
	// });
	// }

	private void fetchRelatedEmpAndSetSelected(ArrayList<Integer> depIds,
			final ArrayList<Integer> resourcesSelectedForthisJob) {
		rpcService.fetchEmployeesBySkillId(DTO.getStrategicId(), new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onSuccess(ArrayList<Employee> result) {

				if (display.getProposedResources().getItemCount() == 1) {
					for (int i = 0; i < result.size(); ++i) {

						display.getProposedResources().addItem(result.get(i).getEmployeeName(),
								String.valueOf(result.get(i).getEmployeeId()) + "" // relation
																					// id
																					// 0
																					// for
																					// first
																					// time
						);
					}
				}

				fetchReouceIds();

			}

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchEmployeesByDeptId .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchEmployeesByDeptId .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployeesByDeptId");// After FAIL
																	// ... write
																	// RPC Name
																	// NOT
																	// Method
																	// Name..
				}

			}
		});
	}

	private void fetchReouceIds() {

		rpcService.fetchResourceIds(DTO.getStrategicId(), new AsyncCallback<ArrayList<Integer>>() {

			@Override
			public void onFailure(Throwable arg0) {

			}

			@Override
			public void onSuccess(ArrayList<Integer> resourcesSelectedForthisJob) {
				if (display.getProposedResources().getItemCount() > 1) {
					for (int j = 0; j < resourcesSelectedForthisJob.size(); j++) {
						for (int i = 0; i < display.getProposedResources().getItemCount(); i++) {
							if (Integer.parseInt(
									display.getProposedResources().getValue(i)) == resourcesSelectedForthisJob.get(j)) {
								display.getProposedResources().setItemSelected(i, true);
							}
						}
					}
				} else if (display.getProposedResources().getItemCount() > 0) {
					display.getProposedResources().setSelectedIndex(1);
				}
				display.getProposedResources().setItemSelected(0, false);
				fetchJobSoftSkills();
			}
		});

	}

	private void fetchJobSoftSkills() {

		rpcService.fetchJobSoftSkills(DTO.getStrategicId(), new AsyncCallback<ArrayList<Integer>>() {

			@Override
			public void onFailure(Throwable arg0) {

			}

			@Override
			public void onSuccess(ArrayList<Integer> softSkillsSelectedForthisJob) {
				if (display.getSoftSkill().getItemCount() > 1) {
					for (int j = 0; j < softSkillsSelectedForthisJob.size(); j++) {
						for (int i = 0; i < display.getSoftSkill().getItemCount(); i++) {
							if (Integer.parseInt(display.getSoftSkill().getValue(i)) == softSkillsSelectedForthisJob
									.get(j)) {
								display.getSoftSkill().setItemSelected(i, true);
							}
						}
					}
				}
			}
		});

	}

	private void fetchEmployees(final int idToSelect) {
		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchEmployees .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchEmployees .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchEmployees");// After FAIL ... write
															// RPC Name NOT
															// Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<Employee> result) {

				if (!(display.getAuditHead().getItemCount() > 1)) // if NOT list
																	// already
																	// populated
				{ // populate list
					for (int i = 0; i < result.size(); i++) {
						if (result.get(i).getRollId() == 1) {
							display.getAuditHead().addItem(result.get(i).getEmployeeName(),
									result.get(i).getEmployeeId() + "");
							display.getAuditHead().setSelectedIndex(1);
							display.getAuditHead().setEnabled(false);
						}
					}
				}

				if (idToSelect != 0 && jobDTO != null) {
					for (int i = 0; i < result.size(); i++) {

						if (idToSelect == result.get(i).getEmployeeId()) {
							// display.getAuditHead().setSelectedIndex(i+1);
						}
					}
				}

			}

		});
	}

}
