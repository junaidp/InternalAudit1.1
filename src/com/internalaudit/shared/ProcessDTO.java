package com.internalaudit.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class ProcessDTO implements Serializable {

	

	private ArrayList<Process> processList;
	
	private ArrayList<SubProcess> subProcessList;
	private ArrayList<JobType> jobTypeList;
		
	

	public ProcessDTO() { 

	}



	public ArrayList<Process> getProcessList() {
		return processList;
	}



	public void setProcessList(ArrayList<Process> processList) {
		this.processList = processList;
	}



	public ArrayList<SubProcess> getSubProcessList() {
		return subProcessList;
	}



	public void setSubProcssList(ArrayList<SubProcess> subProcessList) {
		this.subProcessList = subProcessList;
	}



	public ArrayList<JobType> getJobTypeList() {
		return jobTypeList;
	}



	public void setJobTypeList(ArrayList<JobType> jobTypeList) {
		this.jobTypeList = jobTypeList;
	}

}
