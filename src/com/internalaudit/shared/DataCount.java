package com.internalaudit.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;

public class DataCount {

	public class Domain {
		
		public int strategic = 0 ;
		public int operations = 0; //dummy data
		public int compliance = 0;
		public int reporting = 0;
	}
	
	public class Divisions {
		
		public int HeadOffice = 0;
		public int AGHKhoba = 0;
		public int AGHDammam = 0;
		public int AGHHofuf = 0;
		public int AGHJubail = 0;
		public int AMCRakkah = 0;
		public int AMCJubail = 0;
		public int AGHAzzizia = 0;
		public int AMCDammam = 0;
		public int EMATradingDivision = 0;
		public int MACHSCollege = 0;
		
		// todo : add others
		
	}
	
	public class RiskAssesment {
		
		public int hi = 0; //dummy
		public int mid = 0;
		public int low = 0;
		
	}
	
	public Domain domain = new Domain();
	public Divisions div = new Divisions();
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

	public void getDivisionCount(ArrayList<Division> divisionsList) {
		for( int k = 0; k < divisionsList.size(); k++)
		{
			String r =  divisionsList.get(k).getDivisionName(); //need it here. for charts??
			
			if ( r.equals("Head Office") ) div.HeadOffice++;
			
			if ( r.equals("AGH Khobar") ) div.AGHKhoba++;
			
			if ( r.equals("AGH Dammam") ) div.AGHDammam++;
			
			if ( r.equals("AGH Hofuf") ) div.AGHHofuf++;
			
			if ( r.equals("AGH Jubail") ) div.AGHJubail++;
			
			if ( r.equals("AMC Rakkah") ) div.AMCRakkah++;

			if ( r.equals("AMC Jubail") ) div.AMCJubail++;
			
			if ( r.equals("AGH Azzizia") ) div.AGHAzzizia++;
			
			if ( r.equals("AMC Dammam") ) div.AMCDammam++;
			
			if ( r.equals("EMA-Trading Division") ) div.EMATradingDivision++;
			
			if ( r.equals("MACHS College") ) div.MACHSCollege++;
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
