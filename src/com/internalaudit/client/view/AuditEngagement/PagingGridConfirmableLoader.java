package com.internalaudit.client.view.AuditEngagement;
import com.google.gwt.core.client.Callback;
import com.internalaudit.shared.SamplingExcelSheetEntity;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;

public class PagingGridConfirmableLoader extends PagingLoader<PagingLoadConfig, PagingLoadResult<SamplingExcelSheetEntity>> {
  public PagingGridConfirmableLoader(DataProxy<PagingLoadConfig, PagingLoadResult<SamplingExcelSheetEntity>> proxy) {
    super(proxy);
  }

  @Override
  public void loadData(PagingLoadConfig config) {
    super.loadData(config);
  }
}
