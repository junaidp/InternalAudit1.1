package com.internalaudit.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.internalaudit.client.InternalAuditServiceAsync;
import com.internalaudit.client.presenter.CompanyInductionFormPresenter.Display;
import com.internalaudit.client.view.CompanyInductionFormView;
import com.internalaudit.client.view.SettingMenuView;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class SettingMenuPresenter implements Presenter {
	
	private final InternalAuditServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;	
	private Logger logger = Logger.getLogger("SettingMenuPresenter");

	public SettingMenuPresenter(InternalAuditServiceAsync rpcService, HandlerManager eventBus,
			SettingMenuView settingMenuView) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = settingMenuView;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		//bind();
	}
	
	public interface Display 
	{
		Widget asWidget();
	}  
	
	private void bind() {
		
	}
	
}
