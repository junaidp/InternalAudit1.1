package com.internalaudit.database;

import java.io.File;
//import java.lang.invoke.VolatileCallSite;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.eclipse.jetty.util.log.Log;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.internalaudit.client.view.InternalAuditReporting.AssesmentGridEntity;
import com.internalaudit.shared.ActivityObjective;
import com.internalaudit.shared.AssesmentGridDbEntity;
//import com.ibm.icu.text.SimpleDateFormat;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditProgramme;
import com.internalaudit.shared.AuditSchedulingReportDTO;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.AuditWorkStatusDTO;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.CompanySkillRelation;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.DashboardListBoxDTO;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.EmployeeJobDTO;
import com.internalaudit.shared.EngagementDTO;
import com.internalaudit.shared.ExcelDataDTO;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.ExceptionsReportDTO;
import com.internalaudit.shared.Feedback;
import com.internalaudit.shared.FieldWorkStatusDTO;
import com.internalaudit.shared.HibernateDetachUtility;
import com.internalaudit.shared.InformationRequestEntity;
import com.internalaudit.shared.InformationRequestLogEntity;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobNamesWithExceptionsImplementationStatus;
import com.internalaudit.shared.JobSkillRelation;
import com.internalaudit.shared.JobStatusDTO;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.JobTimeEstimation;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.JobType;
import com.internalaudit.shared.JobsOfEmployee;
import com.internalaudit.shared.ObjectiveJobRelation;
import com.internalaudit.shared.PlanningStatusDTO;
import com.internalaudit.shared.Process;
import com.internalaudit.shared.ProcessDTO;
import com.internalaudit.shared.ReportDataEntity;
import com.internalaudit.shared.ReportsDTO;
import com.internalaudit.shared.ResourceUse;
import com.internalaudit.shared.RiskAssesmentDTO;
import com.internalaudit.shared.RiskControlMatrixEntity;
import com.internalaudit.shared.RiskFactor;
import com.internalaudit.shared.RiskJobRelation;
import com.internalaudit.shared.RiskObjective;
import com.internalaudit.shared.SkillUpdateData;
import com.internalaudit.shared.Skills;
import com.internalaudit.shared.Strategic;
import com.internalaudit.shared.StrategicAudit;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.StrategicDepartments;
import com.internalaudit.shared.StrategicRisk;
import com.internalaudit.shared.SubProcess;
import com.internalaudit.shared.SuggestedControls;
import com.internalaudit.shared.TimeLineDates;
import com.internalaudit.shared.ToDo;
import com.internalaudit.shared.ToDoLogsEntity;

public class MySQLRdbHelper {
	// private static final Logger LOGGER = Logger.getLogger(
	// MySQLRdbHelper.class.getName() );
	private Session session;

	private SessionFactory sessionFactory;
	// Logger logger;
	private Logger logger = Logger.getLogger(MySQLRdbHelper.class.getName());

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public Employee getAuthentication(String userid, String password) throws Exception {

		System.out.print("Inisde Sign in");
		// logger =
		// Logger.getLogger("com.internalaudit.database.MySQLRdbHelper.class");
		logger = Logger.getLogger("com.internalaudit.database.MySQLRdbHelper.class");
		logger.setLevel(Level.INFO);
		Employee employee = null;
		Session session = null;
		System.out.print(logger.getLevel() + "");
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(Employee.class);
			crit.add(Restrictions.eq("email", userid));
			crit.add(Restrictions.eq("password", password));
			crit.createAlias("countryId", "empcoun");
			crit.createAlias("cityId", "empcit");
			crit.createAlias("reportingTo", "empRep");
			crit.createAlias("skillId", "skill");
			// crit.createAlias("emp.companyId", "company");

			crit.createAlias("empRep.reportingTo", "empRepRep");
			crit.createAlias("empRep.skillId", "skillRep");
			crit.createAlias("empRepRep.skillId", "empRepRepSkill");

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				employee = (Employee) it.next();

				employee.setTodos(fetchUsersTodos(employee));
				employee.setUserRaisedToDos(fetchUsersRaisedToDo(employee));
				employee.setInformationRequests(fetchInformationUserRequest(employee));
				employee.setUserRaisedInformationRequests(fetchUserRaisedInformationRequest(employee));
				// create a method in mysql class to Todo where userid =
				// users.getEmployeeId().getEmployeeId(), set it here

				System.out.println(employee.getEmail() + "Signed In on" + new Date());
				logger.info((employee.getEmail() + "Signed In on from log" + new Date()));
				// new LOGGER.log( Level.ALL, String.format(users.getName() +
				// "Signed In on from logggggg" + new Date()));
			}
			HibernateDetachUtility.nullOutUninitializedFields(employee,
					HibernateDetachUtility.SerializationType.SERIALIZATION);

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getAuthentication", ex.getMessage()), ex);
			System.out.println("Exception occured in getAuthentication" + ex.getMessage());

