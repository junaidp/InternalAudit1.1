package com.internalaudit.client.view.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.EmployeeDashBoardView;

import com.internalaudit.shared.DashBoardDTO;
import com.internalaudit.shared.TimeOutException;

public class EmployeeDashBoardViewData {

	private InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	private Logger logger = Logger.getLogger("EmployeeDashBoardViewData");

	
	public void setData(final EmployeeDashBoardView employeeDashBoardView) {
		
		employeeDashBoardView.getRefresh().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchDashBoard(employeeDashBoardView);
			}});
		
		fetchDashBoard(employeeDashBoardView);
	}
	
	
	public void fetchDashBoard(final EmployeeDashBoardView employeeDashBoardView){
		rpcService.fetchDashBoard(null, new AsyncCallback<ArrayList<DashBoardDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
				
				logger.log(Level.INFO, "FAIL: fetchDashBoard .Inside Audit AuditAreaspresenter");
				if(caught instanceof TimeOutException){
					History.newItem("login");
				}else{
					System.out.println("FAIL: fetchDashBoard .Inside AuditAreaspresenter");
					Window.alert("FAIL: fetchDashBoard");// After FAIL ... write RPC Name  NOT Method Name..
				}
				
			}

			@Override
			public void onSuccess(ArrayList<DashBoardDTO> dashBoard) {
				ArrayList<DashBoardDTO> dashBoardFiltered = new ArrayList<DashBoardDTO>();
				for(int i=0; i< dashBoard.size(); i++){
					if(!dashBoard.get(i).getPhase().equals("5")){
						dashBoardFiltered.add(dashBoard.get(i));
					}
				}
				employeeDashBoardView.populateDashBoard(dashBoardFiltered);
			}});
	}

}
