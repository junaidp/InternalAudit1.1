package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.internalaudit.shared.Employee;
import com.internalaudit.shared.StrategicDTO;

		public class JobCreationEvent extends GwtEvent<JobCreationEventHandler> {
			
			Employee loggedInUser = null;
			private StrategicDTO strategicDTO;
			private int jobId;
			
			public JobCreationEvent(StrategicDTO strategicDTO){
				this.strategicDTO = strategicDTO;
				
				
			}
			

		  public static Type<JobCreationEventHandler> TYPE = new Type<JobCreationEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<JobCreationEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(JobCreationEventHandler handler) {
		    handler.onJobCreation(this);
			
		}

		public Employee getLoggedInUser() {
			return loggedInUser;
		}

		public void setLoggedInUser(Employee loggedInUser) {
			this.loggedInUser = loggedInUser;
		}

		public StrategicDTO getStrategicDTO() {
			return strategicDTO;
		}

		public void setStrategicDTO(StrategicDTO strategicDTO) {
			this.strategicDTO = strategicDTO;
		}

		public int getJobId() {
			return jobId;
		}

		public void setJobId(int jobId) {
			this.jobId = jobId;
		}

	}


