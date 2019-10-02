package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.widgets.SkillsResources;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobTimeEstimation;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.SkillUpdateData;
import com.internalaudit.shared.Skills;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.TimeOutException;

public class JobTimeEstimationPresenter implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("JobTimeEstimationPresenter");
	private ArrayList<Integer> hourstoAdd = new ArrayList<Integer>();

	public interface Display {
		Widget asWidget();

		Object getHtmlErrorMessage = null;

		ListBox getEstWeeksListBox();

		TextBox getFieldWorkManHours();

		TextBox getMgmtHours();

		TextBox getTotalWorkingManHours();

		VerticalPanel getSkillsResourceContainer();

		Button getSaveJobTimeEst();

		void disableFields();

		TextBox getTotalWorkingManHoursTextBox();

		TextArea getHighLevelScopeOfWork();

		ListBox getPlaceofWorkListBox();

		ListBox getTravelingDaysListBox();

		TextBox getHoursInclusiveOfTravel();

		StrategicDTO getStrategicDTO();

		Label getHeading();

		Button getBackButton();

		Label getAreaOfExpertise();

		Label getJobTimeEstId();

		boolean isListCreated();

		void setListCreated(boolean value);

		void setJobEstimationId(int jobTimeEstimationId);

		int getJobEstimationId();
	}

	public JobTimeEstimationPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		// if a record is foung in db- then I create widgets acc to db record
		// set the values of form fields accordingly

		rpcService.fetchJobTime(display.getStrategicDTO().getStrategicId(), new AsyncCallback<JobTimeEstimationDTO>() {

			@Override
			public void onSuccess(JobTimeEstimationDTO result) {

				// display.getEstimatedWeeks().setText(String.valueOf(result.getJobTimeEstimation().getEstimatedWeeks()));

				if (result != null) {
					// Window.alert("not null");
					if (result.getJobTimeEstimation().isApproved()) {

						disablingRequiredResources();
						display.disableFields();
					}
					display.setJobEstimationId(result.getJobTimeEstimation().getJobTimeEstimationId());

					for (int i = 0; i < display.getEstWeeksListBox().getItemCount(); i++) {
						if (display.getEstWeeksListBox().getItemText(i)
								.equals(String.valueOf(result.getJobTimeEstimation().getEstimatedWeeks()))) {
							display.getEstWeeksListBox().setSelectedIndex(i);
							break;
						}

					}

					display.getJobTimeEstId()
							.setText(String.valueOf(result.getJobTimeEstimation().getJobTimeEstimationId()));

					display.getEstWeeksListBox()
							.addItem(String.valueOf(result.getJobTimeEstimation().getEstimatedWeeks()));

					// display.getFieldWorkManHours().setText(
					// String.valueOf(result.getJobTimeEstimation().getManagementHours()
					// ));

					display.getTotalWorkingManHours()
							.setText(String.valueOf(result.getJobTimeEstimation().getHoursInclTravel()));

					display.getFieldWorkManHours()
							.setText(String.valueOf(result.getJobTimeEstimation().getFieldWorkManHours()));

					display.getMgmtHours().setText(String.valueOf(result.getJobTimeEstimation().getManagementHours()));

					display.getHoursInclusiveOfTravel()
							.setText(String.valueOf(result.getJobTimeEstimation().getHoursInclTravel()));

					for (int i = 0; i < display.getPlaceofWorkListBox().getItemCount(); i++) {
						if (display.getPlaceofWorkListBox().getItemText(i)
								.equals(String.valueOf(result.getJobTimeEstimation().getTravelDays()))) {
							display.getPlaceofWorkListBox().setSelectedIndex(i);
							break;
						}
					}

					// display.getPlaceofWorkListBox().addItem(
					// result.getJobTimeEstimation().getPlaceOfWork());

					display.getHighLevelScopeOfWork().setText(result.getJobTimeEstimation().getScopeOfWork());

					for (int i = 0; i < display.getTravelingDaysListBox().getItemCount(); i++) {
						if (display.getTravelingDaysListBox().getItemText(i)
								.equals(String.valueOf(result.getJobTimeEstimation().getTravelDays())))
							display.getTravelingDaysListBox().setSelectedIndex(i);
					}

					display.getTotalWorkingManHoursTextBox()
							.setText(String.valueOf(result.getJobTimeEstimation().getTotalWorkingManHours()));
					display.getSkillsResourceContainer().clear();

					fetchSkillResources(result.getResourceUse(), true);

				}

			}

			private void disablingRequiredResources() {

				rpcService.fetchSkills(new AsyncCallback<ArrayList<Skills>>() {

					@Override
					public void onSuccess(ArrayList<Skills> skillsList) {
						for (int i = 0; i < skillsList.size(); i++) {
							final SkillsResources skillsResources = new SkillsResources();

							skillsResources.getSkillsList().addItem(skillsList.get(i).getSkillName(),
									skillsList.get(i).getSkillId() + "");
							((SkillsResources) display.getSkillsResourceContainer().getWidget(i)).getSkillsList()
									.setEnabled(false);
							// ((SkillsResources)
							// display.getSkillsResourceContainer().getWidget(i)).getNoOfResources()
							// .setEnabled(false);
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failed fetchskills fir disabling fields");

					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {

				logger.log(Level.INFO, "FAIL: fetchJobTime .Inside Audit AuditAreaspresenter");
				if (caught instanceof TimeOutException) {
					History.newItem("login");
				} else {
					System.out.println("FAIL: fetchJobTime .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchJobTime");// After FAIL ... write
														// RPC Name NOT Method
														// Name..
				}

				System.out.println("fail");

			}
		});

		display.getBackButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				History.newItem("auditScheduling");
			}
		});
		// display.getHeading().setText(
		// display.getStrategicDTO().getStrategicObjective() );
		display.getHeading().setText(display.getStrategicDTO().getAuditableUnit());// to
																					// unit

		display.getFieldWorkManHours().setText("0");

		display.getSaveJobTimeEst().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// store the current state of form in DTO

				JobTimeEstimation jobTimeEstEntity = new JobTimeEstimation();
				ArrayList<ResourceUse> resourceAndNo = new ArrayList<ResourceUse>();
				ArrayList<SkillUpdateData> updateForSkills = new ArrayList<SkillUpdateData>();

				try {

					jobTimeEstEntity.setJobTimeEstimationId(Integer.parseInt(display.getJobTimeEstId().getText()));

					jobTimeEstEntity.setJobId(display.getStrategicDTO().getStrategicId());

					jobTimeEstEntity.setEstimatedWeeks(Integer.parseInt(
							display.getEstWeeksListBox().getItemText(display.getEstWeeksListBox().getSelectedIndex())));

					jobTimeEstEntity.setFieldWorkManHours(
							Integer.parseInt(display.getFieldWorkManHours().getText().toString()));

					jobTimeEstEntity.setTotalWorkingManHours(
							Integer.parseInt(display.getTotalWorkingManHours().getText().toString()));

					jobTimeEstEntity.setManagementHours(Integer.parseInt(display.getMgmtHours().getText().toString()));

					jobTimeEstEntity.setHoursInclTravel(
							Integer.parseInt(display.getHoursInclusiveOfTravel().getText().toString()));

					jobTimeEstEntity.setPlaceOfWork(display.getPlaceofWorkListBox()
							.getItemText(display.getPlaceofWorkListBox().getSelectedIndex()));

					jobTimeEstEntity.setScopeOfWork(display.getHighLevelScopeOfWork().getText().toString());

					jobTimeEstEntity.setTravelDays(Integer.parseInt(display.getTravelingDaysListBox()
							.getItemText(display.getTravelingDaysListBox().getSelectedIndex())));

					jobTimeEstEntity.setHoursInclTravel(
							jobTimeEstEntity.getTotalWorkingManHours() + jobTimeEstEntity.getTravelDays() * 8);

					// since the size of listbox is dynamic, we need to get it
					// at the runtime
					// 0th widget of the VerticalPanel is always a
					// 'SkillResource'
					// we cast it to the Type and get skilllist size
					int listSize = /* ((SkillsResources) */display.getSkillsResourceContainer().getWidgetCount();// (0)).getSkillsList().getItemCount();

					boolean maxHoursReached = false;

					// loop through all the list boxes and get the selected
					// index and value / text
					for (int i = 0; i < listSize; i++) {
						// ith widget of Vpanel is always a 'SkillResource'
						SkillsResources res = ((SkillsResources) display.getSkillsResourceContainer().getWidget(i));

						ResourceUse r = new ResourceUse();

						int hoursReqd = Integer.parseInt(res.getNoOfResources().getText().toString()) * 40
								* jobTimeEstEntity.getEstimatedWeeks();

						if (hoursReqd > res.getAvailableHours()) {
							maxHoursReached = false;

							System.out.println("You have selected more hours than available hours for "
									+ res.getSkillsList().getItemText(res.getSkillsList().getSelectedIndex()));
							Window.alert("You have selected more hours than available hours for "
									+ res.getSkillsList().getItemText(res.getSkillsList().getSelectedIndex()));
							// return;

						}
						// get id of skill

						SkillUpdateData updatedInfoForSkill = new SkillUpdateData();

						updatedInfoForSkill.setSkillId(Integer.parseInt(res.getSkillsList().getValue(0)));
						updatedInfoForSkill.setHoursToSubt(hoursReqd);
						if (hourstoAdd.size() > 0 && hourstoAdd.size() > i) {
							updatedInfoForSkill.setHoursToAdd(hourstoAdd.get(i));
						}

						updateForSkills.add(updatedInfoForSkill);

						// get spent hour

						// update db

						r.setNoOfResources(Integer.parseInt(res.getNoOfResources().getText().toString()));

						int index = res.getSkillsList().getSelectedIndex();

						// how to set this ? ..
						Skills skills = new Skills();
						skills.setSkillId(Integer.parseInt(res.getSkillsList().getValue(index)));
						r.setSkillId(skills);

						resourceAndNo.add(r);

					}

				} catch (NumberFormatException e) {

					Window.alert("Please enter a number, characters are not allowed");
					return;
				}

				JobTimeEstimationDTO dto = new JobTimeEstimationDTO();

				dto.setJobTimeEstimation(jobTimeEstEntity);
				dto.setResourceUse(resourceAndNo);

				rpcService.saveJobTimeEstimation(dto, updateForSkills, new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean savedSuccess) {

						if (savedSuccess) {
							new DisplayAlert("Job Estimation Saved");
							History.newItem("auditScheduling");
						} else
							Window.alert("There are not enough number of Resources available");

					}

					@Override
					public void onFailure(Throwable caught) {

						logger.log(Level.INFO, "FAIL: saveJobTimeEstimation .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: saveJobTimeEstimation .Inside AuditAreaspresenter");
							Window.alert("FAIL: saveJobTimeEstimation");// After
																		// FAIL
																		// ...
																		// write
																		// RPC
																		// Name
																		// NOT
																		// Method
																		// Name..
						}

					}
				});

				// History.newItem("jobCreation");

			}
		});

		display.getEstWeeksListBox().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculateManHours();

			}
		});

		display.getPlaceofWorkListBox().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				int index = display.getPlaceofWorkListBox().getSelectedIndex();

				String place = display.getPlaceofWorkListBox().getItemText(index);

				if (place.equals("Outstation") || place.equals("Inhouse and Outstation")) {
					display.getTravelingDaysListBox().setEnabled(true);
					display.getTravelingDaysListBox().setSelectedIndex(0);
				}

				if (place.equals("Inhouse")) {
					// as we have inhouse work, we dont need to go wandering
					display.getTravelingDaysListBox().setEnabled(false);

					// if the user previously selected outstation, this will
					// recalculate the traveling hours and
					// hours incl travel

					int hoursToSubt = Integer.parseInt(display.getTravelingDaysListBox()
							.getItemText(display.getTravelingDaysListBox().getSelectedIndex()));

					int currentHoursForTravel = Integer.parseInt(display.getHoursInclusiveOfTravel().getText());

					display.getHoursInclusiveOfTravel()
							.setText(String.valueOf(currentHoursForTravel - hoursToSubt * 8));

					display.getTravelingDaysListBox().setSelectedIndex(0);

				}

			}
		});

		display.getMgmtHours().addKeyUpHandler(new KeyUpHandler() { // for later

			@Override
			public void onKeyUp(KeyUpEvent event) {
				try {
					calculateHoursInclTravel();

				} catch (Exception e) {
					display.getMgmtHours().setText("");
					display.getTotalWorkingManHours().setText("");
					display.getTotalWorkingManHours().setText("Please enter a correct value");
					event.preventDefault();
				}

			}
		});

		display.getTravelingDaysListBox().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				calculateHoursInclTravel();
			}
		});

		// this.

		// if ( ! display.isListCreated() )
		// {

		fetchSkillResources(null, false);

	}

	private void fetchSkillResources(final ArrayList<ResourceUse> resourceList, final boolean updateState) {
		rpcService.fetchSkills(new AsyncCallback<ArrayList<Skills>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to load skill list");
			}

			@Override
			public void onSuccess(ArrayList<Skills> skillsList) {

				display.getSkillsResourceContainer().clear();

				for (int i = 0; i < skillsList.size(); i++) {
					final SkillsResources skillsResources = new SkillsResources();

					skillsResources.getSkillsList().addItem(skillsList.get(i).getSkillName(),
							skillsList.get(i).getSkillId() + "");

					// skillsResources.setAvailableHours(
					// skillsList.get(i).getAvailableHours());
					if (skillsList.get(i).getCompanySkillRelation() == null) {
						skillsResources.setAvailableHours(0);
					} else {
						skillsResources
								.setAvailableHours(skillsList.get(i).getCompanySkillRelation().getAvailableHours());
					}
					skillsResources.getNoOfResources().setText("0");

					skillsResources.getNoOfResources().addBlurHandler(new BlurHandler() {

						@Override
						public void onBlur(BlurEvent event) {
							int skillId = Integer.parseInt(skillsResources.getSkillsList()
									.getValue(skillsResources.getSkillsList().getSelectedIndex()));
							try {

								int noOfResources = Integer.parseInt(skillsResources.getNoOfResources().getText());
								calculateManHours();
								checkNoOfResourcesForSelectedSkill(noOfResources, skillId);
							} catch (Exception ex) {
								Window.alert("Please select a valid numeric value");
							}

							// fetch
						}

					});

					skillsResources.getSkillsList().setSelectedIndex(i);

					display.getSkillsResourceContainer().add(skillsResources);
					display.getSkillsResourceContainer().addStyleName("spacer");
					skillsResources.getSkillsList().addStyleName("lstSkillResources");
					skillsResources.getNoOfResources().addStyleName("spacer");
				}

				if (updateState && resourceList != null) {
					for (int i = 0; i < resourceList.size(); i++) {

						((SkillsResources) display.getSkillsResourceContainer().getWidget(i)).getSkillsList().addItem(
								resourceList.get(i).getSkill().getSkillName(),
								resourceList.get(i).getSkill().getSkillId() + "");

						((SkillsResources) display.getSkillsResourceContainer().getWidget(i)).getSkillsList()
								.setEnabled(false);
						((SkillsResources) display.getSkillsResourceContainer().getWidget(i)).getNoOfResources()
								.setText(String.valueOf(resourceList.get(i).getNoOfResources()));

						// ((SkillsResources)
						// display.getSkillsResourceContainer().getWidget(i)).getSkillsList()
						// .setSelectedIndex(i);

						// july 2019 commented two lines where it was enabling
						// false

						// ((SkillsResources)
						// display.getSkillsResourceContainer().getWidget(i)).getSkillsList()
						// .setEnabled(false);
						// ((SkillsResources)
						// display.getSkillsResourceContainer().getWidget(i)).getNoOfResources()
						// .setEnabled(false);

					}
				}

				///////////////////////////// Getting old hours//////junaid
				int estimatedWeeks = Integer.parseInt(
						display.getEstWeeksListBox().getItemText(display.getEstWeeksListBox().getSelectedIndex()));
				int listSize = /* ((SkillsResources) */display.getSkillsResourceContainer().getWidgetCount();// (0)).getSkillsList().getItemCount();

				// loop through all the list boxes and get the selected index
				// and value / text
				for (int i = 0; i < listSize; i++) {
					// ith widget of Vpanel is always a 'SkillResource'
					SkillsResources res = ((SkillsResources) display.getSkillsResourceContainer().getWidget(i));
					ResourceUse r = new ResourceUse();
					int toAdd = Integer.parseInt(res.getNoOfResources().getText().toString()) * 40 * estimatedWeeks;
					if (toAdd > 0) {
						hourstoAdd.add(toAdd);
					}
				}
			}

		});
	}
	// }

	private void fetchCheckBoxState() {
		rpcService.fetchCheckBoxStateFor(display.getStrategicDTO().getStrategicId(),
				new AsyncCallback<ArrayList<JobAndAreaOfExpertise>>() {

					@Override
					public void onSuccess(ArrayList<JobAndAreaOfExpertise> result) {

						String title = "";

						for (int i = 0; i < result.size(); ++i) {
							if (result.get(i).getIsChecked() == 1)
								title += result.get(i).getAreaOfExpertise().getDepartmentName() + " ";
						}

						display.getAreaOfExpertise().setText(title);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

						logger.log(Level.INFO, "FAIL: fetchCheckBoxStateFor .Inside Audit AuditAreaspresenter");
						if (caught instanceof TimeOutException) {
							History.newItem("login");
						} else {
							System.out.println("FAIL: fetchCheckBoxStateFor .Inside AuditAreaspresenter");
							Window.alert("FAIL: fetchCheckBoxStateFor");// After
																		// FAIL
																		// ...
																		// write
																		// RPC
																		// Name
																		// NOT
																		// Method
																		// Name..
						}

					}
				});
	}

	private void checkNoOfResourcesForSelectedSkill(int noOfResources, int skillId) {
		rpcService.checkNoOfResourcesForSelectedSkill(noOfResources, skillId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(String result) {
				if (!result.equals("Available")) {
					Window.alert(result);
				}
			}
		});
	}

	private void calculateManHours() {
		try {
			int indexSelected = display.getEstWeeksListBox().getSelectedIndex();

			// if user hasn't yet touched the listbox
			// we set first item as selected by default
			if (indexSelected == -1)
				display.getEstWeeksListBox().setSelectedIndex(0);

			int weeks = Integer.parseInt(display.getEstWeeksListBox().getItemText(indexSelected));

			int listSize = /* ((SkillsResources) */display.getSkillsResourceContainer().getWidgetCount(); // 0)).getSkillsList().getItemCount();

			int totalResources = 0;

			for (int i = 0; i < listSize; ++i)
				totalResources += Integer.parseInt(((SkillsResources) display.getSkillsResourceContainer().getWidget(i))
						.getNoOfResources().getText());

			// Field Work Man Hours = NoOfResources * EstimatedWeeks*40

			display.getFieldWorkManHours().setText(String.valueOf(weeks * 40 * totalResources));

			calculateAndUpdateValues();

		} catch (Exception e) {
			display.getMgmtHours().setText("");
			display.getTotalWorkingManHours().setText("");
			display.getTotalWorkingManHours().setText("Please enter a correct value");

		}
	}

	private void calculateAndUpdateValues() {
		int mgmtHours = Integer.parseInt(display.getMgmtHours().getText().toString());

		int workManHours = Integer.parseInt(display.getFieldWorkManHours().getText().toString());

		// Total Working Man Hours = Field Work Man Hours + mgmt hrs
		display.getTotalWorkingManHours().setText(String.valueOf((mgmtHours + workManHours)));
		int travelDays = Integer.parseInt(
				display.getTravelingDaysListBox().getItemText(display.getTravelingDaysListBox().getSelectedIndex()));

		// Hours inclusive of travel = Total working man hours + travelling
		// days*8

		display.getHoursInclusiveOfTravel().setText(String.valueOf(mgmtHours + workManHours + travelDays * 8));
	}

	private void calculateHoursInclTravel() {

		try {

			// Hours inclusive of travel = Total working man hours + travelling
			// days*8

			calculateAndUpdateValues();
		} catch (Exception e) {
			Window.alert("Please enter only numbers in numeric fields");
		}
	}

}
