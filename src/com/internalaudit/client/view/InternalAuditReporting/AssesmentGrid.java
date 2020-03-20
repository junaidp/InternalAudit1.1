package com.internalaudit.client.view.InternalAuditReporting;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.shared.AssesmentGridDbEntity;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class AssesmentGrid extends VerticalLayoutContainer {
	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	protected static final int MIN_HEIGHT = 1;
	// protected static final int MIN_WIDTH = 1200;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	ContentPanel panel;
	private int id = 0;
	private int selectedJob;
	private static final AssesmentGridProperties reportingProperties = GWT.create(AssesmentGridProperties.class);
	private TextButton removeButton = new TextButton("Remove Selected Row(s)");
	private ArrayList<AssesmentGridEntity> listReporting = new ArrayList<AssesmentGridEntity>();
	private ArrayList<AssesmentGridDbEntity> listGrid = new ArrayList<AssesmentGridDbEntity>();

	public AssesmentGrid(ArrayList<AssesmentGridDbEntity> result, int jobId) {
		this.selectedJob = jobId;
		setData(result);

		//
		Portlet portletReporting = new Portlet();
		portletReporting.setHeadingHtml("Reporting");
		portletReporting.add(createGridReporting());
		configPanel(portletReporting);

		add(panel);
	}

	private void setData(ArrayList<AssesmentGridDbEntity> result) {

		listReporting.clear();
		// REPORTING
		if (result.size() > 0) {

			for (int i = 0; i < result.size(); i++) {

				final AssesmentGridEntity reporting0 = new AssesmentGridEntity();
				reporting0.setName(result.get(i).getAssesmentName());
				reporting0.setId(0 + i);
				reporting0.setUrlCompleteboolean(result.get(i).isCompletelySatisfied());
				reporting0.setUrlSatisfyboolean(result.get(i).isPartiallySatisfied());
				reporting0.setUrlNonSatisfyboolean(result.get(i).isUnSatisfied());
				listReporting.add(reporting0);
			}
		} else {
			AssesmentGridEntity reporting0 = new AssesmentGridEntity();
			reporting0.setName(" Adequate controls are present to achieve the objectives of underlying process");
			reporting0.setId(1);

			AssesmentGridEntity reporting1 = new AssesmentGridEntity();
			reporting1.setName("Findings identified in previous year are rectified");
			reporting1.setId(2);

			AssesmentGridEntity reporting2 = new AssesmentGridEntity();
			reporting2.setName(" Steps were taken by the rseponsible to bring improvement process");
			reporting2.setId(3);

			AssesmentGridEntity reporting3 = new AssesmentGridEntity();
			reporting3.setName(" Process owners perceived audit activity as an opportunity to bring improvements");
			reporting3.setId(4);

			AssesmentGridEntity reporting4 = new AssesmentGridEntity();
			reporting4.setName(" Opportunities exist to further improve effectiveness and efficincy of the power");
			reporting4.setId(5);

			listReporting.add(reporting0);
			listReporting.add(reporting1);
			listReporting.add(reporting2);
			listReporting.add(reporting3);
			listReporting.add(reporting4);

			updateReportingStatusForOthers();
		}
	}

	private void updateReportingStatusForOthers() {

		for (int i = 0; i < listReporting.size(); i++) {
			listReporting.get(i).setUrlSatisfyboolean(false);
			listReporting.get(i).setUrlCompleteboolean(false);
			listReporting.get(i).setUrlNonSatisfyboolean(false);

		}

	}

	public Widget createGridReporting() {

		ColumnConfig<AssesmentGridEntity, String> nameCol = new ColumnConfig<AssesmentGridEntity, String>(
				reportingProperties.name(), 200, "OverAll Assesment");

		ColumnConfig<AssesmentGridEntity, Boolean> complete = new ColumnConfig<AssesmentGridEntity, Boolean>(
				reportingProperties.urlCompleteboolean(), 100, "Completely Satisfied");

		ColumnConfig<AssesmentGridEntity, Boolean> satisfy = new ColumnConfig<AssesmentGridEntity, Boolean>(
				reportingProperties.urlSatisfyboolean(), 100, "Partially Satisfied");
		ColumnConfig<AssesmentGridEntity, Boolean> unsatisfied = new ColumnConfig<AssesmentGridEntity, Boolean>(
				reportingProperties.urlNonSatisfyboolean(), 100, "UnSatisfied");

		CheckBoxCell cellComplete = new CheckBoxCell();
		complete.setCell(cellComplete);

		CheckBoxCell cellSatisfy = new CheckBoxCell();
		satisfy.setCell(cellSatisfy);

		CheckBoxCell cellNonSatisfy = new CheckBoxCell();
		unsatisfied.setCell(cellNonSatisfy);

		List<ColumnConfig<AssesmentGridEntity, ?>> columns = new ArrayList<ColumnConfig<AssesmentGridEntity, ?>>();

		columns.add(nameCol);

		columns.add(complete);
		columns.add(satisfy);
		columns.add(unsatisfied);

		nameCol.setCell(new AbstractCell<String>() {
			@Override
			public void render(Context context, String value, SafeHtmlBuilder sb) {
				if (value == null || value.isEmpty()) {
					sb.append((SafeHtml) new HTML("("));
				} else {
					sb.append(wrapString(value));
				}
			}
		});

		ColumnModel<AssesmentGridEntity> cm = new ColumnModel<AssesmentGridEntity>(columns);

		final ListStore<AssesmentGridEntity> store = new ListStore<AssesmentGridEntity>(reportingProperties.key());
		final Grid<AssesmentGridEntity> grid = new Grid<AssesmentGridEntity>(store, cm);
		grid.setTitle("Assesment Grid");
		grid.setWidth(900);
		grid.getView().setAutoExpandColumn(nameCol);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		// check code
		final GridRowEditing<AssesmentGridEntity> editing = new GridRowEditing<AssesmentGridEntity>(grid);

		editing.addEditor(nameCol, new TextField());
		editing.addEditor(complete, new CheckBox());
		editing.addEditor(satisfy, new CheckBox());
		editing.addEditor(unsatisfied, new CheckBox());
		// column 5 is not editable

		// EDITING //

		TextButton addButton = new TextButton("Add Assesment");

		addButtonHandler(store, editing, addButton);

		removeButton.setEnabled(false);
		removeButtonHandler(store, grid, removeButton);

		addButton.addStyleName("w3-left");
		con.add(addButton);
		store.addAll(listReporting);
		ToolBar toolBar = new ToolBar();
		toolBar.add(addButton);
		con.setWidth("800px");
		// VerticalLayoutContainer co = new VerticalLayoutContainer();
		con.add(toolBar, new VerticalLayoutData(1, -1));
		con.add(grid, new VerticalLayoutData(1, 1));
		// });

		// con.add(grid, new VerticalLayoutData(1, 1));

		panel = new ContentPanel();
		panel.add(con);
		panel.setHeight(350);
		panel.setWidth(1100);
		panel.setHeadingText("Assesment Grid");
		panel.addButton(removeButton);
		panel.addButton(new TextButton("Reset", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				store.rejectChanges();
			}
		}));
		panel.addButton(new TextButton("Save", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				store.commitChanges();

				saveAssesmentGrid();
			}

		}));

		return panel;
	}

	private void removeButtonHandler(final ListStore<AssesmentGridEntity> store, final Grid<AssesmentGridEntity> grid,
			final TextButton removeButton) {
		SelectHandler removeButtonHandler = new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {

				for (AssesmentGridEntity stock : grid.getSelectionModel().getSelectedItems()) {
					// Window.alert(stock.getId() + "");
					// int id = stock.getId() - 1;
					// Window.alert(id + "");
					store.remove(stock); //
					listReporting.remove(stock);

				}

				// AssesmentGridEntity stock =
				// grid.getSelectionModel().getSelectedItem();
				// Window.alert("deleting " +
				// grid.getSelectionModel().getSelectedItem().getId());
				// AssesmentGridEntity stock =
				// grid.getStore().get(grid.getSelectionModel().getSelectedItem().getId());
				// Window.alert("selected" +
				// grid.getSelectionModel().isSelected(stock));
				// if (grid.getSelectionModel().isSelected(stock))
				// store.remove(stock);
				removeButton.setEnabled(true);
			}
		};

		removeButton.addSelectHandler(removeButtonHandler);

		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<AssesmentGridEntity>() {

			@Override
			public void onSelectionChanged(SelectionChangedEvent<AssesmentGridEntity> event) {
				// Window.alert(grid.getSelectionModel().getSelectedItem().getId()
				// + "");
				removeButton.setEnabled(!event.getSelection().isEmpty());

			}
		});
	}

	private void addButtonHandler(final ListStore<AssesmentGridEntity> store,
			final GridRowEditing<AssesmentGridEntity> editing, TextButton addButton) {
		addButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				id++;
				AssesmentGridEntity reporting = new AssesmentGridEntity();
				reporting.setName("reporting");
				reporting.setId(id + 5);

				editing.cancelEditing();
				store.add(0, reporting);

				int row = store.indexOf(reporting);
				// editing.startEditing(new GridCell(row, 0));
				listReporting.add(reporting);
				store.commitChanges();
			}
		});
	}

	private void saveAssesmentGrid() {
		rpcService.saveAssesmentGrid(listReporting, selectedJob, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Window.alert(result);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed saving assesment grid");

			}
		});
	}

	private SafeHtml wrapString(String untrustedString) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<span style='white-space: normal;'>");
		sb.appendEscaped(untrustedString);
		sb.appendHtmlConstant("</span>");
		return sb.toSafeHtml();
	}

	private void configPanel(final Portlet portlet) {

		portlet.setCollapsible(true);
		portlet.setAnimCollapse(false);

		// portlet.setShadow(disabled);
		portlet.getHeader().addTool(new ToolButton(ToolButton.GEAR));
		portlet.getHeader().addTool(new ToolButton(ToolButton.CLOSE, new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				portlet.removeFromParent();
			}
		}));
	}

	private void fetchSavedAssesments(int selectedJobId) {
		rpcService.fetchAssesmentGrid(selectedJobId, new AsyncCallback<ArrayList<AssesmentGridDbEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed Fetch AssesmentGRid");

			}

			@Override
			public void onSuccess(ArrayList<AssesmentGridDbEntity> result) {
				if (result.size() > 0) {

					for (int i = 0; i < result.size(); i++) {
						Window.alert(result.get(i).getAssesmentName());

						final AssesmentGridEntity reporting0 = new AssesmentGridEntity();
						reporting0.setName(result.get(i).getAssesmentName());
						reporting0.setId(0 + i);
						reporting0.setUrlCompleteboolean(result.get(i).isCompletelySatisfied());
						reporting0.setUrlSatisfyboolean(result.get(i).isPartiallySatisfied());
						reporting0.setUrlNonSatisfyboolean(result.get(i).isUnSatisfied());
						listReporting.add(reporting0);
					}
				} else {
					AssesmentGridEntity reporting0 = new AssesmentGridEntity();
					reporting0
							.setName(" Adequate controls are present to achieve the objectives of underlying process");
					reporting0.setId(1);

					AssesmentGridEntity reporting1 = new AssesmentGridEntity();
					reporting1.setName("Findings identified in previous year are rectified");
					reporting1.setId(2);

					AssesmentGridEntity reporting2 = new AssesmentGridEntity();
					reporting2.setName(" Steps were taken by the rseponsible to bringimprovement process");
					reporting2.setId(3);

					AssesmentGridEntity reporting3 = new AssesmentGridEntity();
					reporting3.setName(
							" Process owners perceived audit activity as an opportunity to bring improvements");
					reporting3.setId(4);

					AssesmentGridEntity reporting4 = new AssesmentGridEntity();
					reporting4.setName(
							" Opportunities exist to further improve effectiveness and efficincy of the power");
					reporting4.setId(5);

					listReporting.add(reporting0);
					listReporting.add(reporting1);
					listReporting.add(reporting2);
					listReporting.add(reporting3);
					listReporting.add(reporting4);

					updateReportingStatusForOthers();
				}

			}
		});
	}
}
