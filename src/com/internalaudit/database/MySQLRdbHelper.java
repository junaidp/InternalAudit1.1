package com.internalaudit.database;

//import java.lang.invoke.VolatileCallSite;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

//import com.ibm.icu.text.SimpleDateFormat;
import com.internalaudit.shared.AuditEngagement;
import com.internalaudit.shared.AuditSchedulingReportDTO;
import com.internalaudit.shared.AuditStep;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.Company;
import com.internalaudit.shared.CompanySkillRelation;
import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.DashBoardNewDTO;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.EmployeeJobDTO;
import com.internalaudit.shared.ExcelDataDTO;
import com.internalaudit.shared.Exceptions;
import com.internalaudit.shared.ExceptionsReportDTO;
import com.internalaudit.shared.HibernateDetachUtility;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.JobCreation;
import com.internalaudit.shared.JobCreationDTO;
import com.internalaudit.shared.JobEmployeeRelation;
import com.internalaudit.shared.JobSkillRelation;
import com.internalaudit.shared.JobTimeAllocationReportDTO;
import com.internalaudit.shared.JobTimeEstimation;
import com.internalaudit.shared.JobTimeEstimationDTO;
import com.internalaudit.shared.JobsOfEmployee;
import com.internalaudit.shared.ReportsDTO;
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
import com.internalaudit.shared.TimeLineDates;
import com.internalaudit.shared.User;

public class MySQLRdbHelper {

    private Session session;

    private SessionFactory sessionFactory;
    Logger logger;

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public User getAuthentication(String userid, String password) throws Exception {

	int currentYear = getCurrentYear();

	logger = Logger.getLogger("com.internalaudit.database.MySQLRdbHelper.class");
	logger.setLevel(Level.DEBUG);
	User users = null;
	Session session = null;
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(User.class);
	    crit.add(Restrictions.eq("name", userid));
	    crit.add(Restrictions.eq("password", password));
	    crit.createAlias("employeeId", "emp");
	    crit.createAlias("emp.countryId", "empcoun");
	    crit.createAlias("emp.cityId", "empcit");
	    crit.createAlias("emp.reportingTo", "empRep");
	    crit.createAlias("empRep.userId", "empRepUser");
	    crit.createAlias("emp.skillId", "skill");
	    // crit.createAlias("emp.companyId", "company");
	    crit.createAlias("emp.rollId", "roll");
	    crit.createAlias("empRep.reportingTo", "empRepRep");
	    crit.createAlias("empRep.skillId", "skillRep");
	    crit.createAlias("empRepRep.userId", "empRepRepUser");
	    crit.createAlias("empRep.rollId", "empRepRoll");
	    crit.createAlias("empRepRep.rollId", "empRepRepRoll");
	    crit.createAlias("empRepRep.skillId", "empRepRepSkill");

	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		users = (User) it.next();
		System.out.println(users.getPassword());
		logger.info(String.format("signed In"));
		 logger.warn(String.format("Exception occured in getAuthentication"));
	    }
	    HibernateDetachUtility.nullOutUninitializedFields(users,
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

	return users;
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
	    crit.createAlias("rollId", "roll");
	    crit.createAlias("userId", "user");
	    // crit.createAlias("companyId", "company");
	    crit.createAlias("skillId", "skill");
	    crit.add(Restrictions.ne("employeeId", 0));
	    crit.add(Restrictions.eq("companyId", companyId));
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Employee employee = (Employee) it.next();
		employees.add(employee);
	    }

