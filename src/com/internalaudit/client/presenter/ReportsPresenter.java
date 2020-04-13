package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.client.view.Reporting.ReportAuditEngagement;
import com.internalaudit.client.view.Reporting.ReportAuditExceptions;
import com.internalaudit.client.view.Reporting.ReportAuditPlanning;
import com.internalaudit.client.view.Reporting.ReportAuditScheduling;
import com.internalaudit.client.view.Reporting.ReportJobTimeAllocation;
import com.internalaudit.shared.AuditSchedulingReportDTO;
import com.internalaudit.shared.DataCount;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.ExcelDataDTO;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.ExceptionsReportDTO;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicDepartments;

public class ReportsPresenter implements Presenter

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private ArrayList<Strategic> strategicReportData;
	private ArrayList<JobCreation> jobTimeAllocationDataList;
	private ArrayList<Exceptions> exceptionsDataList;
	private String btnPdf = InternalAuditConstants.PDF;
	private String btnExcel = InternalAuditConstants.EXCEL;

	private Employee loggedInEmployee;
	private Logger logger = Logger.getLogger("ReportsPresenter");
	private int report1RiskChartWidth = 390;
	private int report1RiskChartHeight = 300;
	private int report1DomainChartWidth = 390;
	private int report1DomainChartHeight = 300;
	private int report1DivsionChartWidth = 400;
	private int report1DivisionChartHeight = 300;

	HorizontalPanel chartHpnl = new HorizontalPanel();
	// VerticalPanel chartHpnl = new VerticalPanel();

	public interface Display {
		Widget asWidget();

		ListBox getSelectionBox();

		VerticalPanel getReportsContainer();

		HorizontalPanel getListBoxesContainer();

		ListBox getDivListbox();

		ListBox getDomainListbox();

		ListBox getRiskListbox();

		Button getBtnSearch();

		StackPanel getStackReports();

		ReportAuditPlanning getReport1();

		ReportAuditScheduling getReport2();

		ReportAuditEngagement getReport3();

		ReportJobTimeAllocation getReport4();

		ReportAuditExceptions getReport5();

	}

	public ReportsPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Employee employee,
			Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInEmployee = employee;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());

		rpcService.fetchDepartments(new AsyncCallback<ArrayList<Department>>() {

			@Override
			public void onSuccess(ArrayList<Department> arg0) {
				for (Department d : arg0) {
					// display.getReport1().getDivListbox().addItem(d.getDepartmentName(),String.valueOf(d.getDepartmentId()));
					// display.getReport2().getDivListbox().addItem(d.getDepartmentName(),String.valueOf(d.getDepartmentId()));
					// display.getReport4().getDivListbox().addItem(d.getDepartmentName(),String.valueOf(d.getDepartmentId()));
				}

			}

			@Override
			public void onFailure(Throwable arg0) {

				Window.alert("Error fetching division list box");
			}
		});

		rpcService.fetchEmployees(new AsyncCallback<ArrayList<Employee>>() {

			@Override
			public void onSuccess(ArrayList<Employee> arg0) {

				for (Employee e : arg0) {
					if (e.getRollId() == 5) {
						display.getReport5().getLstResponsibleAuditee().addItem(e.getEmployeeName(),
								String.valueOf(e.getEmployeeId()));
					} else {
						display.getReport5().getLstResource().addItem(e.getEmployeeName(),
								String.valueOf(e.getEmployeeId()));
						display.getReport2().getEmpListbox().addItem(e.getEmployeeName(),
								String.valueOf(e.getEmployeeId()));
						display.getReport4().getLstResource().addItem(e.getEmployeeName(),
								String.valueOf(e.getEmployeeId()));

					}
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Error fetching emp list box");

			}
		});

		rpcService.fetchJobs(new AsyncCallback<ArrayList<JobCreation>>() {

			@Override
			public void onSuccess(ArrayList<JobCreation> arg0) {

				for (JobCreation e : arg0) {

					display.getReport5().getLstJobs().addItem(e.getJobName(), String.valueOf(e.getJobCreationId()));
				}

			}

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Error fetching emp list box");

			}
		});

		bind();

	}

	private void bind() {

		initialize();

		setReport1Handlers();

		setReport2Handlers();

		setReport3Handlers();

		setReport4Handlers();
		setReport5Handlers();

		/*
		 * display.getSelectionBox().addChangeHandler(new ChangeHandler() {
		 * 
		 * @Override public void onChange(ChangeEvent arg0) { // init the
		 * reports view
		 * 
		 * display.getListBoxesContainer().setVisible(true);
		 * 
		 * //ReportsView view = new ReportsView(null);
		 * 
		 * String selected =
		 * display.getSelectionBox().getItemText(display.getSelectionBox().
		 * getSelectedIndex());
		 * 
		 * if ( selected.equals("Report 1")) {
		 * 
		 * display.getStackReports().add(new Report1(null), "Report 1");//,
		 * 300);
		 * 
		 * } else if ( selected.equals("Report 2")) {
		 * 
		 * display.getStackReports().add(new Report2(null), "Report 2");//,
		 * 300);
		 * 
		 * } else if ( selected.equals("Report 3")) {
		 * 
		 * display.getStackReports().add(new Report3(null), "Report 3");//,
		 * 300);
		 * 
		 * }
		 * 
		 * //display.getReportsContainer().add(display.getStackReports());
		 * 
		 * } });
		 */
		// auditplanningreport
		display.getReport1().getBtnExportToExcel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<ExcelDataDTO> excelDataList = auditPlanningReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();

				exportAuditPlanningReport(excelDataList, loadingpopup, btnExcel);

			}

		});

		display.getReport1().getBtnExportToPDF().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<ExcelDataDTO> excelDataList = auditPlanningReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();

				exportAuditPlanningReport(excelDataList, loadingpopup, btnPdf);

			}

		});

		// auditschedulingreport
		display.getReport2().getBtnExportToExcel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<AuditSchedulingReportDTO> excelDataList = auditSchedulingReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();

				exportAuditSchedulingReport(excelDataList, loadingpopup, btnExcel);

			}

		});

		// 2019 sep
		display.getReport2().getBtnExportToPDF().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<AuditSchedulingReportDTO> excelDataList = auditSchedulingReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();

				exportAuditSchedulingReport(excelDataList, loadingpopup, btnPdf);

			}

		});
		// jobtimeestimationreport
		display.getReport4().getBtnExportToExcel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<JobTimeAllocationReportDTO> excelDataList = jobTimeAllocationReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();

				exportJobTimeAllocationReport(excelDataList, loadingpopup, btnExcel);

			}

		});

		display.getReport4().getBtnExportToPDF().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<JobTimeAllocationReportDTO> excelDataList = jobTimeAllocationReportData();

				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();

				exportJobTimeAllocationReport(excelDataList, loadingpopup, btnPdf);
			}

		});
		// auditexceptionreport
		display.getReport5().getBtnExportToExcel().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<ExceptionsReportDTO> excelDataList = exceptionReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();
				exportReportAuditException(excelDataList, loadingpopup, btnExcel);

			}

		});

		display.getReport5().getBtnExportToPDF().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				ArrayList<ExceptionsReportDTO> excelDataList = exceptionReportData();
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();
				exportReportAuditException(excelDataList, loadingpopup, btnPdf);

			}

		});

	}

	// 2019 sep
	// rpc calls and methods new are created here
	private ArrayList<ExcelDataDTO> auditPlanningReportData() {
		ArrayList<ExcelDataDTO> excelDataList = new ArrayList<ExcelDataDTO>();
		for (int i = 0; i < strategicReportData.size(); i++) {
			ExcelDataDTO excelData = new ExcelDataDTO();
			excelData.setAuditableUnit(strategicReportData.get(i).getAuditableUnit());
			excelData.setDivision(strategicReportData.get(i).getDivisionName());
			excelData.setDomain(strategicReportData.get(i).getDomain());
			// excelData.setObjective(strategicReportData.get(i).getStrategicObjective());
			excelData.setObjective(strategicReportData.get(i).getAuditableUnit());
			excelData.setRiskAssesment(strategicReportData.get(i).getRating());
			excelDataList.add(excelData);

		}
		return excelDataList;
	}

	private ArrayList<AuditSchedulingReportDTO> auditSchedulingReportData() {
		ArrayList<AuditSchedulingReportDTO> excelDataList = new ArrayList<AuditSchedulingReportDTO>();
		for (int i = 0; i < strategicReportData.size(); i++) {
			AuditSchedulingReportDTO excelData = new AuditSchedulingReportDTO();
			excelData.setDivision(strategicReportData.get(i).getDivisionName());
			excelData.setDomain(strategicReportData.get(i).getDomain());
			excelData.setJob(strategicReportData.get(i).getJobName());
			String employees = "";
			for (int j = 0; j < strategicReportData.get(i).getEmployees().size(); j++) {
				if (employees == "") {
					employees = employees + strategicReportData.get(i).getEmployees().get(j);
				} else {
					employees = employees + ", " + strategicReportData.get(i).getEmployees().get(j);
				}
			}
			excelData.setResources(employees);
			excelData.setRiskAssesment(strategicReportData.get(i).getRiskFactor().getRiskName());
			excelData.setTimeAllocated(strategicReportData.get(i).getEstimatedWeeks());
			excelDataList.add(excelData);
		}
		return excelDataList;
	}

	private ArrayList<JobTimeAllocationReportDTO> jobTimeAllocationReportData() {
		ArrayList<JobTimeAllocationReportDTO> excelDataList = new ArrayList<JobTimeAllocationReportDTO>();
		for (int i = 0; i < jobTimeAllocationDataList.size(); i++) {
			JobTimeAllocationReportDTO excelData = new JobTimeAllocationReportDTO();
			excelData.setJob(jobTimeAllocationDataList.get(i).getJobName());
			excelData.setWeeks(jobTimeAllocationDataList.get(i).getEstimatedWeeks() + "");

			excelDataList.add(excelData);

		}
		return excelDataList;
	}

	private ArrayList<ExceptionsReportDTO> exceptionReportData() {
		ArrayList<ExceptionsReportDTO> excelDataList = new ArrayList<ExceptionsReportDTO>();
		for (int i = 0; i < exceptionsDataList.size(); i++) {
			ExceptionsReportDTO excelData = new ExceptionsReportDTO();
			excelData.setExceptionName(exceptionsDataList.get(i).getDetail());
			excelData.setAuditee(exceptionsDataList.get(i).getResponsiblePerson().getEmployeeName());
			excelData.setExceptionStatus(exceptionsDataList.get(i).getDisplayStatus());
			excelData.setJobName(exceptionsDataList.get(i).getJobName());

			excelDataList.add(excelData);
		}
		return excelDataList;
	}

	private void exportAuditPlanningReport(ArrayList<ExcelDataDTO> excelDataList, final LoadingPopup loadingpopup,
			String btn) {
		rpcService.exportAuditPlanningReport(excelDataList, btn, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				loadingpopup.remove();
				Window.alert(" auditplanningreport failed");
			}

			@Override
			public void onSuccess(String report) {
				loadingpopup.remove();
				if (report.contains(InternalAuditConstants.PDF)) {
					Window.open("InternalAuditReport/reportauditplanningPDF.pdf", "_blank", "");

				} else {

					Window.open("InternalAuditReport/reportauditplanning.xls", "_blank", "");
				}
			}
		});
	}

	private void exportAuditSchedulingReport(ArrayList<AuditSchedulingReportDTO> excelDataList,
			final LoadingPopup loadingpopup, String btn) {
		rpcService.exportAuditSchedulingReport(excelDataList, btn, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				loadingpopup.remove();
				Window.alert(" auditScheduling failed");
			}

			@Override
			public void onSuccess(String report) {
				loadingpopup.remove();

				if (report.contains(InternalAuditConstants.PDF)) {
					Window.open("InternalAuditReport/reportauditschedulingPDF.pdf", "_blank", "");

				} else {

					Window.open("InternalAuditReport/reportauditscheduling.xls", "_blank", "");
				}
			}
		});
	}

	private void exportJobTimeAllocationReport(ArrayList<JobTimeAllocationReportDTO> excelDataList,
			final LoadingPopup loadingpopup, String btn) {
		rpcService.exportJobTimeAllocationReport(excelDataList, btn, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				loadingpopup.remove();
				Window.alert("jobtimeallocation export failed");
			}

			@Override
			public void onSuccess(String report) {
				loadingpopup.remove();
				if (report.contains(InternalAuditConstants.PDF)) {
					Window.open("InternalAuditReport/reportjobtimePDF.pdf", "_blank", "");

				} else {

					Window.open("InternalAuditReport/reportjobtime.xls", "_blank", "");
				}
			}
		});
	}

	private void exportReportAuditException(ArrayList<ExceptionsReportDTO> excelDataList,
			final LoadingPopup loadingpopup, String btn) {
		rpcService.exportAuditExceptionsReport(excelDataList, btn, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				loadingpopup.remove();
				Window.alert("excel export failed");
			}

			@Override
			public void onSuccess(String report) {
				loadingpopup.remove();
				if (report.contains(InternalAuditConstants.PDF)) {
					Window.open("InternalAuditReport/reportauditexceptionPDF.pdf", "_blank", "");

				} else {

					Window.open("InternalAuditReport/reportauditexception.xls", "_blank", "");
				}
			}
		});
	}

	private void setReport3Handlers() {

		// display.getReport3().get
	}

	private void setReport2Handlers() {

		display.getReport2().getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				// Window.alert("r2");

				// setup search parameters
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();
				ArrayList<String> domain = getSelectedItems(display.getReport2().getDomainListbox());

				ArrayList<String> div = getSelectedItems(display.getReport2().getDivListbox());

				ArrayList<String> selectedEmp = getSelectedItems(display.getReport2().getEmpListbox());

				ArrayList<String> jobStatus = getSelectedItems(display.getReport2().getLstJobStatus());

				// final ReportAuditScheduling view = new
				// ReportAuditScheduling(null);

				rpcService.fetchReportAuditScheduling(div, domain, jobStatus, selectedEmp,
						new AsyncCallback<ArrayList<Strategic>>() {

							@Override
							public void onSuccess(ArrayList<Strategic> strategicList) {
								loadingpopup.remove();
								strategicReportData = strategicList;
								display.getReport2().getVpnlPerviewData().clear();
								chartHpnl.clear();
								if (strategicList.size() > 0) {

									// create a flextable based on results .

									FlexTable resultsTable = new FlexTable();

									resultsTable.setCellPadding(8);
									resultsTable.setCellSpacing(5);
									// resultsTable.setWidth("1000");

									// set up table headings

									LabelHeading lblAuditableUnit = new LabelHeading();
									LabelHeading lblDomain = new LabelHeading();
									LabelHeading lblDivision = new LabelHeading();
									LabelHeading lblRiskAssessment = new LabelHeading();
									LabelHeading lblResources = new LabelHeading();
									LabelHeading lblTimeAllocated = new LabelHeading();

									lblAuditableUnit.setText("Job");
									lblDomain.setText("Domain");
									lblDivision.setText("Division");
									lblRiskAssessment.setText("Risk Assessment");
									lblResources.setText("Resources");
									lblTimeAllocated.setText("Time Allocated");

									resultsTable.setWidget(0, 0, lblAuditableUnit);
									resultsTable.setWidget(0, 1, lblDomain);
									resultsTable.setWidget(0, 2, lblDivision);
									resultsTable.setWidget(0, 3, lblRiskAssessment);
									resultsTable.setWidget(0, 4, lblResources);
									resultsTable.setWidget(0, 5, lblTimeAllocated);

									// resultsTable.setWidget(0, 0, new
									// Label("Job"));
									// resultsTable.setWidget(0, 1, new
									// Label("Domain"));
									// resultsTable.setWidget(0, 2, new
									// Label("Division"));
									// resultsTable.setWidget(0, 3, new
									// Label("Risk Assessment"));
									// resultsTable.setWidget(0, 4, new
									// Label("Resources"));
									// resultsTable.setWidget(0, 5, new
									// Label("Time Allocated"));
									//
									// resultsTable.getCellFormatter().addStyleName(0,
									// 0, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 1, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 2, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 3, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 4, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 5, "labelHeading");

									resultsTable.addStyleName("alignCenter");

									// fill the rows with resulting data

									ArrayList<Integer> ids = new ArrayList<Integer>();

									for (int i = 0; i < strategicList.size(); i++) {
										int j = 0;

										ids.add(strategicList.get(i).getId());

										// resultsTable.getRowFormatter().addStyleName(i+1,
										// "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 1, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 2, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 3, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 4, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 5, "form-row");

										Label lblJobName = new Label();
										lblJobName.setWidth("650px");
										lblJobName.setText(strategicList.get(i).getJobName());
										resultsTable.setWidget(i + 1, j++, lblJobName);

										// resultsTable.setWidget(i + 1, j++,
										// new
										// Label(strategicList.get(i).getJobName()));
										resultsTable.setWidget(i + 1, j++, new Label(strategicList.get(i).getDomain()));
										resultsTable.setWidget(i + 1, j++,
												new Label(strategicList.get(i).getDivisionName()));
										resultsTable.setWidget(i + 1, j++, new Label(strategicList.get(i).getRating()));
										// resultsTable.setWidget(i+1, j++, new
										// Label(
										// strategicList.get(i).getAssignedTo().getEmployeeName()
										// ));
										VerticalPanel vpnlEmployees = new VerticalPanel();
										for (int k = 0; k < strategicList.get(i).getEmployees().size(); k++) {
											vpnlEmployees.add(new Label(strategicList.get(i).getEmployees().get(k)));
										}
										resultsTable.setWidget(i + 1, j++, vpnlEmployees);

										resultsTable.setWidget(i + 1, j++,
												new Label(strategicList.get(i).getEstimatedWeeks() + " weeks"));
									}
									resultsTable.setWidth("100%");
									display.getReport2().getVpnlPerviewData().add(resultsTable);

									display.getReport2().showButtonBelow();

									chartHpnl.addStyleName("chartpadding");
									chartHpnl.setWidth("100%");

									chartHpnl.add(createChart(strategicList, false));

									chartHpnl.add(createDomainChart(strategicList, false));

									// chartHpnl.add( createDivisionChart(ids));

									// ---createDivisionChart(ids, false, view);

									display.getReport2().getVpnlPerviewData().add(chartHpnl);

									display.getReport2().getDetailedChartsView().add(createChart(strategicList, true));

									display.getReport2().getDetailedChartsView()
											.add(createDomainChart(strategicList, true));

									display.getReport2().btnExportToExcel.setVisible(true);
									display.getReport2().btnExportToPDF.setVisible(true);
									// ----createDivisionChart(ids, true,
									// display.getReport2());

									// display.getReportsContainer().add(view);

									// display.getStackReports().add(view,
									// selected);

								}

							}

							@Override
							public void onFailure(Throwable arg0) {
								loadingpopup.remove();
								Window.alert("failed getting search results");

							}
						});

			}
		});

	}

	private void setReport1Handlers() {

		display.getReport1().getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();
				// setup search parameters

				ArrayList<String> domain = getSelectedItems(display.getReport1().getDomainListbox());

				ArrayList<String> div = getSelectedItems(display.getReport1().getDivListbox());

				// final ArrayList<String> selectedDivision = getSelectedItems(
				// display.getDivListbox() );

				ArrayList<String> risk = getSelectedItems(display.getReport1().getRiskListbox());

				final ReportAuditPlanning view = new ReportAuditPlanning(null);

				rpcService.fetchReportSearchResult(div, domain, risk, new AsyncCallback<ArrayList<Strategic>>() {

					@Override
					public void onSuccess(ArrayList<Strategic> strategicList) {
						loadingpopup.remove();
						strategicReportData = strategicList;
						// display.getReport1().getTablePanel().clear();
						display.getReport1().getVpnlPerviewData().clear();
						chartHpnl.clear();
						if (strategicList != null && strategicList.size() > 0) {

							// create a flextable based on results .

							FlexTable resultsTable = new FlexTable();

							resultsTable.setCellPadding(8);
							resultsTable.setCellSpacing(5);

							// set up table headings

							// resultsTable.setWidget(0, 0, new
							// Label("Objective"));
							LabelHeading lblAuditableUnit = new LabelHeading();
							LabelHeading lblDomain = new LabelHeading();
							LabelHeading lblDivision = new LabelHeading();
							LabelHeading lblRiskAssessment = new LabelHeading();

							lblAuditableUnit.setText("Auditable Unit");
							lblDomain.setText("Domain");
							lblDivision.setText("Division");
							lblRiskAssessment.setText("Risk Assessment");

							resultsTable.setWidget(0, 0, lblAuditableUnit);
							resultsTable.setWidget(0, 1, lblDomain);
							resultsTable.setWidget(0, 2, lblDivision);
							resultsTable.setWidget(0, 3, lblRiskAssessment);

							// resultsTable.setWidget(0, 0, new Label("Auditable
							// Unit"));
							// resultsTable.setWidget(0, 1, new
							// Label("Domain"));
							// resultsTable.setWidget(0, 2, new
							// Label("Division"));
							// resultsTable.setWidget(0, 3, new Label("Risk
							// Assessment"));

							// resultsTable.getCellFormatter().addStyleName(0,
							// 0, "labelHeading");
							// resultsTable.getCellFormatter().addStyleName(0,
							// 1, "labelHeading");
							// resultsTable.getCellFormatter().addStyleName(0,
							// 2, "labelHeading");
							// resultsTable.getCellFormatter().addStyleName(0,
							// 3, "labelHeading");
							// resultsTable.getCellFormatter().addStyleName(0,
							// 4, "labelHeading");

							resultsTable.addStyleName("alignCenter");
							resultsTable.addStyleName("form-row");
							resultsTable.setWidth("100%");

							// fill the rows with resulting data
							ArrayList<Integer> ids = new ArrayList<Integer>();

							for (int i = 0; i < strategicList.size(); i++) {
								int j = 0;

								ids.add(strategicList.get(i).getId());

								resultsTable.getCellFormatter().addStyleName(i + 1, j, "form-row");
								resultsTable.getCellFormatter().addStyleName(i + 1, j + 1, "form-row");
								resultsTable.getCellFormatter().addStyleName(i + 1, j + 2, "form-row");
								resultsTable.getCellFormatter().addStyleName(i + 1, j + 3, "form-row");

								// resultsTable.setWidget(i+1, j++, new Label(
								// strategicList.get(i).getStrategicObjective()
								// ));
								// resultsTable.setWidget(i+1, j++, new Label(
								// strategicList.get(i).getStrategicObjective()
								// ));

								Label lblAuditableUnitData = new Label();
								lblAuditableUnitData.setWidth("915px");
								lblAuditableUnitData.setText(strategicList.get(i).getAuditableUnit());
								resultsTable.setWidget(i + 1, j++, lblAuditableUnitData);
								// resultsTable.setWidget(i + 1, j++, new
								// Label(strategicList.get(i).getAuditableUnit()));
								resultsTable.setWidget(i + 1, j++, new Label(strategicList.get(i).getDomain()));
								resultsTable.setWidget(i + 1, j++, new Label(strategicList.get(i).getDivisionName()));
								resultsTable.setWidget(i + 1, j++, new Label(strategicList.get(i).getRating()));

								// resultsTable.getCellFormatter().addStyleName(i+1,
								// j++, "form-row");
							}
							resultsTable.setWidth("100%");
							display.getReport1().getVpnlPerviewData().add(resultsTable);

							display.getReport1().showButtonBelow();

							// chartHpnl.addStyleName("chartpadding");
							chartHpnl.setWidth("100%");
							// chartHpnl.setSpacing(5);

							chartHpnl.add(createChart(strategicList, false));

							chartHpnl.add(createDomainChart(strategicList, false));
							chartHpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

							// chartHpnl.add( createDivisionChart(ids));

							createDivisionChart(ids, false, view);

							display.getReport1().getVpnlPerviewData().add(chartHpnl);

							display.getReport1().getDetailedChartsView().add(createChart(strategicList, true));

							display.getReport1().getDetailedChartsView().add(createDomainChart(strategicList, true));

							createDivisionChart(ids, true, display.getReport1());

							// display.getReportsContainer().add(view);

							// display.getStackReports().add(view, selected);

							display.getReport1().btnExportToPDF.setVisible(true);
							display.getReport1().btnExportToExcel.setVisible(true);

						}
						// else Window.alert("no record matching");
					}

					@Override
					public void onFailure(Throwable arg0) {
						loadingpopup.remove();
						Window.alert("failed getting search results");

					}
				});

			}
		});

	}

	private void setReport4Handlers() {

		display.getReport4().getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				// setup search parameters
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();
				ArrayList<String> domain = getSelectedItems(display.getReport4().getDomainListbox());

				ArrayList<String> div = getSelectedItems(display.getReport4().getDivListbox());

				ArrayList<String> risk = getSelectedItems(display.getReport4().getRiskListbox());
				ArrayList<String> resources = getSelectedItems(display.getReport4().getLstResource());

				// final ReportJobTimeAllocation view = new
				// ReportJobTimeAllocation(null);

				rpcService.fetchReportWithResourcesSearchResult(div, domain, risk, resources,
						new AsyncCallback<ArrayList<JobCreation>>() {

							@Override
							public void onSuccess(ArrayList<JobCreation> jobsList) {
								loadingpopup.remove();
								jobTimeAllocationDataList = jobsList;
								// strategicReportData = jobsList;
								chartHpnl.clear();
								display.getReport4().getVpnlPerviewData().clear();
								if (jobsList.size() > 0) {

									// create a flextable based on results .

									FlexTable resultsTable = new FlexTable();

									resultsTable.setCellPadding(8);
									resultsTable.setCellSpacing(5);

									// set up table headings

									resultsTable.setWidget(0, 0, new LabelHeading("Job"));
									resultsTable.setWidget(0, 1, new LabelHeading("Weeks"));

									resultsTable.addStyleName("alignCenter");
									resultsTable.addStyleName("form-row");
									resultsTable.setWidth("100%");

									// fill the rows with resulting data
									ArrayList<Integer> ids = new ArrayList<Integer>();

									for (int i = 0; i < jobsList.size(); i++) {
										int j = 0;

										ids.add(jobsList.get(i).getJobCreationId());

										resultsTable.getCellFormatter().addStyleName(i + 1, j, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 1, "form-row");

										// resultsTable.setWidget(i+1, j++, new
										// Label(
										// strategicList.get(i).getStrategicObjective()
										// ));
										// resultsTable.setWidget(i+1, j++, new
										// Label(
										// strategicList.get(i).getStrategicObjective()
										// ));
										Label lblJobName = new Label();
										lblJobName.setWidth("1000px");
										lblJobName.setText(jobsList.get(i).getJobName());
										resultsTable.setWidget(i + 1, j++, lblJobName);
										resultsTable.setWidget(i + 1, j++,
												new Label(jobsList.get(i).getEstimatedWeeks() + "("
														+ jobsList.get(i).getStartDate() + " - "
														+ jobsList.get(i).getEndDate() + ")"));
										// resultsTable.setWidget(i+1, j++, new
										// Label(
										// jobsList.get(i).getDivisionName()));
										// resultsTable.setWidget(i+1, j++, new
										// Label( jobsList.get(i).getRating()
										// ));
										resultsTable.getRowFormatter().addStyleName(i, "form-row");
										// resultsTable.getCellFormatter().addStyleName(i+1,
										// j++, "form-row");
									}
									resultsTable.setWidth("100%");
									display.getReport4().getVpnlPerviewData().add(resultsTable);

									display.getReport4().showButtonBelow();

									// chartHpnl.addStyleName("chartpadding");
									chartHpnl.setWidth("100%");
									// chartHpnl.setSpacing(5);

									// chartHpnl.add( createChart(jobsList,
									// false));
									//
									// chartHpnl.add(
									// createDomainChart(jobsList, false));
									chartHpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

									// chartHpnl.add( createDivisionChart(ids));
									//
									// createDivisionChart(ids, false, view);
									// ////////////////////////Commented this///
									//
									// display.getReport4().getTablePanel().add(
									// chartHpnl);

									display.getReport4().getVpnlPerviewData()
											.add(createJobTimeAllocationChart(jobsList));
									//
									// display.getReport1().getDetailedChartsView().add(
									// createDomainChart(jobsList, true));

									// createDivisionChart(ids, true,
									// display.getReport4());

									// display.getReportsContainer().add(view);

									// display.getStackReports().add(view,
									// selected);
									if (jobsList.size() > 0) {
										display.getReport4().getBtnExportToExcel().setVisible(true);
										display.getReport4().getBtnExportToPDF().setVisible(true);
									}
								}
								// else Window.alert("no record matching");
							}

							@Override
							public void onFailure(Throwable arg0) {
								loadingpopup.remove();
								Window.alert("failed getting search results");

							}
						});

			}
		});

	}

	private void setReport5Handlers() {

		display.getReport5().getBtnSearch().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				// setup search parameters
				final LoadingPopup loadingpopup = new LoadingPopup();
				loadingpopup.display();
				ArrayList<String> domain = getSelectedItems(display.getReport5().getDomainListbox());

				ArrayList<String> div = getSelectedItems(display.getReport5().getDivListbox());

				ArrayList<String> risk = getSelectedItems(display.getReport5().getRiskListbox());
				ArrayList<String> resources = getSelectedItems(display.getReport5().getLstResource());
				ArrayList<String> jobs = getSelectedItems(display.getReport5().getLstJobs());
				ArrayList<String> auditees = getSelectedItems(display.getReport5().getLstResponsibleAuditee());
				ArrayList<String> exceptionStatus = getSelectedItems(display.getReport5().getLstExceptionStatus());

				// final ReportJobTimeAllocation view = new
				// ReportJobTimeAllocation(null);

				rpcService.fetchExceptionReports(div, domain, risk, resources, jobs, auditees, exceptionStatus,
						new AsyncCallback<ArrayList<Exceptions>>() {

							@Override
							public void onSuccess(ArrayList<Exceptions> exceptionsList) {
								loadingpopup.remove();
								exceptionsDataList = exceptionsList;
								chartHpnl.clear();
								display.getReport5().getVpnlPerview().clear();
								if (exceptionsList != null && exceptionsList.size() > 0) {

									// create a flextable based on results .

									FlexTable resultsTable = new FlexTable();

									resultsTable.setCellPadding(8);
									resultsTable.setCellSpacing(5);

									// set up table headings

									// resultsTable.setWidget(0, 0, new
									// Label("Objective"));
									resultsTable.setWidget(0, 0, new LabelHeading("Exception"));
									resultsTable.setWidget(0, 1, new LabelHeading("Job"));
									// resultsTable.setWidget(0, 2, new
									// Label("Division"));
									// resultsTable.setWidget(0, 3, new
									// Label("Domain"));
									resultsTable.setWidget(0, 2, new LabelHeading("Status"));
									// resultsTable.setWidget(0, 5, new
									// Label("Resource"));
									resultsTable.setWidget(0, 3, new LabelHeading("Auditee"));
									// resultsTable.setWidget(0, 7, new
									// Label("Risk"));

									// resultsTable.getCellFormatter().addStyleName(0,
									// 0, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 1, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 2, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 3, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 4, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 5, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 6, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 7, "labelHeading");

									// resultsTable.getCellFormatter().addStyleName(0,
									// 3, "labelHeading");
									// resultsTable.getCellFormatter().addStyleName(0,
									// 4, "labelHeading");

									resultsTable.addStyleName("alignCenter");
									resultsTable.addStyleName("form-row");
									resultsTable.setWidth("100%");

									// fill the rows with resulting data
									ArrayList<Integer> ids = new ArrayList<Integer>();

									for (int i = 0; i < exceptionsList.size(); i++) {
										int j = 0;

										ids.add(exceptionsList.get(i).getExceptionId());

										resultsTable.getCellFormatter().addStyleName(i + 1, j, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 1, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 2, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j + 3, "form-row");

										Label lblJobName = new Label();
										lblJobName.setWidth("800px");
										lblJobName.setText(exceptionsList.get(i).getJobName());
										resultsTable.setWidget(i + 1, j++, lblJobName);

										// resultsTable.setWidget(i + 1, j++,
										// new
										// Label(exceptionsList.get(i).getDetail()));
										resultsTable.setWidget(i + 1, j++,
												new Label(exceptionsList.get(i).getJobName()));
										resultsTable.setWidget(i + 1, j++,
												new Label(exceptionsList.get(i).getDisplayStatus()));
										resultsTable.setWidget(i + 1, j++, new Label(
												exceptionsList.get(i).getResponsiblePerson().getEmployeeName()));
										resultsTable.getRowFormatter().addStyleName(i, "form-row");
										resultsTable.getCellFormatter().addStyleName(i + 1, j++, "form-row");
									}
									resultsTable.setWidth("100%");
									display.getReport5().getVpnlPerview().add(resultsTable);
									display.getReport5().showButtonBelow();
									chartHpnl.setWidth("100%");
									chartHpnl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
									display.getReport5().getVpnlPerview().add(createExceptionsChart(exceptionsList));
									display.getReport5().btnExportToExcel.setVisible(true);
									display.getReport5().btnExportToPDF.setVisible(true);
								}
							}

							@Override
							public void onFailure(Throwable arg0) {
								loadingpopup.remove();
								Window.alert("failed getting search results");

							}
						});

			}
		});

	}

	protected void initialize() {

		/*
		 * display.getBtnSearch().addClickHandler(new ClickHandler() {
		 * 
		 * @Override public void onClick(ClickEvent arg0) { //setup search
		 * parameters
		 * 
		 * 
		 * ArrayList<String> domain =
		 * getSelectedItems(display.getDomainListbox());
		 * 
		 * ArrayList<String> div = getSelectedItems( display.getDivListbox());
		 * 
		 * //final ArrayList<String> selectedDivision = getSelectedItems(
		 * display.getDivListbox() );
		 * 
		 * ArrayList<String> risk = getSelectedItems(display.getRiskListbox());
		 * 
		 * final Report1 view = new Report1(null);
		 * 
		 * rpcService.fetchReportSearchResult(div, domain, risk, new
		 * AsyncCallback<ArrayList<Strategic>>() {
		 * 
		 * @Override public void onSuccess(ArrayList<Strategic> strategicList) {
		 * strategicReportData = strategicList; if (strategicList.size() > 0) {
		 * 
		 * //create a flextable based on results .
		 * 
		 * FlexTable resultsTable = new FlexTable();
		 * 
		 * resultsTable.setCellPadding(8); resultsTable.setCellSpacing(5);
		 * resultsTable.setWidth("800px");
		 * 
		 * //set up table headings
		 * 
		 * resultsTable.setWidget(0, 0, new Label("Objective"));
		 * resultsTable.setWidget(0, 1, new Label("Auditable Unit"));
		 * resultsTable.setWidget(0, 2, new Label("Domain"));
		 * resultsTable.setWidget(0, 3, new Label("Division"));
		 * resultsTable.setWidget(0, 4, new Label("Risk Assessment"));
		 * 
		 * resultsTable.getCellFormatter().addStyleName(0, 0, "labelHeading");
		 * resultsTable.getCellFormatter().addStyleName(0, 1, "labelHeading");
		 * resultsTable.getCellFormatter().addStyleName(0, 2, "labelHeading");
		 * resultsTable.getCellFormatter().addStyleName(0, 3, "labelHeading");
		 * resultsTable.getCellFormatter().addStyleName(0, 4, "labelHeading");
		 * 
		 * resultsTable.addStyleName("alignCenter");
		 * 
		 * //fill the rows with resulting data ArrayList<Integer> ids = new
		 * ArrayList<Integer>();
		 * 
		 * for ( int i = 0; i < strategicList.size(); i++ ) { int j = 0;
		 * 
		 * ids.add(strategicList.get(i).getId() );
		 * 
		 * resultsTable.getRowFormatter().addStyleName(i+1, "line");
		 * 
		 * resultsTable.setWidget(i+1, j++, new Label(
		 * strategicList.get(i).getStrategicObjective() ));
		 * resultsTable.setWidget(i+1, j++, new Label(
		 * strategicList.get(i).getAuditableUnit()));
		 * resultsTable.setWidget(i+1, j++, new Label(
		 * strategicList.get(i).getDomain() )); resultsTable.setWidget(i+1, j++,
		 * new Label( strategicList.get(i).getDivisionName()));
		 * resultsTable.setWidget(i+1, j++, new Label(
		 * strategicList.get(i).getRating() )); }
		 * 
		 * // and add to UI String selected =
		 * display.getSelectionBox().getItemText(display.getSelectionBox().
		 * getSelectedIndex());
		 * 
		 * //IStackWidget view;
		 * 
		 * if ( selected.equals("Report 1")) {
		 * 
		 * view = new Report1(null);
		 * 
		 * } else if ( selected.equals("Report 2")) {
		 * 
		 * view = new Report2(null);
		 * 
		 * } else if ( selected.equals("Report 3")) {
		 * 
		 * view = new Report3(null);
		 * 
		 * }
		 * 
		 * view.getTablePanel().clear();
		 * 
		 * view.getTablePanel().add(resultsTable);
		 * 
		 * view.showButtonBelow();
		 * 
		 * 
		 * chartHpnl.clear();
		 * 
		 * chartHpnl.addStyleName("chartpadding"); chartHpnl.setWidth("800px");
		 * 
		 * 
		 * chartHpnl.add( createChart(strategicList, false));
		 * 
		 * chartHpnl.add( createDomainChart(strategicList, false));
		 * 
		 * //chartHpnl.add( createDivisionChart(ids));
		 * 
		 * createDivisionChart(ids, false, view);
		 * 
		 * view.getTablePanel().add( chartHpnl);
		 * 
		 * view.getDetailedChartsView().add( createChart(strategicList, true));
		 * 
		 * view.getDetailedChartsView().add( createDomainChart(strategicList,
		 * true));
		 * 
		 * createDivisionChart(ids, true, view);
		 * 
		 * //display.getReportsContainer().add(view);
		 * 
		 * 
		 * 
		 * display.getStackReports().add(view, selected);
		 * 
		 * } else Window.alert("no record matching"); }
		 * 
		 * @Override public void onFailure(Throwable arg0) {
		 * Window.alert("failed getting search results");
		 * 
		 * } });
		 * 
		 * 
		 * 
		 * } });
		 */

	}

	public Chart createChart(ArrayList<Strategic> l, final boolean isDetailed) {

		DataCount cout = new DataCount();
		cout.getDataCount(l);

		final Chart chart = new Chart().setWidth(350).setHeight(350).setType(Series.Type.PIE)

				.setChartTitleText("Risk").setPlotBackgroundColor((String) null).setPlotBorderWidth(null);
		chart.setCredits(new Credits().setText("")

		).setPlotShadow(false)
				.setPiePlotOptions(new PiePlotOptions().setAllowPointSelect(true)
						// .setCursor(PlotOptions.Cursor.POINTER)
						.setPieDataLabels(new PieDataLabels()

								.setConnectorColor("#000000").setEnabled(true).setColor("#000000")
								.setFormatter(new DataLabelsFormatter() {
									public String format(DataLabelsData dataLabelsData) {
										return dataLabelsData.getPointName() + " " + dataLabelsData.getYAsDouble()
												+ " %";
									}
								}))

				).setLegend(new Legend().setEnabled(true)

				).setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";
					}
				}));

		int total = cout.risk.hi + cout.risk.low + cout.risk.mid;

		chart.addSeries(chart.createSeries().setName("Risk Assessment")
				.setPoints(new Point[] { new Point("High", 100 * cout.risk.hi / total),
						new Point("Low", 100 * cout.risk.low / total),
						new Point("Medium", 100 * cout.risk.mid / total) }));

		// if ( !isDetailed ) chart.setSize(350, 300); else chart.setSize(820,
		// 500);
		if (!isDetailed)
			chart.setSize(report1RiskChartWidth, report1RiskChartHeight);
		else
			chart.setSize(820, 500);

		return chart;
	}

	//
	public Chart createJobTimeAllocationChart(ArrayList<JobCreation> jobs) {

		final Chart chart = new Chart().setType(Series.Type.PIE).setChartTitleText("Job Time Allocation")
				.setPlotBackgroundColor((String) null).setPlotBorderWidth(null).setPlotShadow(false);
		chart.setCredits(new Credits().setText("")

		).setPiePlotOptions(new PiePlotOptions().setAllowPointSelect(true)
				// .setCursor(PlotOptions.Cursor.POINTER)
				.setPieDataLabels(new PieDataLabels().setConnectorColor("#000000").setEnabled(true).setColor("#000000")
						.setFormatter(new DataLabelsFormatter() {
							public String format(DataLabelsData dataLabelsData) {
								return "<b>" + dataLabelsData.getPointName() + "</b>: " + dataLabelsData.getYAsLong()
										+ "%";
							}
						})))
				.setLegend(new Legend().setLayout(Legend.Layout.VERTICAL).setAlign(Legend.Align.RIGHT)
						.setVerticalAlign(Legend.VerticalAlign.TOP).setX(-100).setY(100).setFloating(true)
						.setBorderWidth(1).setBackgroundColor("#FFFFFF").setShadow(true))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";
					}
				}));

		int totalWeeks = getTotalWeeks(jobs);

		Point[] points = new Point[jobs.size()];
		for (int i = 0; i < jobs.size(); i++) {
			Point p = new Point(jobs.get(i).getJobName(), 100 * jobs.get(i).getEstimatedWeeks() / totalWeeks);
			// 100*cout.risk.hi / total
			points[i] = p;
		}
		chart.addSeries(chart.createSeries().setName("Browsershare").setPoints(points));
		return chart;

	}

	private int getTotalWeeks(ArrayList<JobCreation> jobs) {
		int count = 0;
		for (int i = 0; i < jobs.size(); i++) {
			count = count + jobs.get(i).getEstimatedWeeks();
		}
		return count;
	}

	public Chart createExceptionsChart(ArrayList<Exceptions> exceptions) {

		final Chart chart = new Chart().setType(Series.Type.PIE).setChartTitleText("Exceptions Staus")
				.setPlotBackgroundColor((String) null).setPlotBorderWidth(null).setPlotShadow(false);
		chart.setCredits(new Credits().setText("")

		).setPiePlotOptions(new PiePlotOptions().setAllowPointSelect(true)
				// .setCursor(PlotOptions.Cursor.POINTER)
				.setPieDataLabels(new PieDataLabels().setConnectorColor("#000000").setEnabled(true).setColor("#000000")
						.setFormatter(new DataLabelsFormatter() {
							public String format(DataLabelsData dataLabelsData) {
								return "<b>" + dataLabelsData.getPointName() + "</b>: " + dataLabelsData.getYAsLong()
										+ "%";
							}
						})))
				.setLegend(new Legend().setLayout(Legend.Layout.VERTICAL).setAlign(Legend.Align.RIGHT)
						.setVerticalAlign(Legend.VerticalAlign.TOP).setX(-100).setY(100).setFloating(true)
						.setBorderWidth(1).setBackgroundColor("#FFFFFF").setShadow(true))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsLong() + "%";
					}
				}));

		ArrayList<Exceptions> filteredExceptions = new ArrayList<Exceptions>();
		ArrayList<String> displayNames = new ArrayList<String>();
		for (int i = 0; i < exceptions.size(); i++) {
			if (!displayNames.contains(exceptions.get(i).getDisplayStatus())) {
				displayNames.add(exceptions.get(i).getDisplayStatus());
				filteredExceptions.add(exceptions.get(i));
			}
		}

		Point[] points = new Point[filteredExceptions.size()];
		int total = 0;
		for (int i = 0; i < filteredExceptions.size(); i++) {

			int count = getExceptionStatusCount(filteredExceptions.get(i).getDisplayStatus(), exceptions);
			total = total + count;
		}
		for (int i = 0; i < filteredExceptions.size(); i++) {

			int count = getExceptionStatusCount(filteredExceptions.get(i).getDisplayStatus(), exceptions);
			Point p = new Point(filteredExceptions.get(i).getDisplayStatus(), 100 * count / total);
			points[i] = p;
		}

		chart.addSeries(chart.createSeries().setName("Browser share").setPoints(points)

		);

		return chart;

	}

	public int getExceptionStatusCount(String exceptionStatus, ArrayList<Exceptions> exceptions) {
		int count = 0;
		for (int i = 0; i < exceptions.size(); i++) {
			if (exceptions.get(i).getDisplayStatus() != null
					&& exceptions.get(i).getDisplayStatus().equals(exceptionStatus)) {
				count++;
			}
		}

		return count;

	}

	public Chart createDomainChart(ArrayList<Strategic> l, boolean isDetailed) {

		DataCount cout = new DataCount();
		cout.getDataCount(l);

		final Chart chart = new Chart().setWidth(350).setHeight(350).setType(Series.Type.PIE)
				.setChartTitleText("Domain").setPlotBackgroundColor((String) null).setPlotBorderWidth(null)
				.setPlotShadow(false);
		chart.setCredits(new Credits().setText("")

		).setPiePlotOptions(new PiePlotOptions().setAllowPointSelect(true)
				// .setCursor(PlotOptions.Cursor.POINTER)
				.setPieDataLabels(new PieDataLabels()

						.setConnectorColor("#000000").setEnabled(true).setColor("#000000")
						.setFormatter(new DataLabelsFormatter() {
							public String format(DataLabelsData dataLabelsData) {
								return dataLabelsData.getPointName() + " " + dataLabelsData.getYAsDouble() + " %";
							}
						})))

				.setLegend(new Legend().setEnabled(true)

		).setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
			public String format(ToolTipData toolTipData) {
				return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";
			}
		}));

		int total = cout.domain.compliance + cout.domain.operations + cout.domain.reporting + cout.domain.strategic;

		chart.addSeries(chart.createSeries().setName("Risk Assessment")
				.setPoints(new Point[] { new Point("compliance", 100 * cout.domain.compliance / total),
						new Point("operations", 100 * cout.domain.operations / total),
						new Point("reporting", 100 * cout.domain.reporting / total),
						new Point("strategic", 100 * cout.domain.strategic / total), }));

		// if ( !isDetailed ) chart.setSize(380, 300); else chart.setSize(820,
		// 500);
		if (!isDetailed)
			chart.setSize(report1DomainChartWidth, report1DomainChartHeight);
		else
			chart.setSize(820, 500);
		return chart;
	}

	public void createDivisionChart(ArrayList<Integer> ids, final boolean isDetailed, final ReportAuditPlanning view) {
		final DataCount cout = new DataCount();

		rpcService.fetchStrategicDepartmentsMultiple(ids, new AsyncCallback<ArrayList<StrategicDepartments>>() {

			@Override
			public void onSuccess(ArrayList<StrategicDepartments> r) {
				cout.getDivisionCount(r);

				final Chart chart = new Chart().setWidth(350).setHeight(350).setType(Series.Type.PIE)
						.setChartTitleText("Divisions").setPlotBackgroundColor((String) null).setPlotBorderWidth(null)
						.setPlotShadow(false);
				chart.setCredits(new Credits().setText("")

				).setPiePlotOptions(new PiePlotOptions().setAllowPointSelect(true)
						// .setCursor(PlotOptions.Cursor.POINTER)
						.setPieDataLabels(new PieDataLabels().setEnabled(isDetailed)

								.setConnectorColor("#000000").setEnabled(true).setColor("#000000")
								.setFormatter(new DataLabelsFormatter() {
									public String format(DataLabelsData dataLabelsData) {
										return dataLabelsData.getPointName() + " " + dataLabelsData.getYAsDouble()
												+ " %";
									}
								}))

				).setLegend(new Legend().setEnabled(true)

				).setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";
					}
				}));

				int total = cout.div.it + cout.div.comm + cout.div.business + cout.div.finance + cout.div.hr
						+ cout.div.reg + cout.div.strategy + cout.div.pl;

				chart.addSeries(chart.createSeries().setName("Risk Assessment")
						.setPoints(new Point[] { new Point("IT", 100 * cout.div.it / total),
								new Point("Business", 100 * cout.div.business / total),
								// new Point("Commercial", 100* cout.div.comm /
								// total),
								new Point("Finance", 100 * cout.div.finance / total),
						// new Point("Human Resource", 100*cout.div.hr / total),
						// new Point("Regulatory", 100* cout.div.reg / total),
						// new Point("Strategy", 100* cout.div.strategy /
						// total),
						// new Point("Logistics", 100* cout.div.pl / total),
				})

				);

				if (!isDetailed) {
					// chart.setSize(480, 300);
					if (!isDetailed)
						chart.setSize(report1DivsionChartWidth, report1DivisionChartHeight);
					else
						chart.setSize(820, 500);
					chartHpnl.add(chart);
				}

				else {
					chart.setSize(820, 500);
					view.getDetailedChartsView().add(chart);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {

				Window.alert(arg0.getMessage());

			}
		});

	}

	private ArrayList<String> getSelectedItems(ListBox listbox) {
		ArrayList<String> selected = new ArrayList<String>();

		for (int i = 0; i < listbox.getItemCount(); i++) {
			if (listbox.isItemSelected(i))
				selected.add(listbox.getValue(i));

		}

		return selected;
	}

}
