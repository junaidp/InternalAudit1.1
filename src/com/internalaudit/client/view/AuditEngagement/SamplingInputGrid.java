package com.internalaudit.client.view.AuditEngagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.view.LoadingPopup;
import com.internalaudit.shared.AuditWork;
import com.internalaudit.shared.InternalAuditConstants;
import com.internalaudit.shared.SamplingExcelSheetEntity;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class SamplingInputGrid extends VerticalLayoutContainer {

	InternalAuditServiceAsync rpcService = GWT.create(InternalAuditService.class);
	protected static final int MIN_HEIGHT = 1;
	protected static final int MIN_WIDTH = 1280;
	protected static final int PREFERRED_HEIGHT = 1;
	protected static final int PREFERRED_WIDTH = 1;
	ArrayList<SamplingExcelSheetEntity> exportList ;
	private ContentPanel panel;
	private Button btnSubmit = new Button("Submit");
	private Button btnExportExcel = new Button("Export To Excel");
	private Button btnExportPDF = new Button("Export To PDF");
	// private static final OutstandingCoachingProperties properties =
	// GWT.create(OutstandingCoachingProperties.class);
	private static final SamplingInputGrid.InputSheetProperties properties = GWT
			.create(SamplingInputGrid.InputSheetProperties.class);

	ListStore<SamplingExcelSheetEntity> store;
	private ArrayList<SamplingExcelSheetEntity> listSamplingSheet;

	public SamplingInputGrid(ArrayList<SamplingExcelSheetEntity> result, TextBox lblPopulationData,
			TextBox lblSamplingSizeData, ListBox listBoxSamplingMethod, Integer auditStepId, Anchor lblSavedAuditReport, Anchor anchorExcelTemplate) {

		add(createGridFieldWork(lblPopulationData, lblSamplingSizeData, listBoxSamplingMethod));
		setData(result);
		clickHandlers(lblPopulationData, lblSamplingSizeData, listBoxSamplingMethod, auditStepId, lblSavedAuditReport, anchorExcelTemplate);
	}


	public interface InputSheetProperties extends PropertyAccess<SamplingExcelSheetEntity> {

		@Path("id")
		ModelKeyProvider<SamplingExcelSheetEntity> key();

		ValueProvider<SamplingExcelSheetEntity, String> date();

		ValueProvider<SamplingExcelSheetEntity, Double> referenceNo();

		ValueProvider<SamplingExcelSheetEntity, String> description();

		ValueProvider<SamplingExcelSheetEntity, Double> amount();

		ValueProvider<SamplingExcelSheetEntity, Integer> id();

		ValueProvider<SamplingExcelSheetEntity, String> location();

		ValueProvider<SamplingExcelSheetEntity, String> jobId();

	}

	private void setData(ArrayList<SamplingExcelSheetEntity> result) {
		listSamplingSheet = new ArrayList<SamplingExcelSheetEntity>();
		if (result != null && !result.isEmpty()) {
			int i =0;
			for (SamplingExcelSheetEntity samplingExcel : result) {
				i++;
				SamplingExcelSheetEntity sampling = new SamplingExcelSheetEntity();
				sampling.setId(i);
				sampling.setAmount(samplingExcel.getAmount());
				sampling.setDate(samplingExcel.getDate());
				sampling.setReferenceNo(samplingExcel.getReferenceNo());
				sampling.setDescription(samplingExcel.getDescription());
				sampling.setJobId(samplingExcel.getJobId());
				sampling.setLocation(samplingExcel.getLocation());
				listSamplingSheet.add(sampling);
			}
			store.clear();
			store.addAll(listSamplingSheet);

		}

	}

	public Widget createGridFieldWork(TextBox lblPopulationData, TextBox lblSamplingSizeData,
			ListBox listBoxSamplingMethod) {

		ColumnConfig<SamplingExcelSheetEntity, Integer> samplingSheetId = new ColumnConfig<SamplingExcelSheetEntity, Integer>(
				properties.id(), 70, "Id");
		ColumnConfig<SamplingExcelSheetEntity, String> date = new ColumnConfig<SamplingExcelSheetEntity, String>(
				properties.date(), 100, "Date");
		ColumnConfig<SamplingExcelSheetEntity, Double> reference = new ColumnConfig<SamplingExcelSheetEntity, Double>(
				properties.referenceNo(), 180, "Reference");
		ColumnConfig<SamplingExcelSheetEntity, Double> amount = new ColumnConfig<SamplingExcelSheetEntity, Double>(
				properties.amount(), 180, "Amount");
		ColumnConfig<SamplingExcelSheetEntity, String> description = new ColumnConfig<SamplingExcelSheetEntity, String>(
				properties.description(), 200, "Description");
		ColumnConfig<SamplingExcelSheetEntity, String> location = new ColumnConfig<SamplingExcelSheetEntity, String>(
				properties.location(), 180, "Location");
		ColumnConfig<SamplingExcelSheetEntity, String> jobId = new ColumnConfig<SamplingExcelSheetEntity, String>(
				properties.jobId(), 150, "Job ID");

		// Cell cellDueDate = new
		// DateCell(DateTimeFormat.getFormat("MM/dd/yy"));
		// outstandingOverDue.setCell(cellDueDate);
		btnExportExcel.setVisible(false);
		btnExportPDF.setVisible(false);
		btnExportExcel.setWidth("125px");
		btnExportPDF.setWidth("125px");
		btnExportPDF.getElement().getStyle().setMarginLeft(40, Unit.PX);

		List<ColumnConfig<SamplingExcelSheetEntity, ?>> columns = new ArrayList<ColumnConfig<SamplingExcelSheetEntity, ?>>();
		columns.add(samplingSheetId);
		columns.add(date);
		columns.add(reference);
		columns.add(amount);
		columns.add(description);
		columns.add(location);
		columns.add(jobId);

		ColumnModel<SamplingExcelSheetEntity> cm = new ColumnModel<SamplingExcelSheetEntity>(columns);

		store = new ListStore<SamplingExcelSheetEntity>(properties.key());

		final Grid<SamplingExcelSheetEntity> grid = new Grid<SamplingExcelSheetEntity>(store, cm);
		// grid.setWidth(580);
		grid.getView().setAutoExpandColumn(samplingSheetId);
		grid.getView().setForceFit(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		ScrollPanel p = new ScrollPanel();
		p.setHeight("200px");

		p.add(grid);

		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(p, new VerticalLayoutData(1, 1));
		// con.add(, new VerticalLayoutData(1, 1));

		VerticalLayoutContainer con1 = new VerticalLayoutContainer();

		panel = new ContentPanel();
		// panel.setHeight(220);
		// panel.setWidth(700);
		panel.setSize("1190px", "350px");
		panel.setHeadingText("Sampling Input");
		HorizontalPanel panelExportButton = new HorizontalPanel();
		panelExportButton.add(btnExportExcel);
		panelExportButton.add(btnExportPDF);
		con.add(btnSubmit);
		con.add(panelExportButton);
		panel.add(con);
		// panel.add(con1);
		return panel;
	}

	private void clickHandlers(final TextBox lblPopulationData, final TextBox lblSamplingSizeData,
			final ListBox listBoxSamplingMethod,final Integer auditStepId, final Anchor lblSavedAuditReport, final Anchor anchorExcelTemplate) {

		btnSubmit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				generateSamplingOutpot(lblPopulationData, lblSamplingSizeData, listBoxSamplingMethod,auditStepId,lblSavedAuditReport,anchorExcelTemplate);
			}

			
		});
		
		btnExportExcel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				String reportFormat = InternalAuditConstants.EXCEL;
				generateReport(listBoxSamplingMethod, reportFormat, auditStepId, lblSavedAuditReport,anchorExcelTemplate);
				
			}
		});
		
		btnExportPDF.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				String reportFormat = InternalAuditConstants.PDF;
				generateReport(listBoxSamplingMethod, reportFormat, auditStepId ,lblSavedAuditReport,anchorExcelTemplate);
				
			}
		});
	}
	private void generateReport(final ListBox listBoxSamplingMethod, String reportFormat,final Integer auditStepId,final Anchor lblSavedAuditReport,final Anchor anchorExcelTemplate) {
		final LoadingPopup loadingpopup = new LoadingPopup();
		loadingpopup.display();
		rpcService.exportSamplingAuditStep(listBoxSamplingMethod.getSelectedItemText(), reportFormat, exportList,auditStepId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Error in exportSamplingAuditStep + ");
				
			}

			@Override
			public void onSuccess(String report) {
				Window.alert(report);
				loadingpopup.remove();
				if (report.contains(InternalAuditConstants.PDF)) {
					Window.open("SamplingAuditStep/reportSamplingAuditPDF.pdf", "_blank", "");

				} else {

					Window.open("SamplingAuditStep/reportSamplingAudit.xls", "_blank", "");
				}
				fetchSavedSamplingPDF(auditStepId,lblSavedAuditReport,anchorExcelTemplate);
				
			}
		});
	}
	
	private void generateSamplingOutpot(final TextBox lblPopulationData, final TextBox lblSamplingSizeData,
			final ListBox listBoxSamplingMethod,final Integer auditStepId,final Anchor lblSavedAuditReport,final Anchor anchorExcelTemplate) {
		rpcService.generateSamplingOutput(lblPopulationData.getText(), lblSamplingSizeData.getText(),
				listBoxSamplingMethod.getSelectedItemText(), listSamplingSheet, auditStepId,
				new AsyncCallback<ArrayList<SamplingExcelSheetEntity>>() {

					@Override
					public void onSuccess(ArrayList<SamplingExcelSheetEntity> samplingList) {
						Window.alert("Success");
						exportList = samplingList;
						setData(samplingList);
						panel.setHeadingText("Sampling Output");
						btnSubmit.setVisible(false);
						btnExportExcel.setVisible(true);
						btnExportPDF.setVisible(true);
						fetchSavedSamplingPDF(auditStepId,lblSavedAuditReport,anchorExcelTemplate);
							
					}

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("Fail");
					}
				});
	}
	
	private void fetchSavedSamplingPDF(Integer auditStepId,final Anchor lblSavedAuditReport,final Anchor anchorExcelTemplate) {
		rpcService.fetchSavedSamplingReport(InternalAuditConstants.SAMPLINGAUDITSTEPFOLDEER, auditStepId+"", new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable arg0) {
			//	Window.alert("Failed Fetching file");
				
			}

			@Override
			public void onSuccess(String file) {
				
				lblSavedAuditReport.setVisible(true);
				lblSavedAuditReport.setText(file);
				anchorExcelTemplate.setVisible(false);
				
			}
		});
	}

}
