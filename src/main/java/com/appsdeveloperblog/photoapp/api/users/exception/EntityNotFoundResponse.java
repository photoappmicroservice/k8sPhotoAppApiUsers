package com.appsdeveloperblog.photoapp.api.users.exception;

import java.io.Serializable;
import java.util.Date;

public class EntityNotFoundResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1311914512403341345L;
	
	private String entityName;
	private String message;
	private String detailedMessage;
	private Date errorTime;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetailedMessage() {
		return detailedMessage;
	}
	public void setDetailedMessage(String detailedMessage) {
		this.detailedMessage = detailedMessage;
	}
	public Date getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}

	
}