	    return employees;// Return BEFORE catch Statement..

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
	    crit.createAlias("rollId", "roll");
	    crit.createAlias("userId", "user");
	    // crit.createAlias("companyId", "company");
	    crit.createAlias("skillId", "skill");
	    crit.createAlias("reportingTo", "reporting");
	    crit.createAlias("reporting.reportingTo", "reportingrep");
	    crit.createAlias("reportingrep.skillId", "reportingrepskill");
	    crit.createAlias("reportingrep.rollId", "reportingrepR");
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
		HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getRollId(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(
			employee.getReportingTo().getReportingTo().getSkillId(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(
			employee.getReportingTo().getReportingTo().getRollId(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);

	    }

	    return employee;// Return BEFORE catch Statement..

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchSkills", ex.getMessage()), ex);

	} finally {
	    session.close();
	}

	return skills;
    }

    public String saveStrategic(Strategic strategic, User loggedInUser, HashMap<String, String> hm, int year,
	    int companyId) {

	try {
	    session = sessionFactory.openSession();
	    String todo = hm.get("todo");
	    String tab = hm.get("tab");
	    saveOneStrategic(strategic, loggedInUser, todo, tab, year, companyId);

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
	    crit.createAlias("userId", "user");
	    crit.createAlias("skillId", "skill");
	    // crit.createAlias("companyId", "company");
	    crit.createAlias("rollId", "roll");

	    crit.add(Restrictions.eq("employeeId", userId));
	    if (crit.list().size() > 0) {
		employee = (Employee) crit.list().get(0);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchSupervisor", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return employee.getReportingTo();
    }

    public void saveOneStrategic(Strategic strategic, User loggedInUser, String todo, String tab, int year,
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
			submitStrategic(strategic, loggedInUser, clientSideStrategic, session);
		    }
		}
	    }

	    strategic.setRating(clientSideStrategic.getRating());
	    strategic.setComments(clientSideStrategic.getComments());
	    strategic.setAuditableUnit(clientSideStrategic.getAuditableUnit());
	    strategic.setStrategicDepartments(clientSideStrategic.getStrategicDepartments());
	    strategic.setDate(new Date());
	    strategic.setTab(selectedTab);
	    strategic.setYear(year); // year Added
	    strategic.setCompanyId(companyId); // companyId Added

	    // session.saveOrUpdate(strategic.getApprovedBy());
	    // session.saveOrUpdate(strategic.getInitiatedBy());
	    // session.saveOrUpdate(strategic.getAssignedTo());
	    // session.saveOrUpdate(strategic.getObjectiveOwner());
	    session.saveOrUpdate(strategic);

	    tr.commit();
	    // saveStrategicAudit(strategic);
	    if (strategic.getPhase() == 1) {
		saveDepartments(strategic);
	    }

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
	    for (int i = 0; i < strategic.getStrategicDepartments().size(); i++) {
		strategic.getStrategicDepartments().get(i).setStrategic(strategic.getId());
		if (!strategicDepartmentAlreadySaved(strategic.getId(),
			strategic.getStrategicDepartments().get(i).getDepartment().getDepartmentId())) {
		    session.saveOrUpdate(strategic.getStrategicDepartments().get(i));
		    session.flush();
		}
	    }

	} catch (Exception ex) {

	    logger.warn(String.format("Exception occured in saveDepartmentsStrategic", ex.getMessage()), ex);

	} finally {
	    session.close();
	}

    }

    private boolean strategicDepartmentAlreadySaved(int strategicId, int departmentId) {
	Session session = null;
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(StrategicDepartments.class);
	    crit.createAlias("department", "dept");
	    crit.add(Restrictions.eq("strategic", strategicId));
	    crit.add(Restrictions.eq("dept.departmentId", departmentId));

	    if (crit.list().size() > 0) {
		return true;
	    }

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saveStrategicAudit", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
    }

    private void approveStrategic(Strategic strategic, User loggedInUser, Strategic clientSideStrategic,
	    Employee initiatedBy, Session session) throws Exception {
	strategic.setStatus("initiated");
	strategic.setPhase(clientSideStrategic.getNextPhase());
	strategic.setAssignedTo(initiatedBy);
	strategic.setApprovedBy(loggedInUser.getEmployeeId());
	// Employee approvedBy = (Employee) session.load(Employee.class, new
	// Integer(loggedInUser.getEmployeeId().getEmployeeId()));
	// Employee approvedBy =
	// fetchEmployeeById(loggedInUser.getEmployeeId().getEmployeeId());
	// strategic.setApprovedBy(approvedBy);
	strategic.setAuditableUnit(clientSideStrategic.getAuditableUnit());
	// strategic.setObjectiveOwner(clientSideStrategic.getObjectiveOwner());//
	// HERE Objective owner
	strategic.setStrategicObjective(clientSideStrategic.getStrategicObjective());
	strategic.setAcheivementDate(clientSideStrategic.getAcheivementDate());
	strategic.setRelevantDepartment(clientSideStrategic.getRelevantDepartment());
	strategic.setAudit(clientSideStrategic.isAudit());
    }

    private void submitStrategic(Strategic strategic, User loggedInUser, Strategic clientSideStrategic,
	    Session session) {
	strategic.setStatus("submitted");
	Employee supervisor = loggedInUser.getEmployeeId().getReportingTo();
	strategic.setAssignedTo(supervisor);
	// Employee emp = new Employee();
	// emp.setEmployeeId(0);
	strategic.setApprovedBy((Employee) session.load(Employee.class, 0));
	strategic.setInitiatedBy(loggedInUser.getEmployeeId());
	strategic.setStrategicObjective(clientSideStrategic.getStrategicObjective());
	strategic.setAudit(clientSideStrategic.isAudit());
    }

    private void initiateStrategic(Strategic strategic, User loggedInUser, Strategic clientSideStrategic,
	    Session session) {
	strategic.setStatus("saved");
	// Employee emp = new Employee();
	// emp.setEmployeeId(0);
	// strategic.setApprovedBy(emp);
	strategic.setApprovedBy((Employee) session.load(Employee.class, 0));
	strategic.setInitiatedBy(loggedInUser.getEmployeeId());
	strategic.setAssignedTo(strategic.getInitiatedBy());
	strategic.setStrategicObjective(clientSideStrategic.getStrategicObjective());
	strategic.setAcheivementDate(clientSideStrategic.getAcheivementDate());
	// strategic.setObjectiveOwner(clientSideStrategic.getObjectiveOwner());//
	// HERE Objective owner
	strategic.setRelevantDepartment(clientSideStrategic.getRelevantDepartment());
	strategic.setAudit(clientSideStrategic.isAudit());
    }

    private void ammendStrategic(Strategic strategic, User loggedInUser, Strategic clientSideStrategic,
	    Session session) {
	strategic.setStatus("amend");
	// Employee emp = new Employee();
	// emp.setEmployeeId(0);
	// strategic.setApprovedBy(emp);
	strategic.setApprovedBy((Employee) session.load(Employee.class, 0));
	strategic.setAssignedTo(strategic.getInitiatedBy());
	strategic.setAudit(clientSideStrategic.isAudit());
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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchRiskFactors", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return riskFactors;
    }

    public ArrayList<Strategic> fetchStrategic(HashMap<String, String> hm, int employeeId) {
	Session session = null;
	String phase = hm.get("phase");
	int tab = Integer.parseInt(hm.get("tab"));
	int year = Integer.parseInt(hm.get("year"));
	int companyId = Integer.parseInt(hm.get("companyId"));
	int phaseValue = Integer.parseInt(phase);
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
	    crit.createAlias("assigned.userId", "assignedUser");
	    // crit.createAlias("assigned.reportingTo", "assignedReporting");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    // crit.createAlias("initiated.reportingTo", "initiatedReporting");
	    // crit.createAlias("initiatedReporting.userId",
	    // "initiatedReportingUser");
	    // crit.createAlias("approvedBy", "approveby");
	    // crit.createAlias("approveby.userId", "approvedUser");
	    // crit.createAlias("approveby.reportingTo", "approvedReposrting");
	    // crit.createAlias("approvedReposrting.userId",
	    // "approvedReposrtingUser");

	    // Disjunction disc = Restrictions.disjunction();
	    // disc.add(Restrictions.eq("assigned.employeeId", employeeId));
	    // disc.add(Restrictions.eq("initiated.employeeId", employeeId));
	    //
	    // crit.add(disc);

	    ///////////////////////
	    crit.createAlias("initiated.skillId", "initatedSkill");
	    crit.createAlias("initiated.rollId", "initatedRoll");

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
	} catch (Exception ex) {
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
	    crit.createAlias("jobCreationId", "jobCreation");
	    crit.add(Restrictions.eq("jobCreation.jobId", jobId));
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
		int resourceId = jobEmployeeRelation.getEmployee().getEmployeeId();
		if (!resourceIds.contains(resourceId)) {
		    resourceIds.add(resourceId);
		}

	    }

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

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
	    crit.createAlias("assigned.userId", "assignedUser");
	    // crit.createAlias("assigned.reportingTo", "reportingAssignedTo");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.skillId", "initatedSkill");
	    crit.createAlias("initiated.rollId", "initatedRoll");

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
    }

    public String saveRiskAssesment(ArrayList<StrategicRisk> strategicRisks, User loggedInUser,
	    HashMap<String, String> hm) {
	String todo = hm.get("todo");
	String tab = hm.get("tab");
	int year = Integer.parseInt(hm.get("year"));
	int companyId = Integer.parseInt(hm.get("companyId"));
	saveOneStrategic(strategicRisks.get(0).getStrategicId(), loggedInUser, todo, tab, year, companyId);

	for (int i = 0; i < strategicRisks.size(); i++) {
	    saveStrategicRisk(strategicRisks.get(i));

	}
	return "risk assesment saved";

    }

    public String amendStrategic(Strategic strategic, User loggedInUser, int year, int companyId) {
	Session session = null;
	try {

	    session = sessionFactory.openSession();
	    Transaction tr = (Transaction) session.beginTransaction();
	    strategic.setInitiatedBy(loggedInUser.getEmployeeId());
	    Employee supervisor = loggedInUser.getEmployeeId().getReportingTo();
	    strategic.setInitiatedBy(supervisor);
	    strategic.setYear(year);// Added year
	    strategic.setCompanyId(companyId);// Added company
	    session.saveOrUpdate(strategic);
	    tr.commit();

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
	    crit.createAlias("owner.userId", "user");
	    crit.createAlias("owner.departmentId", "departmentOwner");
	    crit.createAlias("owner.reportingTo", "ownRep");
	    crit.createAlias("ownRep.reportingTo", "ownReprep");
	    crit.createAlias("ownRep.userId", "ownRepuserId");
	    crit.createAlias("relevantDepartment", "department");
	    crit.createAlias("riskFactor", "risk");
	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("assignedTo", "assigned");
	    crit.createAlias("assigned.userId", "assignedUser");
	    crit.createAlias("assigned.reportingTo", "assignedReporting");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.reportingTo", "initiatedReporting");
	    crit.createAlias("initiatedReporting.userId", "initiatedReportingUser");
	    crit.createAlias("approvedBy", "approveby");
	    crit.createAlias("approveby.userId", "approvedUser");
	    crit.createAlias("approveby.reportingTo", "approvedReposrting");
	    crit.createAlias("approvedReposrting.userId", "approvedReposrtingUser");
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    List rsList = crit.list();

	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		StrategicAudit strategicAudit = (StrategicAudit) it.next();
		strategicAudits.add(strategicAudit);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategicAudits", ex.getMessage()), ex);
	    System.out.println("Exception occured in fetchStrategicAudits " + ex.getMessage());
	} finally {
	    session.close();
	}
	return strategicAudits;
    }

    public ArrayList<DashBoardDTO> fetchDashBoard(User loggedInUser, int year, int companyId) {

	Session session = null;
	// DashBoardDTO dashBoardDTO = new DashBoardDTO();
	ArrayList<DashBoardDTO> dashBoardDTOs = new ArrayList<DashBoardDTO>();
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Strategic.class);
	    crit.createAlias("assignedTo", "assigned");
	    crit.createAlias("assigned.userId", "assignedUser");
	    crit.createAlias("assigned.reportingTo", "assignedReporting");
	    crit.createAlias("approvedBy", "approveby");
	    crit.createAlias("approveby.userId", "approvedUser");
	    crit.add(Restrictions.eq("assigned.employeeId", loggedInUser.getEmployeeId().getEmployeeId()));
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.ne("status", "deleted"));

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
		if (strategic.getApprovedBy().getReportingTo().getReportingTo() != null) {
		    HibernateDetachUtility.nullOutUninitializedFields(
			    strategic.getApprovedBy().getReportingTo().getReportingTo().getUserId(),
			    HibernateDetachUtility.SerializationType.SERIALIZATION);
		}

		DashBoardDTO dashboardDTO = new DashBoardDTO();
		dashboardDTO.setAssignedTo(strategic.getAssignedTo().getEmployeeName());
		dashboardDTO.setInitiatedBy(strategic.getInitiatedBy().getEmployeeName());
		dashboardDTO.setObjectiveName(strategic.getAuditableUnit());
		dashboardDTO.setPhase(strategic.getPhase() + "");
		dashboardDTO.setStatus(strategic.getStatus());
		// dashBoardDTO.getStrategics().add(strategic);
		dashBoardDTOs.add(dashboardDTO);
	    }

	    ArrayList<Risk> risks = fetchEmployeeRisksForApproval(year, companyId,
		    loggedInUser.getEmployeeId().getEmployeeId());
	    ;
	    for (int i = 0; i < risks.size(); i++) {
		DashBoardDTO dashboardDTO = new DashBoardDTO();
		dashboardDTO.setInitiatedBy(risks.get(i).getInitiatedBy().getEmployeeName());
		dashboardDTO.setObjectiveName(risks.get(i).getDescription());
		dashboardDTO.setPhase("Risks(Audit Engagement)");
		dashboardDTO.setStatus("submitted");
		dashBoardDTOs.add(dashboardDTO);
	    }

	    ArrayList<AuditWork> auditWorks = fetchEmployeeAuditWorksForapproval(companyId, year,
		    loggedInUser.getEmployeeId().getEmployeeId());
	    for (int i = 0; i < auditWorks.size(); i++) {
		DashBoardDTO dashboardDTO = new DashBoardDTO();
		dashboardDTO.setInitiatedBy(auditWorks.get(i).getInitiatedBy().getEmployeeName());
		dashboardDTO.setObjectiveName(auditWorks.get(i).getDescription());
		dashboardDTO.setPhase("Audit Work(Audit Engagement)");
		dashboardDTO.setStatus("submitted");
		dashBoardDTOs.add(dashboardDTO);
	    }

	    ArrayList<AuditStep> auditSteps = fetchEmployeeAuditStepsForApproval(year, companyId,
		    loggedInUser.getEmployeeId().getEmployeeId());
	    for (int i = 0; i < auditSteps.size(); i++) {
		DashBoardDTO dashboardDTO = new DashBoardDTO();
		dashboardDTO.setInitiatedBy(auditSteps.get(i).getInitiatedBy().getEmployeeName());
		dashboardDTO.setObjectiveName(auditSteps.get(i).getConclusion());
		dashboardDTO.setPhase("Audit Step(Audit Engagement)");
		dashboardDTO.setStatus("submitted");
		dashBoardDTOs.add(dashboardDTO);
	    }

	    ArrayList<Exceptions> exceptions = fetchEmployeeExceptionsForApproval(year, companyId,
		    loggedInUser.getEmployeeId().getEmployeeId());
	    for (int i = 0; i < exceptions.size(); i++) {
		DashBoardDTO dashboardDTO = new DashBoardDTO();
		dashboardDTO.setInitiatedBy("Audit team");
		dashboardDTO.setObjectiveName(exceptions.get(i).getDetail());
		dashboardDTO.setPhase("Exception(Reporting)");
		dashboardDTO.setStatus("sent");
		dashBoardDTOs.add(dashboardDTO);
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchDashBoard", ex.getMessage()), ex);
	    System.out.println("Exception occured in fetchDashBoard " + ex.getMessage());
	} finally {
	    session.close();
	}

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

		strategics.add(strategic);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return strategics;
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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategics", ex.getMessage()), ex);

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
	    crit.createAlias("rollId", "roll");

	    crit.createAlias("userId", "user");
	    crit.createAlias("skillId", "skill");
	    crit.add(Restrictions.eq("skill.skillId", resourceUse.getSkill().getSkillId()));

	    int resources = crit.list().size();

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

		    return true;
		}

	    }

	    // jNow IT hours are 17 .. now add another resource with IT hours
	    // more than 17
	    // then it should stop the user..

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saveResourceNum", ex.getMessage()), ex);

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
	    crit.createAlias("rollId", "roll");
	    crit.createAlias("reportingTo", "reporting");
	    crit.createAlias("reporting.rollId", "reportingRoll");
	    crit.createAlias("reporting.skillId", "reportingSkill");
	    crit.createAlias("reporting.reportingTo", "reportingTot");
	    crit.add(Restrictions.ne("roll.rollId", 1));
	    crit.add(Restrictions.ne("roll.rollId", 5));
	    crit.add(Restrictions.ne("roll.rollId", 4));

	    crit.createAlias("userId", "user");
	    crit.createAlias("reporting.userId", "userrep");
	    List rsList = crit.list();// .. ?run
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Employee employee = (Employee) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(employee,
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getUserId(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		employees.add(employee);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);

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
	    crit.createAlias("rollId", "roll");
	    crit.createAlias("reportingTo", "reporting");
	    crit.createAlias("reporting.rollId", "reportingRoll");
	    crit.createAlias("reporting.skillId", "reportingSkill");
	    crit.createAlias("reporting.reportingTo", "reportingTot");
	    crit.add(Restrictions.ne("roll.rollId", 1));
	    crit.add(Restrictions.ne("roll.rollId", 5));
	    crit.add(Restrictions.ne("roll.rollId", 4));

	    crit.createAlias("userId", "user");
	    crit.createAlias("reporting.userId", "userrep");
	    List rsList = crit.list();// .. ?run
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Employee employee = (Employee) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(employee,
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getUserId(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(employee.getReportingTo().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		employees.add(employee);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saveResourceNum", ex.getMessage()), ex);

	} finally {
	    session.close();
	}

    }

    public ArrayList<JobAndAreaOfExpertise> fetchCheckBoxStateFor(int jobId) {
	// this is the fetch code .. in your auditPanel mehtiod
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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchCheckBoxStateFor", ex.getMessage()), ex);

	}

	finally {
	    session.close();
	}

	return checkBoxStates;

    }

    public boolean saveCreatedJob(JobCreationDTO job, int year, int companyId) {

	Session session = null;
	try {

	    session = sessionFactory.openSession();
	    deletePreviousResourcesOnThisJob(job.getJob().getJobCreationId());
	    Transaction tr = session.beginTransaction();
	    JobCreation jobCreation = job.getJob();
	    // check below line
	    jobCreation.setRelevantDept(job.getDepartments().get(0).getDepartment().getDepartmentName());
	    jobCreation.setYear(year);
	    jobCreation.setCompanyId(companyId);
	    int jobCreationId = fetchJobCreationId(jobCreation.getJobId());// if
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

	    for (int i = 0; i < job.getJobSkillRelation().size(); ++i)
		saveJobSkillRelation(job.getJobSkillRelation().get(i));
	    tr1.commit();

	} catch (Exception ex) {

	    logger.warn(String.format("Exception occured in saving created job", ex.getMessage()), ex);
	    return false;
	} finally {
	    session.close();
	}

	return true;
    }

    private void deletePreviousResourcesOnThisJob(int jobId) {
	Session session = null;
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(JobEmployeeRelation.class);
	    crit.createAlias("jobCreationId", "jobCreation");
	    crit.createAlias("employeeId", "employee");
	    crit.add(Restrictions.eq("jobCreation.jobCreationId", jobId));
	    List rsList = crit.list();

	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
		session.delete(jobEmployeeRelation);
		session.flush();
	    }
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
	    Criteria crit = session.createCriteria(JobCreation.class);
	    crit.add(Restrictions.eq("jobId", jobId));
	    if (crit.list().size() > 0) {

		JobCreation job = (JobCreation) crit.list().get(0);
		return job.getJobCreationId();
	    }
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
	    Criteria crit = session.createCriteria(JobCreation.class);

	    crit.add(Restrictions.eq(idType, id));

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
	    Criteria crit = session.createCriteria(JobCreation.class);
	    crit.add(Restrictions.eq("jobCreationId", jobcreationId));

	    List rsList = crit.list();

	    for (Iterator it = rsList.iterator(); it.hasNext();)

		return (JobCreation) it.next();

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return null;
    }

    public ArrayList<JobEmployeeRelation> fetchEmployeeJobRelations(int jobCreationId) {
	Session session = null;
	ArrayList<JobEmployeeRelation> relations = new ArrayList<JobEmployeeRelation>();
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(JobEmployeeRelation.class);

	    crit.createAlias("jobCreationId", "jobcreation");
	    // crit.createAlias("jobCreationId", "jobcreation");
	    crit.add(Restrictions.eq("jobcreation.jobCreationId", jobCreationId));

	    crit.createAlias("employeeId", "employee");
	    crit.createAlias("employee.cityId", "employeeCity");
	    crit.createAlias("employee.countryId", "employeeCount");
	    crit.createAlias("employee.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employee.userId", "employeeUser");
	    crit.createAlias("employee.rollId", "employeeRoll");
	    crit.createAlias("employee.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
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
	    crit.createAlias("rollId", "roll");
	    crit.createAlias("reportingTo", "report");
	    crit.createAlias("report.rollId", "reportRoll");
	    crit.createAlias("report.skillId", "reportSKill");
	    crit.createAlias("report.reportingTo", "reportreportingTo");
	    crit.createAlias("reportreportingTo.rollId", "reportreportingToRoll");
	    crit.createAlias("reportreportingTo.skillId", "reporreportingTotSKill");

	    crit.createAlias("userId", "user");
	    crit.createAlias("skillId", "skill");
	    crit.createAlias("reportreportingTo.userId", "userrep");

	    /// Change here
	    crit.add(Restrictions.ne("roll.rollId", 1));
	    crit.add(Restrictions.ne("roll.rollId", 4));
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

	    crit.add(Restrictions.eq("employee.employeeId", employeeId));
	    crit.createAlias("employee.countryId", "employeeCount");
	    crit.createAlias("employee.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employee.userId", "employeeUser");
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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  fetchCreatedJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return relations;
    }

    public JobCreation fetchSelectedJob(int jobId) {
	Session session = null;
	JobCreation job = null;
	try {
	    session = sessionFactory.openSession();
	    job = (JobCreation) session.get(JobCreation.class, jobId);

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
			if (!(employeesOnThisJobs.get(i).getRollId().getRollId() == 1)) {
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

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiatedRep.cityId", "initiatedRCity");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    ArrayList<Integer> jobIds = fetchjobEmployee(loggedInEmployee);
	    crit.createAlias("jobCreation", "jobcreation");
	    ////////// FETCHING ONLY JOBS OF LOGGEDIN EMPLOYEE
	    if (jobIds.size() <= 0) {
		return null;
	    }
	    Disjunction disc = Restrictions.disjunction();
	    for (int i = 0; i < jobIds.size(); i++) {
		disc.add(Restrictions.eq("jobcreation.jobCreationId", jobIds.get(i)));
	    }
	    crit.add(disc);

	    //////////
	    List rsList = crit.list();

	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		AuditEngagement auditEngagement = (AuditEngagement) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(auditEngagement,
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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  fetchAllAuditEngagement", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return records;
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
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiatedRep.cityId", "initiatedRCity");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    crit.createAlias("jobCreation", "jobcreation");
	    crit.add(Restrictions.eq("jobcreation.jobCreationId", jobCreationId));
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));

	    List rsList = crit.list();
	    AuditEngagement auditEngagement = null;
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		// return (AuditEngagement)it.next( ) ;
		auditEngagement = (AuditEngagement) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(auditEngagement,
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getInitiatedBy().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(
			auditEngagement.getInitiatedBy().getReportingTo().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(auditEngagement.getApprovedBy(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
	    }
	    return auditEngagement;

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  fetchAuditEngagement", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return record;
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

	    //// change here
	    record.setApprovedBy((Employee) session.get(Employee.class, 0));
	    record.setInitiatedBy((Employee) session.get(Employee.class, 0));
	    ///
	    session.save(record);
	    tr.commit();

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
	    critAuditEng.createAlias("auditEng.jobCreation", "jobcreation");
	    ////////// FETCHING ONLY JOBS OF LOGGEDIN EMPLOYEE
	    if (jobIds.size() <= 0) {
		return;
	    }
	    Disjunction disc = Restrictions.disjunction();
	    for (int i = 0; i < jobIds.size(); i++) {
		disc.add(Restrictions.eq("jobcreation.jobCreationId", jobIds.get(i)));
	    }
	    critAuditEng.add(disc);

	    //////////
	    critAuditEng.setProjection(Projections.property("jobcreation.jobCreationId"));

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
	    crit.createAlias("jobCreation", "jobCreat");
	    crit.add(Restrictions.eq("jobCreat.jobCreationId", jobCreationId));
	    if (crit.list().size() > 0) {
		alreadySaved = true;
	    } else {
		alreadySaved = false;
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in auditEngagemtAlreadysaved", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return alreadySaved;

    }

    private void saveRisk(Risk risk) {

	Session session = null;
	try {

	    session = sessionFactory.openSession();
	    Transaction tr = session.beginTransaction();

	    if (risk.getInitiatedBy() == null) {
		risk.setInitiatedBy((Employee) session.get(Employee.class, risk.getInitiatedBy().getEmployeeId()));
	    }
	    if (risk.getApprovedBy() != null) {
		risk.setApprovedBy((Employee) session.get(Employee.class, risk.getApprovedBy().getEmployeeId()));
	    }

	    session.saveOrUpdate(risk);
	    tr.commit();

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saving skill relation job", ex.getMessage()), ex);

	} finally {
	    session.close();

	}
    }

    public boolean saveRisks(ArrayList<Risk> records, int year, int companyId) {

	for (Risk r : records) {
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

    public ArrayList<Risk> fetchRisks(int auditEngId, int year, int companyId) {
	Session session = null;
	ArrayList<Risk> record = new ArrayList<Risk>();

	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Risk.class);

	    crit.add(Restrictions.eq("auditEngageId", auditEngId));
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employeeRep.cityId", "employeeRCity");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCity");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    List rsList = crit.list();

	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Risk risk = (Risk) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(risk,
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(risk.getInitiatedBy().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(
			risk.getInitiatedBy().getReportingTo().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(risk.getApprovedBy(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		record.add(risk);
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  fetchAuditEngagement", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return record;
    }

    public ArrayList<Risk> fetchEmployeeRisksForApproval(int year, int companyId, int employeeId) {
	Session session = null;
	ArrayList<Risk> record = new ArrayList<Risk>();

	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Risk.class);

	    // crit.add(Restrictions.eq("auditEngageId" , auditEngId));
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("status", InternalAuditConstants.SUBMIT));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employeeRep.cityId", "employeeRCity");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCity");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    crit.add(Restrictions.eq("initiatedRep.employeeId", employeeId));

	    List rsList = crit.list();

	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Risk risk = (Risk) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(risk,
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(risk.getInitiatedBy().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(
			risk.getInitiatedBy().getReportingTo().getReportingTo(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		HibernateDetachUtility.nullOutUninitializedFields(risk.getApprovedBy(),
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		record.add(risk);
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  fetchAuditEngagement", ex.getMessage()), ex);

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
	    crit.createAlias("rollId", "roll");

	    crit.createAlias("userId", "user");
	    crit.createAlias("skillId", "skill");
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Employee employee = (Employee) it.next();
		employees.add(employee);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEmployees", ex.getMessage()), ex);

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
	    crit.createAlias("employeeId", "emp");
	    crit.createAlias("employeeId.rollId", "empRoll");

	    crit.add(Restrictions.eq("jobCreation.jobCreationId", selectedJobId));
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
		employees.add(jobEmployeeRelation.getEmployee());
	    }
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
		jobCreation.setReportStatus(fetchJobStatus(jobCreation.getJobCreationId()));
		HibernateDetachUtility.nullOutUninitializedFields(jobCreation,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		jobsList.add(jobCreation);
	    }
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
	} catch (ParseException e) {

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

    private int fetchJobStatus(int jobId) {
	Session session = null;
	ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
	int status = 0;
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Exceptions.class);
	    crit.add(Restrictions.eq("jobCreationId", jobId));
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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return status;

    }

    public ArrayList<Exceptions> fetchJobExceptions(int jobId, int year, int companyId) {

	Session session = null;
	ArrayList<Exceptions> exceptions = new ArrayList<Exceptions>();
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Exceptions.class);
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.createAlias("responsiblePerson", "employee");
	    crit.createAlias("employee.countryId", "employeeCount");
	    crit.createAlias("employee.cityId", "employeeCity");
	    crit.createAlias("employee.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employee.userId", "employeeUser");

	    crit.createAlias("divisionHead", "divHead");
	    crit.createAlias("divHead.countryId", "divHeadCount");
	    crit.createAlias("divHead.cityId", "divHeadCity");
	    crit.createAlias("divHead.reportingTo", "divHeadRep");

	    crit.createAlias("divHead.skillId", "divHeadSkill");
	    crit.createAlias("divHead.rollId", "divHeadRoll");

	    crit.createAlias("divHeadRep.skillId", "divHeadSkill1");
	    crit.createAlias("divHeadRep.rollId", "divHeadRoll1");

	    crit.createAlias("employee.skillId", "employeeSkill");
	    crit.createAlias("employee.rollId", "employeeRoll");

	    crit.createAlias("employeeRep.skillId", "employeeSkill2");
	    crit.createAlias("employeeRep.rollId", "employeeRoll2");

	    // crit.createAlias("divHead.countryId", "divHeadRCount");
	    crit.createAlias("divHead.userId", "divHeadUser");
	    if (jobId != 0) {
		crit.add(Restrictions.eq("jobCreationId", jobId));
	    }
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Exceptions exception = (Exceptions) it.next();

		HibernateDetachUtility.nullOutUninitializedFields(exception,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		exceptions.add(exception);
	    }
	    if (jobId == 0) {
		sendEmailNotifications(exceptions);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return exceptions;
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
	    String jobName = fetchJobName(exception.getJobCreationId(), session);
	    int auditHead = fetchAuditHead(exception.getJobCreationId(), session);
	    Transaction tr = session.beginTransaction();

	    exception.setJobName(jobName);
	    if (exception.getAuditHead() == 0) {
		exception.setAuditHead(auditHead);
	    }
	    exception.setYear(year);
	    exception.setCompanyId(companyId);
	    session.update(exception);
	    tr.commit();
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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.createAlias("responsiblePerson", "employee");
	    crit.createAlias("divisionHead", "divhead");
	    crit.add(Restrictions.eq("employee.employeeId", employeeId));
	    crit.add(Restrictions.eq("initialStatus", "Approved"));
	    crit.add(Restrictions.eq("jobCreationId", jobId));
	    crit.createAlias("employee.countryId", "employeeCount");
	    crit.createAlias("employee.cityId", "employeeCity");
	    crit.createAlias("employee.reportingTo", "employeeRep");
	    crit.createAlias("divhead.reportingTo", "divheadRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employeeRep.cityId", "employeeRCity");
	    crit.createAlias("employee.userId", "employeeUser");
	    crit.createAlias("employee.rollId", "employeeRoll");
	    crit.createAlias("employee.skillId", "employeeSkill");
	    crit.createAlias("divhead.rollId", "divheadRoll");
	    crit.createAlias("divhead.cityId", "divheadcityy");
	    crit.createAlias("divhead.skillId", "divheadSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");
	    crit.createAlias("divheadRep.rollId", "divheadRepRoll");
	    crit.createAlias("divheadRep.skillId", "divheadRepSkill");

	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Exceptions exception = (Exceptions) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(exception,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		exceptions.add(exception);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saving skill relation job", ex.getMessage()), ex);

	} finally {
	    // session.close();

	}

    }

    private void saveAuditStep(AuditStep auditstep, ArrayList<Exceptions> exceptions, int year, int companyId) {

	Session session = null;
	Transaction tr = null;
	try {
	    System.out.println("d");
	    session = sessionFactory.openSession();
	    tr = session.beginTransaction();
	    if (auditstep.getAuditStepId() != 0) {
		Employee initiatedBy = fetchInitiatedBy(auditstep.getAuditStepId());
		auditstep.setInitiatedBy(initiatedBy);
	    }

	    auditstep.setYear(year);
	    auditstep.setCompanyId(companyId);

	    if (auditstep.getInitiatedBy() == null) {
		auditstep.setInitiatedBy(
			(Employee) session.get(Employee.class, auditstep.getInitiatedBy().getEmployeeId()));
	    }
	    if (auditstep.getApprovedBy() != null) {
		auditstep.setApprovedBy(
			(Employee) session.get(Employee.class, auditstep.getApprovedBy().getEmployeeId()));
	    }

	    session.saveOrUpdate(auditstep);
	    session.flush();
	    for (Exceptions exception : exceptions)
		saveException(exception, session, auditstep.getAuditStepId(), year, companyId);
	    tr.commit();

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
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    AuditStep auditStep = (AuditStep) crit.list().get(0);
	    initiatedBy = auditStep.getInitiatedBy();

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
	    crit.createAlias("audWork.jobCreationId", "jobCreationId");
	    crit.add(Restrictions.eq("audWork.auditWorkId", auditWorkId));
	    crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("auditWork.initiatedBy", "initiatedAuditWork");
	    crit.createAlias("auditWork.approvedBy", "approvedAuditWork");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.cityId", "employeeCity");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCity");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

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

		return auditStep;
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

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
	    crit.createAlias("audWork.jobCreationId", "jobCreationId");
	    crit.add(Restrictions.eq("status", InternalAuditConstants.SUBMIT));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("auditWork.initiatedBy", "initiatedAuditWork");
	    crit.createAlias("auditWork.approvedBy", "approvedAuditWork");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.cityId", "employeeCity");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCity");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
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
	    // return records;
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.createAlias("responsiblePerson", "responsible");
	    // crit.createAlias("auditStep", "audStep");
	    crit.add(Restrictions.eq("auditStep", auditStepId));
	    // crit.add(Restrictions.eq("jobCreationId", selectedJobId));
	    crit.createAlias("divisionHead", "division");

	    crit.createAlias("responsible.countryId", "employeeCount");
	    crit.createAlias("responsible.cityId", "employeeCity");
	    crit.createAlias("responsible.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("responsible.userId", "employeeUser");

	    crit.createAlias("division.countryId", "employeeCount1");
	    crit.createAlias("division.cityId", "employeeCity1");
	    crit.createAlias("division.reportingTo", "employeeRep1");
	    crit.createAlias("employeeRep1.countryId", "employeeRCount1");
	    crit.createAlias("divisionHead.userId", "employeeUser1");

	    crit.createAlias("division.rollId", "divisionRoll");
	    crit.createAlias("division.skillId", "divisionSkill");
	    crit.createAlias("employeeRep1.rollId", "divisionRoll1");
	    crit.createAlias("employeeRep1.skillId", "divisionSkill1");

	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Exceptions exception = (Exceptions) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(exception,
			HibernateDetachUtility.SerializationType.SERIALIZATION);
		records.add(exception);

	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.createAlias("responsiblePerson", "responsible");
	    crit.createAlias("divisionHead", "division");

	    crit.createAlias("responsible.countryId", "employeeCount");
	    crit.createAlias("responsible.cityId", "employeeCity");
	    crit.createAlias("responsible.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("responsible.userId", "employeeUser");

	    crit.createAlias("division.countryId", "employeeCount1");
	    crit.createAlias("division.cityId", "employeeCity1");
	    crit.createAlias("division.reportingTo", "employeeRep1");
	    crit.createAlias("employeeRep1.countryId", "employeeRCount1");
	    crit.createAlias("divisionHead.userId", "employeeUser1");

	    crit.createAlias("division.rollId", "divisionRoll");
	    crit.createAlias("division.skillId", "divisionSkill");
	    crit.createAlias("employeeRep1.rollId", "divisionRoll1");
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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchEceptions", ex.getMessage()), ex);

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saving skill relation job", ex.getMessage()), ex);

	} finally {
	    session.close();

	}

    }

    public void updateKickoffStatus(int auditEngId, int year, int companyId, User loggedInUser) {
	Session session = null;

	try {

	    session = sessionFactory.openSession();

	    AuditEngagement prevCreated = fetchAuditEngagement(auditEngId, year, companyId);

	    if (prevCreated != null) {
		prevCreated.setJobStatus("In Progress");
		prevCreated.setYear(year);
		// prevCreated.setInitiatedBy(loggedInUser.getEmployeeId());
		prevCreated.setInitiatedBy(
			(Employee) session.get(Employee.class, loggedInUser.getEmployeeId().getEmployeeId()));
		prevCreated.setApprovedBy((Employee) session.get(Employee.class, 0));
		Transaction tr = session.beginTransaction();
		session.saveOrUpdate(prevCreated);
		tr.commit();
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in  updating date", ex.getMessage()), ex);
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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.createAlias("responsiblePerson", "employee");
	    crit.createAlias("divisionHead", "divHead");
	    // crit.add(Restrictions.eq("auditHead", auditHeadId));
	    crit.add(Restrictions.eq("jobCreationId", selectedJob));

	    crit.createAlias("employee.countryId", "employeeCount");
	    crit.createAlias("employee.cityId", "employeeCity");
	    crit.createAlias("employee.reportingTo", "employeeRep");
	    crit.createAlias("divHead.reportingTo", "divHeadRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employee.userId", "employeeUser");

	    crit.createAlias("employee.rollId", "employeeRoll");
	    crit.createAlias("employee.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepkill");
	    crit.createAlias("divHead.rollId", "divHeadRoll");
	    crit.createAlias("divHead.cityId", "divHeadCity");
	    crit.createAlias("divHead.skillId", "divHeadSkill");
	    crit.createAlias("divHeadRep.rollId", "divHeadRepRoll");
	    crit.createAlias("divHeadRep.skillId", "divHeadRepSkill");

	    crit.add(Restrictions.ne("managementComments", ""));
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Exceptions exception = (Exceptions) it.next();
		HibernateDetachUtility.nullOutUninitializedFields(exception,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		exceptions.add(exception);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchAuditHeadEx", ex.getMessage()), ex);

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
	    crit.add(Restrictions.eq("jobCreation.jobCreationId", jocreationid));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.ne("status", InternalAuditConstants.DELETED));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.cityId", "employeeCityy");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCityy");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchAuditWorkRows", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return rows;
    }

    //
    public ArrayList<AuditWork> fetchEmployeeAuditWorksForapproval(int companyId, int year, int employeeId) {

	Session session = null;
	ArrayList<AuditWork> rows = new ArrayList<AuditWork>();
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(AuditWork.class);
	    crit.createAlias("jobCreationId", "jobCreation");
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("status", InternalAuditConstants.SUBMIT));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.cityId", "employeeCityy");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCityy");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    crit.add(Restrictions.eq("initiatedRep.employeeId", employeeId));

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchAuditWorkRows", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return rows;
    }

    public ArrayList<AuditWork> fetchApprovedAuditWorkRows(int selectedJobId) {
	Session session = null;
	ArrayList<AuditWork> rows = new ArrayList<AuditWork>();
	try {
	    session = sessionFactory.openSession();

	    Criteria crit = session.createCriteria(AuditWork.class);
	    crit.createAlias("jobCreationId", "jobCreation");
	    crit.add(Restrictions.eq("jobCreation.jobCreationId", selectedJobId));
	    crit.add(Restrictions.eq("status", InternalAuditConstants.APPROVED));

	    crit.createAlias("approvedBy", "approved");
	    crit.createAlias("approved.countryId", "employeeCount");
	    crit.createAlias("approved.cityId", "employeeCity");
	    crit.createAlias("approved.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("approved.userId", "employeeUser");
	    crit.createAlias("approved.rollId", "employeeRoll");
	    crit.createAlias("approved.skillId", "employeeSkill");
	    crit.createAlias("employeeRep.rollId", "employeeRepRoll");
	    crit.createAlias("employeeRep.skillId", "employeeRepSkill");

	    crit.add(Restrictions.eq("employeeRoll.rollId", 1));

	    crit.createAlias("initiatedBy", "initiated");
	    crit.createAlias("initiated.countryId", "initiatedCount");
	    crit.createAlias("initiated.cityId", "initiatedCity");
	    crit.createAlias("initiated.reportingTo", "initiatedRep");
	    crit.createAlias("initiatedRep.countryId", "initiatedRCount");
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchApprovedExceptions", ex.getMessage()), ex);

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

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
	    crit.createAlias("employeeId", "employee");
	    crit.add(Restrictions.eq("employee.employeeId", employeeId));
	    crit.createAlias("jobCreationId", "jobCreation");

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

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
	    crit.add(Restrictions.eq("employee.employeeId", employeeId));
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		JobEmployeeRelation jobEmployeeRelation = (JobEmployeeRelation) it.next();
		JobCreation job = jobEmployeeRelation.getJobCreationId();
		employeeJobs.add(job);

		HibernateDetachUtility.nullOutUninitializedFields(jobEmployeeRelation,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

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
			&& auditStep.getApprovedBy().getRollId().getRollId() == 1) {
		    jobAuditStepApproved = true;
		} else {
		    jobAuditStepApproved = false;
		}

		HibernateDetachUtility.nullOutUninitializedFields(auditStep,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return jobAuditStepApproved;

    }

    // FETCH EMPLYEE JOBS FOR which audit steps are approved by Audit Head
    public ArrayList<JobCreation> fetchEmployeeJobs(Employee loggedInEmployee, int year, int companyId) {
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
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		JobCreation jobCreation = (JobCreation) it.next();
		jobCreation.setReportStatus(fetchJobStatus(jobCreation.getJobCreationId()));
		// ADDED these 2 lines
		session.update(jobCreation);
		session.flush();
		// ENd added
		HibernateDetachUtility.nullOutUninitializedFields(jobCreation,
			HibernateDetachUtility.SerializationType.SERIALIZATION);

		jobsList.add(jobCreation);
	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return jobsList;
    }

    public String saveAuditNotification(int auditEngagementId, String message, String to, String cc, int year,
	    int companyId) {
	Session session = null;
	try {
	    session = sessionFactory.openSession();
	    AuditEngagement auditEngagement = (AuditEngagement) session.get(AuditEngagement.class, auditEngagementId);
	    auditEngagement.setAuditNotification(message);
	    auditEngagement.setTo(to);
	    auditEngagement.setCc(cc);
	    auditEngagement.setYear(year);
	    auditEngagement.setCompanyId(companyId);
	    session.flush();
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saveAuditNotification", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	sendEmail(message, to, cc, "Audit Notification");
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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofInProgressJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return num;
    }

    public boolean isJobInprogress(int jobId) {
	Session session = null;
	boolean completed = false;
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(AuditEngagement.class);
	    crit.createAlias("jobCreation", "job");
	    crit.add(Restrictions.eq("job.jobId", jobId));
	    crit.add(Restrictions.eq("jobStatus", "In Progress"));
	    if (crit.list().size() > 0) {
		completed = true;
	    } else {
		completed = false;
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofInProgressJobs", ex.getMessage()), ex);

	} finally {
	    session.close();
	}
	return completed;
    }

    public boolean isJobCompleted(int jobId) {
	Session session = null;
	boolean jobInProgress = false;
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(JobCreation.class);
	    crit.add(Restrictions.eq("jobId", jobId));
	    crit.add(Restrictions.eq("reportStatus", 3));
	    if (crit.list().size() > 0) {
		jobInProgress = true;
	    }

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
	    crit.add(Restrictions.eq("jobId", jobId));
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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in isJobCompleted", ex.getMessage()), ex);

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
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
		Date date = df.parse(job.getStartDate());
		DateTime enteredDate = new DateTime(date);
		DateTime currentDate = new DateTime(); // current date
		int week = enteredDate.getWeekOfWeekyear();
		int currentWeek = currentDate.getWeekOfWeekyear();
		if (week - currentWeek == 0) {// jobs kick of in this week
		    jobs.add(job.getJobName());
		}
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofComletedJobs", ex.getMessage()), ex);

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
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
		Date date = df.parse(job.getEndDate());
		DateTime enteredDate = new DateTime(date);
		DateTime currentDate = new DateTime(); // current date
		int week = enteredDate.getWeekOfWeekyear();
		int currentWeek = currentDate.getWeekOfWeekyear();
		if (week - currentWeek == 0) {// jobs kick of in this week
		    jobs.add(job.getJobName());
		}
	    }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofComletedJobs", ex.getMessage()), ex);

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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.setProjection(Projections.rowCount());
	    num = (Integer) crit.uniqueResult();

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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.ne("finalStatus", "Approved"));
	    // crit.add(Restrictions.isNotEmpty("managementComments"));
	    crit.add(Restrictions.isNotNull("managementComments"));
	    crit.setProjection(Projections.rowCount());
	    num = (Integer) crit.uniqueResult();

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberOfAufitObservations", ex.getMessage()), ex);

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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("isImplemented", 1));
	    crit.setProjection(Projections.rowCount());
	    num = (Integer) crit.uniqueResult();

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberOfAufitObservations", ex.getMessage()), ex);

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
	    // crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("isImplemented", 0));
	    crit.add(Restrictions.le("implementaionDate", new Date()));
	    crit.setProjection(Projections.rowCount());
	    num = (Integer) crit.uniqueResult();

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberOfAufitObservations", ex.getMessage()), ex);

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
	    crit.createAlias("rollId", "roll");

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberOfAufitObservations", ex.getMessage()), ex);

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchJobResourceId", ex.getMessage()), ex);

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
	    crit.createAlias("assigned.userId", "assignedUser");
	    crit.createAlias("initiated.userId", "initiatedUser");
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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofComletedJobs", ex.getMessage()), ex);
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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in strategicHaveTheDept", ex.getMessage()), ex);

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

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchStrategicdepartments", ex.getMessage()), ex);

	} finally {

	}
	return strategicDepartments;

    }

    public String exportToExcel(ArrayList<ExcelDataDTO> excelDataList, String rootDir) {
	try {

	    FileOutputStream fileOut = new FileOutputStream(rootDir + "/report.xls");// "D:\\POI111.xls"
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
	    crit.createAlias("assigned.userId", "assignedUser");
	    crit.createAlias("initiated.userId", "initiatedUser");
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

	    if (!jobStatus.contains("All")) {
		for (int i = 0; i < jobStatus.size(); i++) {

		    jobStatusOR.add(Restrictions.eq("rating", jobStatus.get(i)));
		}
		crit.add(jobStatusOR);
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
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofComletedJobs", ex.getMessage()), ex);
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
	    crit.add(Restrictions.eq("jobId", strategicId));
	    if (crit.list().size() > 0) {
		job = (JobCreation) crit.list().get(0);

	    }
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
		User user = employee.getUserId();

		user.setEmployeeId(employee);
		session.save(user);
		employee.setUserId(user);
		if (employee.getReportingTo().getEmployeeId() == 0) {
		    employee.setReportingTo(employee);
		}
		session.update(employee);
		session.flush();
		if (employee.getRollId().getRollId() != 4 && employee.getRollId().getRollId() != 5) {
		    addAvailableHoursInSkills(employee.getSkillId().getSkillId(),
			    employee.getTotalNumberOfHoursAvailable(), year, companyId);

		}

		return "user saved";
	    } catch (Exception ex) {
		logger.warn(String.format("Exception occured in saveUser", ex.getMessage()), ex);
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
	    Criteria crit = session.createCriteria(User.class);
	    crit.add(Restrictions.eq("name", email));
	    if (crit.list().size() > 0) {
		return false;
	    } else {
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
	    return "company added";
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in saveCompany", ex.getMessage()), ex);
	    return "company adding failed";
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

	    return companies;// Return BEFORE catch Statement..

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchCompanies", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}
    }

    public ArrayList<Rolls> fetchRolls() {
	Session session = null;
	ArrayList<Rolls> rolls = new ArrayList<Rolls>();
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Rolls.class);
	    crit.add(Restrictions.ne("rollId", 0));
	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Rolls roll = (Rolls) it.next();
		rolls.add(roll);
	    }

	    return rolls;// Return BEFORE catch Statement..

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchRoles", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}
    }

    public String updateStrategic(Strategic strategic) {
	Session session = null;
	try {
	    session = sessionFactory.openSession();
	    session.update(strategic);
	    session.flush();
	    return "strategic updated";
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in updateStrategic", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}
    }

    public String deleteRisk(Risk risk) {
	Session session = null;
	try {
	    session = sessionFactory.openSession();
	    risk.setInitiatedBy((Employee) session.get(Employee.class, risk.getInitiatedBy().getEmployeeId()));
	    risk.setApprovedBy((Employee) session.get(Employee.class, risk.getApprovedBy().getEmployeeId()));
	    session.update(risk);
	    session.delete(risk);
	    session.flush();
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
	    return "exception deleted";
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in deleteException", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}
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

	    return approveSchedulingInJobTimeEstimation(companyId);

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in approveScheduling", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}

    }

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
	    return true;
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in approveScheduling", ex.getMessage()), ex);
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
	    employee = (Employee) session.get(Employee.class, employeeId);
	    // Criteria crit = session.createCriteria(Employee.class);
	    // crit.add(Restrictions.eq(employeeId, value))
	    return employee;
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchSelectedEmployee", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}

    }

    public String updateUser(int previousHours, Employee employee) throws Exception {
	try {
	    session = sessionFactory.openSession();
	    session.update(employee);
	    User user = employee.getUserId();
	    session.update(user);
	    if (employee.getReportingTo().getEmployeeId() == 0) {
		employee.setReportingTo(employee);
	    }
	    session.update(employee);
	    session.flush();
	    if (employee.getRollId().getRollId() != 4 && employee.getRollId().getRollId() != 5) {
		// addAvailableHoursInSkills(employee.getSkillId().getSkillId(),
		// employee.getTotalNumberOfHoursAvailable(), year, companyId);

	    }
	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchSelectedEmployee", ex.getMessage()), ex);
	    return null;
	} finally {
	    session.close();
	}
	return null;
    }

    public ArrayList<JobCreation> fetchReportWithResourcesSearchResult(ArrayList<String> div, ArrayList<String> domain,
	    ArrayList<String> risk, ArrayList<String> resources, int year, int companyId) {
	Session session = null;
	ArrayList<JobCreation> jobs = new ArrayList<JobCreation>();
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
	    // u there?
	    // for now forget about sending back the Departments list ..??vthis

	    // }

	} catch (Exception ex) {
	    logger.warn(String.format("Exception occured in fetchNumberofComletedJobs", ex.getMessage()), ex);
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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));

	    // crit.createAlias("responsiblePerson", "respPerson");

	    ///////////////////////

	    crit.createAlias("responsiblePerson", "employee");
	    crit.createAlias("employee.countryId", "employeeCount");
	    crit.createAlias("employee.cityId", "employeeCity");
	    crit.createAlias("employee.reportingTo", "employeeRep");
	    crit.createAlias("employeeRep.countryId", "employeeRCount");
	    crit.createAlias("employee.userId", "employeeUser");

	    crit.createAlias("divisionHead", "divHead");
	    crit.createAlias("divHead.countryId", "divHeadCount");
	    crit.createAlias("divHead.cityId", "divHeadCity");
	    crit.createAlias("divHead.reportingTo", "divHeadRep");

	    crit.createAlias("divHead.skillId", "divHeadSkill");
	    crit.createAlias("divHead.rollId", "divHeadRoll");

	    crit.createAlias("divHeadRep.skillId", "divHeadSkill1");
	    crit.createAlias("divHeadRep.rollId", "divHeadRoll1");

	    crit.createAlias("employee.skillId", "employeeSkill");
	    crit.createAlias("employee.rollId", "employeeRoll");

	    crit.createAlias("employeeRep.skillId", "employeeSkill2");
	    crit.createAlias("employeeRep.rollId", "employeeRoll2");

	    // crit.createAlias("divHead.countryId", "divHeadRCount");
	    crit.createAlias("divHead.userId", "divHeadUser");

	    ///////////////////////

	    crit.add(Restrictions.ne("employee.employeeId", 0));
	    crit.add(Restrictions.ne("divHead.employeeId", 0));
	    Disjunction jobsOR = Restrictions.disjunction();

	    for (int i = 0; i < jobIds.size(); i++) {

		jobsOR.add(Restrictions.eq("jobCreationId", jobIds.get(i)));
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

	    FileOutputStream fileOut = new FileOutputStream(rootDir + "/report.xls");// "D:\\POI111.xls"
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

	    FileOutputStream fileOut = new FileOutputStream(rootDir + "/report.xls");// "D:\\POI111.xls"
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

	    FileOutputStream fileOut = new FileOutputStream(rootDir + "/report.xls");// "D:\\POI111.xls"
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
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("isImplemented", 1));

	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Exceptions exceptions = (Exceptions) it.next();
		exceptionImplementationsOverdueJobs.add(exceptions.getJobName());

	    }
	    return exceptionImplementationsOverdueJobs;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.info("eeror in : exceptionImplementationsOverdueJobs");

	    throw e;
	}
    }

    public ArrayList<String> fetchExceptioNotImplemented(int year, int companyId) throws Exception {
	Session session = null;
	ArrayList<String> exceptionImplementationsOverdueJobs = new ArrayList<String>();
	try {
	    session = sessionFactory.openSession();
	    Criteria crit = session.createCriteria(Exceptions.class);
	    crit.add(Restrictions.eq("year", year));
	    crit.add(Restrictions.eq("companyId", companyId));
	    crit.add(Restrictions.eq("isImplemented", 0));
	    crit.add(Restrictions.eq("status", "Approved"));

	    List rsList = crit.list();
	    for (Iterator it = rsList.iterator(); it.hasNext();) {
		Exceptions exceptions = (Exceptions) it.next();
		exceptionImplementationsOverdueJobs.add(exceptions.getJobName());

	    }
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

    public DashBoardNewDTO fetchDashBoard(int year, int companyId) throws Exception {

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

	dashBoardDTO.setJobsInExecCount(executionCount);
	dashBoardDTO.setJobsInPlaning(planningCount);
	dashBoardDTO.setJobsInReporting(inReportingCount);

	dashBoardDTO.setImplemented(implemented);
	dashBoardDTO.setNotImplemented(notImplemented);

	fetchReports(year, companyId, dashBoardDTO);

	return dashBoardDTO;
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
	    crit.createAlias("initiated.userId", "initiatedUser");
	    crit.createAlias("initiated.rollId", "initiatedRoll");
	    crit.createAlias("initiated.skillId", "initiatedSkill");
	    crit.createAlias("initiatedRep.rollId", "initiatedRepRoll");
	    crit.createAlias("initiatedRep.skillId", "initiatedRepSkill");

	    AuditStep auditStep = (AuditStep) crit.list().get(0);
	    auditStep.setUploadedFile(auditStepFiles.get(0));
	    session.update(auditStep);
	    session.flush();
	    return "file uploaded";
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.info("eeror in : updateUploadedAuditStepFile");

	    throw e;
	}

    }

}
