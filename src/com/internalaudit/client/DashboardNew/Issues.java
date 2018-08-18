package com.internalaudit.client.DashboardNew;
import java.io.Serializable;
import java.util.Date;

public class Issues implements Serializable {
	
	//field work data 
	private String issueTitle;
	private String managementResponce;
	private String status;
	private Integer id;
 

  private static int COUNTER = 0;

  public Issues() {
    this.id = Integer.valueOf(COUNTER++);
  }

public String getIssueTitle() {
	return issueTitle;
}

public void setIssueTitle(String issueTitle) {
	this.issueTitle = issueTitle;
}

public String getManagementResponce() {
	return managementResponce;
}

public void setManagementResponce(String managementResponce) {
	this.managementResponce = managementResponce;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public static int getCOUNTER() {
	return COUNTER;
}

public static void setCOUNTER(int cOUNTER) {
	COUNTER = cOUNTER;
}

}