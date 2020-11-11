package com.internalaudit.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.AuditEngagement.LabelBold;
import com.internalaudit.client.view.AuditEngagement.LabelHeading;
import com.internalaudit.shared.Department;
import com.internalaudit.shared.Division;
import com.internalaudit.shared.StrategicDepartments;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sencha.gxt.chart.client.draw.engine.SVG.TextBBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class AddDivisionDepartmentView extends VerticalLayoutContainer {
	
	private InternalAuditServiceAsync rpcService;
	private ListBox listBoxDivision = new ListBox();
	private ListBox listBoxDepartment = new ListBox();
	private TextBox txtDivision = new TextBox();
	private TextBox txtDepartment = new TextBox();
	private Button btnAddDivision = new Button("Add Division");
	private Button btnAddDepartment = new Button("Add Department");
	private Button btnEditDivision = new Button("Edit");
	private Button btnEditDepartment = new Button("Edit");
	private Image imgDeleteDivision = new Image("images/deleteIcon.png");
	private Image imgDeleteDepartment = new Image("images/deleteIcon.png");
	private Button btnSaveDivision= new Button("Save");
	private Button btnSaveDepartment = new Button("Save");
	private Button btnSaveEditDivision = new Button("Save");
	private Button btnSaveEditDepartment = new Button("Save");
	private Anchor addDivision = new Anchor("Add Division");
	private Anchor addDepartment = new Anchor("Add Department");
	private TextBox txtAddDivision = new TextBox();
	private TextBox txtEditDivision = new TextBox();
	private TextBox txtAddDepartment = new TextBox();
	private TextBox txtEditDepartment = new TextBox();
	private HorizontalPanel hpnlButtonDivision = new HorizontalPanel();
	private HorizontalPanel hpnlButtonDepartment = new HorizontalPanel();
	
	public AddDivisionDepartmentView() {
		rpcService = GWT.create(InternalAuditService.class);
		fetchDivision();
		add(tabLaout());
		handlers();
	}
	
	private Widget tabLaout() {
		VerticalPanel vpnlMain = new VerticalPanel();
		VerticalPanel vpnlUperView = new VerticalPanel();
		vpnlMain.add(vpnlUperView);
		
		LabelBold lblHeading = new LabelBold("Audit Universe Identification");
		vpnlUperView.add(lblHeading);
		
		Label lblMsg = new Label("Create and manage your drop down list for your organisation");
		vpnlUperView.add(lblMsg);
		
		Label lblCheckMultipleJobs = new Label("Create Multiple jobs based on selected location/division/Department");
		vpnlUperView.add(lblCheckMultipleJobs);
		
		HorizontalPanel hpnlHeader = new HorizontalPanel();
		LabelHeading lblDivision = new LabelHeading("Division");
		lblDivision.setWidth("400px");
		hpnlHeader.add(lblDivision);
		
		LabelHeading lblDepartment = new LabelHeading("Department");
		hpnlHeader.add(lblDepartment);
		lblDepartment.setWidth("400px");
		vpnlMain.add(hpnlHeader);
		
		FlexTable flexPanelData = new FlexTable();
		vpnlMain.add(flexPanelData);
		
		//Division View
		listBoxDivision.setWidth("260px");
		flexPanelData.setWidget(0, 0, listBoxDivision);
				
		hpnlButtonDivision.add(imgDeleteDivision);
		hpnlButtonDivision.add(btnEditDivision);
		hpnlButtonDivision.setWidth("150px");
		flexPanelData.setWidget(0, 1, hpnlButtonDivision);
		btnEditDivision.setVisible(false);
		imgDeleteDivision.setVisible(false);
		
		txtEditDivision.setWidth("260px");
		txtEditDivision.setVisible(false);
		btnSaveEditDivision.setVisible(false);
		flexPanelData.setWidget(1, 0, txtEditDivision);
		flexPanelData.setWidget(1, 1, btnSaveEditDivision);
		
		flexPanelData.setWidget(2, 0, addDivision);
		txtAddDivision.setWidth("260px");
		txtAddDivision.setVisible(false);
		btnSaveDivision.setVisible(false);
		flexPanelData.setWidget(3, 0, txtAddDivision);
		flexPanelData.setWidget(3, 1, btnSaveDivision);
		
		//Department View
		listBoxDepartment.setWidth("260px");
		listBoxDepartment.addItem("--Select Department--", "0");
		flexPanelData.setWidget(0, 2, listBoxDepartment);
				
		hpnlButtonDepartment.add(imgDeleteDepartment);
		hpnlButtonDepartment.add(btnEditDepartment);
		flexPanelData.setWidget(0, 3, hpnlButtonDepartment);
		hpnlButtonDepartment.setWidth("150px");
		hpnlButtonDepartment.setVisible(false);
		
		txtEditDepartment.setWidth("260px");
		txtEditDepartment.setVisible(false);
		btnSaveEditDepartment.setVisible(false);
		flexPanelData.setWidget(1, 2, txtEditDepartment);
		flexPanelData.setWidget(1, 3, btnSaveEditDepartment);
		
		flexPanelData.setWidget(2, 2, addDepartment);
		txtAddDepartment.setWidth("260px");
		txtAddDepartment.setVisible(false);
		btnSaveDepartment.setVisible(false);
		flexPanelData.setWidget(3, 2, txtAddDepartment);
		flexPanelData.setWidget(3, 3, btnSaveDepartment);
		
		return vpnlMain;
	}
	
	private void handlers() {
				
		listBoxDivision.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent arg0) {
				if(!listBoxDivision.getSelectedValue().equals("0")) {
					btnEditDivision.setVisible(true);
					imgDeleteDivision.setVisible(true);
					fetchDivisionDepartments();
					hpnlButtonDepartment.setVisible(true);
				}
				else
				{
					btnEditDivision.setVisible(false);
					imgDeleteDivision.setVisible(false);
					txtEditDivision.setVisible(false);
					btnSaveEditDivision.setVisible(false);
					hpnlButtonDepartment.setVisible(false);
					txtEditDepartment.setVisible(false);
					btnSaveEditDepartment.setVisible(false);
				}
			}
		});
		
		btnEditDivision.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				txtEditDivision.setVisible(true);
				txtEditDivision.setText(listBoxDivision.getSelectedItemText());
				btnSaveEditDivision.setVisible(true);
			}
		});
		
		addDivision.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				txtAddDivision.setVisible(true);
				btnSaveDivision.setVisible(true);
			}
		});
				
		btnEditDepartment.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				txtEditDepartment.setVisible(true);
				txtEditDepartment.setText(listBoxDepartment.getSelectedItemText());
				btnSaveEditDepartment.setVisible(true);
			}
		});
		
		addDepartment.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				txtAddDepartment.setVisible(true);
				btnSaveDepartment.setVisible(true);
			}
		});
		
		btnSaveDivision.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				addNewDivision();
			}
		});
		
		btnSaveDepartment.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				addNewDepartment();
			}
		});
		
		btnSaveEditDivision.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				Division disision = new Division();
				disision.setDivisionID(Integer.parseInt(listBoxDivision.getSelectedValue()));
				disision.setDivisionName(txtEditDivision.getText());
				editDivision(disision); 
				}
		});
		
		btnSaveEditDepartment.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				Department department = new Department();
				department.setDivisionID(Integer.parseInt(listBoxDivision.getSelectedValue()));
				department.setDepartmentId(Integer.parseInt(listBoxDepartment.getSelectedValue()));
				department.setDepartmentName(txtEditDepartment.getText());
				editDepartment(department); 
				}
		});
		
		imgDeleteDivision.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent arg0) {
					boolean confirm = Window.confirm("Are you sure to delete selected Division?");
					if(confirm)
						deleteDivision();
				}
			});
		
		imgDeleteDepartment.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				boolean confirm = Window.confirm("Are you sure to delete selected Department?");
				if(confirm)
					deleteDepartment();
			}
		});
		
	}
	
	private void fetchDivision() {
		rpcService.fetchDivision(new AsyncCallback<ArrayList<Division>>() {
			
			@Override
			public void onSuccess(ArrayList<Division> divisions) {
				// TODO Auto-generated method stub
				listBoxDivision.clear();
				listBoxDivision.addItem("--SelectDivision--","0");
				for(Division div:divisions) {
					listBoxDivision.addItem(div.getDivisionName(), div.getDivisionID()+"");
				}
				fetchDivisionDepartments();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Window.alert("Unable to fetch Divisions : " + " Add Division");
			}
		});
	}
	
	private void fetchDivisionDepartments() {
		int divisionID = Integer.parseInt(listBoxDivision.getSelectedValue());
		listBoxDepartment.clear();	
		if(divisionID == 0)
			listBoxDepartment.addItem("--Select Department--");
		else {
			rpcService.fetchDivisionDepartments(divisionID ,new AsyncCallback<ArrayList<Department>>() {
				
				@Override
				public void onSuccess(ArrayList<Department> departments) {
					if(departments.size() < 1)
						listBoxDepartment.addItem("--no Department added--");
					for(Department dept:departments) {
						listBoxDepartment.addItem(dept.getDepartmentName(), dept.getDepartmentId()+"");
					}
				}
				
				@Override
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					Window.alert("Unable to fetch Departments : " + " Add Department");
				}	
			});
		}
	}
		
	private void addNewDivision() {
		rpcService.addDivision(txtAddDivision.getText(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Unable to add Division : " + arg0);
			}

			@Override
			public void onSuccess(String arg0) {
				new MessageBox(arg0);
				txtAddDivision.setText("");
				fetchDivision();
				listBoxDivision.setSelectedIndex(0);
				txtAddDivision.setVisible(false);
				btnSaveDivision.setVisible(false);
			}
		});
	}
	
	private void addNewDepartment() {
		rpcService.addDepartment(Integer.parseInt(listBoxDivision.getSelectedValue()), txtAddDepartment.getText(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				Window.alert("Unable to add Department");
			}

			@Override
			public void onSuccess(String arg0) {
				txtAddDepartment.setText("");
				fetchDivisionDepartments();
				listBoxDepartment.setSelectedIndex(0);
				txtAddDepartment.setVisible(false);
				btnSaveDepartment.setVisible(false);
			}
		});
	}
	
	private void editDivision(Division division) {
		rpcService.editDivisionName(division, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Unable to Edit Division Name : " + arg0);
			}

			@Override
			public void onSuccess(String msg) {
				txtEditDivision.setText("");
				txtEditDivision.setVisible(false);
				btnSaveEditDivision.setVisible(false);
				fetchDivision();
			}
		});
	}
	
	private void editDepartment(Department department) {
		rpcService.editDepartmentName(department, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Unable to Edit Department Name : " + arg0);
			}

			@Override
			public void onSuccess(String msg) {
				txtEditDepartment.setText("");
				txtEditDepartment.setVisible(false);
				btnSaveEditDepartment.setVisible(false);
				fetchDivisionDepartments();				
			}
		});
	}
	
	private void deleteDivision() {
		rpcService.deleteDivision(Integer.parseInt(listBoxDivision.getSelectedValue()), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Unable to Delete Division : " + arg0);
			}

			@Override
			public void onSuccess(String arg0) {
				fetchDivision();
			}
		});
	}
	
	private void deleteDepartment() {
		rpcService.deleteDepartment(Integer.parseInt(listBoxDepartment.getSelectedValue()), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Unable to Delete Department : " + arg0);
			}

			@Override
			public void onSuccess(String arg0) {
				fetchDivisionDepartments();
			}
		});
	}
	
	public ListBox getListBoxDivision() {
		return listBoxDivision;
	}

	public void setListBoxDivision(ListBox listBoxDivision) {
		this.listBoxDivision = listBoxDivision;
	}

	public ListBox getListBoxDepartment() {
		return listBoxDepartment;
	}

	public void setListBoxDepartment(ListBox listBoxDepartment) {
		this.listBoxDepartment = listBoxDepartment;
	}

	public TextBox getTxtDivision() {
		return txtDivision;
	}

	public void setTxtDivision(TextBox txtDivision) {
		this.txtDivision = txtDivision;
	}

	public TextBox getTxtDepartment() {
		return txtDepartment;
	}

	public void setTxtDepartment(TextBox txtDepartment) {
		this.txtDepartment = txtDepartment;
	}

	public Button getBtnAddDivision() {
		return btnAddDivision;
	}

	public Button getBtnAddDepartment() {
		return btnAddDepartment;
	}
	
}
