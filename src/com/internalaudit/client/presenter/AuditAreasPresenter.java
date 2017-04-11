package com.internalaudit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.user.client.History;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.event.AuditSchedulingEvent;
import com.internalaudit.client.view.DisplayAlert;
import com.internalaudit.client.widgets.JobAndArea;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.JobAndAreaOfExpertise;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.TimeOutException;


public class AuditAreasPresenter implements Presenter 

{
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private ArrayList<Department> departments = new  ArrayList<Department>();
	private Logger logger = Logger.getLogger("AuditAreasPresenter");

	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		VerticalPanel getMainPanel();
		Button getBackButton();
	}
	
	public AuditAreasPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}



	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		
		fetchDepartments();
		
		bind();
	}

	private void bind() {

		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				eventBus.fireEvent(new AuditSchedulingEvent(display.getVpnlAuditScheduing()));
				History.newItem("auditScheduling");
			}
		});
	}

	public void fetchAuditAreas() {
		HashMap<String,String> hm = new HashMap<String,String>();

		rpcService.fetchSchedulingStrategic(hm, new AsyncCallback<ArrayList<StrategicDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				logger.log(Level.INFO, "FAIL: fetchSchedulingStrategic .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchSchedulingStrategic .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchSchedulingStrategic");// After FAIL ... write RPC Name  NOT Method Name..
				}

			}

			@Override
			public void onSuccess(ArrayList<StrategicDTO> result) {
				for(int i=0; i< result.size(); i++){

					display.getMainPanel().add(auditPanel(result.get(i)));
				}
			}});

	}

	private void fetchDepartments() {
		rpcService.fetchDepartments(new AsyncCallback<ArrayList<Department>>(){

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.INFO, "FAIL: fetchDepartments .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchDepartments .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchDepartments");// After FAIL ... write RPC Name  NOT Method Name..
				}
			}

			@Override
			public void onSuccess(ArrayList<Department> result) {
				departments.clear();
				for(int i=0; i<result.size(); i++ ){
					if(result.get(i).getDepartmentId() == 1 || result.get(i).getDepartmentId() ==2 ||result.get(i).getDepartmentId() ==3  ){
						departments.add(result.get(i))	;
					}
				}
				
//				departments = result;
				fetchAuditAreas();
			}});
	}

	public JobAndArea auditPanel(final StrategicDTO strategicDTO){
		
		final JobAndArea jobAndArea = new JobAndArea();
		
		jobAndArea.setJobId( strategicDTO.getStrategicId() );
		
		jobAndArea.getJobLink().setText(strategicDTO.getAuditableUnit());
		
		String upperCasedJobLink = jobAndArea.getJobLink().getText();
		jobAndArea.getJobLink().setText(upperCasedJobLink);
		jobAndArea.getJobLink().addStyleName("blue");
		
		for(int i=0; i< departments.size(); i++){
			CheckBox c = new CheckBox();
			c.setText(departments.get(i).getDepartmentName()); 
			c.setFormValue(departments.get(i).getDepartmentId()+"");
			jobAndArea.getSkills().add(c);
		}
		
		// since Conataining widget is a HPanel, we need to add chkbox and button after link
		//elements are shown in the order they're added.
		//unless of course you have got CSS ingenuity 
		
		jobAndArea.addCheckBoxesAndButton();
		
		//Add handler to save the record for which save is called.
		
		jobAndArea.getSaveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
								
				//construct a list holding values representing the state of widget in question
								
				ArrayList<JobAndAreaOfExpertise> entitiesToSave = new ArrayList<JobAndAreaOfExpertise>();
				
				for(int i =0; i < jobAndArea.getSkills().size(); ++i )
				{
					
						//create a record to be saved
						JobAndAreaOfExpertise entity = new JobAndAreaOfExpertise();
					
						entity.setJobId( jobAndArea.getJobId() );

						Department department = new Department();
						department.setDepartmentId( Integer.parseInt(jobAndArea.getSkills().get(i).getFormValue()));
						entity.setAreaOfExpertise( department );
						entity.setIsChecked(  jobAndArea.getSkills().get(i).getValue() ?  1 : 0 );
						entitiesToSave.add(entity);
						
						//Window.alert( jobAndArea.getJobId() + " Clicked " + c.getFormValue());
					//}
				}
				
				rpcService.saveJobAndAreaOfExpertiseState(entitiesToSave, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						new DisplayAlert("Changes Saved !");
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
						logger.log(Level.INFO, "FAIL: saveJobAndAreaOfExpertiseState .Inside Audit AuditAreaspresenter");
						if(caught instanceof TimeOutException){
							History.newItem("login");
						}else{
							System.out.println("FAIL: saveJobAndAreaOfExpertiseState .Inside AuditAreaspresenter");
							Window.alert("FAIL: saveJobAndAreaOfExpertiseState");// After FAIL ... write RPC Name  NOT Method Name..
						}
						
					}
				}); //end RPC
				
			}
		}); //end handler
		
		//update ticks based on already stored data in db for this job id.
		
		rpcService.fetchCheckBoxStateFor(jobAndArea.getJobId(), new AsyncCallback<ArrayList<JobAndAreaOfExpertise>>() {
			
			@Override
			public void onSuccess(ArrayList<JobAndAreaOfExpertise> result) {
				
				//now if result has deptId of some checkbox, then that checkbox should be checked
				
				
				//go through result array.
				for( int i = 0; i < result.size(); ++i )
				{
						if ( result.get(i).getIsChecked() == 1)
						{
							jobAndArea.getSkills().get(i).setValue(true);
						}					
				}				
				

			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
				logger.log(Level.INFO, "FAIL: fetchCheckBoxStateFor .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchCheckBoxStateFor .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchCheckBoxStateFor");// After FAIL ... write RPC Name  NOT Method Name..
				}
			}
		});
		
		return jobAndArea;

	}


}


