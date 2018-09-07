
package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class DashboardListBoxDTO implements Serializable {

	

	private ArrayList<Process> processList;
	private ArrayList<JobCreation> jobList;
	private ArrayList<Employee> employList;
	
		
	
	
//	private ArrayList<SubProcess> subProcessList;
//	private ArrayList<JobType> jobTypeList;
		
	

	public DashboardListBoxDTO() { 

	}

	public ArrayList<Process> getProcessList() {
		return processList;
	}

	public void setProcessList(ArrayList<Process> processList) {
		this.processList = processList;
	}

	public ArrayList<JobCreation> getJobList() {
		return jobList;
	}

	public void setJobList(ArrayList<JobCreation> jobList) {
		this.jobList = jobList;
	}

	public ArrayList<Employee> getEmployList() {
		return employList;
	}

	public void setEmployList(ArrayList<Employee> employList) {
		this.employList = employList;
	}


}