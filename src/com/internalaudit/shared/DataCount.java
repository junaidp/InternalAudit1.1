package com.internalaudit.shared;

import java.util.ArrayList;

public class DataCount {

	public class Domain {
		
		public int strategic = 0 ;
		public int operations = 0; //dummy data
		public int compliance = 0;
		public int reporting = 0;
	}
	
	public class Division {
		
		public int it = 0;
		public int finance = 0;
		public int business = 0;
		public int strategy = 0;
		public int reg = 0;
		public int comm = 0;
		public int hr = 0;
		public int pl = 0;
		
		// todo : add others
		
	}
	
	public class RiskAssesment {
		
		public int hi = 0; //dummy
		public int mid = 0;
		public int low = 0;
		
	}
	
	public Domain domain = new Domain();
	public Division div = new Division();
	public RiskAssesment risk = new RiskAssesment();
	
	public void getDataCount( ArrayList<Strategic> list)
	{
		for( int k = 0; k < list.size(); k++)
		{
			String r = list.get(k).getRating();
			
			if ( r.equals("High") ) risk.hi++;
			
			if ( r.equals("Low") ) risk.low++;
			
			if ( r.equals("Medium") ) risk.mid++;
		}
		
		for( int k = 0; k < list.size(); k++)
		{
			String r = list.get(k).getDomain();
			
			if ( r.equals("Strategic") ) domain.strategic++;
			
			if ( r.equals("Operations") ) domain.operations++;
			
			if ( r.equals("Compliance") ) domain.compliance++;
			
			if ( r.equals("Reporting") ) domain.reporting++;
		}

		
		
	}

	public void getDivisionCount(ArrayList<StrategicDepartments> list) {
		
		for( int k = 0; k < list.size(); k++)
		{
			String r =  list.get(k).getDepartment().getDepartmentName(); //need it here. for charts??
			
			if ( r.equals("IT") ) div.it++;
			
			if ( r.equals("Finance") ) div.finance++;
			
			if ( r.equals("Business") ) div.business++;
			
			if ( r.equals("Strategy") ) div.strategy++;
			
			if ( r.equals("Commercial") ) div.comm++;
			
			if ( r.equals("Human Resource") ) div.hr++;

			if ( r.equals("Regularoty") ) div.reg++;
			
			if ( r.equals("Procurement and Logistics") ) div.pl++;
		}
		
		
	}
	
	
	public void getJobDurationDataCount( ArrayList<JobCreation> list)
	{
		for( int k = 0; k < list.size(); k++)
		{
			String r = list.get(k).getEstimatedWeeks()+"";
			
			if ( r.equals("High") ) risk.hi++;
			
			if ( r.equals("Low") ) risk.low++;
			
			if ( r.equals("Medium") ) risk.mid++;
		}
	}
	 
}
