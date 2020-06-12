package com.internalaudit.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.internalaudit.client.view.InternalAuditReporting.AssesmentGridEntity;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.AssesmentGridDbEntity;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditProgramme;
import com.internalaudit.shared.AuditSchedulingReportDTO;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.DashboardListBoxDTO;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.ExcelDataDTO;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.ExceptionsReportDTO;
import com.internalaudit.shared.Feedback;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InformationRequestLogEntity;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobStatusDTO;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.JobsOfEmployee;
import com.internalaudit.shared.ProcessDTO;
import com.internalaudit.shared.ReportDataEntity;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskControlMatrixEntity;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.SamplingExcelSheetEntity;
import com.internalaudit.shared.SkillUpdateData;
import com.internalaudit.shared.Skills;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicAudit;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.StrategicRisk;
import com.internalaudit.shared.SubProcess;
import com.internalaudit.shared.SuggestedControls;
import com.internalaudit.shared.ToDo;
import com.internalaudit.shared.ToDoLogsEntity;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface InternalAuditServiceAsync {

	void signIn(String userid, String password, AsyncCallback<Employee> callback);

	void fetchObjectiveOwners(AsyncCallback<ArrayList<Employee>> callback);

	void fetchDepartments(AsyncCallback<ArrayList<Department>> callback);

	void fetchRiskFactors(AsyncCallback<ArrayList<RiskFactor>> callback);

	void saveStrategic(Strategic strategic, HashMap<String, String> hm, AsyncCallback<String> callback);

	void fetchStrategic(HashMap<String, String> hm, AsyncCallback<ArrayList<Strategic>> callback);

	void fetchRiskAssesment(HashMap<String, String> hm, AsyncCallback<ArrayList<RiskAssesmentDTO>> callback);

	void saveRiskAssesment(HashMap<String, String> hm, ArrayList<StrategicRisk> strategicRisks,
			AsyncCallback<String> callback);

	void sendBackStrategic(Strategic strategics, AsyncCallback<String> callback);

	void declineStrategic(int startegicId, AsyncCallback<String> callback);

	void fetchStrategicAudit(AsyncCallback<ArrayList<StrategicAudit>> callback);

	void fetchDashBoard(HashMap<String, String> hm, AsyncCallback<ArrayList<DashBoardDTO>> callback);

	void fetchFinalAuditables(AsyncCallback<ArrayList<Strategic>> callback);

	void checkDate(Date date, AsyncCallback<Boolean> callback);

	void fetchSchedulingStrategic(HashMap<String, String> hm, AsyncCallback<ArrayList<StrategicDTO>> callback);

	void fetchSkills(AsyncCallback<ArrayList<Skills>> callback);

	void saveJobTimeEstimation(JobTimeEstimationDTO entity, ArrayList<SkillUpdateData> updateForSkills,
			AsyncCallback<Boolean> callback);

	void fetchJobTime(int jobId, AsyncCallback<JobTimeEstimationDTO> callback);

	void fetchEmployees(AsyncCallback<ArrayList<Employee>> callback);

	void fetchResourceUseFor(int jobId, AsyncCallback<ArrayList<ResourceUse>> callback);

	void fetchEmployeesByDeptId(ArrayList<Integer> depIds, AsyncCallback<ArrayList<Employee>> asyncCallback);

	void saveJobAndAreaOfExpertiseState(ArrayList<JobAndAreaOfExpertise> state, AsyncCallback<Void> callback);

	void fetchCheckBoxStateFor(int jobId, AsyncCallback<ArrayList<JobAndAreaOfExpertise>> callback);

	void saveCreatedJob(JobCreationDTO job, AsyncCallback<String> callback);

	void fetchCreatedJobs(boolean getEmpRelation, boolean getSkillRelation,
			AsyncCallback<ArrayList<JobCreationDTO>> asyncCallback);

	void getEndDate(Date value, int estimatedWeeks, AsyncCallback<String> asyncCallback);

	void fetchEmployeesWithJobs(AsyncCallback<ArrayList<JobsOfEmployee>> callback);

	void updateEndDateForJob(int jobId, String startDate, String endDate, AsyncCallback<JobCreation> asyncCallback);

	void getMonthsInvolved(String string, String string2, AsyncCallback<int[]> callback);

	void fetchAllAuditEngagement(int loggedInEmployee, AsyncCallback<ArrayList<AuditEngagement>> callback);

	void fetchCreatedJob(int jobId, AsyncCallback<JobCreation> callback);

	void updateAuditEngagement(AuditEngagement e, String fieldToUpdate, AsyncCallback<Boolean> callback);

	void syncAuditEngagementWithCreatedJobs(int loggedInEmployee, AsyncCallback<Void> asyncCallback);

	void saveRisks(ArrayList<RiskControlMatrixEntity> records, AsyncCallback<Boolean> asyncCallback);

	void sendEmail(String body, String sendTo, AsyncCallback<Boolean> asyncCallback);

	void fetchAuditEngagement(int selectedJobId, AsyncCallback<AuditEngagement> asyncCallback);

	void fetchRisks(int auditEngId, AsyncCallback<ArrayList<RiskControlMatrixEntity>> asyncCallback);

	// void fetchEmpForThisJob(
	// int selectedJobId,
	// AsyncCallback<ArrayList<Object>> asyncCallback);

	void fetchEmployeeJobRelations(int selectedJobId, AsyncCallback<ArrayList<JobEmployeeRelation>> asyncCallback);

	void fetchJobs(AsyncCallback<ArrayList<JobCreation>> asyncCallback);

	void fetchEmployeeJobs(Employee loggedInEmployee, String reportingTab,
			AsyncCallback<ArrayList<JobCreation>> asyncCallback);

	void fetchJobExceptions(int jobId, AsyncCallback<ArrayList<Exceptions>> asyncCallbac);

	void fetchEmployeeExceptions(int employeeId, int jobId, AsyncCallback<ArrayList<Exceptions>> asyncCallbac);

	void sendException(Exceptions exception, Boolean sendMail, String selectedView, AsyncCallback<String> asyncCallbac);

	void saveAuditStepAndExceptions(AuditStep step, ArrayList<Exceptions> exs, AsyncCallback<Void> asyncCallback);

	void getSavedAuditStep(int selectedJobId, int auditWorkId, AsyncCallback<AuditStep> asyncCallback);

	void getSavedExceptions(int selectedJobId, AsyncCallback<ArrayList<Exceptions>> asyncCallback);

	void saveAuditWork(ArrayList<AuditWork> records, AsyncCallback<Void> asyncCallback);

	void updateKickoffStatus(int auditEngId, Employee loggedInUser, AsyncCallback<Void> asyncCallback);

	void fetchAuditHeadExceptions(int auditHeadId, int selectedJob, AsyncCallback<ArrayList<Exceptions>> asyncCallback);

	void fetchCreatedJob(int id, boolean b, boolean c, String string, AsyncCallback<JobCreationDTO> asyncCallback);

	void fetchAuditWorkRows(int selectedJobId, AsyncCallback<ArrayList<AuditWork>> asyncCallback);

	void fetchApprovedAuditWorkRows(int selectedJobId, AsyncCallback<ArrayList<AuditWork>> asyncCallback);

	void saveAuditNotification(int auditEngagementId, String message, String to, String cc, String refNo, String from,
			String subject, String filePath, String momoNo, String date, int status,
			AsyncCallback<String> asyncCallback);

	void logOut(AsyncCallback<String> asyncCallback);

	void selectYear(int year, AsyncCallback<Void> asyncCallback);

	void fetchNumberofPlannedJobs(AsyncCallback<Integer> asyncCallback);

	void fetchNumberofInProgressJobs(AsyncCallback<Integer> asyncCallback);

	void fetchNumberofCompletedJobs(AsyncCallback<Integer> asyncCallback);

	void fetchJobsKickOffWithInaWeek(AsyncCallback<ArrayList<String>> asyncCallback);

	void fetchNumberOfAuditObservations(AsyncCallback<Integer> asyncCallback);

	void fetchNumberOfExceptionsInProgress(AsyncCallback<Integer> asyncCallback);

	void fetchNumberOfExceptionsImplemented(AsyncCallback<Integer> asyncCallback);

	void fetchNumberOfExceptionsOverdue(AsyncCallback<Integer> asyncCallback);

	void fetchEmployeesAvilbleForNext2Weeks(AsyncCallback<ArrayList<String>> asyncCallback);

	void fetchStrategicDepartments(int strategicId, AsyncCallback<ArrayList<StrategicDepartments>> asyncCallback);

	void fetchResourceIds(int strategicId, AsyncCallback<ArrayList<Integer>> asyncCallback);

	void fetchJobSoftSkills(int strategicId, AsyncCallback<ArrayList<Integer>> asyncCallback);

	void fetchReportSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk,
			AsyncCallback<ArrayList<Strategic>> callback);

	void fetchReportWithResourcesSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk,
			ArrayList<String> resources, AsyncCallback<ArrayList<JobCreation>> callback);

	void fetchStrategicDepartmentsMultiple(ArrayList<Integer> ids,
			AsyncCallback<ArrayList<StrategicDepartments>> callback);

	void exportAuditPlanningReport(ArrayList<ExcelDataDTO> excelDataList, String btn, AsyncCallback<String> callback);

	void fetchReportAuditScheduling(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> jobStatus,
			ArrayList<String> responsiblePerson, AsyncCallback<ArrayList<Strategic>> callback);

	void approveFinalAuditable(Strategic strategic, AsyncCallback<String> callback);

	void declineFinalAuditable(Strategic strategic, AsyncCallback<String> asyncCallback);

	void saveUser(Employee employee, AsyncCallback<String> asyncCallback);

	void updateUser(int previousHours, Employee employee, AsyncCallback<String> asyncCallback);

	void getStartEndDates(AsyncCallback<ArrayList<Date>> asyncCallback);

	void fetchNumberOfDaysBetweenTwoDates(Date from, Date to, AsyncCallback<Integer> asyncCallback);

	void saveCompany(Company company, AsyncCallback<String> asyncCallback);

	void fetchCompanies(AsyncCallback<ArrayList<Company>> asyncCallback);

	void updateStrategic(Strategic strategic, AsyncCallback<String> asyncCallback);

	void deleteRisk(RiskControlMatrixEntity risk, AsyncCallback<String> asyncCallback);

	void deleteAuditWork(int auditWorkId, AsyncCallback<String> asyncCallback);

	void fetchCurrentYear(AsyncCallback<Integer> asyncCallback);

	void fetchEmployeesBySkillId(int jobId, AsyncCallback<ArrayList<Employee>> asyncCallback);

	void checkNoOfResourcesForSelectedSkill(int noOfResources, int skillId, AsyncCallback<String> asyncCallback);

	void deleteException(int exceptionId, AsyncCallback<String> asyncCallback);

	void approveScheduling(AsyncCallback<String> asyncCallback);

	void fetchSelectedEmployee(int employeeId, AsyncCallback<Employee> asyncCallback);

	void fetchExceptionReports(ArrayList<String> div, ArrayList<String> domain, ArrayList<String> risk,
			ArrayList<String> resources, ArrayList<String> jobs, ArrayList<String> auditees,
			ArrayList<String> exceptionStatus, AsyncCallback<ArrayList<Exceptions>> asyncCallback);

	void exportJobTimeAllocationReport(ArrayList<JobTimeAllocationReportDTO> excelDataList, String btn,
			AsyncCallback<String> callback);

	void exportAuditExceptionsReport(ArrayList<ExceptionsReportDTO> excelDataList, String btn,
			AsyncCallback<String> asyncCallback);

	void exportAuditSchedulingReport(ArrayList<AuditSchedulingReportDTO> excelDataList, String btn,
			AsyncCallback<String> asyncCallback);

	void isScheduleApproved(AsyncCallback<Boolean> asyncCallback);

	void fetchDashboard(HashMap<String, String> hm, AsyncCallback<DashBoardNewDTO> asyncCallback);

	void updateUploadedAuditStepFile(int auditStepId, AsyncCallback<String> asyncCallback);

	void saveSelectedAuditStepIdInSession(int auditStepId, AsyncCallback<String> asyncCallback);

	void submitFeedBack(Feedback feedBack, AsyncCallback<String> asyncCallback);

	void fetchProcessDTOs(AsyncCallback<ArrayList<ProcessDTO>> callback);

	void fetchSubProcess(int processId, AsyncCallback<ArrayList<SubProcess>> callback);

	void saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives, int jobid, int status,
			AsyncCallback<String> callback);

	void saveRiskObjectives(ArrayList<RiskObjective> riskObjectives, int jobid, int status,
			AsyncCallback<String> callback);

	void saveExistingControls(ArrayList<SuggestedControls> suggestedControls, AsyncCallback<String> callback);

	void saveAuditWorkProgram(ArrayList<AuditProgramme> auditWorkProgramme, int selectedJobId,
			AsyncCallback<String> callback);

	void fetchApprovedAuditProgrammeRows(int selectedJobId, AsyncCallback<ArrayList<AuditProgramme>> asyncCallback);

	void deleteRiskObjective(int riskId, int jobId, AsyncCallback<String> asyncCallback);

	void fetchJobStatus(int jobId, AsyncCallback<JobStatusDTO> asyncCallback);

	void fetchDashBoardListBoxDTOs(AsyncCallback<ArrayList<DashboardListBoxDTO>> callback);

	void savetoDo(ToDo todo, AsyncCallback<String> callback);

	void saveinformationRequest(InformationRequestEntity informationrequest, String filepath,
			AsyncCallback<String> callback);

	void fetchEmailAttachments(AsyncCallback<ArrayList<String>> callback);

	void saveToDoLogs(ToDoLogsEntity toDoLogsEntity, AsyncCallback<String> callback);

	void saveInformationRequestLogs(InformationRequestLogEntity informationRequestLogEntity,
			AsyncCallback<String> callback);

	void fetchAuditStepExceptions(String id, AsyncCallback<ArrayList<String>> callback);

	void fetchAuditStepsProcerdure(String id, String mainFolder, AsyncCallback<ArrayList<String>> callback);

	void deleteAttachmentFile(String id, String mainFolder, String fileName, AsyncCallback<String> callback);

	void deleteUnsavedAttachemnts(String pathtodouploads, AsyncCallback<String> callback);

	void fetchAssignedFromToDos(AsyncCallback<ArrayList<ToDo>> callback);

	void fetchAssignedToToDos(AsyncCallback<ArrayList<ToDo>> callback);

	void fetchAssignedFromIRReLoad(AsyncCallback<ArrayList<InformationRequestEntity>> callback);

	void fetchJobExceptionWithImplicationRating(int jobId, int ImplicationRating,
			AsyncCallback<ArrayList<Exceptions>> callback);

	void fetchControlsForReport(int jobId, AsyncCallback<ArrayList<SuggestedControls>> callback);

	void fetchSelectedJob(int jobId, AsyncCallback<JobCreation> callback);

	void saveReportDataPopup(ReportDataEntity reportData, AsyncCallback<String> callback);

	void fetchReportDataPopup(int jobId, AsyncCallback<ReportDataEntity> callback);

	void saveAssesmentGrid(ArrayList<AssesmentGridEntity> listAssesment, int jobid, AsyncCallback<String> callback);

	void fetchAssesmentGrid(int jobId, AsyncCallback<ArrayList<AssesmentGridDbEntity>> callback);

	void deleteActivityObjective(int jobId, AsyncCallback<String> callback);

	void getNextYear(Date value, AsyncCallback<Date> asyncCallback);

	void readExcel(String subFolder, String mainFolder, AsyncCallback<ArrayList<SamplingExcelSheetEntity>> callback);

	void fetchAssignedToIRReLoad(AsyncCallback<ArrayList<InformationRequestEntity>> asyncCallback);

	void generateSamplingOutput(String populationSize, String samplingSize, String samplingMehod,
			ArrayList<SamplingExcelSheetEntity> list, AsyncCallback<ArrayList<SamplingExcelSheetEntity>> callback);
}
