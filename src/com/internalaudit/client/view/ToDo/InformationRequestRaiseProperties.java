package com.internalaudit.client.view.ToDo;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.internalaudit.shared.InformationRequestEntity;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface InformationRequestRaiseProperties extends PropertyAccess<InformationRequestRaiseEntity> {
	@Path("id")
	ModelKeyProvider<InformationRequestRaiseEntity> key();

	ValueProvider<InformationRequestRaiseEntity, String> requestedItem();

	ValueProvider<InformationRequestRaiseEntity, String> raisedTo();

	ValueProvider<InformationRequestRaiseEntity, String> relatedJob();
	
	ValueProvider<InformationRequestRaiseEntity, String> status();
	
	ValueProvider<InformationRequestRaiseEntity, String> viewButton();
	
	ValueProvider<InformationRequestRaiseEntity, Date> overDueDays();

	ValueProvider<InformationRequestRaiseEntity, Integer> id();
	
	ValueProvider<InformationRequestRaiseEntity, Integer> raisedToId();
	
	ValueProvider<InformationRequestRaiseEntity, Integer> raisedById();
	
	ValueProvider<InformationRequestRaiseEntity, Integer> relatedJobId();
	
	ValueProvider<InformationRequestRaiseEntity, String> Reply();

	
	ValueProvider<InformationRequestReceiverEntity, String> contactEmail();
	
	ValueProvider<InformationRequestReceiverEntity, Integer> sstatus();
	
	ValueProvider<InformationRequestReceiverEntity, Boolean> sendReminder();
	
	ValueProvider<InformationRequestReceiverEntity, Boolean> sendNotification();
}
