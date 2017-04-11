package com.internalaudit.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


public class TimeOutException extends Exception implements IsSerializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Exception exception = new Exception();
	
	public TimeOutException(){
		super();
	}
	
	public TimeOutException(String message){
		super(message);
	}
	
	public TimeOutException(Exception ex){
		super();
		this.exception = ex;
	}
	
	public TimeOutException(String message, Exception ex){
		super(message);
		this.exception = ex;
	}
	
	public String getMessage(){
		String msgStr = super.getMessage();
		if(msgStr == null){
			msgStr = exception.getMessage();
		}
		return msgStr;
	}
	
	public String toString(){
		return super.getMessage() +" : " + exception.toString();
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
