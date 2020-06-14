package com.internalaudit.client.view.AuditEngagement;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditService;
import com.internalaudit.client.InternalAuditServiceAsync;
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
	private ContentPanel panel;
	private Button submit = new Button("Submit");
	// private static final OutstandingCoachingProperties properties =
	// GWT.create(OutstandingCoachingProperties.class);
	private static final SamplingInputGrid.InputSheetProperties properties = GWT
			.create(SamplingInputGrid.InputSheetProperties.class);

	ListStore<SamplingExcelSheetEntity> store;
	private ArrayList<SamplingExcelSheetEntity> listSamplingSheet;

	public SamplingInputGrid(ArrayList<SamplingExcelSheetEntity> result, TextBox lblPopulationData,
			TextBox lblSamplingSizeData, ListBox listBoxSamplingMethod) {

		add(createGridFieldWork(lblPopulationData, lblSamplingSizeData, listBoxSamplingMethod));
		setData(result);
		clickHandlers(lblPopulationData, lblSamplingSizeData, listBoxSamplingMethod);
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
			for (SamplingExcelSheetEntity samplingExcel : result) {
				SamplingExcelSheetEntity sampling = new SamplingExcelSheetEntity();
				sampling.setId(samplingExcel.getId());
				sampling.setAmount(samplingExcel.getAmount());
				sampling.setDate(samplingExcel.getDate());
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
		con.add(submit);
		panel.add(con);
		// panel.add(con1);
		return panel;
	}

	private void clickHandlers(final TextBox lblPopulationData, final TextBox lblSamplingSizeData,
			final ListBox listBoxSamplingMethod) {

		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				rpcService.generateSamplingOutput(lblPopulationData.getText(), lblSamplingSizeData.getText(),
						listBoxSamplingMethod.getSelectedItemText(), listSamplingSheet,
						new AsyncCallback<ArrayList<SamplingExcelSheetEntity>>() {

							@Override
							public void onSuccess(ArrayList<SamplingExcelSheetEntity> samplingList) {
								Window.alert("Success");
								setData(samplingList);
								panel.setHeadingText("Sampling Output");

							}

							@Override
							public void onFailure(Throwable arg0) {
								Window.alert("Fail");
							}
						});

			}
		});
	}
}