	package com.internalaudit.server;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.icu.text.SimpleDateFormat;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.database.MySQLRdbHelper;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditSchedulingReportDTO;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.ExcelDataDTO;
import com.internalaudit.shared.ExceptionsReportDTO;
import com.internalaudit.shared.Feedback;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.JobType;
import com.internalaudit.shared.JobsOfEmployee;
import com.internalaudit.shared.Process;
import com.internalaudit.shared.ProcessDTO;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.Risk;
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
import com.internalaudit.shared.TimeOutException;
import com.internalaudit.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class InternalAuditServiceImpl extends RemoteServiceServlet implements
InternalAuditService {
	HttpSession session ;
	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
	@Override

	public User signIn(String userid, String password) throws Exception {// NOT HERE
		//String result = "";
		
		User user = (User) rdbHelper.getAuthentication(userid, password);

		if(user!=null)
		{
			session=getThreadLocalRequest().getSession(true);
			
			session.setAttribute("user", user);
			int currentYear = getCurrentYear();
			session.setAttribute("year", currentYear);
			session.setAttribute("companyId", user.getEmployeeId().getCompanyId());
			session.setMaxInactiveInterval(InternalAuditConstants.TIMEOUT);
		}
		return user;

	}
	@Override
	public ArrayList<Employee> fetchObjectiveOwners() throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchEmployees(companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}	
	}
	@Override
	public ArrayList<Department> fetchDepartments() throws Exception {
		if(isLoggedIn()){
			
			return rdbHelper.fetchDepartments();
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}	
	}

	@Override
	public ArrayList<Skills> fetchSkills() throws Exception {
		session=getThreadLocalRequest().getSession(true);
		int year = (Integer) session.getAttribute("year");
		int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchSkills(year, companyId);
		
	}

	@Override
	public String saveStrategic(Strategic strategic, HashMap<String, String> hm)
			throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			User loggedInUser = (User) session.getAttribute("user");

			////
			
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			
			if(strategic.getYear()>year && hm.get("todo").equalsIgnoreCase("submit"))
			{
				year= strategic.getYear();
			}
			
			return rdbHelper.saveStrategic(strategic, loggedInUser, hm, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<RiskFactor> fetchRiskFactors() throws Exception {

		if(isLoggedIn()){
			
			return rdbHelper.fetchRiskFactors();

		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Strategic> fetchStrategic(HashMap<String, String> hm) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			User loggedInUser = (User) session.getAttribute("user");
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			hm.put("year", year+"");
			hm.put("companyId", companyId+"");
			return rdbHelper.fetchStrategic(hm, loggedInUser.getEmployeeId().getEmployeeId());
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String saveRiskAssesment(HashMap<String, String> hm,ArrayList<StrategicRisk> strategicRisks)
			throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			
			User loggedInUser = (User) session.getAttribute("user");
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			hm.put("year", year+"");
			hm.put("companyId", companyId+"");
			return rdbHelper.saveRiskAssesment(strategicRisks, loggedInUser, hm);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public String sendBackStrategic(Strategic strategics) throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			
			User loggedInUser = (User) session.getAttribute("user");
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");

			return rdbHelper.amendStrategic(strategics, loggedInUser, year, companyId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<RiskAssesmentDTO> fetchRiskAssesment(HashMap<String, String> hm) throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			
			User loggedInUser = (User) session.getAttribute("user");
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchRiskAssesment(hm, loggedInUser.getEmployeeId().getEmployeeId(), year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String declineStrategic(int strategicId) throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			
			User loggedInUser = (User) session.getAttribute("user");
			return rdbHelper.declineStrategic(strategicId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<StrategicAudit> fetchStrategicAudit() throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchStrategicAidit(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<DashBoardDTO> fetchDashBoard() throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			User loggedInUser = (User) session.getAttribute("user");
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchDashBoard(loggedInUser, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Strategic> fetchFinalAuditables() throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchFinalAuditables(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<StrategicDTO> fetchSchedulingStrategic(
			HashMap<String, String> hm) throws Exception {
		if(isLoggedIn()){
			
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchSchdulingStrategic(hm, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public Boolean checkDate(Date date) throws Exception {
		if(isLoggedIn()){
			DateTime enteredDate = new DateTime(date); 
			DateTime currentDate = new DateTime(); //current date
			if(currentDate.isAfter(enteredDate) ){
				return false;
			}else 
				return true;
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}


	@Override
	public JobTimeEstimationDTO fetchJobTime(int jobId) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchJobTime(jobId, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Employee> fetchEmployees() throws Exception{ // Add throw Exception  here..
		session=getThreadLocalRequest().getSession(true);
		int year = (Integer) session.getAttribute("year");
		int companyId = (Integer) session.getAttribute("companyId");
						return rdbHelper.fetchEmployees(companyId);
		
	}
	@Override
	public ArrayList<ResourceUse> fetchResourceUseFor(int jobId) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchResourceUseFor(jobId, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Employee> fetchEmployeesByDeptId(ArrayList<Integer> depIds) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchEmployeesByDeptId(depIds, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public ArrayList<Employee> fetchEmployeesBySkillId(int jobId) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchEmployeesBySkillId(jobId, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public void saveJobAndAreaOfExpertiseState(ArrayList<JobAndAreaOfExpertise> state) throws Exception{
		if(isLoggedIn()){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");

			rdbHelper.saveJobAndAreaOfExpertiseState(state);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<JobAndAreaOfExpertise> fetchCheckBoxStateFor(int jobId) throws Exception{
		if(isLoggedIn()){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

			MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");

			return rdbHelper.fetchCheckBoxStateFor(jobId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public void saveCreatedJob(JobCreationDTO job) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			rdbHelper.saveCreatedJob(job, year, companyId);	
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public ArrayList<JobCreationDTO> fetchCreatedJobs( boolean getEmpRelation, boolean getSkillRelation) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchCreatedJobs(getEmpRelation, getSkillRelation, year, companyId);	
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public ArrayList<JobsOfEmployee> fetchEmployeesWithJobs() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			ArrayList<JobsOfEmployee> test = rdbHelper.fetchEmployeesWithJobs(companyId);

			return test;
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public String getEndDate(Date startDate, int estWeeks) throws Exception
	{
		if(isLoggedIn()){
			DateTime enddate = new DateTime(startDate);

			enddate = enddate.plusWeeks(estWeeks);

			return enddate.toString(DateTimeFormat.forPattern("dd-MM-yy"));	
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public int[] getMonthsInvolved(String startDate, String endDate) throws Exception
	{
		if(isLoggedIn()){
			if ( startDate == null || endDate == null )
				return new int[] {0,0};

			Date end = null;
			Date start = null;
			SimpleDateFormat fmt = new SimpleDateFormat("dd, MM, yyyy");

			try {
				end = fmt.parse(endDate);
				start = fmt.parse(startDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}


			Calendar c = Calendar.getInstance();


			c.setTime(start);

			int startMonth = c.get(Calendar.MONTH) + 1;


			c.setTime(end);


			int endMonth = c.get(Calendar.MONTH) + 1;


			return new int[] { startMonth, endMonth };
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}

	@Override
	public JobCreation updateEndDateForJob(int jobId, String startDate, String endDate) throws Exception{
		if(isLoggedIn()){
			

			return rdbHelper.updateEndDateForJob( jobId, startDate,  endDate);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public boolean saveJobTimeEstimation(JobTimeEstimationDTO entity, ArrayList<SkillUpdateData> updateForSkills) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.saveJobTimeEstimation( entity, updateForSkills, year, companyId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<AuditEngagement> fetchAllAuditEngagement(int loggedInEmployee) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchAllAuditEngagement(loggedInEmployee, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public JobCreation fetchCreatedJob(int jobId) throws Exception{
		if(isLoggedIn()){
			
			return rdbHelper.fetchCreatedJob(jobId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public boolean updateAuditEngagement(AuditEngagement e, String fieldToUpdate) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.updateAuditEngagement(e, fieldToUpdate, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public void syncAuditEngagementWithCreatedJobs(int loggedInEmployee)  throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			rdbHelper.syncAuditEngagementWithCreatedJobs(loggedInEmployee, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public boolean saveRisks(ArrayList<Risk> records)  throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.saveRisks(records, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public boolean sendEmail(String body, String sendTo)  throws Exception{
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

			MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");

			return rdbHelper.sendEmail(body, sendTo, "mfaheempiracha@gmail.com","Congrats ! First Username Request received for InternalAudit Software :)");
		
	}
	@Override
	public AuditEngagement fetchAuditEngagement(int selectedJobId)  throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchAuditEngagement(selectedJobId, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Risk> fetchRisks(int auditEngId) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchRisks(auditEngId, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	//	@Override
	//	public ArrayList<Object> fetchEmpForThisJob(int selectedJobId) {
	//		
	//		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	//		
	//		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
	//		*/
	//		
	//		return null;//rdbHelper.fetchEmpForThisJob(selectedJobId);
	//	}
	@Override
	public ArrayList<JobEmployeeRelation> fetchEmployeeJobRelations (
			int selectedJobId) throws Exception{
		if(isLoggedIn()){
			return rdbHelper.fetchEmployeeJobRelations(selectedJobId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<JobCreation> fetchJobs()  throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchJobs(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public ArrayList<Exceptions> fetchJobExceptions(int jobId)  throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchJobExceptions(jobId, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String sendException(Exceptions exception) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.sendException(exception, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Exceptions> fetchEmployeeExceptions(int employeeId, int jobId) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchEmployeeExceptions(employeeId, jobId, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public void saveAuditStepAndExceptions(AuditStep auditstep,
			ArrayList<Exceptions> exs) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			rdbHelper.saveAuditStepAndExceptions(auditstep, exs, year, companyId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public AuditStep getSavedAuditStep(int jobid, int auditWorkId) throws Exception{

		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			AuditStep auditStep = rdbHelper.getSavedAuditStep(jobid, auditWorkId, year, companyId );
//			session.setAttribute("auditStep", auditStep.getAuditStepId());
			return auditStep;
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public ArrayList<Exceptions> getSavedExceptions(int selectedJobId) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.getSavedExceptions(selectedJobId, year, companyId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public void saveAuditWork(ArrayList<AuditWork> records) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			rdbHelper.saveAuditWork( records, year, companyId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public void updateKickoffStatus(int auditEngId, User loggedInUser) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			rdbHelper.updateKickoffStatus( auditEngId, year, companyId, loggedInUser );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public ArrayList<Exceptions> fetchAuditHeadExceptions(int auditHeadId,int selectedJob) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchAuditHeadExceptions(auditHeadId, selectedJob, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}

	@Override
	public JobCreationDTO fetchCreatedJob(int id, boolean b, boolean c,	String string) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			return rdbHelper.fetchCreatedJob( id, b, c ,string );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<AuditWork> fetchAuditWorkRows(int jocreationid) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchAuditWorkRows( jocreationid, companyId, year );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public ArrayList<AuditWork> fetchApprovedAuditWorkRows(int selectedJobId) throws Exception{
		if(isLoggedIn()){
			
			
			return rdbHelper.fetchApprovedAuditWorkRows( selectedJobId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<JobCreation> fetchEmployeeJobs(Employee loggedInEmployee) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchEmployeeJobs( loggedInEmployee, year, companyId );
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String saveAuditNotification(int auditEngagementId,String message, String to, String cc, String refNo , String from , String subject) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.saveAuditNotification( auditEngagementId, message, to, cc , year, companyId, refNo, from, subject);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}


	private boolean isLoggedIn() throws Exception{

		HttpSession session = getThreadLocalRequest().getSession(true);
		if(session.getAttribute("user") == null){
			return false;
		}else{
			return true;
		}

	}

	public String logOut() throws Exception{
		if(isLoggedIn()){
			getThreadLocalRequest().getSession().invalidate();
			return "loggedOut";
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public void selectYear(int year) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			session.setAttribute("year", year);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberofPlannedJobs() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberofPlannedJobs(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberofInProgressJobs() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberofInProgressJobs(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberofCompletedJobs() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberofCompletedJobs(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<String> fetchJobsKickOffWithInaWeek() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchJobsKickOffWithInaWeek(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberOfAuditObservations() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberOfAufitObservations(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberOfExceptionsInProgress() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberOfExceptionsInProgress(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberOfExceptionsImplemented() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberOfExceptionsImplemented(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public int fetchNumberOfExceptionsOverdue() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchNumberOfExceptionsOverdue(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<String> fetchEmployeesAvilbleForNext2Weeks() throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchEmployeesAvilbleForNext2Weeks(year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<StrategicDepartments> fetchStrategicDepartments(
			int strategicId) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			return rdbHelper.fetchStrategicDepartments(strategicId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Integer> fetchResourceIds(int strategicId)
			throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			return rdbHelper.fetchResourceIds(strategicId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Integer> fetchJobSoftSkills(int strategicId)
			throws Exception {
		if(isLoggedIn()){
			int year = (Integer) session.getAttribute("year");
			return rdbHelper.fetchJobSoftSkills(strategicId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Strategic> fetchReportSearchResult(ArrayList<String> dept, ArrayList<String> domain, ArrayList<String> risk) throws Exception{
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchReportSearchResult( dept,  domain,  risk, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<StrategicDepartments> fetchStrategicDepartmentsMultiple(
			ArrayList<Integer> ids) throws  Exception {

		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			return rdbHelper.fetchStrategicDepartmentsMutiple(ids);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String exportToExcel(ArrayList<ExcelDataDTO> excelDataList)
			throws Exception {
		String rootDir = getServletContext().getRealPath("/");
		return rdbHelper.exportToExcel(excelDataList, rootDir);
	}
	@Override
	public ArrayList<Strategic> fetchReportAuditScheduling(
			ArrayList<String> dept, ArrayList<String> domain,
			ArrayList<String> jobStatus, ArrayList<String> responsiblePerson) throws Exception {


		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchReportAuditScheduling( dept,  domain,  jobStatus, responsiblePerson, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}

	}
	@Override
	public String approveFinalAuditable(Strategic strategic) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			return rdbHelper.approveFinalAuditable(strategic);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String declineFinalAuditable(Strategic strategic) throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			return rdbHelper.declineFinalAuditable(strategic);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String saveUser(Employee employee) throws Exception {
		session=getThreadLocalRequest().getSession(true);
		int year = (Integer) session.getAttribute("year");
		int companyId = (Integer) session.getAttribute("companyId");
		if(isValidEmailAddress(employee.getEmail())){
		return rdbHelper.saveEmployee(employee, year, companyId);
		}else{
			return InternalAuditConstants.INVALIDEMAIL;
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	public int getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		Date todaysDate =cal.getTime();
		String date = todaysDate.toString();
		int length = date.length();
		date = date.substring(length-4,  length);
		int currentYear = Integer.parseInt(date);
		return currentYear;
	}
	
	public ArrayList<Date> getStartEndDates(){
		ArrayList<Date> startEndDates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		Date todaysDate =cal.getTime();
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		Date start = cal.getTime();
		cal.set(Calendar.MONTH, 11); 
		cal.set(Calendar.DAY_OF_MONTH, 31);

		Date end = cal.getTime();
		
		startEndDates.add(start);
		startEndDates.add(end);
		startEndDates.add(todaysDate);
		return startEndDates;
	}
	@Override
	public Integer fetchNumberOfDaysBetweenTwoDates(Date from, Date to) {
		 
	    Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();
	    cal1.setTime(from);
	    cal2.setTime(to);

	    int numberOfDays = 0;
	    while (cal1.before(cal2)) {
	        if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
	           &&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
	            numberOfDays++;
	            cal1.add(Calendar.DATE,1);
	        }else {
	            cal1.add(Calendar.DATE,1);
	        }
	    }
	    System.out.println(numberOfDays);
		return numberOfDays;
	}
	@Override
	public String saveCompany(Company company) throws Exception {
		return rdbHelper.saveCompany(company);
	}
	@Override
	public ArrayList<Company> fetchCompanies() throws Exception {
		return rdbHelper.fetchCompanies();
	}
	@Override
	public ArrayList<Rolls> fetchRolls() throws Exception {
		return rdbHelper.fetchRolls();
	}
	@Override
	public String updateStrategic(Strategic strategic) throws Exception {
		return rdbHelper.updateStrategic(strategic);
	}
	@Override
	public String deleteRisk(Risk risk) throws Exception {
		return rdbHelper.deleteRisk(risk);
	}
	@Override
	public String deleteAuditWork(int auditWorkId) throws Exception {
		return rdbHelper.deleteAuditWok(auditWorkId);
	}
	@Override
	public Integer fetchCurrentYear() throws Exception {
		int year = getCurrentYear();
		return year;
	}
	@Override
	public String checkNoOfResourcesForSelectedSkill(int noOfResources,
			int skillId) throws Exception {
		session=getThreadLocalRequest().getSession(true);
		int year = (Integer) session.getAttribute("year");
		int companyId = (Integer) session.getAttribute("companyId");
		
		return rdbHelper.checkNoOfResourcesForSelectedSkill(noOfResources, skillId, companyId);
	}
	@Override
	public String deleteException(int exceptionId) throws Exception {
		return rdbHelper.deleteException(exceptionId);
	}
	@Override
	public String approveScheduling() throws Exception {
		session=getThreadLocalRequest().getSession(true);
		int companyId = (Integer) session.getAttribute("companyId");
		int year = (Integer) session.getAttribute("year");
		return rdbHelper.approveScheduling(companyId, year);
	}
	@Override
	public Employee fetchSelectedEmployee(int employeeId) throws Exception {
		return rdbHelper.fetchSelectedEmployee(employeeId);
	}
	@Override
	public String updateUser(int previousHours,Employee employee) throws Exception {
		return rdbHelper.updateUser(previousHours, employee);
	}
	@Override
	public ArrayList<JobCreation> fetchReportWithResourcesSearchResult(
			ArrayList<String> dept, ArrayList<String> domain,
			ArrayList<String> risk, ArrayList<String> resources)
			throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchReportWithResourcesSearchResult( dept,  domain,  risk,resources, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public ArrayList<Exceptions> fetchExceptionReports(ArrayList<String> div,
			ArrayList<String> domain, ArrayList<String> risk,
			ArrayList<String> resources, ArrayList<String> jobs,
			ArrayList<String> auditees, ArrayList<String> exceptionStatus)
			throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchExceptionReports( div,  domain,  risk,resources,jobs,auditees, exceptionStatus, year, companyId);
		}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String exportJobTimeAllocationReportToExcel(
			ArrayList<JobTimeAllocationReportDTO> excelDataList)
			throws Exception {
		
		if(isLoggedIn()){
			String rootDir = getServletContext().getRealPath("/");
			return rdbHelper.exportJobTimeAllocationReportToExcel(excelDataList, rootDir);		
			}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String exportExceptionsReportToExcel(
			ArrayList<ExceptionsReportDTO> excelDataList) throws Exception {
		if(isLoggedIn()){
			String rootDir = getServletContext().getRealPath("/");
			return rdbHelper.exportExceptionsReportToExcel(excelDataList, rootDir);		
			}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String exportAuditSchedulingReportToExcel(
			ArrayList<AuditSchedulingReportDTO> excelDataList) throws Exception {
		if(isLoggedIn()){
			String rootDir = getServletContext().getRealPath("/");
			return rdbHelper.exportAuditSchedulingReportToExcel(excelDataList, rootDir);		
			}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public Boolean isScheduleApproved() throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.isScheduleApproved(companyId, year);
			}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
//	@Override
//	public ArrayList<String> fetchJobsInExecution() throws Exception {
//		if(isLoggedIn()){
//			session=getThreadLocalRequest().getSession(true);
//			int year = (Integer) session.getAttribute("year");
//			int companyId = (Integer) session.getAttribute("companyId");
//			return rdbHelper.fetchjobsInExecution(year, companyId);
//			}else{
//
//			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);
//
//		}
//	}
//	@Override
//	public ArrayList<String> fetchManagementCommentsOverdue() throws Exception {
//		if(isLoggedIn()){
//			session=getThreadLocalRequest().getSession(true);
//			int year = (Integer) session.getAttribute("year");
//			int companyId = (Integer) session.getAttribute("companyId");
//			return rdbHelper.fetchManagementCommentsOverdue(year, companyId);
//			}else{
//
//			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);
//
//		}
//	}
	@Override
	public DashBoardNewDTO fetchDashboard() throws Exception {
		if(isLoggedIn()){
			session=getThreadLocalRequest().getSession(true);
			int year = (Integer) session.getAttribute("year");
			int companyId = (Integer) session.getAttribute("companyId");
			return rdbHelper.fetchDashBoard(year, companyId);
			}else{

			throw new TimeOutException(InternalAuditConstants.LOGGEDOUT);

		}
	}
	@Override
	public String updateUploadedAuditStepFile(int auditStepId) throws Exception {
		session=getThreadLocalRequest().getSession(true);
		ArrayList<String>auditStepFiles = (ArrayList<String>) session.getAttribute("auditStepUploadedFiles") ;
		return  rdbHelper.updateUploadedAuditStepFile(auditStepId, auditStepFiles );
		
	}
	@Override
	public String saveSelectedAuditStepIdInSession(int auditStepId)
			throws Exception {
		session=getThreadLocalRequest().getSession(true);
		session.setAttribute("auditStep", auditStepId);
		return "saved";
	}
	@Override
	public String submitFeedBack(Feedback feedBack) throws Exception {
		return rdbHelper.submitFeedBack(feedBack);
	}
	@Override
	public ArrayList<ProcessDTO> fetchProcessDTOs() {
		return rdbHelper.fetchProcessDtOs();
	}
	@Override
	public ArrayList<SubProcess> fetchSubProcess(int processId) {
		return rdbHelper.fetchSubProcess(processId);
	}
	@Override
	public String saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives) throws Exception {
		return rdbHelper.saveActivityObjectives(activityObjectives);
	}
	@Override
	public String saveRiskObjectives(ArrayList<RiskObjective> riskObjectives) throws Exception {
		return rdbHelper.saveRiskObjectives(riskObjectives);
	}
	
	

}
