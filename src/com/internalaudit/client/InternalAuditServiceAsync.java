package com.internalaudit.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditSchedulingReportDTO;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.ExcelDataDTO;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.ExceptionsReportDTO;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.JobsOfEmployee;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.Risk;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.Rolls;
import com.internalaudit.shared.SkillUpdateData;
import com.internalaudit.shared.Skills;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicAudit;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.StrategicRisk;
import com.internalaudit.shared.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface InternalAuditServiceAsync {
	void signIn(String userid, String password, AsyncCallback<User> callback);

	void fetchObjectiveOwners(AsyncCallback<ArrayList<Employee>> callback);

	void fetchDepartments(AsyncCallback<ArrayList<Department>> callback);

	void fetchRiskFactors(AsyncCallback<ArrayList<RiskFactor>> callback);

	void saveStrategic(Strategic strategic, HashMap<String, String> hm,
			AsyncCallback<String> callback);

	void fetchStrategic(HashMap<String, String> hm,
			AsyncCallback<ArrayList<Strategic>> callback);

	void fetchRiskAssesment(HashMap<String, String> hm,
			AsyncCallback<ArrayList<RiskAssesmentDTO>> callback);

	void saveRiskAssesment(HashMap<String, String> hm,
			ArrayList<StrategicRisk> strategicRisks,
			AsyncCallback<String> callback);

	void sendBackStrategic(Strategic strategics, AsyncCallback<String> callback);

	void declineStrategic(int startegicId, AsyncCallback<String> callback);

	void fetchStrategicAudit(AsyncCallback<ArrayList<StrategicAudit>> callback);

	void fetchDashBoard(AsyncCallback<ArrayList<DashBoardDTO>> callback);

	void fetchFinalAuditables(AsyncCallback<ArrayList<Strategic>> callback);

	void checkDate(Date date, AsyncCallback<Boolean> callback);

	void fetchSchedulingStrategic(HashMap<String, String> hm,
			AsyncCallback<ArrayList<StrategicDTO>> callback);

	void fetchSkills(AsyncCallback<ArrayList<Skills>> callback);

	void saveJobTimeEstimation(JobTimeEstimationDTO entity, ArrayList<SkillUpdateData> updateForSkills, AsyncCallback<Boolean> callback);

	void fetchJobTime(int jobId, AsyncCallback<JobTimeEstimationDTO> callback);

	void fetchEmployees(AsyncCallback<ArrayList<Employee>> callback);

	void fetchResourceUseFor(int jobId,
			AsyncCallback<ArrayList<ResourceUse>> callback);

	void fetchEmployeesByDeptId(ArrayList<Integer> depIds,
			AsyncCallback<ArrayList<Employee>> asyncCallback);

	void saveJobAndAreaOfExpertiseState(ArrayList<JobAndAreaOfExpertise> state,
			AsyncCallback<Void> callback);

	void fetchCheckBoxStateFor(int jobId,
			AsyncCallback<ArrayList<JobAndAreaOfExpertise>> callback);

	void saveCreatedJob(JobCreationDTO job, AsyncCallback<Void> callback);

	void fetchCreatedJobs(boolean getEmpRelation, boolean getSkillRelation,
			AsyncCallback<ArrayList<JobCreationDTO>> asyncCallback);

	void getEndDate(Date value, int estimatedWeeks,
			AsyncCallback<String> asyncCallback);

	void fetchEmployeesWithJobs(
			AsyncCallback<ArrayList<JobsOfEmployee>> callback);

	void updateEndDateForJob(int jobId, String startDate, String endDate,
			AsyncCallback<JobCreation> asyncCallback);

	void getMonthsInvolved(String string, String string2,
			AsyncCallback<int[]> callback);

	void fetchAllAuditEngagement(int loggedInEmployee, AsyncCallback<ArrayList<AuditEngagement>> callback);

	void fetchCreatedJob(int jobId,AsyncCallback<JobCreation> callback);

	void updateAuditEngagement(AuditEngagement e, String fieldToUpdate,
			AsyncCallback<Boolean> callback);

	void syncAuditEngagementWithCreatedJobs(int loggedInEmployee, AsyncCallback<Void> asyncCallback);

	void saveRisks(ArrayList<Risk> records, AsyncCallback<Boolean> asyncCallback);

	void sendEmail(String body, String sendTo,
			AsyncCallback<Boolean> asyncCallback);

	void fetchAuditEngagement(int selectedJobId,
			AsyncCallback<AuditEngagement> asyncCallback);

	void fetchRisks(int auditEngId, AsyncCallback<ArrayList<Risk>> asyncCallback);

//	void fetchEmpForThisJob(
//			int selectedJobId,
//			AsyncCallback<ArrayList<Object>> asyncCallback);

	void fetchEmployeeJobRelations(int selectedJobId,
			AsyncCallback<ArrayList<JobEmployeeRelation>> asyncCallback);


    void fetchJobs(AsyncCallback<ArrayList<JobCreation>> asyncCallback );
    void fetchEmployeeJobs(Employee loggedInEmployee, AsyncCallback<ArrayList<JobCreation>> asyncCallback );

	void fetchJobExceptions(int jobId, AsyncCallback<ArrayList<Exceptions>> asyncCallbac);
	void fetchEmployeeExceptions(int employeeId, int jobId, AsyncCallback<ArrayList<Exceptions>> asyncCallbac);
	void sendException(Exceptions exception, AsyncCallback<String> asyncCallbac);

	void saveAuditStepAndExceptions(AuditStep step, ArrayList<Exceptions> exs,
			AsyncCallback<Void> asyncCallback);

	void getSavedAuditStep(int selectedJobId,int auditWorkId, AsyncCallback<AuditStep> asyncCallback);

	void getSavedExceptions(int selectedJobId,
			AsyncCallback<ArrayList<Exceptions>> asyncCallback);

	void saveAuditWork(ArrayList<AuditWork> records,
			AsyncCallback<Void> asyncCallback);

	void updateKickoffStatus(int auditEngId, User loggedInUser, AsyncCallback<Void> asyncCallback);

	void fetchAuditHeadExceptions(int auditHeadId, int selectedJob, AsyncCallback<ArrayList<Exceptions>> asyncCallback);


	void fetchCreatedJob(int id, boolean b, boolean c, String string,
			AsyncCallback<JobCreationDTO> asyncCallback);

	void fetchAuditWorkRows(int selectedJobId,
			AsyncCallback<ArrayList<AuditWork>> asyncCallback);
	void fetchApprovedAuditWorkRows(int selectedJobId,AsyncCallback<ArrayList<AuditWork>> asyncCallback);

	void saveAuditNotification(int auditEngId, String message, String to, String cc, AsyncCallback<String> asyncCallback);

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
	void fetchJobSoftSkills(int strategicId,AsyncCallback<ArrayList<Integer>> asyncCallback);
	void fetchReportSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk,	AsyncCallback<ArrayList<Strategic>> callback);
	void fetchReportWithResourcesSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk,	 ArrayList<String> resources,AsyncCallback<ArrayList<JobCreation>> callback);

	void fetchStrategicDepartmentsMultiple(ArrayList<Integer> ids, AsyncCallback<ArrayList<StrategicDepartments>> callback);
	void exportToExcel(ArrayList<ExcelDataDTO> excelDataList, AsyncCallback<String> callback);
	void fetchReportAuditScheduling(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> jobStatus,	ArrayList<String> responsiblePerson, AsyncCallback<ArrayList<Strategic>> callback);

	void approveFinalAuditable(Strategic strategic, AsyncCallback<String> callback);

	void declineFinalAuditable(Strategic strategic,	AsyncCallback<String> asyncCallback);
	void saveUser(Employee employee,	AsyncCallback<String> asyncCallback);
	void updateUser(int previousHours,Employee employee,	AsyncCallback<String> asyncCallback);
	void getStartEndDates(AsyncCallback<ArrayList<Date>> asyncCallback);
	void fetchNumberOfDaysBetweenTwoDates(Date from, Date to, AsyncCallback<Integer> asyncCallback);
	void saveCompany(Company company,	AsyncCallback<String> asyncCallback);

	void fetchCompanies(AsyncCallback<ArrayList<Company>> asyncCallback);
	void fetchRolls(AsyncCallback<ArrayList<Rolls>> asyncCallback);

	void updateStrategic(Strategic strategic, AsyncCallback<String> asyncCallback);

	void deleteRisk(Risk risk, AsyncCallback<String> asyncCallback);

	void deleteAuditWork(int auditWorkId, AsyncCallback<String> asyncCallback);
	void fetchCurrentYear(AsyncCallback<Integer> asyncCallback);

	void fetchEmployeesBySkillId(int jobId,
			AsyncCallback<ArrayList<Employee>> asyncCallback);

	void checkNoOfResourcesForSelectedSkill(int noOfResources, int skillId, AsyncCallback<String> asyncCallback);
	void deleteException(int exceptionId, AsyncCallback<String> asyncCallback);
	void approveScheduling(AsyncCallback<String> asyncCallback);
	void fetchSelectedEmployee(int employeeId, AsyncCallback<Employee> asyncCallback);

	void fetchExceptionReports(ArrayList<String> div, ArrayList<String> domain,
			ArrayList<String> risk, ArrayList<String> resources,
			ArrayList<String> jobs, ArrayList<String> auditees,
			ArrayList<String> exceptionStatus,
			AsyncCallback<ArrayList<Exceptions>> asyncCallback);
	void exportJobTimeAllocationReportToExcel(ArrayList<JobTimeAllocationReportDTO> excelDataList, AsyncCallback<String> callback);

	void exportExceptionsReportToExcel(
			ArrayList<ExceptionsReportDTO> excelDataList,
			AsyncCallback<String> asyncCallback);

	void exportAuditSchedulingReportToExcel(
			ArrayList<AuditSchedulingReportDTO> excelDataList,
			AsyncCallback<String> asyncCallback);
	
	void isScheduleApproved(AsyncCallback<Boolean> asyncCallback);
	void fetchDashboard(AsyncCallback<DashBoardNewDTO> asyncCallback);
	void updateUploadedAuditStepFile(int auditStepId, AsyncCallback<String> asyncCallback);
	void saveSelectedAuditStepIdInSession(int auditStepId, AsyncCallback<String> asyncCallback);
}
