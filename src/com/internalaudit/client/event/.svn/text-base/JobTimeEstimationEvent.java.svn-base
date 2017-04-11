package com.internalaudit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.internalaudit.shared.StrategicDTO;
import com.internalaudit.shared.User;

		public class JobTimeEstimationEvent extends GwtEvent<JobTimeEstimationEventHandler> {
			
			private int jobId;
			
			private StrategicDTO strategicDTO;
			
//			public JobTimeEstimationEvent(StrategicDTO strategicDTO){
//				this.strategicDTO = strategicDTO;
//			}
			
			
			public JobTimeEstimationEvent( StrategicDTO strategicDTO){
				this.strategicDTO = strategicDTO;
			}
		  public static Type<JobTimeEstimationEventHandler> TYPE = new Type<JobTimeEstimationEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<JobTimeEstimationEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(JobTimeEstimationEventHandler handler) {
		    handler.onJobTimeEstimation(this);
			
		}

		public int getJobId() {
			return jobId;
		}

		public void setJobId(int jobId) {
			this.jobId = jobId;
		}

		public StrategicDTO getStrategicDTO() {
			return strategicDTO;
		}

		public void setStrategicDTO(StrategicDTO strategicDTO) {
			this.strategicDTO = strategicDTO;
		}

	}


