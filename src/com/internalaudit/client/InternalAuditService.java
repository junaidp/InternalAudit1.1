package com.internalaudit.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.internalaudit.shared.ActivityObjective;
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
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobStatusDTO;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.JobTimeEstimation;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.JobType;
import com.internalaudit.shared.JobsOfEmployee;
import com.internalaudit.shared.Process;
import com.internalaudit.shared.ReportsDTO;
import com.internalaudit.shared.ProcessDTO;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.RiskControlMatrixEntity;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.Rolls;
import com.internalaudit.shared.SkillUpdateData;
import com.internalaudit.shared.Skills;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicAudit;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.StrategicRisk;
import com.internalaudit.shared.SubProcess;
import com.internalaudit.shared.SuggestedControls;
import com.internalaudit.shared.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface InternalAuditService extends RemoteService {
	User signIn(String userid, String password) throws Exception;

	ArrayList<Employee> fetchObjectiveOwners() throws Exception;

	ArrayList<Department> fetchDepartments() throws Exception;

	String saveStrategic(Strategic strategic, HashMap<String, String> hm)
			throws Exception;

	ArrayList<RiskFactor> fetchRiskFactors() throws Exception;

	ArrayList<Strategic> fetchStrategic(HashMap<String, String> hm)
			throws Exception;

	ArrayList<RiskAssesmentDTO> fetchRiskAssesment(HashMap<String, String> hm)
			throws Exception;

	String saveRiskAssesment(HashMap<String, String> hm,
			ArrayList<StrategicRisk> strategicRisks) throws Exception;

	String sendBackStrategic(Strategic strategics) throws Exception;

	String declineStrategic(int strategicId) throws Exception;

	ArrayList<StrategicAudit> fetchStrategicAudit() throws Exception;

	ArrayList<DashBoardDTO> fetchDashBoard(HashMap<String, String> hm) throws Exception;

	ArrayList<Strategic> fetchFinalAuditables() throws Exception;

	Boolean checkDate(Date date) throws Exception;

	ArrayList<StrategicDTO> fetchSchedulingStrategic(HashMap<String, String> hm)
			throws Exception;

	ArrayList<Skills> fetchSkills() throws Exception;

	boolean saveJobTimeEstimation(JobTimeEstimationDTO entity,
			ArrayList<SkillUpdateData> updateForSkills) throws Exception;

	JobTimeEstimationDTO fetchJobTime(int jobId) throws Exception;

	ArrayList<Employee> fetchEmployees() throws Exception;// Add throw Exception
															// here..

	public ArrayList<ResourceUse> fetchResourceUseFor(int jobId)
			throws Exception;

	ArrayList<Employee> fetchEmployeesByDeptId(ArrayList<Integer> depIds) throws Exception;

	void saveJobAndAreaOfExpertiseState(ArrayList<JobAndAreaOfExpertise> state)
			throws Exception;

	ArrayList<JobAndAreaOfExpertise> fetchCheckBoxStateFor(int jobId)
			throws Exception;

	void saveCreatedJob(JobCreationDTO job) throws Exception;

	String getEndDate(Date value, int estimatedWeeks) throws Exception;

	ArrayList<JobCreationDTO> fetchCreatedJobs(boolean getEmpRelation,
			boolean getSkillRelation) throws Exception;

	JobCreation fetchCreatedJob(int jobId) throws Exception;

	ArrayList<JobsOfEmployee> fetchEmployeesWithJobs() throws Exception;

	JobCreation updateEndDateForJob(int jobId, String startDate, String endDate)
			throws Exception;

	int[] getMonthsInvolved(String string, String string2) throws Exception;

	public ArrayList<AuditEngagement> fetchAllAuditEngagement(
			int loggedInEmployee) throws Exception;

	boolean updateAuditEngagement(AuditEngagement e, String fieldToUpdate)
			throws Exception;

	void syncAuditEngagementWithCreatedJobs(int loggedInEmployee)
			throws Exception;

	boolean saveRisks(ArrayList<RiskControlMatrixEntity> record) throws Exception;

	boolean sendEmail(String body, String sendTo) throws Exception;

	AuditEngagement fetchAuditEngagement(int selectedJobId) throws Exception;

	ArrayList<RiskControlMatrixEntity> fetchRisks(int auditEngId) throws Exception;

	// ArrayList<Object> fetchEmpForThisJob(int selectedJobId);

	ArrayList<JobEmployeeRelation> fetchEmployeeJobRelations(int selectedJobId)
			throws Exception;

	ArrayList<JobCreation> fetchJobs() throws Exception;

	ArrayList<JobCreation> fetchEmployeeJobs(Employee loggedInEmployee)
			throws Exception;

	ArrayList<Exceptions> fetchJobExceptions(int jobId) throws Exception;

	ArrayList<Exceptions> fetchEmployeeExceptions(int employeeId, int jobId)
			throws Exception;

	String sendException(Exceptions exception) throws Exception;

	void saveAuditStepAndExceptions(AuditStep step, ArrayList<Exceptions> exs)
			throws Exception;

	AuditStep getSavedAuditStep(int selectedJobId, int auditWorkId)
			throws Exception;

	ArrayList<Exceptions> getSavedExceptions(int selectedJobId)
			throws Exception;

	void saveAuditWork(ArrayList<AuditWork> records) throws Exception;

	void updateKickoffStatus(int auditEngId, User loggedInUser) throws Exception;

	ArrayList<Exceptions> fetchAuditHeadExceptions(int auditHeadId,
			int selectedJob) throws Exception;

	JobCreationDTO fetchCreatedJob(int id, boolean b, boolean c, String string)
			throws Exception;

	ArrayList<AuditWork> fetchAuditWorkRows(int selectedJobId) throws Exception;

	ArrayList<AuditWork> fetchApprovedAuditWorkRows(int selectedJobId)
			throws Exception;

	String saveAuditNotification(int auditEngagementId, String message,
			String to, String cc, String refNo , String from , String subject ) throws Exception;

	String logOut() throws Exception;

	void selectYear(int year) throws Exception;

	int fetchNumberofPlannedJobs() throws Exception;

	int fetchNumberofInProgressJobs() throws Exception;

	int fetchNumberofCompletedJobs() throws Exception;

	ArrayList<String> fetchJobsKickOffWithInaWeek() throws Exception;

	int fetchNumberOfAuditObservations() throws Exception;

	int fetchNumberOfExceptionsInProgress() throws Exception;

	int fetchNumberOfExceptionsImplemented() throws Exception;

	int fetchNumberOfExceptionsOverdue() throws Exception;

	ArrayList<String> fetchEmployeesAvilbleForNext2Weeks() throws Exception;
	ArrayList<StrategicDepartments> fetchStrategicDepartments(int strategicId) throws Exception;
	ArrayList<Integer> fetchResourceIds(int strategicId) throws Exception;
	ArrayList<Integer> fetchJobSoftSkills(int strategicId) throws Exception;
	ArrayList<Strategic> fetchReportSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk)
			throws Exception;
	ArrayList<JobCreation> fetchReportWithResourcesSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk,ArrayList<String> resources)
			throws Exception;

	ArrayList<StrategicDepartments> fetchStrategicDepartmentsMultiple(
			ArrayList<Integer> ids) throws Exception;
	String exportToExcel(ArrayList<ExcelDataDTO> excelDataList) throws Exception;
	ArrayList<Strategic> fetchReportAuditScheduling(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> jobStatus,	ArrayList<String> responsiblePerson)throws Exception;
	String approveFinalAuditable(Strategic strategic)throws Exception;
	String declineFinalAuditable(Strategic strategic)throws Exception;
	
	String saveUser(Employee employee)throws Exception;
	String updateUser(int previousHours,Employee employee)throws Exception;
	ArrayList<Date> getStartEndDates();
	Integer fetchNumberOfDaysBetweenTwoDates(Date from, Date to);
	String saveCompany(Company employee)throws Exception;
	ArrayList<Company> fetchCompanies() throws Exception;
	ArrayList<Rolls> fetchRolls() throws Exception;
	String updateStrategic(Strategic strategic)throws Exception;
	String deleteRisk(RiskControlMatrixEntity risk)throws Exception;
	String deleteAuditWork(int auditWorkId) throws Exception;
	Integer fetchCurrentYear()throws Exception;
	ArrayList<Employee> fetchEmployeesBySkillId(int jobId)throws Exception;
	String checkNoOfResourcesForSelectedSkill(int noOfResources, int skillId)throws Exception;
	String deleteException(int exceptionId) throws Exception;
	String approveScheduling() throws Exception;
	Employee fetchSelectedEmployee(int employeeId) throws Exception;
	ArrayList<Exceptions>fetchExceptionReports(ArrayList<String> div, ArrayList<String> domain,
	ArrayList<String> risk, ArrayList<String> resources,
	ArrayList<String> jobs, ArrayList<String> auditees,
	ArrayList<String> exceptionStatus)throws Exception;
	String exportJobTimeAllocationReportToExcel(ArrayList<JobTimeAllocationReportDTO> excelDataList) throws Exception;
	String exportExceptionsReportToExcel(ArrayList<ExceptionsReportDTO> excelDataList) throws Exception;
	String exportAuditSchedulingReportToExcel(ArrayList<AuditSchedulingReportDTO> excelDataList) throws Exception;
	Boolean isScheduleApproved()throws Exception;
	DashBoardNewDTO fetchDashboard(HashMap<String, String> hm) throws Exception;
	String updateUploadedAuditStepFile(int auditStepId)throws Exception;
	String saveSelectedAuditStepIdInSession(int auditStepId)throws Exception;
	String submitFeedBack(Feedback feedBack)throws Exception;
	//ArrayList<Process p,> fetchConsolidation(ArrayList<String> process,ArrayList<String> subProcess,ArrayList<String> jobType);
	ArrayList<ProcessDTO> fetchProcessDTOs();
	ArrayList<SubProcess> fetchSubProcess(int processId);


	String saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives, int jobid, int status);
	String saveRiskObjectives(ArrayList<RiskObjective> riskObjectives, int jobid, int saved) throws Exception;
	String saveExistingControls(ArrayList<SuggestedControls> suggestedControls ) throws Exception;
	String saveAuditWorkProgram(ArrayList<AuditProgramme> auditWorkProgramme , int selectedJobId) throws Exception;
	ArrayList<AuditProgramme> fetchApprovedAuditProgrammeRows(int selectedJobId)
			throws Exception;
	String deleteRiskObjective(int riskId, int jobId) throws Exception;
	JobStatusDTO fetchJobStatus(int jobId) throws Exception;
	ArrayList<DashboardListBoxDTO> fetchDashBoardListBoxDTOs();

	
}