			throw new Exception("Exception occured in getAuthentication");// Add
			// this
			// Line
			// Accordingly
			// in
			// all
			// method
		} finally {
			session.close();
		}

		return employee;
	}

	private ArrayList<InformationRequestEntity> fetchUserRaisedInformationRequest(Employee employeeId) {
		Session session = null;
		ArrayList<InformationRequestEntity> informationRequests = new ArrayList<InformationRequestEntity>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(InformationRequestEntity.class);
			crit.createAlias("contactResponsible", "assignedto");
			crit.createAlias("assignedFrom", "assignedfrom");
			crit.add(Restrictions.eq("assignedFrom.employeeId", employeeId.getEmployeeId()));
			crit.createAlias("job", "jobCreation");
			jobsStrategicAlias(crit);
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				InformationRequestEntity informationRequest = (InformationRequestEntity) it.next();
				ArrayList<InformationRequestLogEntity> informationrequestlogs = fetchInformationRequestLogs(
						informationRequest.getInformationRequestId());
				informationRequest.setInformationRequestLogList(informationrequestlogs);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest.getAssignedFrom(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest.getContactResponsible(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest.getJob(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				informationRequests.add(informationRequest);
			}
			logger.info(String.format("Inside fetchInformationUserRaisedRequest() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchInformationUserRAisedRequest()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return informationRequests;
	}

	private ArrayList<InformationRequestLogEntity> fetchInformationRequestLogs(int informationRequestId) {
		Session session = null;
		ArrayList<InformationRequestLogEntity> informationRequestLogs = new ArrayList<InformationRequestLogEntity>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(InformationRequestLogEntity.class);
			crit.createAlias("assignedFrom", "from");
			crit.createAlias("assignedTo", "To");

			// crit.add(Restrictions.eq("companyId", companyId));

			crit.createAlias("from.countryId", "employeeCount");
			crit.createAlias("from.cityId", "employeeCity");
			crit.createAlias("from.reportingTo", "employeeRep");
			crit.createAlias("from.skillId", "employeeSkill");
			crit.createAlias("To.skillId", "employeeSkill2");
			crit.createAlias("To.countryId", "employeeCountR");
			crit.createAlias("To.cityId", "employeeCityR");
			crit.createAlias("To.reportingTo", "employeeRepR");

			crit.add(Restrictions.eq("informationRequestId", informationRequestId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				InformationRequestLogEntity informationRequest = (InformationRequestLogEntity) it.next();

				informationRequestLogs.add(informationRequest);
			}
			logger.info(String.format("Inside fetchInformationUserRequestLogs() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchInformationUserRequestLogs()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return informationRequestLogs;
	}

	private ArrayList<ToDo> fetchUsersRaisedToDo(Employee employeeId) {
		Session session = null;
		ArrayList<ToDo> toDos = new ArrayList<ToDo>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ToDo.class);
			crit.createAlias("assignedTo", "assignedto");
			crit.createAlias("assignedFrom", "assignedfrom");
			crit.createAlias("job", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("assignedfrom.employeeId", employeeId.getEmployeeId()));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ToDo toDo = (ToDo) it.next();
				ArrayList<ToDoLogsEntity> todos = fetchtoDoLogsLogs(toDo.getToDoId());
				toDo.setTodosLogList(todos);
				HibernateDetachUtility.nullOutUninitializedFields(toDo,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(toDo.getAssignedFrom(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(toDo.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(toDo.getJob(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				toDos.add(toDo);
			}
			logger.info(String.format("Inside fetchUsersRaisedTodo() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchUsersRaisedTodo()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return toDos;
	}

	private ArrayList<ToDoLogsEntity> fetchtoDoLogsLogs(int toDoId) {
		Session session = null;
		ArrayList<ToDoLogsEntity> toDoLogs = new ArrayList<ToDoLogsEntity>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ToDoLogsEntity.class);
			crit.createAlias("assignedFrom", "from");
			crit.createAlias("assignedTo", "To");

			// crit.add(Restrictions.eq("companyId", companyId));

			crit.createAlias("from.countryId", "employeeCount");
			crit.createAlias("from.cityId", "employeeCity");
			crit.createAlias("from.reportingTo", "employeeRep");

			crit.createAlias("from.skillId", "employeeSkill");

			crit.createAlias("To.skillId", "employeeSkill2");

			crit.createAlias("To.countryId", "employeeCountR");
			crit.createAlias("To.cityId", "employeeCityR");
			crit.createAlias("To.reportingTo", "employeeRepR");
			crit.add(Restrictions.eq("toDoId", toDoId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ToDoLogsEntity toDo = (ToDoLogsEntity) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(toDo,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				toDoLogs.add(toDo);
			}
			logger.info(String.format("Inside fetchtoDotLogs() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchtoDolLogs()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return toDoLogs;
	}

	private ArrayList<InformationRequestEntity> fetchInformationUserRequest(Employee employeeId) {
		Session session = null;
		ArrayList<InformationRequestEntity> informationRequests = new ArrayList<InformationRequestEntity>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(InformationRequestEntity.class);
			crit.createAlias("contactResponsible", "assignedto");
			crit.createAlias("assignedFrom", "assignedfrom");
			crit.add(Restrictions.eq("contactResponsible.employeeId", employeeId.getEmployeeId()));
			crit.add(Restrictions.eq("read", false));
			crit.createAlias("job", "jobCreation");
			jobsStrategicAlias(crit);
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				InformationRequestEntity informationRequest = (InformationRequestEntity) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest.getAssignedFrom(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest.getContactResponsible(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(informationRequest.getJob(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				informationRequests.add(informationRequest);
			}
			logger.info(String.format("Inside fetchInformationUserRequest() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchInformationUserRequest()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return informationRequests;
	}

	private ArrayList<ToDo> fetchUsersTodos(Employee employeeId) {

		Session session = null;
		ArrayList<ToDo> toDos = new ArrayList<ToDo>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ToDo.class);
			crit.createAlias("assignedTo", "assignedto");
			crit.createAlias("assignedFrom", "assignedfrom");
			crit.createAlias("job", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("assignedto.employeeId", employeeId.getEmployeeId()));
			crit.add(Restrictions.eq("read", false));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ToDo toDo = (ToDo) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(toDo,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(toDo.getAssignedFrom(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(toDo.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(toDo.getJob(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				toDos.add(toDo);
			}
			logger.info(String.format("Inside fetchUsersTodo() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchUsersTodo()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return toDos;
	}

	public int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		Date todaysDate = cal.getTime();
		String date = todaysDate.toString();
		int length = date.length();
		date = date.substring(length - 4, length);
		int currentYear = Integer.parseInt(date);
		return currentYear;
	}

	public ArrayList<Employee> fetchEmployees(int companyId) throws Exception {// Add
		// this
		// Throw
		// Exception
		// in
		// all
		// methods

		Session session = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("cityId", "city");
			crit.createAlias("countryId", "countryId");

			// crit.createAlias("companyId", "company");
			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.ne("employeeId", 0));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Employee employee = (Employee) it.next();
				employees.add(employee);
			}
			logger.info(String.format(companyId + ":" + "inside fetchEmployees()" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchEmployees");// Add
			// this
			// Line
			// Accordingly
			// in all
			// method
		} finally {
			session.close();
		}
		return employees;// Return BEFORE catch Statement..
	}

	public Employee fetchEmployeeById(int employeeId) throws Exception {// Add
		// this
		// Throw
		// Exception
		// in
		// all
		// methods

		Session session = null;
		Employee employee = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);

			crit.createAlias("cityId", "city");
			crit.createAlias("countryId", "countryId");
			// crit.createAlias("userId", "user");
			// crit.createAlias("companyId", "company");
			crit.createAlias("skillId", "skill");
			crit.createAlias("reportingTo", "reporting");
			crit.createAlias("reporting.reportingTo", "reportingrep");
			crit.createAlias("reportingrep.skillId", "reportingrepskill");
			crit.add(Restrictions.eq("employeeId", employeeId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {

				employee = (Employee) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(employee,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						employee.getReportingTo().getReportingTo().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

			}
			logger.info(String.format(
					"(Inside fetchEmployeeById) fetching employee with employeeID: " + employeeId + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);
			throw new Exception("Exception occured in fetchEmployees");// Add
			// this
			// Line
			// Accordingly
			// in all
			// method
		} finally {
			session.close();
		}
		return employee;// Return BEFORE catch Statement..
	}

	public ArrayList<Department> fetchDepartments() {
		Session session = null;
		ArrayList<Department> departments = new ArrayList<Department>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Department.class);
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Department department = (Department) it.next();
				departments.add(department);
			}
			logger.info(String.format("Inside fetchDepartments() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchDepartments", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return departments;
	}

	public JobTimeEstimationDTO fetchJobTime(int jobId, int year, int companyId) {

		Session session = null;
		JobTimeEstimationDTO record = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobTimeEstimation.class);

			// crit.add( Restrictions.eq("jobTimeEstimationId", jobId) );

			crit.add(Restrictions.eq("jobId", jobId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();

			// u fetching from resourceuse table?
			// yes /where its mentioned

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				record = new JobTimeEstimationDTO();
				JobTimeEstimation jobTimeEstimation = (JobTimeEstimation) it.next();
				record.setJobTimeEstimation(jobTimeEstimation);
				HibernateDetachUtility.nullOutUninitializedFields(jobTimeEstimation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				record.setResourceUse(fetchResourceUse(jobId));
			}
			logger.info(String.format("(Inside fetchJobTime) fetching jobtime for job: " + jobId + " of company "
					+ companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchDepartments", ex.getMessage()), ex);
		} finally {
			session.close();
		}

		return record;
	}

	public ArrayList<ResourceUse> fetchResourceUse(int jobId) {

		Session session = null;

		ArrayList<ResourceUse> records = new ArrayList<ResourceUse>();
		try {// come here
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ResourceUse.class);
			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.eq("jobId", jobId));
			crit.setMaxResults(2); // should be equal to num of skills
			// :) new tpo me
			List rsList = crit.list();
			// i think its fine ... continue
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				records.add((ResourceUse) it.next());
			}
			logger.info(String.format("(Inside fetchResourceUse) for Job ID : " + jobId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in resource use", ex.getMessage()), ex);
		} finally {
			session.close();
		}

		return records;
	}

	// public ArrayList<Skills> fetchSkills() {
	// Session session = null;
	// ArrayList<Skills> skills = new ArrayList<Skills>();
	// try{
	// session = sessionFactory.openSession();
	// Criteria crit = session.createCriteria(Skills.class);
	// crit.add(Restrictions.ne("skillId", 0));
	// List rsList = crit.list();
	// for(Iterator it=rsList.iterator();it.hasNext();)
	// {
	// Skills skill = (Skills)it.next();
	// skills.add(skill);
	// }
	// }catch(Exception ex){
	// logger.warn(String.format("Exception occured in fetchSkills",
	// ex.getMessage()), ex);
	//
	// }
	// finally{
	// session.close();
	// }
	// return skills;
	// }

	public ArrayList<Skills> fetchSkills(int year, int companyId) {
		Session session = null;
		ArrayList<Skills> skills = new ArrayList<Skills>();
		ArrayList<CompanySkillRelation> companySkillList = new ArrayList<CompanySkillRelation>();

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Skills.class);
			crit.add(Restrictions.ne("skillId", 0));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Skills skill = (Skills) it.next();
				//////////////////////////////

				Criteria crit1 = session.createCriteria(CompanySkillRelation.class);
				crit1.createAlias("skillId", "skill");
				crit1.add(Restrictions.eq("skill.skillId", skill.getSkillId()));
				crit1.add(Restrictions.eq("year", year));
				crit1.add(Restrictions.eq("companyId", companyId));
				List rsList1 = crit1.list();
				if (rsList1.size() > 0) {
					CompanySkillRelation companySkillRelation = (CompanySkillRelation) rsList1.get(0);
					skill.setCompanySkillRelation(companySkillRelation);
				}

				///////////////////////////////
				skills.add(skill);
			}
			logger.info(String.format("(Inside fetchSkills) fetching skills for year : " + year + " of company "
					+ companyId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchSkills", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return skills;
	}

	public String saveStrategic(Strategic strategic, Employee loggedInUser, HashMap<String, String> hm, int year,
			int companyId) {

		try {
			session = sessionFactory.openSession();
			String todo = hm.get("todo");
			String tab = hm.get("tab");
			saveOneStrategic(strategic, loggedInUser, todo, tab, year, companyId);
			logger.info(
					String.format("(Inside saveStrategic) saving strategic for year : " + year + " of User Logged In "
							+ loggedInUser + " under tab " + tab + " todo: " + todo + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveStrategic", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return "added";
	}

	public Employee fetchSupervisor(int userId) {
		Session session = null;
		Employee employee = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);

			crit.createAlias("skillId", "skill");
			// crit.createAlias("companyId", "company");
			crit.add(Restrictions.eq("employeeId", userId));
			if (crit.list().size() > 0) {
				employee = (Employee) crit.list().get(0);
			}
			logger.info(String
					.format("(Inside fetchSupervisor) fetching suprevisor for User ID : " + userId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchSupervisor", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employee.getReportingTo();
	}

	public void saveOneStrategic(Strategic strategic, Employee loggedInUser, String todo, String tab, int year,
			int companyId) {
		Session session = null;
		Transaction tr = null;
		try {
			int selectedTab = Integer.parseInt(tab);
			session = sessionFactory.openSession();
			tr = (Transaction) session.beginTransaction();
			Strategic clientSideStrategic = strategic;

			if (strategic.getId() == 0) {
				if (todo.equals("save")) {
					initiateStrategic(strategic, loggedInUser, clientSideStrategic, session);
				} else {
					submitStrategic(strategic, loggedInUser, clientSideStrategic, session);
				}
			} else {
				strategic = (Strategic) session.get(Strategic.class, strategic.getId());
				if (todo.equals("amend")) {
					ammendStrategic(strategic, loggedInUser, clientSideStrategic, session);
				} else if (todo.equals("save")) {
					initiateStrategic(strategic, loggedInUser, clientSideStrategic, session);
				} else {
					Employee initiatedBy = strategic.getInitiatedBy();
					// if(strategic.getStatus().equals("submitted")){//CHANGE
					if (strategic.getStatus().equals("submitted") || strategic.getPhase() == 3) {
						approveStrategic(strategic, loggedInUser, clientSideStrategic, initiatedBy, session);

					} else {
						strategic.setAuditableUnit(clientSideStrategic.getAuditableUnit());
						strategic.setProcess(clientSideStrategic.getProcess());
						strategic.setSubProcess(clientSideStrategic.getSubProcess());
						strategic.setJobType(clientSideStrategic.getJobType());
						submitStrategic(strategic, loggedInUser, clientSideStrategic, session);
					}
				}
			}

			strategic.setRating(clientSideStrategic.getRating());
			strategic.setUserDefinedRating(clientSideStrategic.getUserDefinedRating());
			strategic.setComments(clientSideStrategic.getComments());
			strategic.setAuditableUnit(clientSideStrategic.getAuditableUnit());
			strategic.setProcess(clientSideStrategic.getProcess());
			strategic.setSubProcess(clientSideStrategic.getSubProcess());
			strategic.setJobType(clientSideStrategic.getJobType());
			strategic.setStrategicDepartments(clientSideStrategic.getStrategicDepartments());
			strategic.setDate(new Date());
			strategic.setTab(selectedTab);
			strategic.setYear(year); // year Added
			strategic.setCompanyId(companyId); // companyId Added

			// session.saveOrUpdate(strategic.getApprovedBy());
			// session.saveOrUpdate(strategic.getInitiatedBy());
			// session.saveOrUpdate(strategic.getAssignedTo());
			// session.saveOrUpdate(strategic.getObjectiveOwner());

			// Setting default values to jobtype,process,subprocess
			if (strategic.getJobType() == null || strategic.getJobType().getJobTypeId() == 0) {
				JobType jobType = new JobType();
				jobType.setJobTypeId(1);
				strategic.setJobType(jobType);
			}
			if (strategic.getProcess() == null || strategic.getProcess().getProcessId() == 0) {
				Process process = new Process();
				process.setProcessId(1);
				strategic.setProcess(process);
			}
			if (strategic.getSubProcess() == null || strategic.getSubProcess().getSubProcessId() == 0) {
				SubProcess subProcess = new SubProcess();
				subProcess.setSubProcessId(5);
				strategic.setSubProcess(subProcess);
			}
			// end

			session.saveOrUpdate(strategic);

			tr.commit();
			// saveStrategicAudit(strategic);
			if (strategic.getPhase() == 1) {
				saveDepartments(strategic);
			}
			logger.info(String
					.format("(Inside saveOneStrategic) saving one strategic for year : " + year + " of User Logged In "
							+ loggedInUser + " under tab " + tab + " todo: " + todo + " " + new Date()));

		} catch (Exception ex) {
			tr.rollback();
			logger.warn(String.format("Exception occured in saveOneStrategic", ex.getMessage()), ex);

		} finally {
			session.close();
		}
	}

	private void saveDepartments(Strategic strategic) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			// 2019 jul
			// created this deletealreadysaved dep and commented
			// !strategicdepartment method this
			// method deletes first already saved departments and then save the
			// new oness
			deleteAlreadySavedDepartment(strategic.getId());
			for (int i = 0; i < strategic.getStrategicDepartments().size(); i++) {
				strategic.getStrategicDepartments().get(i).setStrategic(strategic.getId());

				// if (!strategicDepartmentAlreadySaved(strategic.getId(),
				// strategic.getStrategicDepartments().get(i).getDepartment().getDepartmentId()))
				// {
				session.saveOrUpdate(strategic.getStrategicDepartments().get(i));
				session.flush();
			}
			// }
			logger.info(String.format("(Inside saveDepartments) saving Departments for strategic objective : "
					+ strategic.getStrategicObjective() + " " + new Date()));
		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in saveDepartmentsStrategic", ex.getMessage()), ex);

		} finally {
			session.close();
		}

	}

	private boolean deleteAlreadySavedDepartment(int strategicId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(StrategicDepartments.class);
			crit.createAlias("department", "dept");
			crit.add(Restrictions.eq("strategic", strategicId));
			// crit.add(Restrictions.eq("dept.departmentId", departmentId));
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicDepartments jobEmployeeRelation = (StrategicDepartments) it.next();
				session.delete(jobEmployeeRelation);
				session.flush();
			}

			logger.info(String
					.format("(Inside strategicDepartmentAlreadySaved) StrategicDepartmentsAlreadySaved for strategicID : "
							+ strategicId + " of department : " + new Date()));
		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in strategicDepartmentAlreadySaved", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return true;
	}

	private boolean strategicDepartmentAlreadySaved(int strategicId, int departmentId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(StrategicDepartments.class);
			crit.createAlias("department", "dept");
			crit.add(Restrictions.eq("strategic", strategicId));
			// crit.add(Restrictions.eq("dept.departmentId", departmentId));

			if (crit.list().size() > 0) {
				return true;
			}

			logger.info(String
					.format("(Inside strategicDepartmentAlreadySaved) StrategicDepartmentsAlreadySaved for strategicID : "
							+ strategicId + " of department : " + departmentId + " " + new Date()));
		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in strategicDepartmentAlreadySaved", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return false;
	}

	public void saveStrategicAudit(Strategic strategic) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			StrategicAudit stragicAudit = new StrategicAudit();
			stragicAudit.setAcheivementDate(strategic.getAcheivementDate());
			stragicAudit.setApprovedBy(strategic.getApprovedBy());
			stragicAudit.setAssignedTo(strategic.getAssignedTo());
			stragicAudit.setAuditableUnit(strategic.getAuditableUnit());
			stragicAudit.setComments(strategic.getComments());
			stragicAudit.setInitiatedBy(strategic.getInitiatedBy());
			stragicAudit.setNextPhase(strategic.getNextPhase() + "");
			// stragicAudit.setObjectiveOwner(strategic.getObjectiveOwner());//
			// HERE Objective owner
			stragicAudit.setPhase(strategic.getPhase() + "");
			stragicAudit.setRating(strategic.getRating());
			stragicAudit.setRelevantDepartment(strategic.getRelevantDepartment());
			RiskFactor riskFactor = (RiskFactor) session.get(RiskFactor.class, strategic.getRiskFactor().getRiskId());
			stragicAudit.setRiskFactor(riskFactor);
			stragicAudit.setStatus(strategic.getStatus());
			stragicAudit.setStrategicObjective(strategic.getStrategicObjective());
			stragicAudit.setDate(strategic.getDate());

			session.save(stragicAudit);
			tr.commit();
			logger.info(String.format("(Inside saveStrategicAudit) saving Strategic Audit for strategic objective : "
					+ strategic.getStrategicObjective() + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveStrategicAudit", ex.getMessage()), ex);

		} finally {
			session.close();
		}
	}

	private void approveStrategic(Strategic strategic, Employee loggedInUser, Strategic clientSideStrategic,
			Employee initiatedBy, Session session) throws Exception {
		strategic.setStatus("initiated");
		strategic.setPhase(clientSideStrategic.getNextPhase());
		strategic.setAssignedTo(initiatedBy);
		strategic.setApprovedBy(loggedInUser);
		// Employee approvedBy = (Employee) session.load(Employee.class, new
		// Integer(loggedInUser.getEmployeeId().getEmployeeId()));
		// Employee approvedBy =
		// fetchEmployeeById(loggedInUser.getEmployeeId().getEmployeeId());
		// strategic.setApprovedBy(approvedBy);
		strategic.setAuditableUnit(clientSideStrategic.getAuditableUnit());
		// strategic.setProcess(clientSideStrategic.getProcess());
		// strategic.setSubProcess(clientSideStrategic.getSubProcess());
		// strategic.setJobType(clientSideStrategic.getJobType());
		// strategic.setObjectiveOwner(clientSideStrategic.getObjectiveOwner());//
		// HERE Objective owner
		strategic.setStrategicObjective(clientSideStrategic.getStrategicObjective());
		strategic.setAcheivementDate(clientSideStrategic.getAcheivementDate());
		strategic.setRelevantDepartment(clientSideStrategic.getRelevantDepartment());
		strategic.setAudit(clientSideStrategic.isAudit());
		logger.info(String.format("(Inside approveStrategic) approve strategic for Looged In User : " + loggedInUser
				+ " initiated by " + initiatedBy.getEmployeeName() + " " + new Date()));
	}

	private void submitStrategic(Strategic strategic, Employee loggedInUser, Strategic clientSideStrategic,
			Session session) {
		strategic.setStatus("submitted");
		Employee supervisor = loggedInUser.getReportingTo();
		strategic.setAssignedTo(supervisor);
		// Employee emp = new Employee();
		// emp.setEmployeeId(0);
		strategic.setApprovedBy((Employee) session.load(Employee.class, 0));
		strategic.setInitiatedBy(loggedInUser);
		strategic.setStrategicObjective(clientSideStrategic.getStrategicObjective());
		strategic.setAudit(clientSideStrategic.isAudit());
		logger.info(String.format("(Inside submitStrategic) submitted strategic " + strategic.getStrategicObjective()
				+ " for Looged In User : " + loggedInUser + " " + new Date()));
	}

	private void initiateStrategic(Strategic strategic, Employee loggedInUser, Strategic clientSideStrategic,
			Session session) {
		strategic.setStatus("saved");
		// Employee emp = new Employee();
		// emp.setEmployeeId(0);
		// strategic.setApprovedBy(emp);
		strategic.setApprovedBy((Employee) session.load(Employee.class, 0));
		strategic.setInitiatedBy(loggedInUser);
		strategic.setAssignedTo(strategic.getInitiatedBy());
		strategic.setStrategicObjective(clientSideStrategic.getStrategicObjective());
		strategic.setAcheivementDate(clientSideStrategic.getAcheivementDate());
		// strategic.setObjectiveOwner(clientSideStrategic.getObjectiveOwner());//
		// HERE Objective owner
		strategic.setRelevantDepartment(clientSideStrategic.getRelevantDepartment());
		strategic.setAudit(clientSideStrategic.isAudit());
		logger.info(String.format("(Inside initiateStrategic) Initiated strategic " + strategic.getStrategicObjective()
				+ " for Looged In User : " + loggedInUser + " " + new Date()));
	}

	private void ammendStrategic(Strategic strategic, Employee loggedInUser, Strategic clientSideStrategic,
			Session session) {
		strategic.setStatus("amend");
		// Employee emp = new Employee();
		// emp.setEmployeeId(0);
		// strategic.setApprovedBy(emp);
		strategic.setApprovedBy((Employee) session.load(Employee.class, 0));
		strategic.setAssignedTo(strategic.getInitiatedBy());
		strategic.setAudit(clientSideStrategic.isAudit());
		logger.info(String.format("(Inside ammendStrategic) Amend strategic : " + strategic.getStrategicObjective()
				+ " for Looged In User : " + loggedInUser + " " + new Date()));
	}

	public ArrayList<RiskFactor> fetchRiskFactors() {
		Session session = null;
		ArrayList<RiskFactor> riskFactors = new ArrayList<RiskFactor>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(RiskFactor.class);
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskFactor riskFactor = (RiskFactor) it.next();
				riskFactors.add(riskFactor);
			}
			logger.info(String.format("(Inside fetchRiskFactors) fetching RiskFactors at : " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchRiskFactors", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return riskFactors;
	}

	public ArrayList<Strategic> fetchStrategic(HashMap<String, String> hm, int employeeId) {
		Session session = null;
		System.out.println("inside fetchStrategic");
		String phase = hm.get("phase");
		int tab = Integer.parseInt(hm.get("tab"));
		int year = Integer.parseInt(hm.get("year"));
		int companyId = Integer.parseInt(hm.get("companyId"));
		int phaseValue = Integer.parseInt(phase);
		ArrayList<Strategic> strategics = new ArrayList<Strategic>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Strategic.class);

			// Disjunction disc = Restrictions.disjunction();
			// disc.add(Restrictions.eq("assigned.employeeId", employeeId));
			// disc.add(Restrictions.eq("initiated.employeeId", employeeId));
			//
			// crit.add(disc);

			strategicAlias(crit);

			/////////////////////
			crit.add(Restrictions.ge("phase", phaseValue));
			crit.add(Restrictions.ne("status", "deleted"));
			crit.add(Restrictions.eq("year", year));// Year check added
			crit.add(Restrictions.eq("companyId", companyId));// companyId check
			// added
			crit.add(Restrictions.ne("status", "deleted"));

			if (phaseValue == 1 || phaseValue == 2) {
				crit.add(Restrictions.eq("tab", tab));
			}

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Strategic strategic = (Strategic) it.next();
				strategic.setStrategicDepartments(fetchStrategicdepartments(strategic, session));
				strategic.setLoggedInUser(employeeId);
				//////////// Dont sent those which are SAVED and are not belong
				//////////// to loggedInUser

				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						strategic.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getProcess(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getJobType(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getSubProcess(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCityId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCountryId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getReportingTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);

				// Do not send those strategics which are Only SAVED and current
				// loggedIn user is not the one who initiated that
				if (strategic.getPhase() > phaseValue) {
					strategics.add(strategic);
				} else {

					if (strategic.getStatus().equalsIgnoreCase("saved")
							&& strategic.getInitiatedBy().getEmployeeId() != employeeId) {
						// DO not send this strategy
						// Do not send those strategics which are Only SUBMITTED
						// and current loggedIn user is not the one who
						// initiated that or the to whoe this is assignd to

					} else if (strategic.getStatus().equalsIgnoreCase("submitted")
							&& (strategic.getAssignedTo().getEmployeeId() != employeeId
									&& strategic.getInitiatedBy().getEmployeeId() != employeeId)) {
						// DO not send this strategy
					} else if (strategic.getStatus().equalsIgnoreCase("initiated")
							&& (strategic.getAssignedTo().getEmployeeId() != employeeId
									&& strategic.getInitiatedBy().getEmployeeId() != employeeId)) {
						// DO not send this strategy
					} else {
						strategics.add(strategic);
					}
				}

			}

			logger.info(String.format(
					"(Inside fetchStrategic) fetching Strategic forEmployee Id : " + employeeId + " " + new Date()));
		} catch (Exception ex) {
			System.out.println("Exception occured in fetchStrategics" + ex.getMessage());
			logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		Collections.reverse(strategics);
		return strategics;
	}

	private ArrayList<StrategicDepartments> fetchStrategicdepartments(Strategic strategic, Session session) {
		ArrayList<StrategicDepartments> strategicDepartments = new ArrayList<StrategicDepartments>();
		try {
			Criteria crit = session.createCriteria(StrategicDepartments.class);
			crit.createAlias("department", "dept");
			crit.add(Restrictions.eq("strategic", strategic.getId()));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicDepartments strategicDept = (StrategicDepartments) it.next();
				strategicDepartments.add(strategicDept);

			}
			logger.info(String.format("(Inside fetchStrategicdepartments) fetching Strategic Department strategic : "
					+ strategic.getStrategicObjective() + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchStrategicdepartments", ex.getMessage()), ex);

		} finally {

		}
		return strategicDepartments;
	}

	public ArrayList<Integer> fetchResourceIds(int jobId) {
		ArrayList<Integer> resourceIds = new ArrayList<Integer>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			// 2019 may
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("strategic.strategicId", jobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				int resourceId = jobEmployeeRelation.getEmployee().getEmployeeId();
				if (!resourceIds.contains(resourceId)) {
					resourceIds.add(resourceId);
				}

			}
			logger.info(String.format(
					"(Inside fetchResourceIds) fetching Resource IDs for Job ID : " + jobId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobResourceId", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return resourceIds;
	}

	public ArrayList<StrategicDTO> fetchSchdulingStrategic(HashMap<String, String> hm, int year, int companyId) {
		Session session = null;
		ArrayList<StrategicDTO> strategics = new ArrayList<StrategicDTO>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Strategic.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("audit", true));
			crit.add(Restrictions.eq("phase", 5));// HERE
			crit.add(Restrictions.eq("approvedByAuditHead", true));

			crit.add(Restrictions.ne("status", "deleted"));
			List rsList = crit.list();
			crit.createAlias("relevantDepartment", "dept");
			ArrayList<String> strategicNames = new ArrayList<String>();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Strategic strategic = (Strategic) it.next();
				StrategicDTO strategicDTO = new StrategicDTO();
				strategicDTO.setAuditableUnit(strategic.getAuditableUnit());
				strategicDTO.setStrategicObjective(strategic.getStrategicObjective());
				strategicDTO.setRiskRating(strategic.getRating());
				// strategicDTO.setDepartmentName(strategic.getRelevantDepartment().getDepartmentName());
				strategicDTO.setTab(strategic.getTab());
				// strategicDTO.setDeptId(strategic.getRelevantDepartment().getDepartmentId());

				strategicDTO.setStrategicId(strategic.getId());
				strategicDTO.setJobName(strategic.getStrategicObjective());
				// strategicDTO.setDepartments(fetchStrategicDepartments(strategicDTO,
				// strategic.getId()));
				if (!strategicNames.contains(strategic.getAuditableUnit())) {
					strategicNames.add(strategic.getAuditableUnit());
					strategics.add(strategicDTO);
				}
			}

			logger.info(String.format("(Inside fetchSchdulingStrategic) fetching scheduling strategic for year : "
					+ year + " of Company ID : " + companyId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return strategics;
	}

	public ArrayList<StrategicDepartments> fetchStrategicDepartments(int strategicId) {

		ArrayList<StrategicDepartments> strategicDeps = new ArrayList<StrategicDepartments>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(StrategicDepartments.class);
			crit.createAlias("department", "dept");
			crit.add(Restrictions.eq("strategic", strategicId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicDepartments strategicDept = (StrategicDepartments) it.next();
				strategicDeps.add(strategicDept);
			}
			logger.info(
					String.format("(Inside fetchStrategicDepartments) fetching strategic department for strategic ID : "
							+ strategicId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchStrategicsdapartments", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return strategicDeps;
	}

	public ArrayList<RiskAssesmentDTO> fetchRiskAssesment(HashMap<String, String> hm, int employeeId, int year,
			int companyId) {

		int tab = Integer.parseInt(hm.get("tab"));
		String phase = hm.get("phase");
		int phaseValue = Integer.parseInt(phase);
		Session session = null;
		ArrayList<RiskAssesmentDTO> riskAssesmentDTOs = new ArrayList<RiskAssesmentDTO>();
		ArrayList<Strategic> strategics = new ArrayList<Strategic>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Strategic.class);

			// crit.createAlias("objectiveOwner", "owner");
			// crit.createAlias("owner.cityId", "city");
			// crit.createAlias("owner.countryId", "country");
			// crit.createAlias("owner.userId", "user");
			// crit.createAlias("owner.departmentId", "departmentOwner");
			// crit.createAlias("owner.reportingTo", "ownRep");
			// crit.createAlias("ownRep.reportingTo", "ownReprep");
			// crit.createAlias("ownRep.userId", "ownRepuserId");
			// crit.createAlias("relevantDepartment", "department");
			// crit.createAlias("riskFactor", "risk");
			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("assignedTo", "assigned");
			crit.createAlias("initiated.skillId", "initatedSkill");

			// crit.createAlias("approvedBy", "approveby");
			// crit.createAlias("approveby.userId", "approvedUser");
			// crit.createAlias("approveby.reportingTo", "approvedReposrting");
			// crit.createAlias("approvedReposrting.userId",
			// "approvedReposrtingUser");

			// crit.add(Restrictions.eq("assigned.employeeId", employeeId));
			// Disjunction disc = Restrictions.disjunction();
			// disc.add(Restrictions.eq("assigned.employeeId", employeeId));
			// disc.add(Restrictions.eq("initiated.employeeId", employeeId));
			//
			// crit.add(disc);
			// crit.add(Restrictions.eq("phase", "RiskAssesment"));
			crit.add(Restrictions.ge("phase", 2));
			crit.add(Restrictions.ne("status", "deleted"));
			crit.add(Restrictions.eq("tab", tab));
			crit.add(Restrictions.eq("year", year));// year added
			crit.add(Restrictions.eq("companyId", companyId));// companyId added

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Strategic strategic = (Strategic) it.next();
				strategic.setLoggedInUser(employeeId);
				RiskAssesmentDTO riskAssesmentDTO = new RiskAssesmentDTO();
				strategics.add(strategic);
				riskAssesmentDTO.setStrategic(strategic);
				riskAssesmentDTO.setStrategicRisks(fetchRiskStrategic(strategic.getId()));

				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);//
				// HERE Objective owner
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCountryId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						strategic.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				if (strategic.getPhase() > phaseValue) {
					riskAssesmentDTOs.add(riskAssesmentDTO);
				} else {
					// Do not send those strategics which are Only SAVED and
					// current loggedIn user is not the one who initiated that
					if (strategic.getStatus().equalsIgnoreCase("saved")
							&& strategic.getInitiatedBy().getEmployeeId() != employeeId) {
						// DO not send this strategy
						// Do not send those strategics which are Only SUBMITTED
						// and current loggedIn user is not the one who
						// initiated that or the to whoe this is assignd to

					} else if (strategic.getStatus().equalsIgnoreCase("submitted")
							&& (strategic.getAssignedTo().getEmployeeId() != employeeId
									&& strategic.getInitiatedBy().getEmployeeId() != employeeId)) {
						// DO not send this strategy
					} else if (strategic.getStatus().equalsIgnoreCase("initiated")
							&& (strategic.getAssignedTo().getEmployeeId() != employeeId
									&& strategic.getInitiatedBy().getEmployeeId() != employeeId)) {
						// DO not send this strategy
					} else {
						riskAssesmentDTOs.add(riskAssesmentDTO);
					}
				}
			}
			logger.info(String.format("(Inside fetchRiskAssesment)Fetching Risk Assesment for year : " + year
					+ " for Employee ID : " + employeeId + " for company ID: " + companyId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetching risk assesment for years", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return riskAssesmentDTOs;
	}

	public ArrayList<StrategicRisk> fetchRiskStrategic(int strategicId) {

		Session session = null;
		ArrayList<StrategicRisk> strategicRisks = new ArrayList<StrategicRisk>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(StrategicRisk.class);
			crit.createAlias("strategicId", "strategic");

			// crit.createAlias("strategic.objectiveOwner", "owner");
			// crit.createAlias("owner.cityId", "city");
			// crit.createAlias("owner.countryId", "country");
			// crit.createAlias("owner.userId", "user");
			// crit.createAlias("owner.departmentId", "departmentOwner");
			// crit.createAlias("owner.reportingTo", "ownRep");
			// crit.createAlias("ownRep.reportingTo", "ownReprep");
			// crit.createAlias("ownRep.userId", "ownRepuserId");
			// crit.createAlias("strategic.relevantDepartment", "department");
			// crit.createAlias("strategic.riskFactor", "risk");
			// crit.createAlias("strategic.initiatedBy", "initiated");
			// crit.createAlias("strategic.assignedTo", "assigned");
			// crit.createAlias("assigned.userId", "assignedUser");
			// crit.createAlias("initiated.userId", "initiatedUser");
			// crit.createAlias("strategic.approvedBy", "approveby");
			// crit.createAlias("approveby.userId", "approvedUser");
			// crit.createAlias("approveby.reportingTo", "approvedReposrting");
			// crit.createAlias("approvedReposrting.userId",
			// "approvedReposrtingUser");

			crit.add(Restrictions.eq("strategic.id", strategicId));
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicRisk strategicRisk = (StrategicRisk) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(strategicRisk,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategicRisk.getStrategicId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo().getCountryId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo().getReportingTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getApprovedBy().getReportingTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getApprovedBy().getReportingTo().getReportingTo().getUserId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);

				strategicRisks.add(strategicRisk);
			}
			logger.info(String.format("(Inside fetchRiskStrategic) fetching Risk Strategic for strategicID : "
					+ strategicId + " " + new Date()));

		} catch (Exception ex) {
			logger.info(String.format("Exception occured in fetchRiskStrategic", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return strategicRisks;

	}

	// public ArrayList<StrategicRisk> fetchRiskStrategicBk(int employeeId){
	//
	// Session session = null;
	// ArrayList<StrategicRisk> strategicRisks = new ArrayList<StrategicRisk>();
	// try{
	// session = sessionFactory.openSession();
	// Criteria crit = session.createCriteria(StrategicRisk.class);
	// crit.createAlias("strategicId", "strategic");
	//
	//
	// crit.createAlias("strategic.objectiveOwner", "owner");
	// crit.createAlias("owner.cityId", "city");
	// crit.createAlias("owner.countryId", "country");
	// crit.createAlias("owner.userId", "user");
	// crit.createAlias("owner.departmentId", "departmentOwner");
	// crit.createAlias("owner.reportingTo", "ownRep");
	// crit.createAlias("ownRep.reportingTo", "ownReprep");
	// crit.createAlias("ownRep.userId", "ownRepuserId");
	// crit.createAlias("strategic.relevantDepartment", "department");
	// crit.createAlias("strategic.riskFactor", "risk");
	// crit.createAlias("strategic.initiatedBy", "initiated");
	// crit.createAlias("strategic.assignedTo", "assigned");
	// crit.createAlias("assigned.userId", "assignedUser");
	// crit.createAlias("initiated.userId", "initiatedUser");
	// crit.createAlias("strategic.approvedBy", "approveby");
	// crit.createAlias("approveby.userId", "approvedUser");
	// crit.createAlias("approveby.reportingTo", "approvedReposrting");
	// crit.createAlias("approvedReposrting.userId", "approvedReposrtingUser");
	//
	// crit.add(Restrictions.eq("assigned.employeeId", employeeId));
	// List rsList = crit.list();
	//
	// for(Iterator it=rsList.iterator();it.hasNext();)
	// {
	// StrategicRisk strategicRisk = (StrategicRisk)it.next();
	// strategicRisks.add(strategicRisk);
	// }
	//
	//
	// }catch(Exception ex){
	// logger.info(String.format("Exception occured in fetchRiskStrategic",
	// ex.getMessage()), ex);
	//
	// }
	// finally{
	// session.close();
	// }
	//
	// return strategicRisks;
	//
	// }

	public void saveStrategicRisk(StrategicRisk strategicRisk) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(StrategicRisk.class);
			crit.createAlias("strategicId", "strategic");
			crit.createAlias("riskFactorId", "riskFactor");
			crit.add(Restrictions.eq("strategic.id", strategicRisk.getStrategicId().getId()));
			crit.add(Restrictions.eq("riskFactor.riskId", strategicRisk.getRiskFactorId().getRiskId()));
			if (crit.list().size() > 0) {
				Transaction tr1 = session.beginTransaction();
				StrategicRisk savedStrategicRisk = (StrategicRisk) crit.list().get(0);
				savedStrategicRisk.setRating(strategicRisk.getRating());
				savedStrategicRisk.setImpact(strategicRisk.getImpact());
				savedStrategicRisk.setProbabality(strategicRisk.getProbabality());
				savedStrategicRisk.setComments(strategicRisk.getComments());
				session.update(savedStrategicRisk);
				tr1.commit();
			} else {
				Transaction tr = session.beginTransaction();
				session.save(strategicRisk);
				tr.commit();
			}
			logger.info(String.format("(Inside saveStrategicRisk) saving StrategicRisk for strategicObjective : "
					+ strategicRisk.getStrategicId().getStrategicObjective() + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchStrategicsRisk", ex.getMessage()), ex);

		} finally {
			session.close();
		}
	}

	public String saveRiskAssesment(ArrayList<StrategicRisk> strategicRisks, Employee loggedInUser,
			HashMap<String, String> hm) {
		String todo = hm.get("todo");
		String tab = hm.get("tab");
		int year = Integer.parseInt(hm.get("year"));
		int companyId = Integer.parseInt(hm.get("companyId"));
		saveOneStrategic(strategicRisks.get(0).getStrategicId(), loggedInUser, todo, tab, year, companyId);

		for (int i = 0; i < strategicRisks.size(); i++) {
			saveStrategicRisk(strategicRisks.get(i));

		}
		logger.info(String.format("(Inside saveRiskAssesment) saving Risk Assesment for year : " + year
				+ "for Company ID : " + companyId + " of User Logged In " + loggedInUser + " under tab " + tab
				+ " todo: " + todo + " " + new Date()));
		return "risk assesment saved";

	}

	public String amendStrategic(Strategic strategic, Employee loggedInUser, int year, int companyId) {
		Session session = null;
		try {

			session = sessionFactory.openSession();
			Transaction tr = (Transaction) session.beginTransaction();
			strategic.setInitiatedBy(loggedInUser);
			Employee supervisor = loggedInUser.getReportingTo();
			strategic.setInitiatedBy(supervisor);
			strategic.setYear(year);// Added year
			strategic.setCompanyId(companyId);// Added company
			session.saveOrUpdate(strategic);
			tr.commit();
			logger.info(String.format("(Inside amendStrategic) Amend Strategic for strategicobjective : "
					+ strategic.getStrategicObjective() + " of Company : " + companyId + " for year : " + year + " "
					+ new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in amendStrategic", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return "strategic updated";
	}

	public String declineStrategic(int strategicId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Strategic strategic = (Strategic) session.get(Strategic.class, strategicId);
			strategic.setStatus("deleted");
			session.update(strategic);
			session.flush();
			logger.info(String.format(
					"(Inside declineStrategic) Decline Strategic for strategID : " + strategicId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in declineStrategic", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return null;
	}

	public ArrayList<StrategicAudit> fetchStrategicAidit(int year, int companyId) {
		Session session = null;
		ArrayList<StrategicAudit> strategicAudits = new ArrayList<StrategicAudit>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(StrategicAudit.class);
			crit.createAlias("objectiveOwner", "owner");
			crit.createAlias("owner.cityId", "city");
			crit.createAlias("owner.countryId", "country");

			crit.createAlias("owner.departmentId", "departmentOwner");
			crit.createAlias("owner.reportingTo", "ownRep");
			crit.createAlias("ownRep.reportingTo", "ownReprep");

			crit.createAlias("relevantDepartment", "department");
			crit.createAlias("riskFactor", "risk");
			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("assignedTo", "assigned");

			crit.createAlias("assigned.reportingTo", "assignedReporting");

			crit.createAlias("initiated.reportingTo", "initiatedReporting");
			crit.createAlias("approvedBy", "approveby");
			crit.createAlias("approveby.reportingTo", "approvedReposrting");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicAudit strategicAudit = (StrategicAudit) it.next();
				strategicAudits.add(strategicAudit);
			}
			logger.info(String.format("(Inside fetchStrategicAidit) Fetching strategic Audit for year : " + year
					+ " of Coompany ID " + companyId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchStrategicAudits", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchStrategicAudits " + ex.getMessage());
		} finally {
			session.close();
		}
		return strategicAudits;
	}

	public ArrayList<DashBoardDTO> fetchDashBoard(Employee loggedInUser, int year, int companyId,
			HashMap<String, String> hm) {

		Session session = null;
		// DashBoardDTO dashBoardDTO = new DashBoardDTO();
		ArrayList<DashBoardDTO> dashBoardDTOs = new ArrayList<DashBoardDTO>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(Strategic.class);
			// crit.createAlias("assignedTo", "assigned");
			// crit.createAlias("assigned.reportingTo", "assignedReporting");
			crit.createAlias("approvedBy", "approveby");
			crit.add(Restrictions.eq("assigned.employeeId", loggedInUser.getEmployeeId()));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.ne("status", "deleted"));
			strategicAlias(crit);
			System.out.println("Above fetchDashboard");
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Strategic strategic = (Strategic) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						strategic.getAssignedTo().getReportingTo().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						strategic.getAssignedTo().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getApprovedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				DashBoardDTO dashboardDTO = new DashBoardDTO();
				dashboardDTO.setAssignedTo(strategic.getAssignedTo().getEmployeeName());
				dashboardDTO.setInitiatedBy(strategic.getInitiatedBy().getEmployeeName());
				dashboardDTO.setObjectiveName(strategic.getAuditableUnit());
				dashboardDTO.setPhase(strategic.getPhase() + "");
				dashboardDTO.setStatus(strategic.getStatus());
				// dashBoardDTO.getStrategics().add(strategic);
				dashBoardDTOs.add(dashboardDTO);
			}
			System.out.println("Above FettchEmployeeRiskForApproval");
			ArrayList<RiskControlMatrixEntity> risks = fetchEmployeeRisksForApproval(year, companyId,
					loggedInUser.getEmployeeId());
			System.out.println("Above insideEmployeeRiskForApproval");
			;
			for (int i = 0; i < risks.size(); i++) {
				DashBoardDTO dashboardDTO = new DashBoardDTO();
				dashboardDTO.setInitiatedBy(risks.get(i).getInitiatedBy().getEmployeeName());
				// dashboardDTO.setObjectiveName(risks.get(i).getDescription());
				if (risks.get(i).getSuggestedControlsId() != null
						&& risks.get(i).getSuggestedControlsId().getRiskId() != null)
					dashboardDTO.setObjectiveName(risks.get(i).getSuggestedControlsId().getRiskId().getRiskname());
				dashboardDTO.setPhase("Risks(Audit Engagement)");
				dashboardDTO.setStatus("submitted");
				dashBoardDTOs.add(dashboardDTO);
			}
			System.out.println("Above fetchEmployeeAuditWorksForapproval");
			ArrayList<AuditWork> auditWorks = fetchEmployeeAuditWorksForapproval(companyId, year,
					loggedInUser.getEmployeeId());
			System.out.println("inside fetchEmployeeAuditWorksForapproval");
			for (int i = 0; i < auditWorks.size(); i++) {

				DashBoardDTO dashboardDTO = new DashBoardDTO();
				dashboardDTO.setInitiatedBy(auditWorks.get(i).getInitiatedBy().getEmployeeName());
				dashboardDTO.setObjectiveName(auditWorks.get(i).getDescription());
				dashboardDTO.setPhase("Audit Work(Audit Engagement)");
				dashboardDTO.setStatus("submitted");
				dashBoardDTOs.add(dashboardDTO);
			}
			System.out.println("Above fetchEmployeeAuditStepsForApproval");
			ArrayList<AuditStep> auditSteps = fetchEmployeeAuditStepsForApproval(year, companyId,
					loggedInUser.getEmployeeId());
			System.out.println("inside fetchEmployeeAuditStepsForApproval");
			for (int i = 0; i < auditSteps.size(); i++) {
				DashBoardDTO dashboardDTO = new DashBoardDTO();
				dashboardDTO.setInitiatedBy(auditSteps.get(i).getInitiatedBy().getEmployeeName());
				dashboardDTO.setObjectiveName(auditSteps.get(i).getConclusion());
				dashboardDTO.setPhase("Audit Step(Audit Engagement)");
				dashboardDTO.setStatus("submitted");
				dashBoardDTOs.add(dashboardDTO);
			}
			System.out.println("Above fetchEmployeeExceptionsForApproval");
			ArrayList<Exceptions> exceptions = fetchEmployeeExceptionsForApproval(year, companyId,
					loggedInUser.getEmployeeId());
			System.out.println("Inside fetchEmployeeExceptionsForApproval");
			for (int i = 0; i < exceptions.size(); i++) {
				DashBoardDTO dashboardDTO = new DashBoardDTO();
				dashboardDTO.setInitiatedBy("Audit team");
				dashboardDTO.setObjectiveName(exceptions.get(i).getDetail());
				dashboardDTO.setPhase("Exception(Reporting)");
				dashboardDTO.setStatus("sent");
				dashBoardDTOs.add(dashboardDTO);
			}
			logger.info(String.format("(Inside fetchDashBoard) Fetching dashboard for user :" + loggedInUser.getEmail()
					+ " for year : " + year + " of Company ID " + companyId + " " + new Date()));
		}

		catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchDashBoard", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchDashBoard " + ex.getMessage());
		} finally {
			session.close();
		}
		System.out.println("DASHBOARD DTO : " + dashBoardDTOs);
		return dashBoardDTOs;
	}

	public ArrayList<Strategic> fetchFinalAuditables(int year, int companyId) {

		Session session = null;
		ArrayList<Strategic> strategics = new ArrayList<Strategic>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Strategic.class);
			// crit.createAlias("objectiveOwner", "owner");
			// crit.createAlias("owner.cityId", "city");
			// crit.createAlias("owner.countryId", "country");
			// crit.createAlias("owner.userId", "user");
			// crit.createAlias("owner.departmentId", "departmentOwner");
			// crit.createAlias("owner.reportingTo", "ownRep");
			// crit.createAlias("ownRep.reportingTo", "ownReprep");
			// crit.createAlias("ownRep.userId", "ownRepuserId");
			// crit.createAlias("relevantDepartment", "department");
			// crit.createAlias("riskFactor", "risk");
			// crit.createAlias("initiatedBy", "initiated");
			// crit.createAlias("assignedTo", "assigned");
			// crit.createAlias("assigned.userId", "assignedUser");
			// crit.createAlias("assigned.reportingTo", "assignedReporting");
			// crit.createAlias("initiated.userId", "initiatedUser");
			// crit.createAlias("initiated.reportingTo", "initiatedReporting");
			// crit.createAlias("initiatedReporting.userId",
			// "initiatedReportingUser");
			// crit.createAlias("approvedBy", "approveby");
			// crit.createAlias("approveby.userId", "approvedUser");
			// crit.createAlias("approveby.reportingTo", "approvedReposrting");
			// crit.createAlias("approveby.countryId", "approvedCountry");
			// crit.createAlias("approveby.rollId", "approvedRoll");
			// crit.createAlias("approveby.skillId", "approvedSkill");
			// crit.createAlias("approvedReposrting.userId",
			// "approvedReposrtingUser");
			crit.add(Restrictions.ne("status", "deleted"));
			crit.add(Restrictions.eq("audit", true));
			crit.add(Restrictions.eq("status", "initiated"));
			crit.add(Restrictions.eq("phase", 5));

			crit.add(Restrictions.eq("year", year));// yr added
			crit.add(Restrictions.eq("companyId", companyId));// yr added

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Strategic strategic = (Strategic) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				// Added 2018
				strategic.setJobCreationId(fetchJobCreationIdAgainstStrategin(strategic.getId()));
				// end
				strategics.add(strategic);

			}
			logger.info(String.format("(Inside fetchFinalAuditables) fetching final auditabled for year : " + year
					+ " for company: " + companyId + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchFinalAuditables", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return strategics;
	}

	private int fetchJobCreationIdAgainstStrategin(int strategicId) {
		JobCreation jobCreation = null;
		try {

			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.createAlias("strategicId", "strategic");
			crit.add(Restrictions.eq("strategic.strategicId", strategicId));
			if (crit.list().size() > 0)
				jobCreation = (JobCreation) crit.list().get(0);
			else
				return strategicId;

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobCreationIdAgainstStrategin", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobCreation.getJobCreationId();

	}

	public boolean saveJobTimeEstimation(JobTimeEstimationDTO entity, ArrayList<SkillUpdateData> updateForSkills,
			int year, int companyId) {

		Session session = null;
		try {

			session = sessionFactory.openSession();

			// JobTimeEstimationDTO prev =
			// fetchJobTime(entity.getJobTimeEstimation().getJobId());

			Transaction tr = session.beginTransaction();

			// check if enough hours are available
			for (int i = 0; i < updateForSkills.size(); i++) {
				updateSkillData(updateForSkills.get(i), year, companyId);
			}

			// checks if enough Employees are available to get the job done
			for (int i = 0; i < entity.getResourceUse().size(); ++i) {

				if (!checkEnoughResources(entity.getResourceUse().get(i))) {

					return false;
				}
			}
			JobTimeEstimation jobTimeEstimation = entity.getJobTimeEstimation();
			jobTimeEstimation.setYear(year);
			jobTimeEstimation.setCompanyId(companyId);
			session.saveOrUpdate(jobTimeEstimation);

			// impl save or update.
			tr.commit();

			for (int i = 0; i < entity.getResourceUse().size(); ++i) {
				entity.getResourceUse().get(i).setCompanyId(companyId);
				entity.getResourceUse().get(i).setYear(year);
				entity.getResourceUse().get(i).setJobId(entity.getJobTimeEstimation().getJobId());
				saveResourceNum(entity.getResourceUse().get(i), year);
			}

			logger.info(String.format("(Inside saveJobTimeEstimation) saving job time estimation for year : " + year
					+ " for company: " + companyId + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in save job estimation", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return true;
	}

	private boolean checkEnoughResources(ResourceUse resourceUse) {

		Session session = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("cityId", "city");
			crit.createAlias("countryId", "countryId");
			// crit.createAlias("companyId", "company");

			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.eq("skill.skillId", resourceUse.getSkill().getSkillId()));

			int resources = crit.list().size();

			logger.info(String.format("(Inside saveJobTimeEstimation) checking enough resources for resourceUse : "
					+ resourceUse.getSkill().getSkillName() + " " + new Date()));

			return resources >= resourceUse.getNoOfResources();

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return false;

	}

	// private boolean updateSkillData(SkillUpdateData skillUpdateData) {
	//
	// Session session =null;
	// try{
	//
	// //impl save or update
	// session = sessionFactory.openSession();
	//
	// Criteria crit = session.createCriteria(Skills.class);
	//
	//
	// crit.add( Restrictions.eq("skillId", skillUpdateData.getSkillId()) );
	//
	// List<Skills> rsList = crit.list();
	//
	// Transaction tr = session.beginTransaction();
	//
	// for(Iterator<Skills> it = rsList.iterator(); it.hasNext() ; )
	// {
	// Skills skillPrev = ( ( Skills ) it.next() );
	//
	// if ( skillPrev != null) //update
	// {
	// if ( skillPrev.getAvailableHours() < skillUpdateData.getHoursToSubt())
	// {
	// System.out.println("More than available hours asked");
	// return false;
	// }
	// skillPrev.setAvailableHours( skillPrev.getAvailableHours() +
	// skillUpdateData.getHoursToAdd() );
	//
	// skillPrev.setAvailableHours( skillPrev.getAvailableHours() -
	// skillUpdateData.getHoursToSubt() );
	//
	// session.saveOrUpdate(skillPrev);
	// tr.commit();
	//
	// return true;
	// }
	//
	// }
	//
	// // jNow IT hours are 17 .. now add another resource with IT hours more
	// than 17
	// // then it should stop the user..
	//
	// }catch(Exception ex){
	// logger.warn(String.format("Exception occured in saveResourceNum",
	// ex.getMessage()), ex);
	//
	// }
	// finally{
	// session.close();
	// }
	//
	// return false;
	//
	// }

	private boolean updateSkillData(SkillUpdateData skillUpdateData, int year, int companyId) {

		Session session = null;
		try {

			// impl save or update
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(CompanySkillRelation.class);

			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.eq("skill.skillId", skillUpdateData.getSkillId()));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List<CompanySkillRelation> rsList = crit.list();

			Transaction tr = session.beginTransaction();

			for (Iterator<CompanySkillRelation> it = rsList.iterator(); it.hasNext();) {
				CompanySkillRelation skillPrev = ((CompanySkillRelation) it.next());

				if (skillPrev != null) // update
				{
					if (skillPrev.getAvailableHours() < skillUpdateData.getHoursToSubt()) {
						System.out.println("More than available hours asked");
						return false;
					}
					skillPrev.setAvailableHours(skillPrev.getAvailableHours() + skillUpdateData.getHoursToAdd());

					skillPrev.setAvailableHours(skillPrev.getAvailableHours() - skillUpdateData.getHoursToSubt());

					session.saveOrUpdate(skillPrev);
					tr.commit();
					logger.info(String.format("(Inside SkillUpdateData) updating skills for year : " + year
							+ " for company: " + companyId + " " + new Date()));

					return true;
				}

			}

			// jNow IT hours are 17 .. now add another resource with IT hours
			// more than 17
			// then it should stop the user..

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in skillUpdateData", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return false;

	}

	private boolean saveResourceNum(ResourceUse entity, int year) {

		Session session = null;
		try {

			// impl save or update
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(ResourceUse.class);

			crit.createAlias("skillId", "skills");

			crit.add(Restrictions.eq("jobId", entity.getJobId()));
			crit.add(Restrictions.eq("skills.skillId", entity.getSkill().getSkillId()));

			List<ResourceUse> rsList = crit.list();

			Transaction tr = session.beginTransaction();

			for (Iterator<ResourceUse> it = rsList.iterator(); it.hasNext();) {

				ResourceUse prev = ((ResourceUse) it.next());

				if (prev != null) // update
				{
					prev.setNoOfResources(entity.getNoOfResources());
					prev.setYear(year);
					prev.setCompanyId(entity.getCompanyId());
					session.saveOrUpdate(prev);

					tr.commit();

					return true;
				}

				// save new

			}
			session.save(entity);
			tr.commit();

			logger.info(String.format("(Inside saveResourceNum) saving Respurses for year : " + year + "skill"
					+ entity.getSkill().getSkillName() + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveResourceNum", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return false;
	}

	public ArrayList<ResourceUse> fetchResourceUseFor(int jobId, int year, int companyId) {

		Session session = null;
		ArrayList<ResourceUse> records = new ArrayList<ResourceUse>();

		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(ResourceUse.class);
			// sould this be jobId// yes ::(
			crit.add(Restrictions.eq("jobId", jobId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("skillId", "skill");
			List<ResourceUse> rsList = crit.list();

			for (Iterator<ResourceUse> it = rsList.iterator(); it.hasNext();) {

				records.add((ResourceUse) it.next());
			}

			logger.info(String.format("(Inside fetchResourceUseFor) fetching ResourceUseFor for year : " + year
					+ "vfor job" + jobId + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchResourceUseFor ", ex.getMessage()), ex);
		} finally {
			session.close();
		}
		// come here on debug..
		return records;
	}

	public ArrayList<Employee> fetchEmployeesByDeptId(ArrayList<Integer> depIds, int companyId) {
		Session session = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("skillId", "skill");
			crit.createAlias("countryId", "countryId");
			crit.add(Restrictions.in("skill.skillId", depIds));// lets see what
			// it
			crit.add(Restrictions.ne("employeeId", 0));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("cityId", "city");
			// crit.createAlias("companyId", "company");
			crit.createAlias("reportingTo", "reporting");
			crit.createAlias("reporting.skillId", "reportingSkill");
			crit.createAlias("reporting.reportingTo", "reportingTot");
			crit.add(Restrictions.ne("rollId", 1));
			crit.add(Restrictions.ne("rollId", 5));
			crit.add(Restrictions.ne("rollId", 4));

			List rsList = crit.list();// .. ?run
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Employee employee = (Employee) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(employee,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				employees.add(employee);
			}

			logger.info(String.format("(Inside fetchEmployeesByDeptId)  fetching employs for depid : " + depIds
					+ "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeesByDeptId", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employees;
	}

	public ArrayList<Integer> fetchJobSkills(int jobId, int companyId) {
		Session session = null;
		ArrayList<Integer> skills = new ArrayList<Integer>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ResourceUse.class);
			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.eq("jobId", jobId));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.ne("noOfResources", 0));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ResourceUse resourceUse = (ResourceUse) it.next();
				int skillId = resourceUse.getSkill().getSkillId();
				skills.add(skillId);
			}

			logger.info(String.format("(Inside fetchJobSkills)fetching job skills for job : " + jobId + "for company"
					+ companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobSkills", ex.getMessage()), ex);

		}

		finally {
			session.close();
		}
		return skills;
	}

	public ArrayList<Employee> fetchEmployeesBySkillId(int jobId, int companyId) {
		Session session = null;
		ArrayList<Integer> skills = fetchJobSkills(jobId, companyId);
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("skillId", "skill");
			crit.createAlias("countryId", "countryId");
			crit.add(Restrictions.in("skill.skillId", skills));// lets see what
			// it
			crit.add(Restrictions.ne("employeeId", 0));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("cityId", "city");
			// crit.createAlias("companyId", "company");
			crit.createAlias("reportingTo", "reporting");
			crit.createAlias("reporting.skillId", "reportingSkill");
			crit.createAlias("reporting.reportingTo", "reportingTot");
			crit.add(Restrictions.ne("rollId", 1));
			// crit.add(Restrictions.ne("roll.rollId", 5));
			crit.add(Restrictions.ne("rollId", 4));

			List rsList = crit.list();// .. ?run
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Employee employee = (Employee) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(employee,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				employees.add(employee);
			}

			logger.info(String.format("(Inside fetchEmployeesBySkillId)fetching employs by skills for job : " + jobId
					+ "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeesBySkillId", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employees;
	}

	public void saveJobAndAreaOfExpertiseState(ArrayList<JobAndAreaOfExpertise> state) {

		Session session = null;
		try {

			for (int j = 0; j < state.size(); ++j) {
				session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				JobAndAreaOfExpertise jobAndAreaOfExpertise = state.get(j);
				Criteria crit = session.createCriteria(JobAndAreaOfExpertise.class);
				crit.createAlias("areaOfExpertiseId", "areaOfExpertise");
				crit.add(Restrictions.eq("jobId", jobAndAreaOfExpertise.getJobId()));
				crit.add(Restrictions.eq("areaOfExpertise.departmentId",
						jobAndAreaOfExpertise.getAreaOfExpertise().getDepartmentId()));

				if (crit.list().size() > 0) {
					List rsList = crit.list();
					JobAndAreaOfExpertise jobAndAreaOfExpertisePrevious = null;
					for (Iterator it = rsList.iterator(); it.hasNext();) {
						jobAndAreaOfExpertisePrevious = (JobAndAreaOfExpertise) it.next();
					}
					jobAndAreaOfExpertisePrevious.setIsChecked(jobAndAreaOfExpertise.getIsChecked());
					session.update(jobAndAreaOfExpertisePrevious);
				} else {

					session.save(jobAndAreaOfExpertise);

				}
				tr.commit();
			}

			logger.info(String
					.format("(Inside saveJobAndAreaOfExpertiseState)saving job and area of expertise state for state : "
							+ state + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveJobAndAreaOfExpertiseState", ex.getMessage()), ex);

		} finally {
			session.close();
		}

	}

	public ArrayList<JobAndAreaOfExpertise> fetchCheckBoxStateFor(int jobId) {
		// this is the code .. in your auditPanel mehtiod
		Session session = null;
		ArrayList<JobAndAreaOfExpertise> checkBoxStates = new ArrayList<JobAndAreaOfExpertise>();

		try {

			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(JobAndAreaOfExpertise.class);
			crit.createAlias("areaOfExpertiseId", "areaOfExpertise");
			crit.add(Restrictions.eq("jobId", jobId));

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobAndAreaOfExpertise j = (JobAndAreaOfExpertise) it.next();
				checkBoxStates.add(j);
			}

			logger.info(String.format("(Inside fetchCheckBoxStateForcheckingbox) fetching Boxstate for job : " + jobId
					+ " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchCheckBoxStateFor", ex.getMessage()), ex);

		}

		finally {
			session.close();
		}

		return checkBoxStates;

	}

	private boolean allRolesSelected(ArrayList<JobEmployeeRelation> jobEmployeeList) {
		ArrayList<Integer> roles = new ArrayList<Integer>();
		for (JobEmployeeRelation jobEmployeeRelation : jobEmployeeList) {
			int roleId = fetchEmployeeRoleId(jobEmployeeRelation.getEmployee().getEmployeeId());
			roles.add(roleId);
		}
		if (roles.contains(5) && (roles.contains(1) || roles.contains(2) || roles.contains(3)))
			return true;
		else
			return false;
	}

	private int fetchEmployeeRoleId(int employeeId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Employee employee = (Employee) session.get(Employee.class, employeeId);
			return employee.getRollId();

		} catch (Exception ex) {
			return 0;
		}
	}

	public String saveCreatedJob(JobCreationDTO job, int year, int companyId) throws Exception {

		Session session = null;
		try {
			session = sessionFactory.openSession();
			if (!allRolesSelected(job.getRelation())) {
				return InternalAuditConstants.SELECTALLROLES;
			}

			deletePreviousResourcesOnThisJob(job.getJob().getJobCreationId());
			Transaction tr = session.beginTransaction();
			JobCreation jobCreation = job.getJob();
			// check below line
			jobCreation.setRelevantDept(job.getDepartments().get(0).getDepartment().getDepartmentName());
			jobCreation.setYear(year);
			jobCreation.setCompanyId(companyId);
			int jobCreationId = fetchJobCreationId(jobCreation.getStrategicId().getId());// if
			// job
			// is
			// aready
			// created
			jobCreation.setJobCreationId(jobCreationId);
			session.saveOrUpdate(jobCreation);
			session.flush();
			tr.commit();
			Transaction tr1 = session.beginTransaction();
			for (int i = 0; i < job.getRelation().size(); ++i) {
				job.getRelation().get(i).setJobCreationId(jobCreation);
				saveJobEmployeeRelation(job.getRelation().get(i));
			}

			for (int i = 0; i < job.getJobSkillRelation().size(); ++i) {
				if (i == 0) {
					deletePreviousSkillsOnThisJob(job.getJobSkillRelation().get(0).getJobId());
				}
				saveJobSkillRelation(job.getJobSkillRelation().get(i));

				// tr1.commit();
			}
			tr1.commit();
			logger.info(String.format("(Inside saveCreatedJob) saving created job for year : " + year + "for company"
					+ companyId + "for jobName" + job.getJob().getJobName() + " " + new Date()));

		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in saving created job", ex.getMessage()), ex);
			throw new Exception("Exception occured in saving created job");
		} finally {
			session.close();
		}

		return "Job Saved";
	}

	private void deletePreviousSkillsOnThisJob(int jobId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobSkillRelation.class);
			// crit.createAlias("jobCreationId", "jobCreation");
			// jobsStrategicAlias(crit);
			// crit.createAlias("employeeId", "employee");
			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobSkillRelation jobSkillRelation = (JobSkillRelation) it.next();
				session.delete(jobSkillRelation);
				session.flush();
			}

			logger.info(
					String.format("(Inside deletePreviousJobSkillRelationOnThisJob) deleting JobSkillRelation job : "
							+ jobId + " " + new Date()));

		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in deletePreviousJobSkillRelationOnThisJob", ex.getMessage()),
					ex);
			System.out.println("Exception occured in deletePreviousJobSkillRelationOnThisJob");

		} finally {

		}

	}

	private void deletePreviousResourcesOnThisJob(int jobId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("employeeId", "employee");
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				session.delete(jobEmployeeRelation);
				session.flush();
			}

			logger.info(String.format("(Inside deletePreviousResourcesOnThisJob) deleting resource for job : " + jobId
					+ " " + new Date()));

		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in deletePreviousResourcesOnThisJob", ex.getMessage()), ex);
			System.out.println("Exception occured in deletePreviousResourcesOnThisJob");

		} finally {

		}

	}

	private int fetchJobCreationId(int jobId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			crit.createAlias("strategicId", "strategic");

			crit.add(Restrictions.eq("strategic.strategicId", jobId));
			if (crit.list().size() > 0) {

				JobCreation job = (JobCreation) crit.list().get(0);
				return job.getJobCreationId();
			}

			logger.info(String.format(
					"(Inside fetchJobCreationId) fetching jobcreationId for job : " + jobId + " " + new Date()));

		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in fetchJobCreationId", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return 0;
	}

	private boolean saveJobEmployeeRelation(JobEmployeeRelation jobEmployeeRelation) {

		Session session = null;
		try {
			// job.setEmployeeId(new Employee());

			session = sessionFactory.openSession();
			if (jobEmployeeRelation.getId() == 0) {
				session.save(jobEmployeeRelation);
				session.flush();
			} else {
				session.update(jobEmployeeRelation);
				session.flush();
			}

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saving emp relation job", ex.getMessage()), ex);
			return false;
		} finally {
			session.close();

		}

		return true;

	}

	private boolean saveJobSkillRelation(JobSkillRelation rel) {

		Session session = null;
		try {
			// job.setEmployeeId(new Employee());

			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			session.saveOrUpdate(rel);
			tr.commit();

			logger.info(String.format("(Inside saveJobSkillRelation) saving JobSkillRelation for skill name : "
					+ rel.getSoftskills().getSoftSkillName() + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saving skill relation job", ex.getMessage()), ex);
			return false;
		} finally {
			session.close();

		}

		return true;

	}

	public ArrayList<JobCreationDTO> fetchCreatedJobs(boolean getEmpRelation, boolean getSkillRelation, int year,
			int companyId) {
		Session session = null;
		ArrayList<JobCreationDTO> jobs = new ArrayList<JobCreationDTO>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreationDTO jobDTO = new JobCreationDTO();
				// jobDTO.setDepartments(fetchStrategicDepartments(jobDTO,
				// jobDTO.getJob().getJobCreationId()));

				jobDTO.setJob((JobCreation) it.next());

				// get emp associated with this job

				if (getEmpRelation) {
					jobDTO.setRelation(fetchEmployeeJobRelations(jobDTO.getJob().getJobCreationId()));
				}

				if (getSkillRelation) {
					jobDTO.setJobSkillRelation(fetchJobSkillRelations(jobDTO.getJob().getJobCreationId()));
				}

				// get skills reqd for this job
				jobs.add(jobDTO);
			}
			logger.info(
					String.format("(Inside fetchCreatedJobs) fetching employrelation/skillrelation jobs  for year : "
							+ year + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobs;
	}

	public JobCreationDTO fetchCreatedJob(int id, boolean getEmpRelation, boolean getSkillRelation, String idType) {
		Session session = null;
		JobCreationDTO job = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			// crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			/// crit.createAlias("strategicId", "strategicId");

			crit.add(Restrictions.eq("strategic.strategicId", id));

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				job = new JobCreationDTO();

				job.setJob((JobCreation) it.next());

				// get emp associated with this job

				if (getEmpRelation) {
					job.setRelation(fetchEmployeeJobRelations(job.getJob().getJobCreationId()));
				}

				if (getSkillRelation) {
					job.setJobSkillRelation(fetchJobSkillRelations(job.getJob().getJobCreationId()));
				}

			}
			logger.info(String.format("(Inside fetchCreatedJobs) fetching created jobs  for id : " + id + "for idtype"
					+ idType + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return job;
	}

	public JobCreation fetchCreatedJob(int jobcreationId) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			crit.add(Restrictions.eq("jobCreationId", jobcreationId));
			jobsStrategicAlias(crit);

			List rsList = crit.list();

			// for (Iterator it = rsList.iterator(); it.hasNext();)
			JobCreation jobCreation = (JobCreation) rsList.get(0);
			// ADDED 19-7-2018 to have process ,subprocess,jobtypes in aud eng
			Strategic strategic = fetchStrategicAgainstStrategicId(jobCreation.getStrategicId().getId(), session);
			jobCreation.setStrategic(strategic);
			HibernateDetachUtility.nullOutUninitializedFields(jobCreation,
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(jobCreation.getStrategicId().getRelevantDepartment(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);

			logger.info(String.format("(Inside fetchCreatedJobs) fetching created jobs  for creatuinid : "
					+ jobcreationId + "" + new Date()));
			return jobCreation;
			// return (JobCreation) it.next();

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return null;
	}

	private Strategic fetchStrategicAgainstStrategicId(int strategicId, Session session) {
		Strategic strategic = null;
		try {
			Criteria crit = session.createCriteria(Strategic.class);
			crit.add(Restrictions.eq("id", strategicId));
			strategicAlias(crit);
			strategic = (Strategic) crit.list().get(0);

			HibernateDetachUtility.nullOutUninitializedFields(strategic,
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCountryId(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getReportingTo(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(
					strategic.getInitiatedBy().getReportingTo().getReportingTo(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);

			HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCityId(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);

			HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCountryId(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getProcess(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getJobType(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);
			HibernateDetachUtility.nullOutUninitializedFields(strategic.getSubProcess(),
					HibernateDetachUtility.SerializationType.SERIALIZATION);

		} catch (Exception ex) {

		}
		return strategic;
	}

	private void strategicAlias(Criteria crit) {
		crit.createAlias("initiatedBy", "initiated");
		crit.createAlias("assignedTo", "assigned");
		crit.createAlias("assigned.reportingTo", "assignedReporting");
		crit.createAlias("process", "processId");
		crit.createAlias("subProcess", "subProcessId");
		crit.createAlias("jobType", "jobTypeId");

		crit.createAlias("initiated.skillId", "initatedSkill");
	}

	public ArrayList<JobEmployeeRelation> fetchEmployeeJobRelations(int jobCreationId) {
		Session session = null;
		ArrayList<JobEmployeeRelation> relations = new ArrayList<JobEmployeeRelation>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);

			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			// crit.createAlias("jobCreationId", "jobcreation");
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobCreationId));

			crit.createAlias("employeeId", "employee");
			crit.createAlias("employee.cityId", "employeeCity");
			crit.createAlias("employee.countryId", "employeeCount");
			crit.createAlias("employee.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("employee.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation job = (JobEmployeeRelation) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(job,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getEmployee().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getEmployee().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getEmployee().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getEmployee().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				relations.add(job);
			}

			logger.info(String
					.format("(Inside fetchEmployeeJobRelations) fetching EmployeeJobRelations  for jobcreationid : "
							+ jobCreationId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return relations;
	}

	private ArrayList<JobSkillRelation> fetchJobSkillRelations(int jobCreationId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<JobsOfEmployee> fetchEmployeesWithJobs(int companyId) {
		Session session = null;

		ArrayList<JobsOfEmployee> employees = new ArrayList<JobsOfEmployee>();

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("cityId", "city");
			crit.createAlias("countryId", "countryId");
			// crit.createAlias("companyId", "company");
			crit.createAlias("reportingTo", "report");
			crit.createAlias("report.skillId", "reportSKill");
			crit.createAlias("report.reportingTo", "reportreportingTo");
			crit.createAlias("reportreportingTo.skillId", "reporreportingTotSKill");

			crit.createAlias("skillId", "skill");

			/// Change here
			crit.add(Restrictions.ne("rollId", 1));
			// crit.add(Restrictions.ne("roll.rollId", 4));
			crit.add(Restrictions.ne("rollId", 4));
			crit.add(Restrictions.eq("companyId", companyId));
			////
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobsOfEmployee jobForEmp = new JobsOfEmployee();

				Employee employee = (Employee) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(employee,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				jobForEmp.setEmployee(employee);

				jobForEmp.setJobs(getAllJobsForEmployee(employee.getEmployeeId()));

				employees.add(jobForEmp);
			}
			logger.info(String.format("(Inside fetchEmployeesWithJobs) fetching  EmployeesWithJobs  for company : "
					+ companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employees;
	}

	private ArrayList<JobCreation> getAllJobsForEmployee(int employeeId) {
		Session session = null;
		ArrayList<JobCreation> relations = new ArrayList<JobCreation>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			// crit.add(Restrictions.eq("employeeId", employeeId));
			crit.createAlias("employeeId", "employee");
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("employee.employeeId", employeeId));
			crit.createAlias("employee.countryId", "employeeCount");
			crit.createAlias("employee.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");

			// crit.createAlias("employeeUser.countryId", "employeeUCount");

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation job = (JobEmployeeRelation) it.next();
				TimeLineDates dates = getMonthsInvolved(job.getJobCreationId().getStartDate(),
						job.getJobCreationId().getEndDate());
				job.getJobCreationId().setTimeLineDates(dates);
				HibernateDetachUtility.nullOutUninitializedFields(job,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				relations.add(job.getJobCreationId());

			}

			logger.info(String.format("(Inside getAllJobsForEmployee) getting   AllJobsForEmployee  for employe : "
					+ employeeId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return relations;
	}

	private JobCreation fetchSelectedJob(int jobId) {
		Session session = null;
		JobCreation job = null;
		try {
			session = sessionFactory.openSession();
			job = (JobCreation) session.get(JobCreation.class, jobId);

			logger.info(String
					.format("(Inside fetchSelectedJob) fetching   slelctedJob  for job : " + jobId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchSelectedJob", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return job;
	}

	public JobCreation updateEndDateForJob(int jobId, String startDate, String endDate) throws Exception {

		ArrayList<EmployeeJobDTO> employeeJobDTO = fetchEmployeeAvailabality(jobId, startDate, endDate);

		Session session = null;
		JobCreation prevCreated = null;
		try {

			session = sessionFactory.openSession();

			prevCreated = fetchCreatedJob(jobId);

			if (prevCreated != null) {
				prevCreated.setEndDate(endDate);
				prevCreated.setStartDate(startDate);
				TimeLineDates dates = getMonthsInvolved(prevCreated.getStartDate(), prevCreated.getEndDate());
				prevCreated.setTimeLineDates(dates);
			}

			Transaction tr = session.beginTransaction();
			prevCreated.setEmployeeJobDTO(employeeJobDTO);
			session.saveOrUpdate(prevCreated);
			tr.commit();

			logger.info(String.format("(Inside updateEndDateForJob) updating    EndDateForJob  for job : " + jobId
					+ "startdate" + startDate + "endDate" + endDate + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  updating date", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return prevCreated;
	}

	private ArrayList<EmployeeJobDTO> fetchEmployeeAvailabality(int jobId, String startDate, String endDate)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yy");
		Date start = format.parse(startDate);
		Date end = format.parse(endDate);
		ArrayList<EmployeeJobDTO> employeeJobDTOs = new ArrayList<EmployeeJobDTO>();

		ArrayList<Employee> employeesOnThisJobs = fetchEmpForSelectedJob(jobId);
		for (int i = 0; i < employeesOnThisJobs.size(); i++) {
			ArrayList<Integer> jobOfThisEmployee = fetchjobEmployeeOtherThanGiveOne(
					employeesOnThisJobs.get(i).getEmployeeId(), jobId);
			for (int j = 0; j < jobOfThisEmployee.size(); j++) {
				JobCreation job = fetchSelectedJob(jobOfThisEmployee.get(j));
				if (startDate != null && job.getStartDate() != null) {
					Date jobStart = format.parse(job.getStartDate());
					Date jobEnd = format.parse(job.getEndDate());
					if ((jobStart.equals(start) || jobStart.after(start)) && jobStart.before(end)
							|| start.after(jobStart) && start.before(jobEnd)) {
						EmployeeJobDTO employeeJobDTO = new EmployeeJobDTO();
						employeesOnThisJobs.get(i).getEmployeeName();

						employeeJobDTO.setEmployeeName(employeesOnThisJobs.get(i).getEmployeeName());
						employeeJobDTO.setEndDate(job.getEndDate());
						employeeJobDTO.setSatrtDate(job.getStartDate());
						employeeJobDTO.setJobName(job.getJobName());
						if (!(employeesOnThisJobs.get(i).getRollId() == 1)) {
							employeeJobDTOs.add(employeeJobDTO);
						}
					}
				}

			}
		}
		return employeeJobDTOs;
	}

	public ArrayList<AuditEngagement> fetchAllAuditEngagement(int loggedInEmployee, int year, int companyId) {
		Session session = null;
		ArrayList<AuditEngagement> records = new ArrayList<AuditEngagement>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			//
			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.cityId", "cityId");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiatedRep.cityId", "initiatedRCity");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			ArrayList<Integer> jobIds = fetchjobEmployee(loggedInEmployee);
			crit.createAlias("jobCreationId", "jobCreation");
			// jobsStrategicAlias(crit);
			////////////////////////////////////////////////////////
			/*
			 * crit.createAlias("jobCreation.strategicId", "strategic");
			 * crit.createAlias("strategic.process", "processId");
			 * crit.createAlias("strategic.subProcess", "subProcessId");
			 * crit.createAlias("strategic.jobType", "jobTypeId");
			 * crit.createAlias("strategic.relevantDepartment", "dept");
			 * crit.createAlias("strategic.riskFactor", "riskFact");
			 * 
			 * crit.createAlias("strategic.initiatedBy", "sinitiated");
			 * crit.createAlias("strategic.assignedTo", "sassigned");
			 * crit.createAlias("strategic.approvedBy", "sapproved");
			 * crit.createAlias("sassigned.userId", "sassigendCdUser");
			 * crit.createAlias("sassigned.rollId", "sassigendCRoll");
			 * crit.createAlias("sassigned.skillId", "sassigendCSkill");
			 */
			/////////////////////////////////////////////////////////

			//////////// FETCHING ONLY JOBS OF LOGGEDIN EMPLOYEE
			if (jobIds.size() <= 0) {
				return null;
			}
			Disjunction disc = Restrictions.disjunction();
			for (int i = 0; i < jobIds.size(); i++) {
				disc.add(Restrictions.eq("jobCreation.jobCreationId", jobIds.get(i)));
			}
			crit.add(disc);

			//////////
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditEngagement auditEngagement = (AuditEngagement) it.next();
				// ADDED 19-7-2018 to have process ,subprocess,jobtypes in aud
				// eng
				// Strategic strategic = (Strategic)
				// session.get(Strategic.class,
				// auditEngagement.getJobCreation().getJobId()) ;
				// auditEngagement.setStrategic(strategic);
				// Strategic strategic =
				// fetchStrategicAgainstStrategicId(auditEngagement.getJobCreation().getJobId(),
				// session);
				// auditEngagement.setStrategic(strategic);
				// EngagementDTO engagementDTO =
				// fetchActivityObjective(strategic.getSubProcess().getSubProcessId(),
				// session);
				// auditEngagement.setEngagementDTO(engagementDTO);
				/////
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getJobCreation(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getJobCreation().getStrategicId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditEngagement.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				records.add(auditEngagement);

			}

			logger.info(
					String.format("(Inside fetchAllAuditEngagement) fetching AllAuditEngagement  for loggedinemploye : "
							+ loggedInEmployee + "for company" + companyId + "for job" + jobIds + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchAllAuditEngagement", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return records;
	}

	private EngagementDTO fetchActivityObjective(int subProcessId, Session session) {
		EngagementDTO engagementDTO = new EngagementDTO();
		// ArrayList<ActivityObjective> listObjective = new
		// ArrayList<ActivityObjective>();
		try {
			Criteria crit = session.createCriteria(ActivityObjective.class);
			crit.createAlias("subProcessId", "subProcess");
			crit.add(Restrictions.eq("subProcess.subProcessId", subProcessId));
			crit.add(Restrictions.eq("checked", false));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ActivityObjective activityObjective = (ActivityObjective) it.next();

				// move this to be from Selected activityObjectives
				// fetchActivityRiskAgainstActivityObjective(activityObjective.getObjectiveId(),
				// session, engagementDTO);

				engagementDTO.getActivityObjectiveList().add(activityObjective);
			}
		} catch (Exception ex) {
			System.out.println("error in fetchSuggestedControlsAgainstRiskObjective " + ex);
		}
		return engagementDTO;
	}

	// This will All Risks for all SELECTED objectives
	private void fetchActivityRiskAgainstActivityObjective(int objectiveId, Session session,
			EngagementDTO engagementDTO) {

		try {
			Criteria crit = session.createCriteria(RiskObjective.class);
			crit.createAlias("objectiveId", "objective");
			crit.add(Restrictions.eq("objective.objectiveId", objectiveId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskObjective riskObjective = (RiskObjective) it.next();
				// move to call from saved risks
				// fetchSuggestedControlsAgainstRiskObjective(riskObjective.getRiskId(),
				// session, engagementDTO);
				engagementDTO.getRiskObjectiveList().add(riskObjective);
			}
		} catch (Exception ex) {
			System.out.println("error in fetchActivityRiskAgainstActivityObjective " + ex);
		}

	}

	// all Controls in a library against saved Risks
	private void fetchSuggestedControlsAgainstRiskObjective(int riskId, Session session, EngagementDTO engagementDTO) {

		try {
			Criteria crit = session.createCriteria(SuggestedControls.class);
			crit.createAlias("riskId", "risk");
			crit.add(Restrictions.eq("risk.riskId", riskId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				SuggestedControls suggestedControls = (SuggestedControls) it.next();

				engagementDTO.getSuggestedControlsList().add(suggestedControls);
			}
		} catch (Exception ex) {
			System.out.println("error in fetchSuggestedControlsAgainstRiskObjective " + ex);
		}

	}

	private void fetchAuditProgramsAgainstConstrol(int controlId, Session session, EngagementDTO engagementDTO) {

		try {
			Criteria crit = session.createCriteria(AuditProgramme.class);
			crit.createAlias("suggestedControlsId", "suggestedControl");
			crit.add(Restrictions.eq("suggestedControl.suggestedControlsId", controlId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditProgramme auditProgramme = (AuditProgramme) it.next();
				engagementDTO.getAuditProgrammeList().add(auditProgramme);
			}
		} catch (Exception ex) {
			System.out.println("error in fetchAuditProgramsAgainstConstrol " + ex);
		}

	}

	public AuditEngagement fetchAuditEngagement(int jobCreationId, int year, int companyId) {
		Session session = null;
		AuditEngagement record = null;

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");

			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiatedRep.cityId", "initiatedRCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");

			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);

			// crit.createAlias("initiated.userId", "initiatedUser");
			// crit.createAlias("initiated.skillId", "initatedSkill");
			// crit.createAlias("initiated.rollId", "initatedRoll");

			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobCreationId));
			crit.add(Restrictions.eq("year", year));

			crit.add(Restrictions.eq("companyId", companyId));

			List rsList = crit.list();
			AuditEngagement auditEngagement = null;
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				// return (AuditEngagement)it.next( ) ;
				auditEngagement = (AuditEngagement) it.next();

				// ADDED 19072018
				// Library's
				Strategic strategic = fetchStrategicAgainstStrategicId(
						auditEngagement.getJobCreation().getStrategicId().getId(), session);
				auditEngagement.setStrategic(strategic);
				EngagementDTO engagementDTO = fetchActivityObjective(strategic.getSubProcess().getSubProcessId(),
						session);
				// AcitivityObjectives of the selected job
				// User's
				engagementDTO.setSelectedActivityObjectives(
						fetchActivityObjectivesForSelectedJob(jobCreationId, session, engagementDTO));
				engagementDTO.setSelectedObjectiveRisks(
						fetchObjectiveRisksForSelectedJob(jobCreationId, session, engagementDTO));
				engagementDTO.setSelectedControls(
						fetchControlsAgainstEngagementId(auditEngagement.getAuditEngId(), engagementDTO));

				// END

				auditEngagement.setEngagementDTO(engagementDTO);

				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditEngagement.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getJobCreation().getStrategic(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getEngagementDTO(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
			}

			logger.info(String.format("(Inside fetchAuditEngagement) fetching AuditEngagement  for jobcreationId : "
					+ jobCreationId + "for company" + companyId + "for year" + year + " " + new Date()));

			return auditEngagement;

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchAuditEngagement", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return record;
	}

	// This will All Objectives for a selected job
	private ArrayList<ActivityObjective> fetchActivityObjectivesForSelectedJob(int jobCreationId, Session session,
			EngagementDTO engagementDTO) {
		ArrayList<ActivityObjective> objectivesList = new ArrayList<ActivityObjective>();
		try {
			Criteria crit = session.createCriteria(ObjectiveJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");

			jobsStrategicAlias(crit);

			crit.createAlias("objectiveId", "objective");

			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobCreationId));

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ObjectiveJobRelation objectiveJobRelation = (ObjectiveJobRelation) it.next();
				ActivityObjective activityObjective = objectiveJobRelation.getObjectiveId();
				activityObjective.setActivityJobRelation(objectiveJobRelation.getActivityJobId());
				activityObjective.setStatus(objectiveJobRelation.getStatus());

				fetchActivityRiskAgainstActivityObjective(activityObjective.getObjectiveId(), session, engagementDTO);
				objectivesList.add(activityObjective);
			}

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchActivityObjectivesForSelectedJob", ex.getMessage()),
					ex);

		} finally {

		}
		return objectivesList;

	}

	private void jobsStrategicAliasSmall(Criteria crit) {
		crit.createAlias("jobCreation.strategicId", "strategic");
		crit.createAlias("strategic.process", "processId");
		crit.createAlias("strategic.subProcess", "subProcessId");
		crit.createAlias("strategic.jobType", "jobTypeId");
		crit.createAlias("strategic.relevantDepartment", "dept");
		crit.createAlias("strategic.riskFactor", "riskFact");

	}

	private void jobsStrategicAliasSmall1(Criteria crit) {
		crit.createAlias("jobCreation.strategicId", "strategic");
		crit.createAlias("strategic.process", "processId");
		crit.createAlias("strategic.subProcess", "subProcessId");
		crit.createAlias("strategic.jobType", "jobTypeId");
		crit.createAlias("strategic.relevantDepartment", "dept");
		crit.createAlias("strategic.riskFactor", "riskFact");

		crit.createAlias("strategic.initiatedBy", "sinitiated");
		crit.createAlias("strategic.assignedTo", "sassigned");
		crit.createAlias("strategic.approvedBy", "sapproved");

		crit.createAlias("sassigned.countryId", "sassigendCount");
		crit.createAlias("sassigned.cityId", "sassigendCCity");
		crit.createAlias("sassigned.reportingTo", "sassigendCdRep");

		crit.createAlias("sassigned.skillId", "sassigendCSkill");

		crit.createAlias("sinitiated.countryId", "sinitiatedCount");
		crit.createAlias("sinitiated.cityId", "sinitiatedCity");
		crit.createAlias("sinitiated.reportingTo", "sinitiatedRep");

		crit.createAlias("sinitiated.skillId", "sinitiatedSkill");

		crit.createAlias("sapproved.countryId", "sapprovedCount");
		crit.createAlias("sapproved.cityId", "sapprovedCity");
		crit.createAlias("sapproved.reportingTo", "sapprovedRep");

		crit.createAlias("sapproved.skillId", "sapprovedSkill");

	}

	private void jobsStrategicAlias(Criteria crit) {
		crit.createAlias("jobCreation.strategicId", "strategic");
		crit.createAlias("strategic.process", "processId");
		crit.createAlias("strategic.subProcess", "subProcessId");
		crit.createAlias("strategic.jobType", "jobTypeId");
		crit.createAlias("strategic.relevantDepartment", "dept");
		crit.createAlias("strategic.riskFactor", "riskFact");

		crit.createAlias("strategic.initiatedBy", "sinitiated");
		crit.createAlias("strategic.assignedTo", "sassigned");
		crit.createAlias("strategic.approvedBy", "sapproved");

		crit.createAlias("sassigned.countryId", "sassigendCount");
		crit.createAlias("sassigned.cityId", "sassigendCCity");
		crit.createAlias("sassigned.reportingTo", "sassigendCdRep");

		crit.createAlias("sassigned.skillId", "sassigendCSkill");

		crit.createAlias("sinitiated.countryId", "sinitiatedCount");
		crit.createAlias("sinitiated.cityId", "sinitiatedCity");
		crit.createAlias("sinitiated.reportingTo", "sinitiatedRep");

		crit.createAlias("sinitiated.skillId", "sinitiatedSkill");

		crit.createAlias("sapproved.countryId", "sapprovedCount");
		crit.createAlias("sapproved.cityId", "sapprovedCity");
		crit.createAlias("sapproved.reportingTo", "sapprovedRep");

		crit.createAlias("sapproved.skillId", "sapprovedSkill");

	}

	private void jobsStrategicAliasbk(Criteria crit) {
		crit.createAlias("jobCreation.strategicId", "strategic");
		crit.createAlias("strategic.initiatedBy", "sinitiated");
		crit.createAlias("strategic.assignedTo", "sassigned");
		crit.createAlias("strategic.relevantDepartment", "dept");
		crit.createAlias("strategic.riskFactor", "riskFact");

		/*
		 * crit.createAlias("initiated.countryId", "initiatedCount");
		 * crit.createAlias("initiated.cityId", "initiatedCity");
		 * crit.createAlias("initiated.reportingTo", "initiatedRep");
		 * crit.createAlias("initiated.userId", "initiatedUser");
		 * crit.createAlias("initiated.rollId", "initiatedRoll");
		 * crit.createAlias("initiated.skillId", "initiatedSkill");
		 */

		crit.createAlias("sassigned.countryId", "sassigendCount");
		crit.createAlias("sassigned.cityId", "sassigendCCity");
		crit.createAlias("sassigned.reportingTo", "sassigendCdRep");

		crit.createAlias("sassigned.skillId", "sassigendCSkill");

		crit.createAlias("sinitiated.countryId", "sinitiatedCount");
		crit.createAlias("sinitiated.cityId", "sinitiatedCity");
		crit.createAlias("sinitiated.reportingTo", "sinitiatedRep");
		crit.createAlias("sinitiated.skillId", "sinitiatedSkill");

		crit.createAlias("strategic.process", "processId");
		crit.createAlias("strategic.subProcess", "subProcessId");
		crit.createAlias("strategic.jobType", "jobTypeId");
	}

	// This will All Selected/Saved Risks for a selected job (users
	// library)
	private ArrayList<RiskObjective> fetchObjectiveRisksForSelectedJob(int jobCreationId, Session session,
			EngagementDTO engagementDTO) {
		ArrayList<RiskObjective> risksList = new ArrayList<RiskObjective>();
		try {
			Criteria crit = session.createCriteria(RiskJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("riskObjective", "risk");

			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobCreationId));

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskJobRelation riskJobRelation = (RiskJobRelation) it.next();
				RiskObjective riskObjective = riskJobRelation.getRiskObjective();
				fetchSuggestedControlsAgainstRiskObjective(riskObjective.getRiskId(), session, engagementDTO);
				riskObjective.setRiskJobRelation(riskJobRelation.getRiskjobrelationId());
				riskObjective.setStatus(riskJobRelation.getStatus());
				risksList.add(riskObjective);
			}

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchObjectiveRisksForSelectedJob", ex.getMessage()), ex);

		} finally {

		}
		return risksList;

	}

	public boolean updateAuditEngagement(AuditEngagement e, String fieldToUpdate, int year, int companyId) {

		Session session = null;

		try {

			session = sessionFactory.openSession();

			AuditEngagement prevCreated = fetchAuditEngagement(e.getJobCreation().getJobCreationId(), year, companyId);

			if (prevCreated != null) {
				// check which field needs to be updated

				if ("jobstatus".equals(fieldToUpdate)) {

					prevCreated.setJobStatus(e.getJobStatus());
					prevCreated.setYear(year);
					prevCreated.setCompanyId(companyId);
				}

				else if ("objectives".equals(fieldToUpdate)) {

					prevCreated.setAssignmentObj(e.getAssignmentObj());

					// else if ( "activityObj".equals(fieldToUpdate))

					prevCreated.setActivityObj(e.getActivityObj());

					prevCreated.setProcess(e.getProcess());
					prevCreated.setStatus(e.getStatus());

				}
			}
			if (e.getInitiatedBy() != null) {
				prevCreated.setInitiatedBy((Employee) session.get(Employee.class, e.getInitiatedBy().getEmployeeId()));
			}
			if (e.getApprovedBy() != null) {
				prevCreated.setApprovedBy((Employee) session.get(Employee.class, e.getApprovedBy().getEmployeeId()));
			}
			Transaction tr = session.beginTransaction();
			session.saveOrUpdate(prevCreated);
			tr.commit();
			logger.info(String.format("(Inside updateAuditEngagement) updating AuditEngagement  for employe name : "
					+ e.getInitiatedBy().getEmployeeName() + "for company" + companyId + "for year" + year + " "
					+ new Date()));

			return true;

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  updating date", ex.getMessage()), ex);
			return false;
		} finally {
			session.close();
		}
	}

	private boolean saveAuditEngagement(AuditEngagement record) {

		Session session = null;
		try {

			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();

			record.setCc("");
			record.setTo("");
			record.setAuditNotification("");
			record.setActivityObj("");
			record.setAssignmentObj("");
			record.setProcess("");
			record.setReferenceNo("");
			record.setSubject("");
			record.setFrom("");

			//// change here
			record.setApprovedBy((Employee) session.get(Employee.class, 0));
			record.setInitiatedBy((Employee) session.get(Employee.class, 0));
			///
			session.save(record);
			tr.commit();

			logger.info(String.format("(Inside saveAuditEngagement) saving AuditEngagement  for process : "
					+ record.getProcess() + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saving skill relation job", ex.getMessage()), ex);
			return false;
		} finally {
			session.close();

		}

		return true;

	}

	public void syncAuditEngagementWithCreatedJobs(int loggedInEmployee, int year, int companyId) {

		/*
		 * Ok, here we need to do.
		 * 
		 * Take id's of jobcreation. then check if auditengement has at least
		 * one record with that jobcreationid.
		 * 
		 * If not, then we take that jobcreation id and put that in
		 * auditengagement table
		 * 
		 */

		ArrayList<Integer> jobIds = fetchjobEmployee(loggedInEmployee);

		Session session = null;

		try {

			session = sessionFactory.openSession();

			Criteria critJobCreation = session.createCriteria(JobCreation.class, "job");
			critJobCreation.add(Restrictions.eq("year", year));
			critJobCreation.add(Restrictions.eq("companyId", companyId));
			critJobCreation.setProjection(Projections.property("job.jobCreationId"));
			critJobCreation.add(Restrictions.eq("approved", true));

			List<Integer> idsJob = critJobCreation.list();

			Criteria critAuditEng = session.createCriteria(AuditEngagement.class, "auditEng");
			critAuditEng.add(Restrictions.eq("year", year));
			critAuditEng.add(Restrictions.eq("companyId", companyId));
			critAuditEng.createAlias("auditEng.jobCreationId", "jobCreation");
			jobsStrategicAlias(critAuditEng);
			////////// FETCHING ONLY JOBS OF LOGGEDIN EMPLOYEE
			if (jobIds.size() <= 0) {
				return;
			}
			Disjunction disc = Restrictions.disjunction();
			for (int i = 0; i < jobIds.size(); i++) {
				disc.add(Restrictions.eq("jobCreation.jobCreationId", jobIds.get(i)));
			}
			critAuditEng.add(disc);

			//////////
			critAuditEng.setProjection(Projections.property("jobCreation.jobCreationId"));

			List<Integer> idsEng = critAuditEng.list();

			// filter those jobcreationid's which are NOT in auditEngage table

			ArrayList<Integer> needToBeSynced = new ArrayList<Integer>();

			for (Iterator<Integer> i = idsJob.iterator(); i.hasNext();) {
				int currentId = i.next();

				if (!idsEng.contains(currentId))
					needToBeSynced.add(currentId);

			}

			for (int i = 0; i < needToBeSynced.size(); ++i) {
				AuditEngagement newRecord = new AuditEngagement();

				newRecord.setJobStatus("Not Started");
				newRecord.setYear(year);
				newRecord.setCompanyId(companyId);
				JobCreation jobCreation = new JobCreation();
				jobCreation.setJobCreationId(needToBeSynced.get(i));
				newRecord.setJobCreation(jobCreation);
				if (!auditEngagemtAlreadysaved(jobCreation.getJobCreationId())) {
					saveAuditEngagement(newRecord);
				}

			}

			// Transaction tr = session.beginTransaction();
			//
			// tr.commit();
			//

			logger.info(String
					.format("(Inside syncAuditEngagementWithCreatedJobs) syncing AuditEngagementWithCreatedJobs  for logggedinemploye : "
							+ loggedInEmployee + "for year" + year + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  updating date", ex.getMessage()), ex);

		} finally {
			session.close();
		}

	}

	private boolean auditEngagemtAlreadysaved(int jobCreationId) {
		Session session = null;
		boolean alreadySaved = false;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			crit.createAlias("jobCreationId", "jobCreation");
			// jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobCreationId));
			if (crit.list().size() > 0) {
				alreadySaved = true;
			} else {
				alreadySaved = false;
			}
			logger.info(String
					.format("(Inside auditEngagemtAlreadysaved) checking auditEngagemtAlreadysaved  for jobcreationid : "
							+ jobCreationId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in auditEngagemtAlreadysaved", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return alreadySaved;

	}

	private void saveRisk(RiskControlMatrixEntity risk) {

		Session session = null;
		try {

			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();

			if (risk.getInitiatedBy() == null) {
				risk.setInitiatedBy((Employee) session.get(Employee.class, risk.getInitiatedBy().getEmployeeId()));
				risk.setDate(new Date());
			}
			if (risk.getApprovedBy() != null) {
				risk.setApprovedBy((Employee) session.get(Employee.class, risk.getApprovedBy().getEmployeeId()));
				risk.setDate(new Date());
			}

			session.saveOrUpdate(risk.getSuggestedControlsId());
			session.saveOrUpdate(risk);
			tr.commit();

			logger.info(String.format("(Inside saveRisk)  savingRisk  for employenames : "
					+ risk.getApprovedBy().getEmployeeName() + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saving skill relation job", ex.getMessage()), ex);

		} finally {
			session.close();

		}
	}

	public boolean saveRisks(ArrayList<RiskControlMatrixEntity> records, int year, int companyId) {

		for (RiskControlMatrixEntity r : records) {
			r.setYear(year);
			r.setCompanyId(companyId);

			saveRisk(r);

		}

		return true;

	}

	public boolean sendEmail(String body, String sendTo, String cc, String subject) {

		final String username = "hyphenconsult@gmail.com";
		final String password = "ilzhkshpmtqduzuc";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		javax.mail.Session sessionMail = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(sessionMail);
			message.setFrom(new InternetAddress("hyphenconsult@gmail.com"));
			if (cc.equals("")) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
			} else {
				//////
				String addresses[] = { sendTo, cc };

				InternetAddress[] addressTo = new InternetAddress[addresses.length];
				for (int i = 0; i < addresses.length; i++) {
					addressTo[i] = new InternetAddress(addresses[i]);
				}

				message.setRecipients(Message.RecipientType.TO, addressTo);
				/////
			}

			message.setSubject(subject);
			message.setContent(body, "text/html");

			Transport.send(message);

			System.out.println("email sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return false;
	}

	public void sendAttachmentEmail(String body, String sendTo, String cc, String subject, String filePath,
			String from) {
		try {

			final String username = "hyphenconsult@gmail.com";
			final String password = "ilzhkshpmtqduzuc";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(username, from));

			msg.setReplyTo(InternetAddress.parse(username, false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo, false));
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment

			if (filePath != null) {
				messageBodyPart = new MimeBodyPart();
				String filename = filePath;
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
			}

			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RiskControlMatrixEntity> fetchRisks(int auditEngId, int year, int companyId) {
		Session session = null;
		ArrayList<RiskControlMatrixEntity> record = new ArrayList<RiskControlMatrixEntity>();

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(RiskControlMatrixEntity.class);

			// crit.add(Restrictions.eq("auditEngageId", auditEngId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("employeeRep.cityId", "employeeRCity");

			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");

			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.createAlias("auditEngageId", "audEng");
			crit.add(Restrictions.eq("audEng.auditEngId", auditEngId));
			///
			crit.createAlias("audEng.jobCreationId", "jobCreation");
			jobsStrategicAliasSmall(crit);

			crit.createAlias("audEng.approvedBy", "approvedEng");
			crit.createAlias("approvedEng.countryId", "employeeCounteng");
			crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			crit.createAlias("employeeRepeng.countryId", "employeeRCounteng");

			crit.createAlias("approvedEng.skillId", "employeeSkilleng");
			crit.createAlias("employeeRepeng.skillId", "employeeRepSkilleng");

			crit.createAlias("audEng.initiatedBy", "initiatedeng");
			crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			crit.createAlias("initiatedRepeng.countryId", "initiatedRCounteng");
			crit.createAlias("initiatedeng.skillId", "initiatedSkilleng");
			crit.createAlias("initiatedRepeng.skillId", "initiatedRepSkilleng");

			crit.createAlias("suggestedControlsId", "suggestedControls");
			crit.createAlias("suggestedControls.riskId", "risk");

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskControlMatrixEntity risk = (RiskControlMatrixEntity) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(risk,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						risk.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getAuditEngageId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getSuggestedControlsId().getRiskId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				record.add(risk);
			}

			logger.info(String.format("(Inside fetchRisks)  fetching Risks  for auditengid : " + auditEngId + "for year"
					+ year + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchRisks", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return record;
	}

	////
	public ArrayList<SuggestedControls> fetchControlsAgainstEngagementId(int auditEngId, EngagementDTO engagementDTO) {
		Session session = null;
		ArrayList<SuggestedControls> record = new ArrayList<SuggestedControls>();

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(RiskControlMatrixEntity.class);

			// crit.add(Restrictions.eq("auditEngageId", auditEngId));
			crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("employeeRep.cityId", "employeeRCity");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.createAlias("auditEngageId", "audEng");
			crit.add(Restrictions.eq("audEng.auditEngId", auditEngId));
			///
			crit.createAlias("audEng.jobCreationId", "audJobCreation");

			crit.createAlias("audEng.approvedBy", "approvedEng");
			crit.createAlias("approvedEng.countryId", "employeeCounteng");
			crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			crit.createAlias("employeeRepeng.countryId", "employeeRCounteng");
			crit.createAlias("approvedEng.skillId", "employeeSkilleng");
			crit.createAlias("employeeRepeng.skillId", "employeeRepSkilleng");

			crit.createAlias("audEng.initiatedBy", "initiatedeng");
			crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			crit.createAlias("initiatedRepeng.countryId", "initiatedRCounteng");
			crit.createAlias("initiatedeng.skillId", "initiatedSkilleng");
			crit.createAlias("initiatedRepeng.skillId", "initiatedRepSkilleng");

			crit.createAlias("suggestedControlsId", "suggestedControls");
			crit.createAlias("suggestedControls.riskId", "risk");

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskControlMatrixEntity risk = (RiskControlMatrixEntity) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(risk,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						risk.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getAuditEngageId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getSuggestedControlsId().getRiskId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				fetchAuditProgramsAgainstConstrol(risk.getSuggestedControlsId().getSuggestedControlsId(), session,
						engagementDTO);

				record.add(risk.getSuggestedControlsId());
			}

			logger.info(
					String.format("(Inside fetchRisks)  fetching Risks  for auditengid : " + auditEngId + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchRisks", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return record;
	}

	public ArrayList<RiskControlMatrixEntity> fetchEmployeeRisksForApproval(int year, int companyId, int employeeId) {
		Session session = null;
		ArrayList<RiskControlMatrixEntity> record = new ArrayList<RiskControlMatrixEntity>();

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(RiskControlMatrixEntity.class);

			// crit.add(Restrictions.eq("auditEngageId" , auditEngId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("status", InternalAuditConstants.SUBMIT));
			// 2019 april
			crit.createAlias("suggestedControlsId", "suggested");
			crit.createAlias("suggested.riskId", "riskid");

			// end
			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("employeeRep.cityId", "employeeRCity");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.createAlias("auditEngageId", "audEng");
			///
			crit.createAlias("audEng.jobCreationId", "audJobCreation");

			crit.createAlias("audEng.approvedBy", "approvedEng");
			crit.createAlias("approvedEng.countryId", "employeeCounteng");
			crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			crit.createAlias("employeeRepeng.countryId", "employeeRCounteng");
			crit.createAlias("approvedEng.skillId", "employeeSkilleng");
			crit.createAlias("employeeRepeng.skillId", "employeeRepSkilleng");

			crit.createAlias("audEng.initiatedBy", "initiatedeng");
			crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			crit.createAlias("initiatedRepeng.countryId", "initiatedRCounteng");
			crit.createAlias("initiatedeng.skillId", "initiatedSkilleng");
			crit.createAlias("initiatedRepeng.skillId", "initiatedRepSkilleng");

			crit.add(Restrictions.eq("initiatedRep.employeeId", employeeId));

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskControlMatrixEntity risk = (RiskControlMatrixEntity) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(risk,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						risk.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(risk.getAuditEngageId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				record.add(risk);
			}

			logger.info(
					String.format("(Inside fetchEmployeeRisksForApproval) fetchEmployeeRisksForApproval for employ : "
							+ employeeId + "for year" + year + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchEmployeeRisksForApproval", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return record;
	}

	public ArrayList<Employee> fetchEmpForThisJob(int selectedJobId) {
		Session session = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("cityId", "city");
			crit.createAlias("countryId", "countryId");
			// crit.createAlias("companyId", "company");

			crit.createAlias("skillId", "skill");
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Employee employee = (Employee) it.next();
				employees.add(employee);
			}

			logger.info(String.format("(Inside fetchEmpForThisJob) fetching EmpForThisJob for selectedjob : "
					+ selectedJobId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmpForThisJob", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employees;
	}

	public ArrayList<Employee> fetchEmpForSelectedJob(int selectedJobId) {
		Session session = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			// crit.createAlias("cityId", "city");
			// crit.createAlias("countryId", "countryId");
			// // crit.createAlias("companyId", "company");
			// crit.createAlias("rollId", "roll");
			//
			// crit.createAlias("userId", "user");
			// crit.createAlias("skillId", "skill");
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("employeeId", "emp");

			crit.add(Restrictions.eq("jobCreation.jobCreationId", selectedJobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				employees.add(jobEmployeeRelation.getEmployee());
			}

			logger.info(String.format("(Inside fetchEmpForSelectedJob) fetching EmpForSelectedJob for selectedjob : "
					+ selectedJobId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmpForSelectedJob", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employees;
	}

	public ArrayList<JobCreation> fetchJobs(int year, int companyId) {
		Session session = null;
		ArrayList<JobCreation> jobsList = new ArrayList<JobCreation>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreation jobCreation = (JobCreation) it.next();
				if (!(jobCreation.getStartDate() == null)) {
					TimeLineDates dates = getMonthsInvolved(jobCreation.getStartDate(), jobCreation.getEndDate());
					jobCreation.setTimeLineDates(dates);
				}
				jobCreation.setReportStatus(fetchJobExceptionStatus(jobCreation.getJobCreationId()));
				HibernateDetachUtility.nullOutUninitializedFields(jobCreation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				jobsList.add(jobCreation);
			}

			logger.info(String.format("(Inside fetchJobs) fetching jobs for year : " + year + "for company" + companyId
					+ " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobsList;
	}

	public TimeLineDates getMonthsInvolved(String startDate, String endDate) {

		TimeLineDates timeLineDates = new TimeLineDates();
		if (startDate == null || endDate == null) {
			timeLineDates.setEndWeek(0);
			timeLineDates.setStartWeek(0);
			timeLineDates.setFormattedEndDate("");
			timeLineDates.setFormattedStartDat("");
			return timeLineDates;
		}

		Date end = null;
		Date start = null;
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yy");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");

		try {
			end = fmt.parse(endDate);
			start = fmt.parse(startDate);

			logger.info(String.format("(Inside getMonthsInvolved) getting MonthsInvolved for startdate : " + startDate
					+ "for end date" + endDate + " " + new Date()));

		} catch (ParseException e) {
			// logger.warn(String.format("Exception occured in
			// getMonthsInvolved", ex.getMessage()), ex);

			e.printStackTrace();
		}

		String formattedStartDate = format.format(start);
		String formattedEndDate = format.format(end);

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		int startWeek = c.get(Calendar.WEEK_OF_YEAR);
		c.setTime(end);
		int endWeek = c.get(Calendar.WEEK_OF_YEAR);

		timeLineDates.setEndWeek(endWeek);
		timeLineDates.setStartWeek(startWeek);
		timeLineDates.setFormattedEndDate(formattedEndDate);
		timeLineDates.setFormattedStartDat(formattedStartDate);
		return timeLineDates;

	}

	private int fetchJobExceptionStatus(int jobId) {
		Session session = null;
		ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
		int status = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			// jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exception = (Exceptions) it.next();
				exceptions.add(exception);
			}

			for (int i = 0; i < exceptions.size(); i++) {
				if (exceptions.get(i).getInitialStatus() == null
						|| !exceptions.get(i).getInitialStatus().equalsIgnoreCase("Approved")) {
					// &&( exceptions.get(i).getResponsiblePerson() == null ||
					// exceptions.get(i).getResponsiblePerson().getEmployeeId()==0)){
					status = 1;
					break;
				} else if (exceptions.get(i).getManagementComments() == null
						|| exceptions.get(i).getManagementComments().equals("")) {
					status = 2;

				}

				else if (exceptions.get(i).getFinalStatus() != null
						&& exceptions.get(i).getFinalStatus().equals("Approved")) {
					status = 5;

				} else if (exceptions.get(i).getStatus() != null && exceptions.get(i).getStatus().equals("Approved")) {
					status = 4;

				}

				else {
					if (status == 0)
						status = 3;
				}
			}

			logger.info(
					String.format("(Inside fetchJobStatus)  fetching JobStatus for job : " + jobId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobStatus", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return status;

	}

	public ArrayList<Exceptions> fetchJobExceptions(int jobId, int year, int companyId, HashMap<String, String> hm) {

		Session session = null;
		ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			crit.createAlias("responsiblePerson", "employee");
			// crit.add(Restrictions.ne("employee.employeeId", 0));// CHANGE
			// 15/12/2018 .. Reason: the reporting tab was not displaying thia
			// exception when click on a job anchor.

			jobsStrategicAlias(crit);

			// check filters
			if (hm != null) {
				if (!hm.get("Process").equals("0")) {
					crit.add(Restrictions.eq("processId.processId", Integer.parseInt(hm.get("Process"))));
				}
				if (!hm.get("Unit").equals("0")) {
					crit.add(Restrictions.eq("strategic.auditableUnit", hm.get("Unit")));
				}
				if (!hm.get("Division").equals("0") && !hm.get("Division").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("dept.departmentName", hm.get("Division")));// TODO
					// should
					// be
					// depId

				}
				if (!hm.get("Resource").equals("0")) {
					crit.add(Restrictions.eq("employee.employeeId", Integer.parseInt(hm.get("Resource"))));
				}
				if (!hm.get("Risk").equals("0") && !hm.get("Risk").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.riskRating", hm.get("Risk")));
				}
				if (!hm.get("Domain").equals("0") && !hm.get("Domain").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.domainText", hm.get("Domain")));
				}
			}
			// filters end

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			crit.createAlias("employee.countryId", "employeeCount");
			crit.createAlias("employee.cityId", "employeeCity");
			crit.createAlias("employee.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");

			crit.createAlias("divisionHead", "divHead");
			crit.createAlias("divHead.countryId", "divHeadCount");
			crit.createAlias("divHead.cityId", "divHeadCity");
			crit.createAlias("divHead.reportingTo", "divHeadRep");

			crit.createAlias("divHead.skillId", "divHeadSkill");

			crit.createAlias("divHeadRep.skillId", "divHeadSkill1");

			crit.createAlias("employee.skillId", "employeeSkill");

			crit.createAlias("employeeRep.skillId", "employeeSkill2");

			// crit.createAlias("divHead.countryId", "divHeadRCount");
			if (jobId != 0) {
				crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			}
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exception = (Exceptions) it.next();

				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				String status = getJobCreationStatus(exception.getJobCreationId().getJobCreationId());
				exception.setDisplayStatus(status);

				exceptions.add(exception);
			}
			if (jobId == 0) {
				sendEmailNotifications(exceptions);
			}

			logger.info(String.format("(Inside fetchJobExceptions)  fetching JobExceptions for job : " + jobId
					+ "for year" + year + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return exceptions;
	}

	//
	public ArrayList<InformationRequestEntity> fetchInformationRequest(int companyId, HashMap<String, String> hm) {

		Session session = null;
		ArrayList<InformationRequestEntity> informationRequests = new ArrayList<InformationRequestEntity>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(InformationRequestEntity.class);

			crit.add(Restrictions.eq("companyId", companyId));

			crit.createAlias("assignedFrom", "from");
			crit.createAlias("contactResponsible", "To");

			// crit.add(Restrictions.eq("companyId", companyId));

			crit.createAlias("from.countryId", "employeeCount");
			crit.createAlias("from.cityId", "employeeCity");
			crit.createAlias("from.reportingTo", "employeeRep");
			crit.createAlias("from.skillId", "employeeSkill");
			crit.createAlias("To.skillId", "employeeSkill2");
			crit.createAlias("To.countryId", "employeeCountR");
			crit.createAlias("To.cityId", "employeeCityR");
			crit.createAlias("To.reportingTo", "employeeRepR");

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				InformationRequestEntity informationReq = (InformationRequestEntity) it.next();

				HibernateDetachUtility.nullOutUninitializedFields(informationReq,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(informationReq.getContactResponsible(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(informationReq.getAssignedFrom(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				informationRequests.add(informationReq);
			}

			logger.info(String.format("(Inside fetchInformationRequest)  fetching informationRequests : "
					+ "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchInformationRequest", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return informationRequests;
	}

	public ArrayList<ToDo> fetchToDo(int companyId, HashMap<String, String> hm) {

		Session session = null;
		ArrayList<ToDo> toDos = new ArrayList<ToDo>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ToDo.class);
			crit.createAlias("assignedFrom", "from");
			crit.createAlias("assignedTo", "To");

			// crit.add(Restrictions.eq("companyId", companyId));

			crit.createAlias("from.countryId", "employeeCount");
			crit.createAlias("from.cityId", "employeeCity");
			crit.createAlias("from.reportingTo", "employeeRep");

			crit.createAlias("from.skillId", "employeeSkill");

			crit.createAlias("To.skillId", "employeeSkill2");

			crit.createAlias("To.countryId", "employeeCountR");
			crit.createAlias("To.cityId", "employeeCityR");
			crit.createAlias("To.reportingTo", "employeeRepR");

			// crit.add(Restrictions.eq("read", 0));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ToDo toDo = (ToDo) it.next();

				HibernateDetachUtility.nullOutUninitializedFields(toDo,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				toDos.add(toDo);
			}

			logger.info(String
					.format("(Inside fetchTodo)  fetching todos : " + "for company" + companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchtodo", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return toDos;
	}

	private void sendEmailNotifications(ArrayList<Exceptions> exceptions) {
		Date todaysDate = new Date();
		for (int i = 0; i < exceptions.size(); i++) {

			long diff = getDatesDiff(todaysDate, exceptions.get(i).getImplementaionDate());

			if (diff > 0 && diff < 7 && exceptions.get(i).getEmailSent() == 0) {

				String date = exceptions.get(i).getImplementaionDate().toLocaleString();
				String implenDate = date.substring(0, 13);
				String managementsMessage = "Dear " + exceptions.get(i).getResponsiblePerson().getEmployeeName() + " "
						+ " <br></br> <br></br>" + " Less than a week remaining In implemting the <br></br> <br></br>"
						+ " Exception :" + exceptions.get(i).getDetail() + "<br></br> <br></br>" + " For Job :"
						+ exceptions.get(i).getJobName() + "<br></br> <br></br>" + " Due Date :" + implenDate
						+ "<br></br> <br></br>";

				sendEmail(managementsMessage, exceptions.get(i).getResponsiblePerson().getEmail(), "",
						"Exceptions Implementation Date");
				updateException(exceptions.get(i));
			}
		}
		// sendEmail("test", "junaidp@gmail.com");

	}

	private void updateException(Exceptions exception) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			exception.setEmailSent(1);
			session.update(exception);
			tr.commit();

			logger.info(String.format("(Inside updateException)  updating Exception for implementationComments: "
					+ exception.getImplementaionComments() + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in updating exception", ex.getMessage()), ex);

		} finally {
			session.close();
		}

	}

	private long getDatesDiff(Date implementaionDate, Date todaysDate) {

		long daysBw = Days.daysBetween(new DateTime(implementaionDate), new DateTime(todaysDate)).getDays();
		return daysBw;

	}

	public String sendException(Exceptions exception, int year, int companyId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String jobName = fetchJobName(exception.getJobCreationId().getJobCreationId(), session);
			int auditHead = fetchAuditHead(exception.getJobCreationId().getJobCreationId(), session);
			// Transaction tr = session.beginTransaction();

			exception.setJobName(jobName);
			if (exception.getAuditHead() == 0) {
				exception.setAuditHead(auditHead);
			}
			exception.setYear(year);
			exception.setCompanyId(companyId);
			session.update(exception);
			session.flush();

			logger.info(String.format("(Inside sendException)  sending Exception for year: " + year + "for company"
					+ companyId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in send exceptoin", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return "exception sent";
	}

	private int fetchAuditHead(int jobId, Session session) {
		Criteria crit = session.createCriteria(JobCreation.class);
		crit.add(Restrictions.eq("jobCreationId", jobId));
		JobCreation jobCreation = (JobCreation) crit.list().get(0);
		return jobCreation.getAuditHead();
	}

	private String fetchJobName(int jobId, Session session) {
		Criteria crit = session.createCriteria(JobCreation.class);
		crit.add(Restrictions.eq("jobCreationId", jobId));
		JobCreation jobCreation = (JobCreation) crit.list().get(0);
		return jobCreation.getJobName();

	}

	public ArrayList<Exceptions> fetchEmployeeExceptions(int employeeId, int jobId, int year, int companyId) {
		Session session = null;
		ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("responsiblePerson", "employee");
			crit.createAlias("divisionHead", "divhead");
			crit.add(Restrictions.eq("employee.employeeId", employeeId));
			crit.add(Restrictions.eq("initialStatus", "Approved"));
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			crit.createAlias("employee.countryId", "employeeCount");
			crit.createAlias("employee.cityId", "employeeCity");
			crit.createAlias("employee.reportingTo", "employeeRep");
			crit.createAlias("divhead.reportingTo", "divheadRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("employeeRep.cityId", "employeeRCity");
			crit.createAlias("employee.skillId", "employeeSkill");
			crit.createAlias("divhead.cityId", "divheadcityy");
			crit.createAlias("divhead.skillId", "divheadSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");
			crit.createAlias("divheadRep.skillId", "divheadRepSkill");

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exception = (Exceptions) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				exceptions.add(exception);
			}
			logger.info(String.format("(Inside fetchEmployeeExceptions)  fetching EmployeeExceptions for year: " + year
					+ "for company" + companyId + "employe id" + employeeId + "job id" + jobId + " " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeeExceptions", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return exceptions;
	}

	public void saveAuditStepAndExceptions(AuditStep auditstep, ArrayList<Exceptions> exceptions, int year,
			int companyId) {

		// save audit step

		saveAuditStep(auditstep, exceptions, year, companyId);

		// for ( Exceptions exception : exceptions)
		// saveException(exception);

		// save execptions
	}

	// private void saveExcetion(Exceptions e) {
	//
	// Session session =null;
	// try{
	//
	// session = sessionFactory.openSession();
	// Transaction tr = session.beginTransaction();
	// session.saveOrUpdate(e);
	// tr.commit();
	//
	// }catch(Exception ex){
	// logger.warn(String.format("Exception occured in saving skill relation
	// job", ex.getMessage()), ex);
	//
	// }
	// finally{
	// session.close();
	//
	// }
	//
	// }

	private void saveException(Exceptions exception, Session session, int auditStepId, int year, int companyId) {

		try {

			Employee responsiblePerson = (Employee) session.load(Employee.class, 0);
			// responsiblePerson.setEmployeeId(0);
			// Employee divisionHead = new Employee();
			Employee divisionHead = (Employee) session.load(Employee.class, 0);
			// divisionHead.setEmployeeId(0);
			exception.setDivisionHead(divisionHead);
			exception.setResponsiblePerson(responsiblePerson);
			exception.setAuditStep(auditStepId);
			exception.setYear(year);
			exception.setCompanyId(companyId);
			session.saveOrUpdate(exception);
			session.flush();

			String message = "Dear " + responsiblePerson.getEmployeeName() + " <br></br> <br></br>"
					+ " Your have received an Exception update from Abilite: <br></br> <br></br>"
					+ "  please click on the link below.<br></br> <br></br>"
					+ " <a href= http://127.0.0.1:8888/InternalAudit.html#Reporting/employeeId="
					+ exception.getJobCreationId() + "/year=" + year + "/companyId=" + companyId + "/employeeId="
					+ responsiblePerson.getEmployeeId() + "View Exception</a>";

			sendEmail(message, responsiblePerson.getEmail(), "", "Abilite: Exception Received");

			logger.info(String.format(
					"(Inside saveException) Email for exception to management,  saving Exception for year: " + year
							+ "for company" + companyId + "for Exception" + exception.getDetail() + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saving exceptiom", ex.getMessage()), ex);

		} finally {
			// session.close();

		}

	}

	private void saveAuditStep(AuditStep auditstep, ArrayList<Exceptions> exceptions, int year, int companyId) {

		Session session = null;
		Transaction tr = null;
		try {

			session = sessionFactory.openSession();
			tr = session.beginTransaction();
			if (auditstep.getAuditStepId() != 0) {
				Employee initiatedBy = fetchInitiatedBy(auditstep.getAuditStepId());
				auditstep.setInitiatedBy(initiatedBy);
			}

			auditstep.setYear(year);
			auditstep.setCompanyId(companyId);

			if (auditstep.getInitiatedBy() != null) {
				auditstep.setInitiatedBy(
						(Employee) session.get(Employee.class, auditstep.getInitiatedBy().getEmployeeId()));
				auditstep.setDate(new Date());
			}
			if (auditstep.getApprovedBy() != null) {
				auditstep.setApprovedBy(
						(Employee) session.get(Employee.class, auditstep.getApprovedBy().getEmployeeId()));
				auditstep.setDate(new Date());
			}

			session.saveOrUpdate(auditstep);
			session.flush();
			for (Exceptions exception : exceptions)
				saveException(exception, session, auditstep.getAuditStepId(), year, companyId);
			tr.commit();

			logger.info(String.format("(Inside saveAuditStep)   saving AuditStep for year: " + year + "for audit step"
					+ auditstep.getApprovedBy() + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveAuditStep", ex.getMessage()), ex);
			tr.rollback();
		} finally {
			session.close();

		}

	}

	private Employee fetchInitiatedBy(int auditStepId) {
		Session session = null;
		Employee initiatedBy = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditStep.class);
			crit.add(Restrictions.eq("auditStepId", auditStepId));

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			AuditStep auditStep = (AuditStep) crit.list().get(0);
			initiatedBy = auditStep.getInitiatedBy();
			logger.info(String.format("(Inside fetchInitiatedBy)    fetching InitiatedBy for audit step: " + auditStepId
					+ "" + new Date()));

		} catch (Exception ex) {
			System.out.println("error in fetchInitiatedBy from auditStep");
		}

		return initiatedBy;
	}

	public AuditStep getSavedAuditStep(int jobid, int auditWorkId, int year, int companyId) {
		Session session = null;
		AuditStep record = new AuditStep();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditStep.class);
			// crit.createAlias("responsiblePerson", "responsible");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("jobId", jobid));
			crit.createAlias("auditWork", "audWork");
			crit.createAlias("audWork.jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("audWork.auditWorkId", auditWorkId));// UNDO
			// 2018,
			// done
			crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("audWork.initiatedBy", "initiatedAuditWork");
			crit.createAlias("audWork.approvedBy", "approvedAuditWork");
			crit.createAlias("audWork.suggestedControlsId", "audSugges");
			crit.createAlias("audSugges.riskId", "controlrisk");
			crit.createAlias("controlrisk.objectiveId", "activityobj");
			crit.createAlias("activityobj.subProcessId", "subpro");
			crit.createAlias("subpro.processId", "process");

			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.cityId", "employeeCity");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			///
			/*
			 * crit.createAlias("audWork.riskId", "risk");
			 * crit.createAlias("risk.auditEngageId", "audEng");
			 * crit.createAlias("audEng.jobCreation", "audJobCreation");
			 * 
			 * crit.createAlias("audEng.approvedBy", "approvedEng");
			 * crit.createAlias("approvedEng.countryId", "employeeCounteng");
			 * crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			 * crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			 * crit.createAlias("employeeRepeng.countryId",
			 * "employeeRCounteng"); crit.createAlias("approvedEng.userId",
			 * "employeeUsereng"); crit.createAlias("approvedEng.rollId",
			 * "employeeRolleng"); crit.createAlias("approvedEng.skillId",
			 * "employeeSkilleng"); crit.createAlias("employeeRepeng.rollId",
			 * "employeeRepRolleng"); crit.createAlias("employeeRepeng.skillId",
			 * "employeeRepSkilleng");
			 * 
			 * crit.createAlias("audEng.initiatedBy", "initiatedeng");
			 * crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			 * crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			 * crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			 * crit.createAlias("initiatedRepeng.countryId",
			 * "initiatedRCounteng"); crit.createAlias("initiatedeng.userId",
			 * "initiatedUsereng"); crit.createAlias("initiatedeng.rollId",
			 * "initiatedRolleng"); crit.createAlias("initiatedeng.skillId",
			 * "initiatedSkilleng"); crit.createAlias("initiatedRepeng.rollId",
			 * "initiatedRepRolleng");
			 * crit.createAlias("initiatedRepeng.skillId",
			 * "initiatedRepSkilleng");
			 */

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditStep auditStep = (AuditStep) it.next();
				auditStep.setExceptions(getSavedExceptions(auditStep.getAuditStepId(), year, companyId));
				HibernateDetachUtility.nullOutUninitializedFields(auditStep,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getInitiatedBy().getReportingTo().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getAuditWork().getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getAuditWork().getApprovedBy().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getInitiatedBy().getReportingTo().getReportingTo().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getAuditWork().getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getInitiatedBy().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getSuggestedControlsId().getRiskId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				return auditStep;
			}

			logger.info(String.format("(Inside getSavedAuditStep)    getting SavedAuditStep for job: " + jobid
					+ "for audit work" + auditWorkId + "for year" + year + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getSavedAuditStep", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return record;
	}

	//
	public ArrayList<AuditStep> fetchEmployeeAuditStepsForApproval(int year, int companyId, int employeeId) {
		Session session = null;
		ArrayList<AuditStep> records = new ArrayList<AuditStep>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditStep.class);
			// crit.createAlias("responsiblePerson", "responsible");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("auditWork", "audWork");
			crit.createAlias("audWork.jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("status", InternalAuditConstants.SUBMIT));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("auditWork.initiatedBy", "initiatedAuditWork");
			crit.createAlias("auditWork.approvedBy", "approvedAuditWork");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.cityId", "employeeCity");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.add(Restrictions.eq("initiatedRep.employeeId", employeeId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditStep auditStep = (AuditStep) it.next();
				auditStep.setExceptions(getSavedExceptions(auditStep.getAuditStepId(), year, companyId));
				HibernateDetachUtility.nullOutUninitializedFields(auditStep,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getAuditWork().getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						auditStep.getAuditWork().getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditStep.getAuditWork().getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				records.add(auditStep);
			}

			logger.info(String
					.format("(Inside fetchEmployeeAuditStepsForApproval)   fetching EmployeeAuditStepsForApproval for company: "
							+ companyId + "for employee id" + employeeId + "for year" + year + "" + new Date()));

			// return records;
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeeAuditStepsForApproval", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return records;
	}

	public ArrayList<Exceptions> getSavedExceptions(int auditStepId, int year, int companyId) {
		Session session = null;
		ArrayList<Exceptions> records = new ArrayList<Exceptions>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("responsiblePerson", "responsible");
			// crit.createAlias("auditStep", "audStep");
			crit.add(Restrictions.eq("auditStep", auditStepId));
			// crit.add(Restrictions.eq("jobCreationId", selectedJobId));
			crit.createAlias("divisionHead", "division");
			crit.createAlias("divisionHead.skillId", "divisionSkill");

			/*
			 * crit.createAlias("responsible.countryId", "employeeCount");
			 * crit.createAlias("responsible.cityId", "employeeCity");
			 * crit.createAlias("responsible.reportingTo", "employeeRep");
			 * crit.createAlias("employeeRep.countryId", "employeeRCount");
			 * 
			 * crit.createAlias("division.countryId", "employeeCount1");
			 * crit.createAlias("division.cityId", "employeeCity1");
			 * crit.createAlias("division.reportingTo", "employeeRep1");
			 * crit.createAlias("employeeRep1.countryId", "employeeRCount1");
			 * 
			 * crit.createAlias("division.skillId", "divisionSkill");
			 * crit.createAlias("employeeRep1.skillId", "divisionSkill1");
			 */

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exception = (Exceptions) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(exception.getJobCreationId().getStrategicId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(exception.getDivisionHead().getSkillId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				records.add(exception);

			}

			logger.info(String.format("(Inside getSavedExceptions)   getting SavedExceptions for company: " + companyId
					+ "for auditstep" + auditStepId + "for year" + year + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getSavedExceptions", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return records;
	}

	//
	public ArrayList<Exceptions> fetchEmployeeExceptionsForApproval(int year, int companyId, int employeeId) {
		Session session = null;
		ArrayList<Exceptions> records = new ArrayList<Exceptions>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("responsiblePerson", "responsible");
			crit.createAlias("divisionHead", "division");

			crit.createAlias("responsible.countryId", "employeeCount");
			crit.createAlias("responsible.cityId", "employeeCity");
			crit.createAlias("responsible.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");

			crit.createAlias("division.countryId", "employeeCount1");
			crit.createAlias("division.cityId", "employeeCity1");
			crit.createAlias("division.reportingTo", "employeeRep1");
			crit.createAlias("employeeRep1.countryId", "employeeRCount1");

			crit.createAlias("division.skillId", "divisionSkill");
			crit.createAlias("employeeRep1.skillId", "divisionSkill1");
			Disjunction disc = Restrictions.disjunction();
			disc.add(Restrictions.eq("status", "Sent"));
			disc.add(Restrictions.eq("finalStatus", "Sent"));
			crit.add(disc);

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exception = (Exceptions) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				records.add(exception);

			}

			logger.info(String
					.format("(Inside fetchEmployeeExceptionsForApproval)   fetching EmployeeExceptionsForApproval for company: "
							+ companyId + "for employe" + employeeId + "for year" + year + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeeExceptionsForApproval", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return records;
	}

	public void saveAuditWork(ArrayList<AuditWork> auditWorks, int year, int companyId) {

		for (AuditWork auditWork : auditWorks) {
			auditWork.setYear(year);
			auditWork.setCompanyId(companyId);
			saveAuditWork(auditWork);
		}
	}

	private void saveAuditWork(AuditWork auditWork) {

		Session session = null;
		try {

			session = sessionFactory.openSession();

			if (auditWork.getInitiatedBy() == null) {
				auditWork.setInitiatedBy(
						(Employee) session.get(Employee.class, auditWork.getInitiatedBy().getEmployeeId()));
			}
			if (auditWork.getApprovedBy() != null) {
				auditWork.setApprovedBy(
						(Employee) session.get(Employee.class, auditWork.getApprovedBy().getEmployeeId()));
			}

			Transaction tr = session.beginTransaction();
			session.saveOrUpdate(auditWork);
			tr.commit();

			logger.info(String.format("(Inside saveAuditWork)   saving AuditWork for auditWork: "
					+ auditWork.getDescription() + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saving audit work", ex.getMessage()), ex);

		} finally {
			session.close();

		}

	}

	public void updateKickoffStatus(int auditEngId, int year, int companyId, Employee loggedInUser) {
		Session session = null;

		try {

			session = sessionFactory.openSession();

			AuditEngagement prevCreated = fetchAuditEngagement(auditEngId, year, companyId);

			if (prevCreated != null) {
				prevCreated.setJobStatus("In Progress");
				prevCreated.setYear(year);
				// prevCreated.setInitiatedBy(loggedInUser.getEmployeeId());
				prevCreated.setInitiatedBy((Employee) session.get(Employee.class, loggedInUser.getEmployeeId()));
				prevCreated.setApprovedBy((Employee) session.get(Employee.class, 0));
				Transaction tr = session.beginTransaction();
				session.saveOrUpdate(prevCreated);
				tr.commit();
			}
			logger.info(String.format(
					"(Inside updateKickoffStatus)   updating KickoffStatus for audit eng: " + auditEngId + "for company"
							+ companyId + "for logged in user" + loggedInUser.getEmail() + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  updating kickoff status", ex.getMessage()), ex);
		} finally {
			session.close();

		}
	}

	public ArrayList<Exceptions> fetchAuditHeadExceptions(int auditHeadId, int selectedJob, int year, int companyId) {
		Session session = null;
		ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("responsiblePerson", "employee");
			crit.createAlias("divisionHead", "divHead");
			// crit.add(Restrictions.eq("auditHead", auditHeadId));
			crit.add(Restrictions.eq("jobCreation.jobCreationId", selectedJob));

			crit.createAlias("employee.countryId", "employeeCount");
			crit.createAlias("employee.cityId", "employeeCity");
			crit.createAlias("employee.reportingTo", "employeeRep");
			crit.createAlias("divHead.reportingTo", "divHeadRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");

			crit.createAlias("employee.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepkill");
			crit.createAlias("divHead.cityId", "divHeadCity");
			crit.createAlias("divHead.skillId", "divHeadSkill");
			crit.createAlias("divHeadRep.skillId", "divHeadRepSkill");

			crit.add(Restrictions.ne("managementComments", ""));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exception = (Exceptions) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				exceptions.add(exception);
			}

			logger.info(String.format(
					"(Inside fetchAuditHeadExceptions)   fetching AuditHeadExceptions for audit head: " + auditHeadId
							+ "for company" + companyId + "for selected job" + selectedJob + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchAuditHeadExceptions", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return exceptions;
	}

	public ArrayList<AuditWork> fetchAuditWorkRows(int jocreationid, int companyId, int year) {

		Session session = null;
		ArrayList<AuditWork> rows = new ArrayList<AuditWork>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(AuditWork.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jocreationid));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.cityId", "employeeCityy");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCityy");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.createAlias("suggestedControlsId", "controls");

			/*
			 * crit.createAlias("riskId", "risk");
			 * crit.createAlias("risk.auditEngageId", "audEng");
			 * crit.createAlias("audEng.jobCreation", "audJobCreation");
			 * 
			 * 
			 * 
			 * //crit.createAlias("audEng.approvedBy", "approvedEng");
			 * crit.createAlias("approvedEng.countryId", "employeeCounteng");
			 * crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			 * crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			 * crit.createAlias("employeeRepeng.countryId",
			 * "employeeRCounteng"); crit.createAlias("approvedEng.userId",
			 * "employeeUsereng"); crit.createAlias("approvedEng.rollId",
			 * "employeeRolleng"); crit.createAlias("approvedEng.skillId",
			 * "employeeSkilleng"); crit.createAlias("employeeRepeng.rollId",
			 * "employeeRepRolleng"); crit.createAlias("employeeRepeng.skillId",
			 * "employeeRepSkilleng");
			 * 
			 * crit.createAlias("audEng.initiatedBy", "initiatedeng");
			 * crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			 * crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			 * crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			 * crit.createAlias("initiatedRepeng.countryId",
			 * "initiatedRCounteng"); crit.createAlias("initiatedeng.userId",
			 * "initiatedUsereng"); crit.createAlias("initiatedeng.rollId",
			 * "initiatedRolleng"); crit.createAlias("initiatedeng.skillId",
			 * "initiatedSkilleng"); crit.createAlias("initiatedRepeng.rollId",
			 * "initiatedRepRolleng");
			 * crit.createAlias("initiatedRepeng.skillId",
			 * "initiatedRepSkilleng");
			 */

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditWork row = (AuditWork) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(row,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						row.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getSuggestedControlsId().getRiskId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getSuggestedControlsId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				rows.add(row);
			}

			logger.info(String.format("(Inside fetchAuditWorkRows)   fetching AuditWorkRows for job creationID: "
					+ jocreationid + "for company" + companyId + "for year" + year + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchAuditWorkRows", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return rows;
	}

	// 2019april
	public ArrayList<AuditWork> fetchEmployeeAuditWorksForapproval(int companyId, int year, int employeeId) {

		Session session = null;
		ArrayList<AuditWork> rows = new ArrayList<AuditWork>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(AuditWork.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("status", InternalAuditConstants.SUBMIT));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.cityId", "employeeCityy");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCityy");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			crit.add(Restrictions.eq("initiatedRep.employeeId", employeeId));

			/// 2019 april commented below lines crit
			//
			crit.createAlias("suggestedControlsId", "suggestedControlsId");
			//
			// crit.createAlias("suggestedControlsId.riskId", "risk");
			// crit.createAlias("risk.auditEngageId", "audEng");
			// crit.createAlias("audEng.jobCreationId", "jobCreation1");
			// // jobsStrategicAlias(crit);
			// crit.createAlias("audEng.approvedBy", "approvedEng");
			// crit.createAlias("approvedEng.countryId", "employeeCounteng");
			// crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			// crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			// crit.createAlias("employeeRepeng.countryId",
			// "employeeRCounteng");
			// crit.createAlias("approvedEng.skillId", "employeeSkilleng");
			// crit.createAlias("employeeRepeng.skillId",
			// "employeeRepSkilleng");
			//
			// crit.createAlias("audEng.initiatedBy", "initiatedeng");
			// crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			// crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			// crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			// crit.createAlias("initiatedRepeng.countryId",
			// "initiatedRCounteng");
			// crit.createAlias("initiatedeng.skillId", "initiatedSkilleng");
			// crit.createAlias("initiatedRepeng.skillId",
			// "initiatedRepSkilleng");

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditWork row = (AuditWork) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(row,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						row.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				rows.add(row);
			}
			logger.info(String
					.format("(Inside fetchEmployeeAuditWorksForapproval)   fetching EmployeeAuditWorksForapproval for employeId: "
							+ employeeId + "for company" + companyId + "for year" + year + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeeAuditWorksForapproval", ex.getMessage()), ex);
			System.out.println("HERE inisde catch....." + ex.getLocalizedMessage());

		} finally {
			session.close();
		}
		System.out.println("HERE NOT in catch....." + ":::" + rows);
		return rows;
	}

	public ArrayList<AuditWork> fetchApprovedAuditWorkRows(int selectedJobId) {
		Session session = null;
		ArrayList<AuditWork> rows = new ArrayList<AuditWork>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(AuditWork.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("jobCreation.jobCreationId", selectedJobId));
			crit.add(Restrictions.eq("status", InternalAuditConstants.APPROVED));

			crit.createAlias("approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.cityId", "employeeCity");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.add(Restrictions.eq("employeeRep.rollId", 1));

			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.cityId", "initiatedCity");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			///
			/*
			 * crit.createAlias("riskId", "risk");
			 * crit.createAlias("risk.auditEngageId", "audEng");
			 * crit.createAlias("audEng.jobCreation", "audJobCreation");
			 * 
			 * crit.createAlias("audEng.approvedBy", "approvedEng");
			 * crit.createAlias("approvedEng.countryId", "employeeCounteng");
			 * crit.createAlias("approvedEng.cityId", "employeeCityyeng");
			 * crit.createAlias("approvedEng.reportingTo", "employeeRepeng");
			 * crit.createAlias("employeeRepeng.countryId",
			 * "employeeRCounteng"); crit.createAlias("approvedEng.userId",
			 * "employeeUsereng"); crit.createAlias("approvedEng.rollId",
			 * "employeeRolleng"); crit.createAlias("approvedEng.skillId",
			 * "employeeSkilleng"); crit.createAlias("employeeRepeng.rollId",
			 * "employeeRepRolleng"); crit.createAlias("employeeRepeng.skillId",
			 * "employeeRepSkilleng");
			 * 
			 * crit.createAlias("audEng.initiatedBy", "initiatedeng");
			 * crit.createAlias("initiatedeng.countryId", "initiatedCounteng");
			 * crit.createAlias("initiatedeng.cityId", "initiatedCityyeng");
			 * crit.createAlias("initiatedeng.reportingTo", "initiatedRepeng");
			 * crit.createAlias("initiatedRepeng.countryId",
			 * "initiatedRCounteng"); crit.createAlias("initiatedeng.userId",
			 * "initiatedUsereng"); crit.createAlias("initiatedeng.rollId",
			 * "initiatedRolleng"); crit.createAlias("initiatedeng.skillId",
			 * "initiatedSkilleng"); crit.createAlias("initiatedRepeng.rollId",
			 * "initiatedRepRolleng");
			 * crit.createAlias("initiatedRepeng.skillId",
			 * "initiatedRepSkilleng");
			 */

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditWork row = (AuditWork) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(row,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						row.getInitiatedBy().getReportingTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(row.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				rows.add(row);
			}

			logger.info(String
					.format("(Inside fetchApprovedAuditWorkRows)   fetching ApprovedAuditWorkRows for selected job: "
							+ selectedJobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchApprovedAuditWorkRows", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return rows;
	}

	public ArrayList<Integer> fetchjobEmployeeWithApprovedAuditStep(int employeeId) {

		Session session = null;
		ArrayList<Integer> employeeJobs = new ArrayList<Integer>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			crit.createAlias("employeeId", "employee");
			crit.createAlias("jobCreationId", "jobCreation");
			// jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("employee.employeeId", employeeId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				int job = jobEmployeeRelation.getJobCreationId().getJobCreationId();
				if (isJobAuditStepApproved(job)) {/////// FOR JOBS WHICH AUDIT
					/////// STEP IS APPROVED
					employeeJobs.add(job);
				}
				HibernateDetachUtility.nullOutUninitializedFields(jobEmployeeRelation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

			}

			logger.info(String
					.format("(Inside fetchjobEmployeeWithApprovedAuditStep)   fetching jobEmployeeWithApprovedAuditStep for employe: "
							+ employeeId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchjobEmployeeWithApprovedAuditStep", ex.getMessage()),
					ex);

		} finally {
			session.close();
		}
		return employeeJobs;
	}

	public ArrayList<Integer> fetchjobEmployee(int employeeId) {

		Session session = null;
		ArrayList<Integer> employeeJobs = new ArrayList<Integer>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			crit.createAlias("employeeId", "employee");
			crit.add(Restrictions.eq("employee.employeeId", employeeId));
			// crit.createAlias("jobCreationId", "jobCreation");
			// jobsStrategicAlias(crit);
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				int job = jobEmployeeRelation.getJobCreationId().getJobCreationId();
				if (!employeeJobs.contains(job)) {
					employeeJobs.add(job);
				}

				HibernateDetachUtility.nullOutUninitializedFields(jobEmployeeRelation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

			}

			logger.info(String.format(
					"(Inside fetchjobEmployee)   fetching jobEmployee for employe: " + employeeId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchjobEmployee", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employeeJobs;
	}

	public ArrayList<Integer> fetchjobEmployeeOtherThanGiveOne(int employeeId, int jobId) {

		Session session = null;
		ArrayList<Integer> employeeJobs = new ArrayList<Integer>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			crit.createAlias("employeeId", "employee");
			crit.add(Restrictions.eq("employee.employeeId", employeeId));
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.ne("jobCreationId.jobCreationId", jobId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				int job = jobEmployeeRelation.getJobCreationId().getJobCreationId();
				if (!employeeJobs.contains(job)) {
					employeeJobs.add(job);
				}

				HibernateDetachUtility.nullOutUninitializedFields(jobEmployeeRelation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

			}

			logger.info(String
					.format("(Inside fetchjobEmployeeOtherThanGiveOne)   fetching jobEmployeeOtherThanGiveOne for employe: "
							+ employeeId + "for job" + jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchjobEmployeeOtherThanGiveOne", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employeeJobs;
	}

	public ArrayList<JobCreation> fetchjobEmployeeEntity(int employeeId) {

		Session session = null;
		ArrayList<JobCreation> employeeJobs = new ArrayList<JobCreation>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			crit.createAlias("employeeId", "employee");
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("employee.employeeId", employeeId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
				JobCreation job = jobEmployeeRelation.getJobCreationId();
				employeeJobs.add(job);

				HibernateDetachUtility.nullOutUninitializedFields(jobEmployeeRelation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

			}

			logger.info(String.format("(Inside fetchjobEmployeeEntity)   fetching obEmployeeEntity for employe: "
					+ employeeId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchjobEmployeeEntity", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employeeJobs;
	}

	public boolean isJobAuditStepApproved(int jobId) {
		Session session = null;
		boolean jobAuditStepApproved = false;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditStep.class);
			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				jobAuditStepApproved = true;
				AuditStep auditStep = (AuditStep) it.next();
				int status = auditStep.getStatus();
				if (status == InternalAuditConstants.APPROVED
						&& (auditStep.getApprovedBy().getRollId() == 1 || auditStep.getApprovedBy().getRollId() == 2)) {
					jobAuditStepApproved = true;
				} else {
					jobAuditStepApproved = false;
				}

				HibernateDetachUtility.nullOutUninitializedFields(auditStep,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

			}

			logger.info(String.format("(Inside isJobAuditStepApproved)   checking isJobAuditStepApproved for job: "
					+ jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in isJobAuditStepApproved", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobAuditStepApproved;

	}

	// EMPLYEE JOBS FOR which audit steps are approved by Audit Head
	public ArrayList<JobCreation> fetchEmployeeJobs(Employee loggedInEmployee, int year, int companyId,
			String reportingTab) {
		Session session = null;
		ArrayList<Integer> jobIds = fetchjobEmployeeWithApprovedAuditStep(loggedInEmployee.getEmployeeId());
		ArrayList<JobCreation> jobsList = new ArrayList<JobCreation>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			Disjunction disc = Restrictions.disjunction();
			if (jobIds.size() <= 0 && loggedInEmployee.getFromInternalAuditDept().equalsIgnoreCase("yes")) {
				return null;
			}
			for (int i = 0; i < jobIds.size(); i++) {
				disc.add(Restrictions.eq("jobCreationId", jobIds.get(i)));
			}
			crit.add(disc);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.createAlias("strategicId", "strategic");

			crit.createAlias("strategic.approvedBy", "approved");
			crit.createAlias("approved.countryId", "employeeCount");
			crit.createAlias("approved.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("approved.skillId", "employeeSkill");
			crit.createAlias("employeeRep.skillId", "employeeRepSkill");

			crit.createAlias("strategic.initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiatedRep.cityId", "initiatedRCity");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreation jobCreationTemp = (JobCreation) it.next();

				JobCreation jobCreation = fetchSelectedJobCreation(session, jobCreationTemp.getJobCreationId());

				jobCreation.setReportStatus(fetchJobExceptionStatus(jobCreation.getJobCreationId()));
				// ADDED these 2 lines
				session.update(jobCreation);
				session.flush();
				// ENd added

				HibernateDetachUtility.nullOutUninitializedFields(jobCreation,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(jobCreation.getStrategicId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				// jobsList.add(jobCreation);
				/// for getting followup tab jobs and reporting separately///
				if (reportingTab.equals(InternalAuditConstants.FOLLOWUPTAB)) {
					if (jobCreation.getReportStatus() == 4 || jobCreation.getReportStatus() == 3) {
						jobsList.add(jobCreation);
					}

				} else if (reportingTab.equals(InternalAuditConstants.REPORTINGTAB)) {
					if (jobCreation.getReportStatus() == 1 || jobCreation.getReportStatus() == 2
							|| jobCreation.getReportStatus() == 5) {

						jobsList.add(jobCreation);
					}

				}
				/// end followup ///////

			}

			logger.info(String
					.format("(Inside fetchjobEmployeeWithApprovedAuditStep)   fetching jobEmployeeWithApprovedAuditStep for loggedIn Employ: "
							+ loggedInEmployee.getEmployeeName() + "for year" + year + "for company" + companyId + ""
							+ new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchjobEmployeeWithApprovedAuditStep", ex.getMessage()),
					ex);

		} finally {
			session.close();
		}
		return jobsList;
	}

	private JobCreation fetchSelectedJobCreation(Session session, int jobcreationId) {
		JobCreation jobCreation = null;
		try {

			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			crit.add(Restrictions.eq("jobCreationId", jobcreationId));
			jobsStrategicAlias(crit);

			List rsList = crit.list();

			jobCreation = (JobCreation) rsList.get(0);

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchSelectedJobCreation", ex.getMessage()), ex);

		} finally {

		}
		return jobCreation;

	}

	public String saveAuditNotification(int auditEngagementId, String message, String to, String cc, int year,
			int companyId, String refNo, String from, String subject, String filePath, String momoNo, String date,
			int status) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			AuditEngagement auditEngagement = (AuditEngagement) session.get(AuditEngagement.class, auditEngagementId);
			auditEngagement.setAuditNotification(message);
			auditEngagement.setTo(to);
			auditEngagement.setCc(cc);
			auditEngagement.setReferenceNo(refNo);
			// auditEngagement.setDateTime(new Date());
			auditEngagement.setSubject(subject);
			auditEngagement.setFrom(from);

			auditEngagement.setYear(year);
			auditEngagement.setCompanyId(companyId);
			auditEngagement.setProcess(momoNo);
			auditEngagement.setNotificationSentDate(new Date());
			auditEngagement.setEmailStatus(status);
			session.saveOrUpdate(auditEngagement);
			session.flush();

			logger.info(String.format("(Inside saveAuditNotification)    saving AuditNotification for message to: " + to
					+ "for message" + message + "for year" + year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveAuditNotification", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		if (status == 1) {
			sendAttachmentEmail(message, to, cc, "Audit Notification", filePath, from);
		}
		return "Audit Notification saved";
	}

	public int fetchNumberofPlannedJobs(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Strategic.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(String.format("(Inside fetchNumberofPlannedJobs)   fetching NumberofPlannedJobs:for year" + year
					+ "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in fetchNumberofPlannedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public int fetchNumberofInProgressJobs(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("jobStatus", "In Progress"));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(String.format("(Inside fetchNumberofInProgressJobs)   fetching NumberofInProgressJobs year"
					+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchNumberofInProgressJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public boolean isJobCompleted(int jobId) {
		Session session = null;
		boolean completed = false;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			// 2019 may
			crit.add(Restrictions.eq("jobCreation.strategicId.strategicId", jobId));
			crit.add(Restrictions.eq("jobStatus", "In Progress"));
			if (crit.list().size() > 0) {
				completed = true;
			} else {
				completed = false;
			}
			logger.info(String
					.format("(Inside isJobInprogress) checking isJobInprogress for job" + jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in isJobInprogress", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return completed;
	}

	public boolean isJobInprogress(int jobId) {
		Session session = null;
		boolean jobInProgress = false;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			// 2019 may
			crit.add(Restrictions.eq("strategicId.strategicId", jobId));
			crit.add(Restrictions.eq("reportStatus", 3));
			if (crit.list().size() > 0) {
				jobInProgress = true;
			}
			logger.info(
					String.format("(Inside isJobCompleted) checking isJobCompleted for job" + jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in isJobCompleted", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobInProgress;
	}

	public String getJobStatus(int jobId) {
		Session session = null;
		String jobStatus = "";
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			// crit.createAlias("strategicId", "strategic");
			// crit.add(Restrictions.eq("strategic.strategicId", jobId));
			crit.add(Restrictions.eq("jobCreationId", jobId));
			if (crit.list().size() > 0) {
				JobCreation jobCreation = (JobCreation) crit.list().get(0);
				int status = jobCreation.getReportStatus();

				if (status == 0) {
					jobStatus = "";
				}
				if (status == 1) {
					jobStatus = InternalAuditConstants.EXCEPTIONSTOSENT;

				} else if (status == 2) {
					jobStatus = InternalAuditConstants.AWAITINGCOMMENTS;

				} else if (status == 3) {
					jobStatus = InternalAuditConstants.COMMENTSRECEIVED;

				} else if (status == 4) {
					jobStatus = InternalAuditConstants.REPORTISSUED;

				} else if (status == 5) {
					jobStatus = InternalAuditConstants.FINALREPORTISSUED;

				}

			}

			logger.info(String.format("(Inside getjobstatus) getting job status for job" + jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getjobstatus", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobStatus;
	}

	public String getJobCreationStatus(int jobId) {
		Session session = null;
		String jobStatus = "";
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("jobCreationId", jobId));
			if (crit.list().size() > 0) {
				JobCreation jobCreation = (JobCreation) crit.list().get(0);
				int status = jobCreation.getReportStatus();

				if (status == 0) {
					jobStatus = "";
				}
				if (status == 1) {
					jobStatus = InternalAuditConstants.EXCEPTIONSTOSENT;

				} else if (status == 2) {
					jobStatus = InternalAuditConstants.AWAITINGCOMMENTS;

				} else if (status == 3) {
					jobStatus = InternalAuditConstants.COMMENTSRECEIVED;

				} else if (status == 4) {
					jobStatus = InternalAuditConstants.REPORTISSUED;

				} else if (status == 5) {
					jobStatus = InternalAuditConstants.FINALREPORTISSUED;

				}

			}

			logger.info(String.format("(Inside getjobstatus) getting job status for job" + jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getjobstatus", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobStatus;
	}

	public int getJobCreationStatusId(int jobId) {
		Session session = null;
		int jobStatus = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("jobCreationId", jobId));
			if (crit.list().size() > 0) {
				JobCreation jobCreation = (JobCreation) crit.list().get(0);
				jobStatus = jobCreation.getReportStatus();

			}

			logger.info(String
					.format("(Inside getJobCreationStatusId) getting job status for job" + jobId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getJobCreationStatusId", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobStatus;
	}

	public int fetchNumberofCompletedJobs(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("reportStatus", 3));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(String.format("(Inside fetchNumberofCompletedJobs)fetching NumberofCompletedJobs for year"
					+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchNumberofComletedJobs", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public ArrayList<String> fetchJobsKickOffWithInaWeek(int year, int companyId) {
		Session session = null;
		ArrayList<String> jobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreation job = (JobCreation) it.next();
				// SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
				// //2018 was unparsable date
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				String startDate = job.getStartDate().replaceAll("-", "/");
				// Date date = df.parse(job.getStartDate());
				Date date = df.parse(startDate);
				DateTime enteredDate = new DateTime(date);
				DateTime currentDate = new DateTime(); // current date
				int week = enteredDate.getWeekOfWeekyear();
				int currentWeek = currentDate.getWeekOfWeekyear();
				if (week - currentWeek == 0) {// jobs kick of in this week
					jobs.add(job.getJobName());
				}
			}
			logger.info(
					String.format("(Inside fetchJobsKickOffWithInaWeek)fetching fetchJobsKickOffWithInaWeek for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobsKickOffWithInaWeek", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return jobs;
	}

	public ArrayList<String> fetchJobsDurForCompletionWithInaWeek(int year, int companyId) {
		Session session = null;
		ArrayList<String> jobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreation job = (JobCreation) it.next();
				// SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				String endDate = job.getEndDate().replaceAll("-", "/");
				Date date = df.parse(endDate);
				// Date date = df.parse(job.getEndDate());
				DateTime enteredDate = new DateTime(date);
				DateTime currentDate = new DateTime(); // current date
				int week = enteredDate.getWeekOfWeekyear();
				int currentWeek = currentDate.getWeekOfWeekyear();
				if (week - currentWeek == 0) {// jobs kick of in this week
					jobs.add(job.getJobName());
				}
			}
			logger.info(String
					.format("(Inside fetchJobsDurForCompletionWithInaWeek)fetching JobsDurForCompletionWithInaWeek for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobsDurForCompletionWithInaWeek", ex.getMessage()),
					ex);

		} finally {
			session.close();
		}
		return jobs;
	}

	public int fetchNumberOfAufitObservations(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(
					String.format("(Inside fetchNumberOfAufitObservations)fetching hNumberOfAufitObservations for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchNumberOfAufitObservations", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public int fetchNumberOfExceptionsInProgress(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.ne("finalStatus", "Approved"));
			// crit.add(Restrictions.isNotEmpty("managementComments"));
			crit.add(Restrictions.isNotNull("managementComments"));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(String
					.format("(Inside fetchNumberOfExceptionsInProgress)fetching NumberOfExceptionsInProgress for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchNumberOfExceptionsInProgress", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public int fetchNumberOfExceptionsImplemented(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 1));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(String
					.format("(Inside fetchNumberOfExceptionsImplemented)fetching NumberOfExceptionsImplemented for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchNumberOfExceptionsImplemented", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public int fetchNumberOfExceptionsOverdue(int year, int companyId) {
		Session session = null;
		int num = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			// crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 0));
			crit.add(Restrictions.le("implementaionDate", new Date()));
			crit.setProjection(Projections.rowCount());
			num = (Integer) crit.uniqueResult();

			logger.info(
					String.format("(Inside fetchNumberOfExceptionsOverdue)fetching NumberOfExceptionsOverdue for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchNumberOfExceptionsOverdue", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return num;
	}

	public ArrayList<String> fetchEmployeesAvilbleForNext2Weeks(int year, int companyId) {
		Session session = null;
		int num = 0;
		ArrayList<String> employees = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("skillId", "skill");

			crit.add(Restrictions.ne("employeeId", 0));
			// crit.createAlias("companyId", "company");
			crit.add(Restrictions.ne("company.companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {

				Employee employee = (Employee) it.next();
				ArrayList<JobCreation> jobs = fetchjobEmployeeEntity(employee.getEmployeeId());
				if (jobs.size() < 1) {
					employees.add(employee.getEmployeeName());
				} else {
					boolean available = true;
					for (int i = 0; i < jobs.size(); i++) {
						SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
						Date startDate = df.parse(jobs.get(i).getStartDate());
						Date endDate = df.parse(jobs.get(i).getEndDate());
						DateTime start = new DateTime(startDate);
						DateTime end = new DateTime(endDate);
						int startWeek = start.getWeekOfWeekyear();
						int endWeek = end.getWeekOfWeekyear();
						DateTime currentDate = new DateTime();
						int currentWeek = currentDate.getWeekOfWeekyear();
						int nextWeek = currentWeek + 1;
						if (startWeek == currentWeek || startWeek == nextWeek || endWeek == currentWeek
								|| endWeek == nextWeek) {
							available = false;
							break;
						} else if (startWeek < currentWeek && endWeek > nextWeek) {
							available = false;
							break;
						}
					}
					if (available) {
						employees.add(employee.getEmployeeName());
					}
				}
			}

			logger.info(String
					.format("(Inside fetchEmployeesAvilbleForNext2Weeks)fetching EmployeesAvilbleForNext2Weeks for year"
							+ year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmployeesAvilbleForNext2Weeks", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return employees;
	}

	public ArrayList<Integer> fetchJobSoftSkills(int jobId) {
		ArrayList<Integer> softSkillsIds = new ArrayList<Integer>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobSkillRelation.class);
			crit.createAlias("softskills", "skill");
			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobSkillRelation jobSkillRelation = (JobSkillRelation) it.next();
				int skillId = jobSkillRelation.getSoftskills().getSoftSkillId();
				softSkillsIds.add(skillId);

			}
			logger.info(String
					.format("(Inside fetchjobsoftskills)fetching job soft skills  for job" + jobId + "" + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchEmploySoftSkill", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return softSkillsIds;
	}

	public ArrayList<Strategic> fetchReportSearchResult(ArrayList<String> div, ArrayList<String> domain,
			ArrayList<String> risk, int year, int companyId) // int dept, int
	// domain, String
	// risk)
	{
		Session session = null;
		ArrayList<Strategic> dtoList = new ArrayList<Strategic>();

		// ArrayList<ReportsDTO> dtoList = new ArrayList<ReportsDTO>();

		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(Strategic.class);
			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("assignedTo", "assigned");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			// crit.add(Restrictions.eq("tab", domain));
			// crit.add(Restrictions.eq("rating", risk));
			crit.add(Restrictions.isNotNull("auditableUnit"));
			// crit.add(Restrictions.ne("auditableUnit", ""));

			Disjunction domainOR = Restrictions.disjunction();

			if (!domain.contains("All")) {
				for (int i = 0; i < domain.size(); i++) {

					domainOR.add(Restrictions.eq("tab", Integer.parseInt(domain.get(i))));
				}

				crit.add(domainOR);
			}

			Disjunction riskOR = Restrictions.disjunction();

			if (!risk.contains("All")) {
				for (int i = 0; i < risk.size(); i++) {

					riskOR.add(Restrictions.eq("rating", risk.get(i)));
				}
				crit.add(riskOR);
			}

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ReportsDTO dto = new ReportsDTO();

				Strategic strategic = (Strategic) it.next();

				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCityId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCountryId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getReportingTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);

				if (strategicHaveTheDept(strategic, div, session)) {
					dtoList.add(strategic);
				}

				// dto.setStrategic(strategic);

			}

			// u there?
			// for now forget about sending back the Departments list ..??vthis

			// }

			logger.info(String.format("(Inside fetchReportSearchResult)fetching ReportSearchResult for year" + year
					+ "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchReportSearchResult", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return dtoList;
	}

	public boolean strategicHaveTheDept(Strategic strategic, ArrayList<String> div, Session session) {

		ArrayList<StrategicDepartments> lstDept = new ArrayList<StrategicDepartments>();

		boolean available = false;

		try {

			Criteria crit = session.createCriteria(StrategicDepartments.class);
			crit.createAlias("department", "dept");

			crit.add(Restrictions.eq("strategic", strategic.getId()));
			if (!div.contains("All")) {
				Disjunction or = Restrictions.disjunction();
				for (int i = 0; i < div.size(); i++) {

					or.add(Restrictions.eq("dept.departmentId", Integer.parseInt(div.get(i))));
				}
				crit.add(or);
			}
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicDepartments depts = (StrategicDepartments) it.next();
				strategic.setDivisionName(depts.getDepartment().getDepartmentName());
			}
			if (crit.list().size() > 0 || div.contains("All")) {
				available = true;
			}

			else {
				available = false;
			}

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in strategicHaveTheDept", ex.getMessage()), ex);

		} finally {
		}

		return available;

	}

	//
	public boolean jobHavetheResources(JobCreation job, ArrayList<String> resources, Session session) {

		ArrayList<StrategicDepartments> lstDept = new ArrayList<StrategicDepartments>();

		boolean available = false;

		try {

			Criteria crit = session.createCriteria(JobEmployeeRelation.class);
			crit.createAlias("employeeId", "employee");
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("jobCreation.jobCreationId", job.getJobCreationId()));
			ArrayList<String> employees = new ArrayList<String>();
			if (!resources.contains("All")) {
				Disjunction or = Restrictions.disjunction();

				for (int i = 0; i < resources.size(); i++) {

					or.add(Restrictions.eq("employee.employeeId", Integer.parseInt(resources.get(i))));

				}
				crit.add(or);
			}
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobEmployeeRelation jobrelation = (JobEmployeeRelation) it.next();
				String employee = jobrelation.getEmployee().getEmployeeName();
				employees.add(employee);

			}
			if (crit.list().size() > 0 || resources.contains("All")) {
				available = true;
			}

			else {
				available = false;
			}
			job.setEmployees(employees);

			logger.info(String.format("(Inside jobHavetheResources)checking jobHavetheResources for job"
					+ job.getJobName() + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in jobHavetheResources", ex.getMessage()), ex);

		} finally {
		}

		return available;

	}

	public ArrayList<StrategicDepartments> fetchStrategicDepartmentsMutiple(ArrayList<Integer> ids) {

		ArrayList<StrategicDepartments> strategicDepartments = new ArrayList<StrategicDepartments>();
		try {

			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(StrategicDepartments.class);
			crit.createAlias("department", "dept");
			crit.add(Restrictions.in("strategic", ids));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				StrategicDepartments strategicDept = (StrategicDepartments) it.next();
				strategicDepartments.add(strategicDept);

			}
			logger.info(
					String.format("(Inside fetchStrategicDepartmentsMutiple)fetching StrategicDepartmentsMutiple for id"
							+ ids + "" + new Date()));
		} catch (Exception ex) {
			logger.warn(
					String.format("Exception occured in fetchStrategicDepartmentsMutiple", ex.getLocalizedMessage()),
					ex);

		} finally {

		}
		return strategicDepartments;

	}

	public String exportToExcel(ArrayList<ExcelDataDTO> excelDataList, String rootDir) {
		try {

			FileOutputStream fileOut = new FileOutputStream(rootDir + "/InternalAuditReport/report.xls");// "D:\\POI111.xls"
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Orders Worksheet");
			HSSFRow row = worksheet.createRow((short) 0);
			row.createCell((short) 0).setCellValue("Objective");
			row.createCell((short) 1).setCellValue("Auditable Unit");
			row.createCell((short) 2).setCellValue("Domain");
			row.createCell((short) 3).setCellValue("Division");
			row.createCell((short) 4).setCellValue("Risk Assesment");

			for (int i = 0; i < excelDataList.size(); i++) {
				HSSFRow row1 = worksheet.createRow((short) i + 1);
				row1.createCell((short) 0).setCellValue(excelDataList.get(i).getObjective());
				row1.createCell((short) 1).setCellValue(excelDataList.get(i).getAuditableUnit());
				row1.createCell((short) 2).setCellValue(excelDataList.get(i).getDomain());
				row1.createCell((short) 3).setCellValue(excelDataList.get(i).getDivision());
				row1.createCell((short) 4).setCellValue(excelDataList.get(i).getRiskAssesment());

			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

			logger.info(String.format("(Inside exportToExcel)exporting ToExcel for rootDir" + rootDir + "for excel data"
					+ excelDataList + "" + new Date()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("excel sheet: downloaded");
		return "exported";
	}

	public ArrayList<Strategic> fetchReportAuditScheduling(ArrayList<String> div, ArrayList<String> domain,
			ArrayList<String> jobStatus, ArrayList<String> responsiblePerson, int year, int companyId) {

		Session session = null;
		ArrayList<Strategic> dtoList = new ArrayList<Strategic>();

		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(Strategic.class);
			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("assignedTo", "assigned");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			// crit.add(Restrictions.eq("tab", domain));
			// crit.add(Restrictions.eq("rating", risk));

			Disjunction domainOR = Restrictions.disjunction();

			if (!domain.contains("All")) {
				for (int i = 0; i < domain.size(); i++) {

					domainOR.add(Restrictions.eq("tab", Integer.parseInt(domain.get(i))));
				}

				crit.add(domainOR);
			}

			Disjunction jobStatusOR = Restrictions.disjunction();

			// if (!jobStatus.contains("All")) {
			// for (int i = 0; i < jobStatus.size(); i++) {
			//
			// jobStatusOR.add(Restrictions.eq("rating", jobStatus.get(i)));
			// }
			// crit.add(jobStatusOR);
			// }

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				ReportsDTO dto = new ReportsDTO();

				Strategic strategic = (Strategic) it.next();

				HibernateDetachUtility.nullOutUninitializedFields(strategic,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategic.getApprovedBy(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getApprovedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				HibernateDetachUtility.nullOutUninitializedFields(strategic.getInitiatedBy().getCityId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCountryId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getReportingTo(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getAssignedTo().getCityId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(
				// strategic.getApprovedBy().getReportingTo().getCityId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(
				// strategic.getApprovedBy().getReportingTo().getCountryId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(
				// strategic.getApprovedBy().getReportingTo().getSkillId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCityId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION); //
				// HERE Objective owner
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getCountryId(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				// HibernateDetachUtility.nullOutUninitializedFields(strategic.getObjectiveOwner().getReportingTo(),
				// HibernateDetachUtility.SerializationType.SERIALIZATION);
				JobCreation job = getJob(strategic.getId());
				if (jobHavetheResources(job, responsiblePerson, session)) {

					if (strategicHaveTheDept(strategic, div, session)) {

						if (strategicHaveTheJobStatus(strategic, jobStatus)) {
							dtoList.add(strategic);

							String jobName = job.getJobName();
							strategic.setJobName(jobName);
							strategic.setEstimatedWeeks(job.getEstimatedWeeks() + "");
							strategic.setEmployees(job.getEmployees());
						}

					}
				}
			}
			logger.info(String
					.format("(Inside fetchReportAuditScheduling)fetching ReportAuditScheduling ToExcel for responsibleperson"
							+ responsiblePerson + "for year" + year + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchReportAuditScheduling", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return dtoList;
	}

	private JobCreation getJob(int strategicId) {

		JobCreation job = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.createAlias("strategicId", "strateg");
			crit.add(Restrictions.eq("strateg.strategicId", strategicId));
			if (crit.list().size() > 0) {
				job = (JobCreation) crit.list().get(0);

			}
			logger.info(String
					.format("(Inside JobCreation) getting JobCreation dtrategic" + strategicId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in getJobName", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return job;

	}

	private boolean strategicHaveTheJobStatus(Strategic strategic, ArrayList<String> jobStatus) {

		boolean haveJobStatus = false;

		if (!jobStatus.contains("All")) {
			for (int i = 0; i < jobStatus.size(); i++) {
				if (jobStatus.get(i).equalsIgnoreCase("completed")) {
					if (isJobCompleted(strategic.getId())) {
						haveJobStatus = true;
						strategic.setJobStatus("Completed");
					}
				}

				if (jobStatus.get(i).equalsIgnoreCase("in progress")) {
					if (isJobInprogress(strategic.getId())) {
						haveJobStatus = true;
						strategic.setJobStatus("In Progress");
					}
				}

				if (jobStatus.get(i).equalsIgnoreCase("planned")) {
					if (!isJobInprogress(strategic.getId()) && !isJobCompleted(strategic.getId())) {
						haveJobStatus = true;
						strategic.setJobStatus("Planned");
					}
				}
			}
		}

		else {
			haveJobStatus = true;
			if (isJobCompleted(strategic.getId())) {
				strategic.setJobStatus("Completed");
			} else if (isJobInprogress(strategic.getId())) {
				strategic.setJobStatus("In Progress");
			} else {
				strategic.setJobStatus("Planned");
			}

		}

		String status = getJobStatus(strategic.getId());
		if (!status.equalsIgnoreCase("")) {
			strategic.setJobStatus(status);
		}

		return haveJobStatus;

	}

	public String approveFinalAuditable(Strategic strategic) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Strategic strategicEntity = (Strategic) session.get(Strategic.class, strategic.getId());
			strategicEntity.setApprovedByAuditHead(strategic.isApprovedByAuditHead());
			strategicEntity.setComments(strategic.getComments());
			session.update(strategicEntity);
			session.flush();

			logger.info(String.format("(Inside approveFinalAuditable)approving FinalAuditable for strategic approved b"
					+ strategic.getApprovedBy() + "" + new Date()));
			return "finalAuditableApproved";

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in approveFinalAuditable", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	public String declineFinalAuditable(Strategic strategic) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Strategic strategicEntity = (Strategic) session.get(Strategic.class, strategic.getId());
			strategicEntity.setApprovedByAuditHead(strategic.isApprovedByAuditHead());
			strategicEntity.setAudit(strategic.isAudit());
			strategicEntity.setComments(strategic.getComments());
			strategicEntity.setPhase(4);
			session.update(strategicEntity);
			session.flush();

			logger.info(String.format("(Inside declineFinalAuditable)declining FinalAuditable for strategic approved b"
					+ strategic.getApprovedBy() + "" + new Date()));

			return "finalAuditableDeclined";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in declineFinalAuditable", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	public String declineFinalAudit(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Strategic strategicEntity = (Strategic) session.load(Strategic.class, id);
			int year = getCurrentYear();
			strategicEntity.setYear(year);
			session.update(strategicEntity);
			session.flush();
			logger.info(String.format("(Inside declineFinalAudit)declining FinalAudit for id" + id + "" + new Date()));

			return "finalAuditableDeclined";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in declineFinalAuditable", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	public int getCurrentYear1() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}

	public String saveEmployee(Employee employee, int year, int companyId) {
		Session session = null;
		if (userAvailable(employee.getEmail())) {
			try {
				session = sessionFactory.openSession();
				session.save(employee);

				if (employee.getReportingTo().getEmployeeId() == 0) {
					employee.setReportingTo(employee);
				}
				session.update(employee);
				session.flush();
				if (employee.getRollId() != 4 && employee.getRollId() != 5) {
					addAvailableHoursInSkills(employee.getSkillId().getSkillId(),
							employee.getTotalNumberOfHoursAvailable(), year, companyId);

				}
				logger.info(String.format("(Inside saveEmployee)saving Employee for year:" + year + "for company"
						+ companyId + "for employ" + employee.getEmployeeName() + "" + new Date()));
				return "user saved";
			} catch (Exception ex) {
				logger.warn(String.format("Exception occured in saveEmploy", ex.getMessage()), ex);
				return null;
			} finally {
				session.close();
			}
		} else {
			return InternalAuditConstants.USERNOTAVAILABLE;
		}
	}

	// private void addAvailableHoursInSkills(int skillId, int hours, int year,
	// int companyId) {
	// Session session = null;
	// try{
	// session = sessionFactory.openSession();
	// Criteria crit = session.createCriteria(Skills.class);
	// crit.add(Restrictions.eq("skillId", skillId));
	//// crit.add(Restrictions.eq("year", year));
	//// crit.add(Restrictions.eq("companyId", companyId));
	// Skills skill = (Skills) crit.list().get(0);
	// int currentHours = skill.getAvailableHours();
	// int newHours = currentHours + hours;
	// skill.setAvailableHours(newHours);
	// session.update(skill);
	// session.flush();
	// }catch(Exception ex){
	// logger.warn(String.format("Exception occured in
	// addAvailableHoursInSkills", ex.getMessage()), ex);
	//
	// }
	// finally{
	// session.close();
	// }
	//
	// }

	private boolean userAvailable(String email) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.add(Restrictions.eq("email", email));
			if (crit.list().size() > 0) {
				return false;
			} else {
				logger.info(String
						.format("(Inside userAvailable)CHECKING userAvailable for EMAIL:" + email + "" + new Date()));
				return true;
			}

		} catch (Exception ex) {
			System.out.println("fail in userAvailable");
		}
		return false;
	}

	private boolean userAvailable(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	private void addAvailableHoursInSkills(int skillId, int hours, int year, int companyId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(CompanySkillRelation.class);
			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.eq("skill.skillId", skillId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			CompanySkillRelation companySkillRelation = null;
			if (crit.list().size() > 0) {
				companySkillRelation = (CompanySkillRelation) crit.list().get(0);
				int currentHours = companySkillRelation.getAvailableHours();
				int newHours = currentHours + hours;
				companySkillRelation.setAvailableHours(newHours);
				session.update(companySkillRelation);
			} else {
				companySkillRelation = new CompanySkillRelation();
				Skills skills = (Skills) session.get(Skills.class, skillId);
				companySkillRelation.setSkillId(skills);
				companySkillRelation.setAvailableHours(hours);
				companySkillRelation.setCompanyId(companyId);
				companySkillRelation.setYear(year);
				session.save(companySkillRelation);
			}
			session.flush();
			logger.info(String.format("(Inside addAvailableHoursInSkills)adding AvailableHoursInSkills for skill:"
					+ skillId + "for hours" + hours + "for year" + year + "for company" + companyId + "" + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in addAvailableHoursInSkills", ex.getMessage()), ex);

		} finally {
			session.close();
		}

	}

	public String saveCompany(Company company) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.save(company);
			session.flush();
			logger.info(String.format(
					"(Inside saveCompany)saving Company for Company name:" + company.getName() + "" + new Date()));
			return "company added";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveCompany", ex.getMessage()), ex);
			return "company dding failed";
		} finally {
			session.close();
		}

	}

	public ArrayList<Company> fetchCompanies() {
		Session session = null;
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Company.class);
			crit.add(Restrictions.ne("companyId", 0));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Company company = (Company) it.next();
				companies.add(company);
			}
			logger.info(String
					.format("(Inside fetchCompanies)fetching  Company for companies:" + companies + "" + new Date()));
			return companies;// Return BEFORE catch Statement..

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchCompanies", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	// public ArrayList<Rolls> () {
	// Session session = null;
	// ArrayList<Rolls> rolls = new ArrayList<Rolls>();
	// try {
	// session = sessionFactory.openSession();
	// Criteria crit = session.createCriteria(Rolls.class);
	// crit.add(Restrictions.ne("rollId", 0));
	// List rsList = crit.list();
	// for (Iterator it = rsList.iterator(); it.hasNext();) {
	// Rolls roll = (Rolls) it.next();
	// rolls.add(roll);
	// }
	// logger.info(String.format("(Inside fetchRolls)fetching Rolls for rolls:"
	// + rolls + "" + new Date()));
	// return rolls;// Return BEFORE catch Statement..
	//
	// } catch (Exception ex) {
	// logger.warn(String.format("Exception occured in fetchRoles",
	// ex.getMessage()), ex);
	// return null;
	// } finally {
	// session.close();
	// }
	// }

	public String updateStrategic(Strategic strategic) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.update(strategic);
			session.flush();

			logger.info(String.format("(Inside updateStrategic)updating  Strategic for strategic initiated by:"
					+ strategic.getInitiatedBy() + "" + new Date()));
			return "strategic updated";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in updateStrategic", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	public String deleteRisk(RiskControlMatrixEntity risk) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			risk.setInitiatedBy((Employee) session.get(Employee.class, risk.getInitiatedBy().getEmployeeId()));
			risk.setApprovedBy((Employee) session.get(Employee.class, risk.getApprovedBy().getEmployeeId()));
			session.update(risk);
			session.delete(risk);
			session.flush();
			logger.info(String.format("(Inside deleteRisk)deleting  risk for risk description:" + "" + new Date()));
			return "risk deleted";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in deleteRisk", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	public String deleteAuditWok(int auditWorkId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			AuditWork auditWork = (AuditWork) session.load(AuditWork.class, auditWorkId);
			auditWork.setStatus(InternalAuditConstants.DELETED);
			session.update(auditWork);
			session.flush();
			logger.info(String
					.format("(Inside deleteAuditWork)deleting  auditwork for audit" + auditWorkId + "" + new Date()));
			return "auditWork deleted";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in deleteauditWork", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	public String checkNoOfResourcesForSelectedSkill(int noOfResources, int skillId, int companyId) {
		Session session = null;
		String result = "";
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.eq("skill.skillId", skillId));
			crit.add(Restrictions.eq("companyId", companyId));
			if (crit.list().size() >= noOfResources) {
				result = "Available";
			} else {
				result = "Only " + crit.list().size() + " Resources available for this Skill";
			}
			logger.info(String
					.format("(Inside checkNoOfResourcesForSelectedSkill)checking NoOfResourcesForSelectedSkill for skill:"
							+ skillId + "for resources" + noOfResources + "for company" + companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in checkNoOfResourcesForSelectedSkill", ex.getMessage()), ex);
			System.out.println("Exception occured in checkNoOfResourcesForSelectedSkill");

			return null;
		} finally {
			session.close();
		}
		return result;
	}

	public String deleteException(int exceptionId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Exceptions exception = (Exceptions) session.load(Exceptions.class, exceptionId);
			session.delete(exception);
			session.flush();
			logger.info(String.format(
					"(Inside deleteException)deleting Exception for exception:" + exceptionId + "" + new Date()));

			return "exception deleted";

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in deleteException", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
	}

	/*
	 * public String approveScheduling(int companyId, int year) throws Exception
	 * { Session session = null; try { session = sessionFactory.openSession();
	 * Criteria crit = session.createCriteria(JobCreation.class);
	 * crit.add(Restrictions.eq("companyId", companyId));
	 * crit.add(Restrictions.eq("year", year));
	 * 
	 * List rsList = crit.list(); for (Iterator it = rsList.iterator();
	 * it.hasNext();) { JobCreation job = (JobCreation) it.next(); if
	 * (job.getStartDate() == null || job.getEndDate() == null) { return
	 * "Jobs scheduling required"; } job.setApproved(true); session.update(job);
	 * session.flush(); } logger.info(String.
	 * format("(Inside approveScheduling)approving Schedulingexception for year:"
	 * + year + "for company" + companyId + "" + new Date()));
	 * 
	 * return approveSchedulingInJobTimeEstimation(companyId);
	 * 
	 * } catch (Exception ex) {
	 * logger.warn(String.format("Exception occured in approveScheduling",
	 * ex.getMessage()), ex); return null; } finally { session.close(); }
	 * 
	 * }
	 */
	public boolean isScheduleApproved(int companyId, int year) throws Exception {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("approved", false));

			List rsList = crit.list();
			if (rsList.size() > 0) {
				return false;
			}
			logger.info(String.format("(Inside isScheduleApproved)checking isScheduleApproved for year:" + year
					+ "for company" + companyId + "" + new Date()));
			return true;
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in isScheduleApproved", ex.getMessage()), ex);
			return false;
		} finally {
			session.close();
		}

	}

	public String approveSchedulingInJobTimeEstimation(int companyId) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobTimeEstimation.class);
			crit.add(Restrictions.eq("companyId", companyId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobTimeEstimation job = (JobTimeEstimation) it.next();

				job.setApproved(true);
				session.update(job);
				session.flush();
			}
			logger.info(String
					.format("(Inside approveSchedulingInJobTimeEstimation)approving SchedulingInJobTimeEstimation for Company:"
							+ companyId + "" + new Date()));

			return "Scedule Approved";
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in approveSchedulingInJobTimeEstimation", ex.getMessage()),
					ex);
			return null;
		} finally {
			session.close();
		}

	}

	public Employee fetchSelectedEmployee(int employeeId) throws Exception {
		Session session = null;
		Employee employee = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Employee.class);
			crit.createAlias("cityId", "city");
			crit.createAlias("countryId", "countryId");

			// crit.createAlias("companyId", "company");
			crit.createAlias("skillId", "skill");
			crit.add(Restrictions.ne("employeeId", employeeId));
			// crit.add(Restrictions.eq("companyId", companyId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				employee = (Employee) it.next();
				// employees.add(employee);
				// employee = (Employee) session.get(Employee.class,
				// employeeId);
			}

			// // Criteria crit = session.createCriteria(Employee.class);
			// // crit.add(Restrictions.eq(employeeId, value))
			// Criteria crit = session.createCriteria(Employee.class);
			// crit.createAlias("cityId", "city");
			// crit.createAlias("counntryId", "country");
			// crit.add(employee);

			logger.info(String.format("(Inside fetchSelectedEmployee)fetching SelectedEmployee for employe:"
					+ employeeId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchSelectedEmployee", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return employee;
	}

	public String updateUser(int previousHours, Employee employee) throws Exception {
		try {
			session = sessionFactory.openSession();
			session.update(employee);

			if (employee.getReportingTo().getEmployeeId() == 0) {
				employee.setReportingTo(employee);
			}
			session.saveOrUpdate(employee);
			session.save(employee);
			session.flush();
			if (employee.getRollId() != 4 && employee.getRollId() != 5) {
				// addAvailableHoursInSkills(employee.getSkillId().getSkillId(),
				// employee.getTotalNumberOfHoursAvailable(), year, companyId);
				logger.info(String.format("(Inside updateUser)updating Userfor employe:" + employee.getEmployeeName()
						+ "for previous hours" + previousHours + "" + new Date()));
			}
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in updateUSer", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return "user updated";
	}

	public ArrayList<JobCreation> fetchReportWithResourcesSearchResult(ArrayList<String> div, ArrayList<String> domain,
			ArrayList<String> risk, ArrayList<String> resources, int year, int companyId) {
		Session session = null;
		ArrayList<JobCreation> jobs = new ArrayList<JobCreation>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			jobsStrategicAlias(crit);

			Disjunction domainOR = Restrictions.disjunction();

			if (!domain.contains("All")) {
				for (int i = 0; i < domain.size(); i++) {

					domainOR.add(Restrictions.eq("domainText", domain.get(i)));
				}

				crit.add(domainOR);
			}

			// Disjunction resourcesOR = Restrictions.disjunction();
			//
			// if ( ! resources.contains("All") )
			// {
			// for(int i=0; i< resources.size(); i++){
			//
			// resourcesOR.add(Restrictions.eq("assigned.employeeId",
			// Integer.parseInt(resources.get(i))));
			// }
			//
			// crit.add(resourcesOR);
			// }

			Disjunction riskOR = Restrictions.disjunction();

			if (!risk.contains("All")) {
				for (int i = 0; i < risk.size(); i++) {

					riskOR.add(Restrictions.eq("riskRating", risk.get(i)));
				}
				crit.add(riskOR);
			}

			Disjunction divisionOR = Restrictions.disjunction();

			if (!div.contains("All")) {
				for (int i = 0; i < div.size(); i++) {
					divisionOR.add(Restrictions.eq("relevantDept", div.get(i)));

				}
				crit.add(divisionOR);
			}

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {

				JobCreation job = (JobCreation) it.next();

				if (jobHavetheResources(job, resources, session)) {
					jobs.add(job);
				}

				// dto.setStrategic(strategic);
			}
			logger.info(String
					.format("(Inside fetchReportWithResourcesSearchResult)fetchReportWithResourcesSearchResult for year"
							+ year + "for company" + companyId + "" + new Date()));

			// u there?
			// for now forget about sending back the Departments list ..??vthis

			// }

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchReportWithResourcesSearchResult", ex.getMessage()),
					ex);
			return null;
		} finally {
			session.close();
		}
		return jobs;
	}

	public ArrayList<Integer> fetchJobsInExceptionReport(ArrayList<String> div, ArrayList<String> domain,
			ArrayList<String> risk, ArrayList<String> resources, ArrayList<String> jobs, int year, int companyId) {
		Session session = null;
		ArrayList<Integer> jobIds = new ArrayList<Integer>();
		ArrayList<Exception> exceptions = new ArrayList<Exception>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			Disjunction domainOR = Restrictions.disjunction();

			if (!domain.contains("All")) {
				for (int i = 0; i < domain.size(); i++) {

					domainOR.add(Restrictions.eq("domainText", domain.get(i)));
				}

				crit.add(domainOR);
			}

			Disjunction riskOR = Restrictions.disjunction();

			if (!risk.contains("All")) {
				for (int i = 0; i < risk.size(); i++) {

					riskOR.add(Restrictions.eq("riskRating", risk.get(i)));
				}
				crit.add(riskOR);
			}

			Disjunction divisionOR = Restrictions.disjunction();

			if (!div.contains("All")) {
				for (int i = 0; i < div.size(); i++) {
					divisionOR.add(Restrictions.eq("relevantDept", div.get(i)));

				}
				crit.add(divisionOR);
			}

			Disjunction jobsOR = Restrictions.disjunction();

			if (!jobs.contains("All")) {
				for (int i = 0; i < jobs.size(); i++) {
					jobsOR.add(Restrictions.eq("jobCreationId", Integer.parseInt(jobs.get(i))));

				}
				crit.add(jobsOR);
			}

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {

				JobCreation job = (JobCreation) it.next();

				if (jobHavetheResources(job, resources, session)) {
					jobIds.add(job.getJobCreationId());
				}
			}

			logger.info(String.format("(Inside fetchJobsInExceptionReport)fetching JobsInExceptionReport for year"
					+ year + "for company" + companyId + "for job" + jobs + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobsInExceptionReport", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return jobIds;

	}

	public ArrayList<Exceptions> fetchExceptionReports(ArrayList<String> div, ArrayList<String> domain,
			ArrayList<String> risk, ArrayList<String> resources, ArrayList<String> jobs, ArrayList<String> auditees,
			ArrayList<String> exceptionStatus, int year, int companyId) {

		ArrayList<Integer> jobIds = fetchJobsInExceptionReport(div, domain, risk, resources, jobs, year, companyId);
		if (jobIds == null || jobIds.size() < 1)
			return null;
		ArrayList<Exceptions> exceptionsList = new ArrayList<Exceptions>();
		Session session = null;
		// ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			// crit.createAlias("responsiblePerson", "respPerson");

			///////////////////////

			crit.createAlias("responsiblePerson", "employee");
			crit.createAlias("employee.countryId", "employeeCount");
			crit.createAlias("employee.cityId", "employeeCity");
			crit.createAlias("employee.reportingTo", "employeeRep");
			crit.createAlias("employeeRep.countryId", "employeeRCount");
			crit.createAlias("divisionHead", "divHead");
			crit.createAlias("divHead.countryId", "divHeadCount");
			crit.createAlias("divHead.cityId", "divHeadCity");
			crit.createAlias("divHead.reportingTo", "divHeadRep");

			crit.createAlias("divHead.skillId", "divHeadSkill");

			crit.createAlias("divHeadRep.skillId", "divHeadSkill1");

			crit.createAlias("employee.skillId", "employeeSkill");

			crit.createAlias("employeeRep.skillId", "employeeSkill2");

			// crit.createAlias("divHead.countryId", "divHeadRCount");

			///////////////////////

			crit.add(Restrictions.ne("employee.employeeId", 0));
			crit.add(Restrictions.ne("divHead.employeeId", 0));
			Disjunction jobsOR = Restrictions.disjunction();

			for (int i = 0; i < jobIds.size(); i++) {

				jobsOR.add(Restrictions.eq("jobCreation.jobCreationId", jobIds.get(i)));
			}

			crit.add(jobsOR);

			Disjunction jobsORInExceptions = Restrictions.disjunction();

			Disjunction auditeeOR = Restrictions.disjunction();

			if (!auditees.contains("All")) {
				for (int i = 0; i < auditees.size(); i++) {

					auditeeOR.add(Restrictions.eq("employee.employeeId", Integer.parseInt(auditees.get(i))));
				}
				crit.add(auditeeOR);
			}

			Disjunction exceptionStatusOR = Restrictions.disjunction();

			if (!exceptionStatus.contains("All")) {
				for (int i = 0; i < exceptionStatus.size(); i++) {
					if (exceptionStatus.get(i).equals(InternalAuditConstants.APPROVEDBYIAHEAD)) {
						exceptionStatusOR.add(Restrictions.eq("finalStatus", "Approved"));
					} else if (exceptionStatus.get(i).equals(InternalAuditConstants.COMMUNICATEDTOMGM)) {
						exceptionStatusOR.add(Restrictions.conjunction().add(Restrictions.eq("status", "Approved"))
								.add(Restrictions.eq("implementaionComments", null)));
					} else if (exceptionStatus.get(i).equals(InternalAuditConstants.AGREEDBYMGM)) {
						// exceptionStatusOR.add(Restrictions.eq("isAgreed",
						// true));
						// exceptionStatusOR.add(Restrictions.eq("isImplemented",
						// false));
						exceptionStatusOR.add(Restrictions.conjunction().add(Restrictions.eq("isAgreed", true))
								.add(Restrictions.eq("isImplemented", false)));
					} else if (exceptionStatus.get(i).equals(InternalAuditConstants.NOTAGREED)) {
						exceptionStatusOR.add(Restrictions.eq("isAgreed", false));
					} else if (exceptionStatus.get(i).equals(InternalAuditConstants.IMPLEMENTATIONINPROGRESS)) {
						// exceptionStatusOR.add(Restrictions.eq("isAgreed",
						// true));
						// exceptionStatusOR.add(Restrictions.eq("isImplemented",
						// false));
						exceptionStatusOR.add(Restrictions.conjunction().add(Restrictions.eq("isAgreed", true))
								.add(Restrictions.eq("isImplemented", false)));
					} else if (exceptionStatus.get(i).equals(InternalAuditConstants.IMPLEMENTATIONDELAYED)) {
						// exceptionStatusOR.add(Restrictions.lt("dueDate", new
						// Date()));
						// exceptionStatusOR.add(Restrictions.eq("isImplemented",
						// false));
						exceptionStatusOR.add(Restrictions.conjunction().add(Restrictions.lt("dueDate", new Date()))
								.add(Restrictions.eq("isImplemented", false)));
					} else if (exceptionStatus.get(i).equals(InternalAuditConstants.IMPLEMENTATIONCOMPLETED)) {
						exceptionStatusOR.add(Restrictions.conjunction().add(Restrictions.eq("finalStatus", null)))
								.add(Restrictions.eq("isImplemented", true));
					}

				}
				crit.add(exceptionStatusOR);
			}

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {

				Exceptions exception = (Exceptions) it.next();
				String status = getExceptionStatus(exception);
				exception.setDisplayStatus(status);
				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);

				exceptionsList.add(exception);

			}

			logger.info(String.format("(Inside " + "Reports)fetching ExceptionReports for year" + year + "for company"
					+ companyId + "for job" + jobs + "for exceptionstatus" + exceptionStatus + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchExceptionReports", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}
		return exceptionsList;
	}

	public String getExceptionStatus(Exceptions exception) {
		String exceptionStatus = "";
		if (exception.getFinalStatus() != null && exception.getFinalStatus().equals("Approved")) {
			exceptionStatus = InternalAuditConstants.APPROVEDBYIAHEAD;
		} else if (exception.getStatus() != null && exception.getStatus().equals("Approved")
				&& exception.getImplementaionComments() == null) {
			exceptionStatus = InternalAuditConstants.COMMUNICATEDTOMGM;
		} else if (exception.getIsAgreed() == 1 && exception.getIsImplemented() == 0) {
			exceptionStatus = InternalAuditConstants.AGREEDBYMGM;
		} else if (exception.getIsAgreed() == 0) {
			exceptionStatus = InternalAuditConstants.NOTAGREED;
		} else if (exception.getDueDate().before(new Date()) && exception.getIsImplemented() == 0) {
			exceptionStatus = InternalAuditConstants.IMPLEMENTATIONDELAYED;
		} else if (exception.getFinalStatus() == null && exception.getIsImplemented() == 1) {
			exceptionStatus = InternalAuditConstants.IMPLEMENTATIONDELAYED;
		}
		return exceptionStatus;
	}

	public String exportJobTimeAllocationReportToExcel(ArrayList<JobTimeAllocationReportDTO> excelDataList,
			String rootDir) {
		try {

			FileOutputStream fileOut = new FileOutputStream(rootDir + "/InternalAuditReport/report.xls");// "D:\\POI111.xls"
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("JobTime Allocation Worksheet");
			HSSFRow row = worksheet.createRow((short) 0);
			row.createCell((short) 0).setCellValue("Job");
			row.createCell((short) 1).setCellValue("Weeks");

			for (int i = 0; i < excelDataList.size(); i++) {
				HSSFRow row1 = worksheet.createRow((short) i + 1);
				row1.createCell((short) 0).setCellValue(excelDataList.get(i).getJob());
				row1.createCell((short) 1).setCellValue(excelDataList.get(i).getWeeks());

			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

			logger.info(String
					.format("(Inside exportJobTimeAllocationReportToExcel)exporting JobTimeAllocationReportToExcelyear for data list"
							+ excelDataList + "for dir" + rootDir + "" + new Date()));

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Jobtime Allocation excel sheet: downloaded");
		return "exported";
	}

	public String exportExceptionsReportToExcel(ArrayList<ExceptionsReportDTO> excelDataList, String rootDir) {
		try {

			FileOutputStream fileOut = new FileOutputStream(rootDir + "/InternalAuditReport/report.xls");// "D:\\POI111.xls"
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Exceptions Worksheet");
			HSSFRow row = worksheet.createRow((short) 0);
			row.createCell((short) 0).setCellValue("Exception");
			row.createCell((short) 1).setCellValue("Job");
			row.createCell((short) 2).setCellValue("Exception Status");
			row.createCell((short) 3).setCellValue("Auditee");

			for (int i = 0; i < excelDataList.size(); i++) {
				HSSFRow row1 = worksheet.createRow((short) i + 1);
				row1.createCell((short) 0).setCellValue(excelDataList.get(i).getExceptionName());
				row1.createCell((short) 1).setCellValue(excelDataList.get(i).getJobName());
				row1.createCell((short) 2).setCellValue(excelDataList.get(i).getExceptionStatus());
				row1.createCell((short) 3).setCellValue(excelDataList.get(i).getAuditee());

			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

			logger.info(String
					.format("(Inside exportExceptionsReportToExcel)exporting ExceptionsReportToExcel for data list"
							+ excelDataList + "for dir" + rootDir + "" + new Date()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Exceptions excel sheet: downloaded");
		return "exported";
	}

	public String exportAuditSchedulingReportToExcel(ArrayList<AuditSchedulingReportDTO> excelDataList,
			String rootDir) {
		try {

			FileOutputStream fileOut = new FileOutputStream(rootDir + "/InternalAuditReport/report.xls");// "D:\\POI111.xls"
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Audit Scheduling Worksheet");
			HSSFRow row = worksheet.createRow((short) 0);
			row.createCell((short) 0).setCellValue("Job");
			row.createCell((short) 1).setCellValue("Domain");
			row.createCell((short) 2).setCellValue("Division");
			row.createCell((short) 3).setCellValue("Risk");
			row.createCell((short) 4).setCellValue("Resources");
			row.createCell((short) 5).setCellValue("Time Allocated");

			for (int i = 0; i < excelDataList.size(); i++) {
				HSSFRow row1 = worksheet.createRow((short) i + 1);
				row1.createCell((short) 0).setCellValue(excelDataList.get(i).getJob());
				row1.createCell((short) 1).setCellValue(excelDataList.get(i).getDomain());
				row1.createCell((short) 2).setCellValue(excelDataList.get(i).getDivision());
				row1.createCell((short) 3).setCellValue(excelDataList.get(i).getRiskAssesment());
				row1.createCell((short) 4).setCellValue(excelDataList.get(i).getResources());
				row1.createCell((short) 5).setCellValue(excelDataList.get(i).getTimeAllocated() + "weeks");

			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			logger.info(String
					.format("(Inside exportAuditSchedulingReportToExcel)exporting AuditSchedulingReportToExcel for data list"
							+ excelDataList + "for dir" + rootDir + "" + new Date()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Audit Scheduling excel sheet: downloaded");
		return "exported";
	}

	public ArrayList<String> fetchjobsInExecution(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> jobsInExecution = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("jobStatus", "In Progress"));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditEngagement auditEng = (AuditEngagement) it.next();

				JobCreation job = auditEng.getJobCreation();
				if (!isJobAuditStepApproved(job.getJobCreationId())) {/////// FOR
					/////// JOBS
					/////// WHICH
					/////// AUDIT
					/////// STEP
					/////// IS
					/////// APPROVED
					jobsInExecution.add(job.getJobName());
				}
			}

			logger.info(String.format("(Inside fetchjobsInExecution)fetching fetchjobsInExecution for year" + year
					+ "for company" + companyId + "" + new Date()));

			return jobsInExecution;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchjobsInExecution");

			throw e;
		}

	}

	public ArrayList<String> fetchjobsInPlanning(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> jobsInExecution = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("jobStatus", "Not Started"));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditEngagement auditEng = (AuditEngagement) it.next();

				JobCreation job = auditEng.getJobCreation();
				if (!isJobAuditStepApproved(job.getJobCreationId())) {/////// FOR
					/////// JOBS
					/////// WHICH
					/////// AUDIT
					/////// STEP
					/////// IS
					/////// APPROVED
					jobsInExecution.add(job.getJobName());
				}
				logger.info(String.format("(Inside fetchjobsInPlanning)fetching jobsInPlanning for year" + year
						+ "for company" + companyId + "" + new Date()));

			}
			return jobsInExecution;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchjobsInExecution");

			throw e;
		}

	}

	public ArrayList<String> fetchjobsInReporting(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> jobsInExecution = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditEngagement.class);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("jobStatus", "In Progress"));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditEngagement auditEng = (AuditEngagement) it.next();

				JobCreation job = auditEng.getJobCreation();
				if (isJobAuditStepApproved(job.getJobCreationId())) {/////// FOR
					/////// JOBS
					/////// WHICH
					/////// AUDIT
					/////// STEP
					/////// IS
					/////// APPROVED
					jobsInExecution.add(job.getJobName());
				}
			}
			logger.info(String.format("(Inside fetchjobsInReporting)fetching jobsInReporting for year" + year
					+ "for company" + companyId + "" + new Date()));

			return jobsInExecution;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchjobsInExecution");

			throw e;
		}

	}

	public ArrayList<String> fetchExceptionImplementationOverdue(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> exceptionImplementationsOverdueJobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 0));
			crit.add(Restrictions.eq("status", "Approved"));
			crit.add(Restrictions.le("dueDate", new Date()));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				if (!exceptionImplementationsOverdueJobs.contains(exceptions.getJobName())) {
					exceptionImplementationsOverdueJobs.add(exceptions.getJobName());
				}
			}
			logger.info(String
					.format("(Inside fetchExceptionImplementationOverdue)fetching ExceptionImplementationOverdue for year"
							+ year + "for company" + companyId + "" + new Date()));

			return exceptionImplementationsOverdueJobs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : exceptionImplementationsOverdueJobs");

			throw e;
		}
	}

	public ArrayList<String> fetchExceptionImplementedAfterDueDate(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> exceptionImplementationsOverdueJobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 1));
			crit.add(Restrictions.eq("status", "Approved"));
			crit.add(Restrictions.le("dueDate", new Date()));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				if (!exceptionImplementationsOverdueJobs.contains(exceptions.getJobName())) {
					exceptionImplementationsOverdueJobs.add(exceptions.getJobName());
				}
			}
			logger.info(String
					.format("(Inside fetchExceptionImplementedAfterDueDate)fetching ExceptionImplementedAfterDueDate for year"
							+ year + "for company" + companyId + "" + new Date()));

			return exceptionImplementationsOverdueJobs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : exceptionImplementationsOverdueJobs");

			throw e;
		}
	}

	public ArrayList<String> fetchExceptionImplemented(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> exceptionImplementationsOverdueJobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 1));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				exceptionImplementationsOverdueJobs.add(exceptions.getJobName());

			}
			logger.info(String.format("(Inside fetchExceptionImplemented)fetching ExceptionImplemented for year" + year
					+ "for company" + companyId + "" + new Date()));

			return exceptionImplementationsOverdueJobs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : exceptionImplementationsOverdueJobs");

			throw e;
		}
	}

	public ArrayList<JobNamesWithExceptionsImplementationStatus> fetchJobNamesWithExceptionStatus(int year,
			int companyId, HashMap<String, String> hm) throws Exception {
		Session session = null;
		ArrayList<JobNamesWithExceptionsImplementationStatus> jobWithExceptionStatus = new ArrayList<JobNamesWithExceptionsImplementationStatus>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			crit.createAlias("responsiblePerson", "resPerson");
			jobsStrategicAlias(crit);

			// crit.createAlias("strategicId", "strategic"); just to explain
			// crit.createAlias("strategic.process", "proc");

			// check filters
			if (hm != null) {
				if (!hm.get("Process").equals("0")) {
					crit.add(Restrictions.eq("processId.processId", Integer.parseInt(hm.get("Process"))));
				}
				if (!hm.get("Unit").equals("0")) {
					crit.add(Restrictions.eq("strategic.auditableUnit", hm.get("Unit")));
				}
				if (!hm.get("Division").equals("0") && !hm.get("Division").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("dept.departmentName", hm.get("Division")));// TODO
					// should
					// be
					// depId

				}
				if (!hm.get("Resource").equals("0")) {
					crit.add(Restrictions.eq("resPerson.employeeId", Integer.parseInt(hm.get("Resource"))));
				}
				if (!hm.get("Risk").equals("0") && !hm.get("Risk").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.riskRating", hm.get("Risk")));
				}
				if (!hm.get("Domain").equals("0") && !hm.get("Domain").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.domainText", hm.get("Domain")));
				}
			}
			// filters end

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				boolean found = false;
				for (int i = 0; i < jobWithExceptionStatus.size(); i++) {
					if (jobWithExceptionStatus.get(i).getJobId() == exceptions.getJobCreationId().getJobCreationId()) {
						found = true;
						if (exceptions.getIsImplemented() == 0) {
							jobWithExceptionStatus.get(i)
									.setNotImplemented(jobWithExceptionStatus.get(i).getNotImplemented() + 1);

						} else {
							jobWithExceptionStatus.get(i)
									.setImplemented(jobWithExceptionStatus.get(i).getImplemented() + 1);

						}
					}
				}
				if (!found) {
					JobNamesWithExceptionsImplementationStatus jobNamesWithExceptionsImplementationStatus = new JobNamesWithExceptionsImplementationStatus();
					jobNamesWithExceptionsImplementationStatus
							.setJobId(exceptions.getJobCreationId().getJobCreationId());
					jobNamesWithExceptionsImplementationStatus
							.setJobName(fetchJobNameFromJobId(exceptions.getJobCreationId().getJobCreationId()));
					if (exceptions.getIsImplemented() == 0) {
						jobNamesWithExceptionsImplementationStatus.setNotImplemented(1);
					}

					else {
						jobNamesWithExceptionsImplementationStatus.setImplemented(1);
					}
					jobWithExceptionStatus.add(jobNamesWithExceptionsImplementationStatus);
				}
			}

			logger.info(String.format("(Inside fetchJobNamesWithExceptionStatus)fetching ExceptionImplemented for year"
					+ year + "for company" + companyId + "" + new Date()));

			return jobWithExceptionStatus;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchJobNamesWithExceptionStatus");

			throw e;
		}
	}

	public ArrayList<AuditWorkStatusDTO> fetchAuditWorkStatus(int year, int companyId, HashMap<String, String> hm)
			throws Exception {
		Session session = null;
		ArrayList<AuditWorkStatusDTO> auditWorkStatusDTOList = new ArrayList<AuditWorkStatusDTO>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			crit.createAlias("responsiblePerson", "resPerson");
			jobsStrategicAlias(crit);

			// check filters
			if (hm != null) {
				if (!hm.get("Process").equals("0")) {
					crit.add(Restrictions.eq("processId.processId", Integer.parseInt(hm.get("Process"))));
				}
				if (!hm.get("Unit").equals("0")) {
					crit.add(Restrictions.eq("strategic.auditableUnit", hm.get("Unit")));
				}
				if (!hm.get("Division").equals("0") && !hm.get("Division").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("dept.departmentName", hm.get("Division")));// TODO
					// should
					// be
					// depId

				}
				if (!hm.get("Resource").equals("0")) {
					crit.add(Restrictions.eq("resPerson.employeeId", Integer.parseInt(hm.get("Resource"))));
				}
				if (!hm.get("Risk").equals("0") && !hm.get("Risk").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.riskRating", hm.get("Risk")));
				}
				if (!hm.get("Domain").equals("0") && !hm.get("Domain").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.domainText", hm.get("Domain")));
				}
			}
			// filters end

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				boolean found = false;
				for (int i = 0; i < auditWorkStatusDTOList.size(); i++) {
					if (auditWorkStatusDTOList.get(i).getJobId() == exceptions.getJobCreationId().getJobCreationId()) {
						found = true;

						String status = getJobCreationStatus(exceptions.getJobCreationId().getJobCreationId());
						exceptions.setDisplayStatus(status);

						if (exceptions.getFinalStatus() != null
								&& exceptions.getFinalStatus().equalsIgnoreCase("Approved")) {
							auditWorkStatusDTOList.get(i)
									.setCompleted(auditWorkStatusDTOList.get(i).getCompleted() + 1);
						} else if (exceptions.getFinalStatus() != null
								&& !exceptions.getFinalStatus().equalsIgnoreCase("Approved")
								&& exceptions.getIsImplemented() == 1) {
							auditWorkStatusDTOList.get(i)
									.setUnderReview(auditWorkStatusDTOList.get(i).getUnderReview() + 1);
						} else {
							auditWorkStatusDTOList.get(i).setOpen(auditWorkStatusDTOList.get(i).getOpen() + 1);
						}

					}
				}
				if (!found) {
					AuditWorkStatusDTO auditWorkStatusDTO = new AuditWorkStatusDTO();
					auditWorkStatusDTO.setJobId(exceptions.getJobCreationId().getJobCreationId());
					auditWorkStatusDTO
							.setJobName(fetchJobNameFromJobId(exceptions.getJobCreationId().getJobCreationId()));
					if (exceptions.getFinalStatus() != null
							&& exceptions.getFinalStatus().equalsIgnoreCase("Approved")) {
						auditWorkStatusDTO.setCompleted(1);
					} else if (exceptions.getFinalStatus() != null
							&& !exceptions.getFinalStatus().equalsIgnoreCase("Approved")
							&& exceptions.getIsImplemented() == 1) {
						auditWorkStatusDTO.setUnderReview(1);
					} else {
						auditWorkStatusDTO.setOpen(1);
					}
					auditWorkStatusDTOList.add(auditWorkStatusDTO);
				}
			}

			logger.info(String.format("(Inside fetchAuditWorkStatus)fetching fetchAuditWorkStatus for year" + year
					+ "for company" + companyId + "" + new Date()));

			return auditWorkStatusDTOList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchAuditWorkStatus");

			throw e;
		}
	}

	private String fetchJobNameFromJobId(int jobCreationId) throws Exception {
		Session session = null;
		if (jobCreationId == 0)
			return "";
		try {
			session = sessionFactory.openSession();
			JobCreation job = (JobCreation) session.get(JobCreation.class, jobCreationId);
			return job.getJobName();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchJobNameFromJobId");

			throw e;
		}
	}

	private JobCreation fetchJobFromJobId(int jobCreationId) throws Exception {
		Session session = null;
		if (jobCreationId == 0)
			return null;
		try {
			session = sessionFactory.openSession();
			JobCreation job = (JobCreation) session.get(JobCreation.class, jobCreationId);

			return job;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchJobFromJobId");

			throw e;
		}
	}

	public JobCreation fetchSelectedJobForClient(int jobCreationId) throws Exception {
		Session session = null;
		if (jobCreationId == 0)
			return null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			crit.add(Restrictions.eq("jobCreationId", jobCreationId));
			// crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			JobCreation job = (JobCreation) crit.list().get(0);

			return job;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchJobFromJobId");

			throw e;
		}
	}

	public ArrayList<String> fetchExceptioNotImplemented(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> exceptionImplementationsOverdueJobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 0));
			crit.add(Restrictions.eq("status", "Approved"));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				exceptionImplementationsOverdueJobs.add(exceptions.getJobName());

			}
			logger.info(String.format("(Inside fetchExceptioNotImplemented)fetching ExceptioNotImplemented for year"
					+ year + "for company" + companyId + "" + new Date()));
			return exceptionImplementationsOverdueJobs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : exceptionImplementationsOverdueJobs");

			throw e;
		}
	}

	public ArrayList<String> fetchExceptionImplementedWithindueDate(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> exceptionImplementationsOverdueJobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("isImplemented", 1));
			crit.add(Restrictions.eq("status", "Approved"));
			crit.add(Restrictions.ge("dueDate", new Date()));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				if (!exceptionImplementationsOverdueJobs.contains(exceptions.getJobName())) {
					exceptionImplementationsOverdueJobs.add(exceptions.getJobName());
				}
			}
			return exceptionImplementationsOverdueJobs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : exceptionImplementationsOverdueJobs");

			throw e;
		}
	}

	public ArrayList<String> fetchManagementCommentsOverdue(int year, int companyId) throws Exception {
		Session session = null;
		ArrayList<String> managementCommentsOverdueJobs = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("managementComments", null));
			crit.add(Restrictions.eq("status", "Approved"));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				if (!managementCommentsOverdueJobs.contains(exceptions.getJobName())) {
					managementCommentsOverdueJobs.add(exceptions.getJobName());
				}
			}
			return managementCommentsOverdueJobs;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("eeror in : fetchManagementCommentsOverdue");

			throw e;
		}
	}

	public DashBoardNewDTO fetchDashBoard(int year, int companyId, HashMap<String, String> hm) throws Exception {

		DashBoardNewDTO dashBoardDTO = new DashBoardNewDTO();
		dashBoardDTO.setJobKDueForKickOffWithinAWeek(fetchJobsKickOffWithInaWeek(year, companyId));
		dashBoardDTO.setJobWithmanagemntCommentsOverdue(fetchManagementCommentsOverdue(year, companyId));
		dashBoardDTO.setJobsInExcutions(fetchjobsInExecution(year, companyId));
		dashBoardDTO.setJobKDueForCompletionWithinAWeek(fetchJobsDurForCompletionWithInaWeek(year, companyId));
		dashBoardDTO.setJobsWithExcepptionImplementationoverdue(fetchExceptionImplementationOverdue(year, companyId));

		ArrayList<String> inExec = fetchjobsInExecution(year, companyId);
		ArrayList<String> inPlanning = fetchjobsInPlanning(year, companyId);
		ArrayList<String> inReporting = fetchjobsInReporting(year, companyId);

		// ArrayList<String> implementedAfterDueDate =
		// fetchExceptionImplementedAfterDueDate(year, companyId);
		// ArrayList<String> implementedWithinDueDate =
		// fetchExceptionImplementedWithindueDate(year, companyId);

		ArrayList<String> exceptionsImplemeted = fetchExceptionImplemented(year, companyId);
		ArrayList<String> exceptionsnotImplemeted = fetchExceptioNotImplemented(year, companyId);

		int executionCount = inExec.size();
		int planningCount = inPlanning.size();
		int inReportingCount = inReporting.size();

		int implemented = exceptionsImplemeted.size();
		int notImplemented = exceptionsnotImplemeted.size();

		////////// NEW DASHBOARD Audit Workspace
		System.out.println("Above setJobNamesWithExceptionImplementationStatus");
		logger.info("Above setJobNamesWithExceptionImplementationStatus");
		dashBoardDTO
				.setJobNamesWithExceptionImplementationStatus(fetchJobNamesWithExceptionStatus(year, companyId, hm));
		System.out.println("Above setCompletedAndInprogressExceptions");
		logger.info("Above setCompletedAndInprogressException");
		dashBoardDTO.setCompletedAndInprogressExceptions(
				fetchCompletedInprogressAndUnderReviewExceptionsCount(year, companyId, hm));
		System.out.println("Above fetchExceptionsReportingStatus");
		logger.info("Above fetchExceptionsReportingStatus");
		dashBoardDTO.setExceptionReportingStatus(fetchExceptionsReportingStatus(year, companyId, hm));
		System.out.println("Above fetchAuditWorkStatus");
		logger.info("Above fetchAuditWorkStatus");
		dashBoardDTO.setAuditWorkStatus(fetchAuditWorkStatus(year, companyId, hm));
		System.out.println("Above fetchJobExceptions");
		logger.info("Above fetchJobExceptions");
		dashBoardDTO.setExceptions(fetchJobExceptions(0, year, companyId, hm));

		////////// NEW DASHBOARD END

		///////// NEW Dashboard Project Management
		System.out.println("Above fetchInformationRequest");
		logger.info("Above fetchInformationRequest");
		dashBoardDTO.setInformationRequests(fetchInformationRequest(companyId, hm));
		System.out.println("Above fetchToDo");
		dashBoardDTO.setTodo(fetchToDo(companyId, hm));

		/////////

		dashBoardDTO.setJobsInExecCount(executionCount);
		dashBoardDTO.setJobsInPlaning(planningCount);
		dashBoardDTO.setJobsInReporting(inReportingCount);

		dashBoardDTO.setImplemented(implemented);
		dashBoardDTO.setNotImplemented(notImplemented);

		fetchReports(year, companyId, dashBoardDTO);

		return dashBoardDTO;
	}

	private HashMap<String, Integer> fetchExceptionsReportingStatus(int year, int companyId, HashMap<String, String> hm)
			throws Exception {
		Session session = null;
		int exceptionsToSent = 0;
		int awaitingComments = 0;
		int commentsReceived = 0;
		int reportIssued = 0;
		int finalReportIssued = 0;

		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class, "jobCreation");
			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			jobsStrategicAlias(crit);
			// check filters
			if (hm != null) {
				if (!hm.get("Process").equals("0")) {
					crit.add(Restrictions.eq("processId.processId", Integer.parseInt(hm.get("Process"))));
				}
				if (!hm.get("Unit").equals("0")) {
					crit.add(Restrictions.eq("strategic.auditableUnit", hm.get("Unit")));
				}
				if (!hm.get("Division").equals("0") && !hm.get("Division").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("dept.departmentName", hm.get("Division")));// TODO
					// should
					// be
					// depId

				}
				if (!hm.get("Resource").equals("0")) {
					crit.add(Restrictions.eq("sassigned.employeeId", Integer.parseInt(hm.get("Resource"))));
				}
				if (!hm.get("Risk").equals("0") && !hm.get("Risk").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.riskRating", hm.get("Risk")));
				}
				if (!hm.get("Domain").equals("0") && !hm.get("Domain").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.domainText", hm.get("Domain")));
				}
			}
			// filters end

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreation jobCreation = (JobCreation) it.next();
				switch (jobCreation.getReportStatus()) {
				case 1:
					exceptionsToSent++;
				case 2:
					awaitingComments++;
				case 3:
					commentsReceived++;
				case 4:
					reportIssued++;
				case 5:
					finalReportIssued++;
				}

			}
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put(InternalAuditConstants.EXCEPTIONSTOSENT, exceptionsToSent);
			map.put(InternalAuditConstants.AWAITINGCOMMENTS, awaitingComments);
			map.put(InternalAuditConstants.COMMENTSRECEIVED, commentsReceived);
			map.put(InternalAuditConstants.REPORTISSUED, reportIssued);
			map.put(InternalAuditConstants.FINALREPORTISSUED, finalReportIssued);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error in : fetchCompletedAndInprogressExceptionsCount");

			throw e;
		}
	}

	private HashMap<String, Integer> fetchCompletedInprogressAndUnderReviewExceptionsCount(int year, int companyId,
			HashMap<String, String> hm) throws Exception {
		Session session = null;
		int completed = 0;
		int inprogress = 0;
		int inReview = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			crit.createAlias("responsiblePerson", "resPerson");
			jobsStrategicAlias(crit);

			// check filters
			if (hm != null) {
				if (!hm.get("Process").equals("0")) {
					crit.add(Restrictions.eq("processId.processId", Integer.parseInt(hm.get("Process"))));
				}
				if (!hm.get("Unit").equals("0")) {
					crit.add(Restrictions.eq("strategic.auditableUnit", hm.get("Unit")));
				}
				if (!hm.get("Division").equals("0") && !hm.get("Division").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("dept.departmentName", hm.get("Division")));// TODO
					// should
					// be
					// depId

				}
				if (!hm.get("Resource").equals("0")) {
					crit.add(Restrictions.eq("resPerson.employeeId", Integer.parseInt(hm.get("Resource"))));
				}
				if (!hm.get("Risk").equals("0") && !hm.get("Risk").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.riskRating", hm.get("Risk")));
				}
				if (!hm.get("Domain").equals("0") && !hm.get("Domain").equalsIgnoreCase("All")) {
					crit.add(Restrictions.eq("jobCreation.domainText", hm.get("Domain")));
				}
			}
			// filters end

			crit.add(Restrictions.eq("year", year));
			crit.add(Restrictions.eq("companyId", companyId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Exceptions exceptions = (Exceptions) it.next();
				if (exceptions.getFinalStatus() != null && exceptions.getFinalStatus().equalsIgnoreCase("Approved")) {
					completed++;
				} else if (exceptions.getFinalStatus() != null
						&& !exceptions.getFinalStatus().equalsIgnoreCase("Approved")
						&& exceptions.getIsImplemented() == 1) {
					inReview++;
				} else {
					inprogress++;
				}
			}
			map.put(InternalAuditConstants.COMPLETEDEXCEPTIONS, completed);
			map.put(InternalAuditConstants.INPROGRESSEXCEPTIONS, inprogress);
			map.put(InternalAuditConstants.INREVIEWEXCEPTIONS, inReview);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error in : fetchCompletedAndInprogressExceptionsCount");

			throw e;
		}

	}

	private void fetchReports(int year, int companyId, DashBoardNewDTO dashBoardDTO) {
		ArrayList<String> domain = new ArrayList<String>();
		domain.add("All");
		ArrayList<String> div = new ArrayList<String>();
		div.add("All");
		ArrayList<String> risk = new ArrayList<String>();
		risk.add("All");
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<Strategic> reports = fetchReportSearchResult(div, domain, risk, year, companyId);
		for (int i = 0; i < reports.size(); i++) {
			ids.add(reports.get(i).getId());
		}
		ArrayList<StrategicDepartments> divs = fetchStrategicDepartmentsMutiple(ids);
		dashBoardDTO.setReports(reports);
		dashBoardDTO.setDivs(divs);
	}

	public String updateUploadedAuditStepFile(Integer auditStepId, ArrayList<String> auditStepFiles) throws Exception {

		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditStep.class);
			crit.add(Restrictions.eq("auditStepId", auditStepId));
			crit.createAlias("initiatedBy", "initiated");
			crit.createAlias("initiated.countryId", "initiatedCount");
			crit.createAlias("initiated.reportingTo", "initiatedRep");
			crit.createAlias("initiatedRep.countryId", "initiatedRCount");
			crit.createAlias("initiated.skillId", "initiatedSkill");
			crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

			AuditStep auditStep = (AuditStep) crit.list().get(0);
			auditStep.setUploadedFile(auditStepFiles.get(0));
			session.update(auditStep);
			session.flush();
			logger.info(String.format("(Inside updateUploadedAuditStepFile)updateUploadedAuditStepFile for audit step"
					+ auditStepId + "for files" + auditStepFiles + "" + new Date()));
			return "file uploaded";

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error in : updateUploadedAuditStepFile");

			throw e;
		}

	}

	public String submitFeedBack(Feedback feedBack) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			logger.info(String.format("(Inside submitFeedBack)submitFeedBack for desc" + feedBack + "" + new Date()));
			session.save(feedBack);
			session.flush();
			return "Feedback Submitted";

		}

		catch (Exception e) {
			e.printStackTrace();
			logger.info("error in : submit feedback");

			throw e;
		}
	}

	public ArrayList<ProcessDTO> fetchProcessDtOs() {
		Session session = null;
		ArrayList<ProcessDTO> processDtos = new ArrayList<ProcessDTO>();

		try {
			session = sessionFactory.openSession();
			ProcessDTO processDTO = new ProcessDTO();

			processDTO.setJobTypeList(fetchJobTypes(session));
			processDTO.setProcessList(fetchProcesses(session));

			processDtos.add(processDTO);

			logger.info(String.format("(Inside fetchProcessDtOs) " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchProcessDTo", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return processDtos;
	}

	private ArrayList<Process> fetchProcesses(Session session) {
		ArrayList<Process> processList = new ArrayList<Process>();
		try {

			Criteria crit = session.createCriteria(Process.class);
			crit.add(Restrictions.ne("processId", 0));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				Process process = (Process) it.next();
				processList.add(process);
				// same line 2 timed

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return processList;
	}

	private ArrayList<JobType> fetchJobTypes(Session session) {
		ArrayList<JobType> jobTypeList = new ArrayList<JobType>();
		try {

			Criteria crit = session.createCriteria(JobType.class);
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobType jobtype = (JobType) it.next();
				jobTypeList.add(jobtype);

			}
		} catch (Exception ex) {
			System.out.println(ex);

		}
		return jobTypeList;
	}

	private ArrayList<SubProcess> fetchSubProcesses() {
		ArrayList<SubProcess> subprocessList = new ArrayList<SubProcess>();
		try {

			Criteria crit = session.createCriteria(SubProcess.class);
			crit.add(Restrictions.ne("subprocessId", 0));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				SubProcess sp = (SubProcess) it.next();
				if (rsList.size() > 0) {
					SubProcess subprocess = (SubProcess) it.next();
					subprocessList.add(subprocess);
				}

			}
		} catch (Exception ex) {

		}
		return subprocessList;
	}

	public ArrayList<SubProcess> fetchSubProcess(int processId) {
		Session session = null;
		ArrayList<SubProcess> subProcess = new ArrayList<SubProcess>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(SubProcess.class);
			crit.createAlias("processId", "process");
			crit.add(Restrictions.eq("process.processId", processId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				SubProcess subprocess = (SubProcess) it.next();
				subProcess.add(subprocess);
			}
			logger.info(String.format("Inside fetchSubProcess() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in FettchSubProcess", ex.getMessage()), ex);

		} finally {
			session.close();

		}
		return subProcess;
	}

	public String saveActivityObjectives(ArrayList<ActivityObjective> activityObjectives, int jobId, int status) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			for (int i = 0; i < activityObjectives.size(); i++) {
				ObjectiveJobRelation objectiveJobRelation = new ObjectiveJobRelation();
				objectiveJobRelation.setObjectiveId(activityObjectives.get(i));
				JobCreation jobCreation = (JobCreation) session.get(JobCreation.class, jobId);
				objectiveJobRelation.setJobCreationId(jobCreation);
				objectiveJobRelation.setActivityJobId(activityObjectives.get(i).getActivityJobRelation());
				if (activityObjectives.get(i).getObjectiveId() == 0) {
					activityObjectives.get(i).setChecked(true);
				}

				session.saveOrUpdate(activityObjectives.get(i));
				int objectiveJobRelationId = fetchExistingActivityJobRelation(jobId,
						activityObjectives.get(i).getObjectiveId());
				objectiveJobRelation.setActivityJobId(objectiveJobRelationId);
				objectiveJobRelation.setStatus(status);
				objectiveJobRelation.setDate(new Date());
				session.saveOrUpdate(objectiveJobRelation);
				session.flush();
			}

			logger.info(String.format("(Inside saveActivityObjectives)    saving activityObjectives : "
					+ activityObjectives.get(0).getObjectiveName() + ", and more " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveActivityObjectives", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return "Activity Objectives Saved";
	}

	private int fetchExistingActivityJobRelation(int jobId, int acitivtyObjectiveId) {
		Session session = null;
		int objectiveJobRelationId = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ObjectiveJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("objectiveId", "objective");
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			crit.add(Restrictions.eq("objective.objectiveId", acitivtyObjectiveId));
			if (crit.list().size() > 0) {
				ObjectiveJobRelation objectiveJobRelation = (com.internalaudit.shared.ObjectiveJobRelation) crit.list()
						.get(0);
				objectiveJobRelationId = objectiveJobRelation.getActivityJobId();
			}
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchExistingActivityJobRelation", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return objectiveJobRelationId;
	}

	private int fetchExistingRiskJobRelation(int jobId, int riskId) {
		Session session = null;
		int riskJobRelationId = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(RiskJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("riskObjective", "risk");
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			crit.add(Restrictions.eq("risk.riskId", riskId));
			if (crit.list().size() > 0) {
				RiskJobRelation riskJobRelation = (RiskJobRelation) crit.list().get(0);
				riskJobRelationId = riskJobRelation.getRiskjobrelationId();
			}
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchExistingActivityJobRelation", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return riskJobRelationId;
	}

	public String saveRiskObjectives(ArrayList<RiskObjective> riskObjectives, int jobId, int status) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			for (int i = 0; i < riskObjectives.size(); i++) {

				RiskJobRelation riskJobRelation = new RiskJobRelation();
				riskJobRelation.setRiskObjective(riskObjectives.get(i));
				JobCreation jobCreation = (JobCreation) session.get(JobCreation.class, jobId);
				riskJobRelation.setJobCreationId(jobCreation);
				riskJobRelation.setRiskjobrelationId(riskObjectives.get(i).getRiskJobRelation());
				session.saveOrUpdate(riskObjectives.get(i).getObjectiveId());
				session.saveOrUpdate(riskObjectives.get(i));
				int riskJobRelationId = fetchExistingRiskJobRelation(jobId, riskObjectives.get(i).getRiskId());
				riskJobRelation.setRiskjobrelationId(riskJobRelationId);
				riskJobRelation.setInherintRisk(riskObjectives.get(i).getRiskRating());
				riskJobRelation.setStatus(status);
				riskJobRelation.setDate(new Date());
				session.saveOrUpdate(riskJobRelation);
				session.flush();

				session.saveOrUpdate(riskObjectives.get(i));
				session.flush();
			}

			logger.info(String.format("(Inside saveRiskObjectives)    saving riskObjectives : "
					+ riskObjectives.get(0).getRiskname() + ", and more " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveExistingControls", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return "Risk Objectives Saved";
	}

	public String saveExistingControls(ArrayList<SuggestedControls> suggestedControls) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			for (int i = 0; i < suggestedControls.size(); i++) {
				session.saveOrUpdate(suggestedControls.get(i));
				session.flush();
			}

			logger.info(String.format("(Inside saveExistingControls)    saving riskObjectives : "
					+ suggestedControls.get(0).getSuggestedControlsName() + ", and more " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in SaveExistingControls", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return "Existing Controls  Saved";
	}

	public String saveAuditWorkProgram(ArrayList<AuditProgramme> auditWorkProgramme, int selectedJobId, int year,
			int companyId) {
		Session session = null;
		ArrayList<AuditWork> auditWorks = new ArrayList<AuditWork>();
		try {
			session = sessionFactory.openSession();
			for (int i = 0; i < auditWorkProgramme.size(); i++) {
				session.saveOrUpdate(auditWorkProgramme.get(i));
				session.flush();

				///////// HERE
				AuditWork auditWork = new AuditWork();
				Employee employee = new Employee();
				employee.setEmployeeId(58);
				auditWork.setApprovedBy(employee);
				auditWork.setDescription(auditWorkProgramme.get(i).getAuditProgrammeName());
				auditWork.setFeedback("");
				auditWork.setInitiatedBy(employee);
				JobCreation job = new JobCreation();
				job.setJobCreationId(selectedJobId);
				auditWork.setJobCreationId(job);
				RiskControlMatrixEntity risk = new RiskControlMatrixEntity();
				risk.setRiskId(63);
				// auditWork.setRiskId(risk);
				auditWork.setStatus(1);
				auditWork.setStepNo(i + 1 + "");
				auditWorks.add(auditWork);
				////////
			}
			saveAuditWork(auditWorks, year, companyId);

			logger.info(String.format("(Inside saveExistingControls)    saving auditWorkProgram : "
					+ auditWorkProgramme.get(0).getAuditProgrammeName() + ", and more " + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in SaveAuditWorkProgram", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return "AuditWorkPrograms  Saved";
	}

	public ArrayList<AuditProgramme> fetchApprovedAuditProgrammeRows(int selectedJobId) {
		Session session = null;
		ArrayList<AuditProgramme> auditProgrammes = new ArrayList<AuditProgramme>();
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AuditProgramme.class);
			crit.createAlias("suggestedControlsId", "suggestedControls");
			crit.createAlias("suggestedControls.riskId", "risk");

			crit.createAlias("reviewer", "review");
			crit.createAlias("review.cityId", "city");
			crit.createAlias("review.countryId", "countryId");

			crit.createAlias("review.skillId", "skill");
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AuditProgramme auditProgramme = (AuditProgramme) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(auditProgramme.getSuggestedControlsId().getRiskId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(auditProgramme,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				auditProgrammes.add(auditProgramme);
			}
			logger.info(String.format("Inside fetchApprovedAuditProgrammeRows() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchApprovedAuditProgrammeRows", ex.getMessage()), ex);

		} finally {
			session.close();

		}
		return auditProgrammes;
	}

	public String deleteRiskObjective(int riskId, int jobId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(RiskJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("riskObjective", "riskObject");

			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			crit.add(Restrictions.eq("riskObject.riskId", riskId));

			if (crit.list().size() > 0)
				session.delete(crit.list().get(0));
			session.flush();

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in deleteRiskObjective", ex.getMessage()), ex);

		} finally {
			session.close();

		}
		return "risk deleted";
	}

	public JobStatusDTO fetchJobStatus(int jobId, int year, int companyId) {
		Session session = null;
		JobStatusDTO jobStatusDTO = new JobStatusDTO();
		try {
			session = sessionFactory.openSession();
			setNotificationStatus(jobId, session, jobStatusDTO);
			seObjevtiveStatus(jobId, session, jobStatusDTO);
			seKeyRiskStatus(jobId, session, jobStatusDTO);
			setRiskControlStatus(jobId, session, jobStatusDTO, year, companyId);
			setAuditWorkProgramStatus(jobId, session, jobStatusDTO, year, companyId);
			setFieldworkStatus(jobId, year, companyId, session, jobStatusDTO);
			jobStatusDTO.setReportingStatus(getJobStatus(jobId));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in deleteRiskObjective", ex.getMessage()), ex);

		} finally {
			session.close();

		}
		return jobStatusDTO;
	}

	private void setFieldworkStatus(int jobId, int year, int companyId, Session session, JobStatusDTO jobStatusDTO) {
		Criteria critAuditStep = session.createCriteria(AuditStep.class);

		critAuditStep.add(Restrictions.eq("year", year));
		critAuditStep.add(Restrictions.eq("companyId", companyId));
		critAuditStep.add(Restrictions.eq("jobId", jobId));
		critAuditStep.createAlias("auditWork", "audWork");
		critAuditStep.createAlias("audWork.jobCreationId", "jobCreationId");
		critAuditStep.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

		List rsListAuditStep = critAuditStep.list();
		for (Iterator it = rsListAuditStep.iterator(); it.hasNext();) {
			AuditStep auditStep = (AuditStep) it.next();
			auditStep.setExceptions(getSavedExceptions(auditStep.getAuditStepId(), year, companyId));
			FieldWorkStatusDTO auditStepStatusDTO = new FieldWorkStatusDTO();
			auditStepStatusDTO.setApprovalDate(auditStep.getDate());
			auditStepStatusDTO.setAuditStepName(auditStep.getAuditWork().getDescription());
			auditStepStatusDTO.setAudtiStepId(auditStep.getAuditStepId());
			auditStepStatusDTO.setHaveExceptions(auditStep.getExceptions().size() > 0 ? true : false);
			auditStepStatusDTO.setStatus(auditStep.getStatus() == InternalAuditConstants.SAVED
					? InternalAuditConstants.INPROGRESS : InternalAuditConstants.COMPLETED);

			jobStatusDTO.getListFieldWorkStatus().add(auditStepStatusDTO);
		}
	}

	private void seObjevtiveStatus(int jobId, Session session, JobStatusDTO jobStatusDTO) {
		Criteria critObjective = session.createCriteria(ObjectiveJobRelation.class);
		critObjective.createAlias("jobCreationId", "job");
		critObjective.add(Restrictions.eq("job.jobCreationId", jobId));

		List rsListObjective = critObjective.list();
		PlanningStatusDTO planningStatusDTO = new PlanningStatusDTO();
		planningStatusDTO.setStatus(InternalAuditConstants.NOT_STARTED);
		planningStatusDTO.setPlanningName(InternalAuditConstants.OBJECTIVES);
		planningStatusDTO.setId(1);
		jobStatusDTO.getListPlanningStatus().add(planningStatusDTO);
		for (Iterator it = rsListObjective.iterator(); it.hasNext();) {
			ObjectiveJobRelation objectiveJobRelation = (ObjectiveJobRelation) it.next();

			planningStatusDTO.setDate(objectiveJobRelation.getDate());
			planningStatusDTO.setStatus(objectiveJobRelation.getStatus() == InternalAuditConstants.SAVED
					? InternalAuditConstants.INPROGRESS : InternalAuditConstants.COMPLETED);
			planningStatusDTO.setId(objectiveJobRelation.getActivityJobId());
		}
	}

	private void seKeyRiskStatus(int jobId, Session session, JobStatusDTO jobStatusDTO) {
		Criteria critObjective = session.createCriteria(RiskJobRelation.class);
		critObjective.createAlias("jobCreationId", "job");
		critObjective.add(Restrictions.eq("job.jobCreationId", jobId));

		List rsListObjective = critObjective.list();
		PlanningStatusDTO planningStatusDTO = new PlanningStatusDTO();
		planningStatusDTO.setPlanningName(InternalAuditConstants.KEY_RISKS);
		planningStatusDTO.setStatus(InternalAuditConstants.NOT_STARTED);
		planningStatusDTO.setId(2);
		jobStatusDTO.getListPlanningStatus().add(planningStatusDTO);
		for (Iterator it = rsListObjective.iterator(); it.hasNext();) {
			RiskJobRelation entity = (RiskJobRelation) it.next();

			planningStatusDTO.setDate(entity.getDate());
			planningStatusDTO.setStatus(entity.getStatus() == InternalAuditConstants.SAVED
					? InternalAuditConstants.INPROGRESS : InternalAuditConstants.COMPLETED);
			planningStatusDTO.setId(entity.getRiskjobrelationId());
		}
	}

	private void setNotificationStatus(int jobId, Session session, JobStatusDTO jobStatusDTO) {
		try {
			Criteria critNotification = session.createCriteria(AuditEngagement.class);
			critNotification.createAlias("jobCreation", "job");
			critNotification.add(Restrictions.eq("job.jobCreationId", jobId));
			// crit.add(Restrictions.isNotEmpty("notificationSentDate"));
			// crit.add(Restrictions.isNotNull("notificationSentDate"));
			List rsListNotification = critNotification.list();
			PlanningStatusDTO planningStatusDTO = new PlanningStatusDTO();
			planningStatusDTO.setStatus(InternalAuditConstants.NOT_STARTED);
			planningStatusDTO.setPlanningName(InternalAuditConstants.AUDIT_NOTIFICATION);
			planningStatusDTO.setId(3);
			for (Iterator it = rsListNotification.iterator(); it.hasNext();) {
				AuditEngagement audEng = (AuditEngagement) it.next();
				planningStatusDTO.setDate(audEng.getNotificationSentDate());
				planningStatusDTO.setStatus(audEng.getNotificationSentDate() == null
						? InternalAuditConstants.NOT_STARTED : InternalAuditConstants.COMPLETED);
				planningStatusDTO.setId(audEng.getAuditEngId());
			}
			jobStatusDTO.getListPlanningStatus().add(planningStatusDTO);
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in setNotificationStatus", ex.getMessage()), ex);

		}
	}

	private void setRiskControlStatus(int jobId, Session sessionbk, JobStatusDTO jobStatusDTO, int year,
			int companyId) {

		Session session = sessionFactory.openSession();
		try {
			AuditEngagement audEng = fetchAuditEngagement(jobId, year, companyId);

			Criteria crit = session.createCriteria(RiskControlMatrixEntity.class);
			crit.createAlias("auditEngageId", "auditEngage");
			crit.add(Restrictions.eq("auditEngage.auditEngId", audEng.getAuditEngId()));
			List rsList = crit.list();
			PlanningStatusDTO planningStatusDTO = new PlanningStatusDTO();
			planningStatusDTO.setStatus(InternalAuditConstants.NOT_STARTED);
			planningStatusDTO.setPlanningName(InternalAuditConstants.OBJ_RISK_CONTROL_MATRIX);
			planningStatusDTO.setId(4);
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskControlMatrixEntity riskControl = (RiskControlMatrixEntity) it.next();

				planningStatusDTO.setDate(riskControl.getDate());
				planningStatusDTO.setStatus(riskControl.getApprovedBy().getEmployeeId() == 0
						? InternalAuditConstants.INPROGRESS : InternalAuditConstants.COMPLETED);
				planningStatusDTO.setId(riskControl.getRiskId());
			}
			jobStatusDTO.getListPlanningStatus().add(planningStatusDTO);
			session.close();
		} catch (Exception ex) {
			;
			session.close();
			System.out.println(ex.getLocalizedMessage());
		}
	}

	private void setAuditWorkProgramStatus(int jobId, Session session, JobStatusDTO jobStatusDTO, int year,
			int companyId) {

		Criteria crit = session.createCriteria(AuditWork.class);
		crit.add(Restrictions.eq("companyId", companyId));
		crit.createAlias("jobCreationId", "jobCreation");
		jobsStrategicAlias(crit);
		crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
		crit.add(Restrictions.eq("year", year));
		List rsList = crit.list();
		PlanningStatusDTO planningStatusDTO = new PlanningStatusDTO();
		planningStatusDTO.setStatus(InternalAuditConstants.NOT_STARTED);
		planningStatusDTO.setPlanningName(InternalAuditConstants.AUDIT_WORK_PROGRAM);
		planningStatusDTO.setId(5);
		for (Iterator it = rsList.iterator(); it.hasNext();) {
			AuditWork auditWork = (AuditWork) it.next();

			planningStatusDTO.setDate(auditWork.getDate());
			planningStatusDTO.setStatus(auditWork.getApprovedBy().getEmployeeId() == 0
					? InternalAuditConstants.INPROGRESS : InternalAuditConstants.COMPLETED);
			planningStatusDTO.setId(auditWork.getAuditWorkId());
		}
		jobStatusDTO.getListPlanningStatus().add(planningStatusDTO);
	}

	public ArrayList<DashboardListBoxDTO> fetchDashBoardListBoxDTOs(int year, int companyId2) {
		Session session = null;
		System.out.println("inside fetchDashBoardListBoxDTOs");
		ArrayList<DashboardListBoxDTO> dashboardListBoxDTOs = new ArrayList<DashboardListBoxDTO>();

		try {
			session = sessionFactory.openSession();
			DashboardListBoxDTO dashboardListBox = new DashboardListBoxDTO();

			dashboardListBox.setProcessList(fetchProcesses(session));
			dashboardListBox.setEmployList(fetchEmployees(companyId2));
			dashboardListBox.setJobList(fetchJobs(year, companyId2));
			dashboardListBoxDTOs.add(dashboardListBox);

			System.out.print("(Inside fetchDashboardListBoxDTO) " + new Date());
			logger.info(String.format("(Inside fetchDashboardListBoxDTO) " + new Date()));
		} catch (Exception ex) {
			System.out.println("Exception occured in fetchDashboardListBoxDTO" + ex.getMessage());

			logger.warn(String.format("Exception occured in fetchDashboardListBoxDTO", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return dashboardListBoxDTOs;
	}

	public String approveScheduling(int companyId, int year) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobCreation.class);
			crit.add(Restrictions.eq("companyId", companyId));
			crit.add(Restrictions.eq("year", year));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				JobCreation job = (JobCreation) it.next();
				if (job.getStartDate() == null || job.getEndDate() == null) {
					return "Jobs scheduling required";
				}
				job.setApproved(true);
				session.update(job);
				session.flush();
			}
			logger.info(String.format("(Inside approveScheduling)approving Schedulingexception for year:" + year
					+ "for company" + companyId + "" + new Date()));

			return approveSchedulingInJobTimeEstimation(companyId);

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in approveScheduling", ex.getMessage()), ex);
			return null;
		} finally {
			session.close();
		}

	}

	// public String savetoDo(String description, int assignedTo, Date dueDate)
	// {
	// Session session = null;
	// try {
	// session = sessionFactory.openSession();
	// ToDo todo = new ToDo();
	// todo.setDescription(description);
	// todo.setAssignedTo(assignedTo);
	// todo.setDueDate(dueDate);
	//
	// session.saveOrUpdate(todo);
	// session.flush();
	//
	//// logger.info(String.format("(Inside saveAuditNotification) saving
	// AuditNotification for message to: " + to
	//// + "for message" + message + "for year" + year + "for company" +
	// companyId + "" + new Date()));
	//
	// } catch (Exception ex) {
	// logger.warn(String.format("Exception occured in savetoDo",
	// ex.getMessage()), ex);
	//
	// }
	// return "saved";
	// }

	public String savetoDo(ToDo todo, String realPath) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.saveOrUpdate(todo);

			session.flush();
			File mainFolder = new File(realPath + "/" + InternalAuditConstants.PATHTODOUPLOADS);
			File deleteFolder = new File(mainFolder + "/" + InternalAuditConstants.PATHTOUNSAVEDATTACHMENTS);
			updateFolder(todo.getToDoId(), mainFolder, deleteFolder);
			// logger.info(String.format("(Inside saveAuditNotification) saving
			// AuditNotification for message to: " + to
			// + "for message" + message + "for year" + year + "for company" +
			// companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in savetoDo", ex.getMessage()), ex);

		}

		return "saved";
	}

	public String saveInformationRequest(InformationRequestEntity informationrequest, String realPath) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.saveOrUpdate(informationrequest);
			session.flush();
			//
			File mainFolder = new File(realPath + "/" + InternalAuditConstants.PATHINFORMATIONREQUESTUPLOADS);
			File deleteFolder = new File(mainFolder + "/" + InternalAuditConstants.PATHTOUNSAVEDATTACHMENTS);
			updateFolder(informationrequest.getInformationRequestId(), mainFolder, deleteFolder);
			// logger.info(String.format("(Inside saveAuditNotification) saving
			// AuditNotification for message to: " + to
			// + "for message" + message + "for year" + year + "for company" +
			// companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveInformationRequest", ex.getMessage()), ex);

		}
		return "saved";
	}

	private void updateFolder(int id, File mainFolder, File deleteFolder) {

		mainFolder.mkdirs();

		if (deleteFolder.exists()) {
			File auditStepUpload = new File(mainFolder + "/" + id);
			deleteFolder.renameTo(auditStepUpload);
			deleteFolder.mkdirs();
		}

		deleteFolder.delete();

	}

	public String saveToDoLogs(ToDoLogsEntity toDoLogsEntity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.saveOrUpdate(toDoLogsEntity);
			session.flush();

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveToDologs", ex.getMessage()), ex);

		}
		return "saved";
	}

	public String saveInformationRequestLogs(InformationRequestLogEntity informationRequestLogEntity) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.saveOrUpdate(informationRequestLogEntity);
			session.flush();

			// call new method from
			// here(informationRequestLogEntity.getAssignedFrom())
			//

			// logger.info(String.format("(Inside saveAuditNotification) saving
			// AuditNotification for message to: " + to
			// + "for message" + message + "for year" + year + "for company" +
			// companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveInformationRequestLogs", ex.getMessage()), ex);

		}
		return "saved";
	}

	public String deleteUnsavedAttachments(File auditSteps) {
		auditSteps.delete();
		return "deleted";
	}

	public ArrayList<ToDo> fetchToDoReload(Employee loggedInUser) {
		// return fetchUsersRaisedToDo(loggedInUser);
		return fetchUsersTodos(loggedInUser);
	}

	/// new method fetchInformationreqLogs...(Employee

	public ArrayList<Exceptions> fetchJobExceptionWithImplicationRating(int jobId, int ImplicationRating) {
		Session session = null;
		ArrayList<Exceptions> exceptionsList = new ArrayList<Exceptions>();
		int status = 0;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Exceptions.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.add(Restrictions.eq("implicationRating", ImplicationRating + ""));
			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {

				Exceptions exception = (Exceptions) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(exception,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				// u if
				// (exception.getImplicationRating().equals(ImplicationRating
				// + "")) {
				exceptionsList.add(exception);
				// }
			}

			logger.info(
					String.format("(Inside fetchJobStatus)  fetching JobStatus for job : " + jobId + " " + new Date()));

		} catch (

		Exception ex) {
			logger.warn(String.format("Exception occured in fetchJobStatus", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return exceptionsList;

	}

	// fetching risks against job
	public ArrayList<SuggestedControls> fetchControlsForReport(int jobId) {
		ArrayList<SuggestedControls> controlList = new ArrayList<SuggestedControls>();
		ArrayList<RiskJobRelation> risksList = new ArrayList<RiskJobRelation>();
		try {
			Criteria crit = session.createCriteria(RiskJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);
			crit.createAlias("riskObjective", "risk");
			crit.createAlias("risk.objectiveId", "riskObjective");
			crit.createAlias("riskObjective.subProcessId", "risksubProcessId2");

			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));

			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				RiskJobRelation riskJobRelation = (RiskJobRelation) it.next();
				RiskObjective riskObjective = riskJobRelation.getRiskObjective();
				HibernateDetachUtility.nullOutUninitializedFields(riskObjective.getObjectiveId().getSubProcessId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				controlList.addAll(fetchSuggestedControlsAgainstRiskInReport(riskObjective.getRiskId(), session));
				risksList.add(riskJobRelation);
			}

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in  fetchObjectiveRisksForSelectedJob", ex.getMessage()), ex);

		} finally {

		}
		return controlList;

	}

	// 2019 for fetching controls
	private ArrayList<SuggestedControls> fetchSuggestedControlsAgainstRiskInReport(int riskId, Session session) {
		ArrayList<SuggestedControls> controlList = new ArrayList<SuggestedControls>();
		try {
			Criteria crit = session.createCriteria(SuggestedControls.class);
			crit.createAlias("riskId", "risk");
			crit.createAlias("risk.objectiveId", "riskObjective");
			crit.createAlias("riskObjective.subProcessId", "risksubProcessId");
			crit.add(Restrictions.eq("risk.riskId", riskId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				SuggestedControls suggestedControls = (SuggestedControls) it.next();
				HibernateDetachUtility.nullOutUninitializedFields(suggestedControls,
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(
						suggestedControls.getRiskId().getObjectiveId().getSubProcessId(),
						HibernateDetachUtility.SerializationType.SERIALIZATION);
				controlList.add(suggestedControls);
			}
		} catch (Exception ex) {
			System.out.println("error in fetchSuggestedControlsAgainstRiskObjective " + ex);
		}
		return controlList;

	}
	// 2019 april

	public String saveReportDataPopup(ReportDataEntity reportData, int loggedInUser) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.saveOrUpdate(reportData);
			// session.save(reportData);
			session.flush();

			// logger.info(String.format("(Inside saveAuditNotification) saving
			// AuditNotification for message to: " + to
			// + "for message" + message + "for year" + year + "for company" +
			// companyId + "" + new Date()));

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveReportData", ex.getMessage()), ex);

		} finally {
			session.close();
		}

		return "reportDataSaved";
	}

	public ReportDataEntity fetchReportDataPopup(int jobId) {

		ArrayList<ReportDataEntity> reportDataList = new ArrayList<ReportDataEntity>();
		Session session = null;
		ReportDataEntity reportData = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ReportDataEntity.class);

			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				reportData = (ReportDataEntity) it.next();
				// reportDataList.add(reportData);
			}
			logger.info(String
					.format("(Inside fetchREportDataPopup) fetching ReportDatat for jobid : " + " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchReportData", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return reportData;
	}

	public String saveAssesmentGrid(ArrayList<AssesmentGridEntity> listAssesment, int jobid) {
		// ArrayList<AssesmentGridDbEntity> listAssesments = new
		// ArrayList<AssesmentGridDbEntity>();
		Session session = null;
		try {

			session = sessionFactory.openSession();
			// Transaction tr = session.beginTransaction();
			deletePreviousAssesmentSaved(jobid);
			for (int i = 0; i < listAssesment.size(); i++) {
				AssesmentGridDbEntity assesmentgrid = new AssesmentGridDbEntity();
				assesmentgrid.setAssesmentName(listAssesment.get(i).getName());
				assesmentgrid.setJobId(jobid);
				assesmentgrid.setCompletelySatisfied(listAssesment.get(i).isUrlCompleteboolean());
				assesmentgrid.setPartiallySatisfied(listAssesment.get(i).isUrlSatisfyboolean());
				assesmentgrid.setUnSatisfied(listAssesment.get(i).isUrlNonSatisfyboolean());

				// listAssesments.add(assesmentgrid);
				session.saveOrUpdate(assesmentgrid);
				session.flush();

			}
			// tr.commit();
			logger.info(String.format("(Inside saveStrategicAudit) saving Assesment Grid  for strategic objective : "
					+ " " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in saveAssesmentGrid", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return "Assesment Grid Saved";
	}

	public ArrayList<AssesmentGridDbEntity> fetchAssesmentGrid(int jobId) {

		Session session = null;
		ArrayList<AssesmentGridDbEntity> assesmentList = new ArrayList<AssesmentGridDbEntity>();
		try {
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(AssesmentGridDbEntity.class);
			crit.add(Restrictions.eq("jobId", jobId));

			List rsList = crit.list();
			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AssesmentGridDbEntity assesmentGrid = (AssesmentGridDbEntity) it.next();

				assesmentList.add(assesmentGrid);
			}
			logger.info(String.format("Inside fetchAssesmentGrid() " + new Date()));
		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in fetchAssesmentGrid()", ex.getMessage()), ex);

		} finally {
			session.close();
		}
		return assesmentList;
	}

	private void deletePreviousAssesmentSaved(int jobId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AssesmentGridDbEntity.class);
			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();

			for (Iterator it = rsList.iterator(); it.hasNext();) {
				AssesmentGridDbEntity assesmentGridDbEntity = (AssesmentGridDbEntity) it.next();
				session.delete(assesmentGridDbEntity);
				session.flush();
			}

			logger.info(String
					.format("(Inside AssesmentGridDbEntity) deleting resource for job : " + jobId + " " + new Date()));

		} catch (Exception ex) {

			logger.warn(String.format("Exception occured in AssesmentGridDbEntity", ex.getMessage()), ex);
			System.out.println("Exception occured in AssesmentGridDbEntity");

		} finally {

		}

	}

	public String deleteActivityObjective(int jobId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(ObjectiveJobRelation.class);
			crit.createAlias("jobCreationId", "jobCreation");
			jobsStrategicAlias(crit);

			crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));

			if (crit.list().size() > 0)
				session.delete(crit.list().get(0));
			session.flush();

		} catch (Exception ex) {
			logger.warn(String.format("Exception occured in deleteActivityObjective", ex.getMessage()), ex);

		} finally {
			session.close();

		}
		return "ActivityObjective deleted";
	}

}
