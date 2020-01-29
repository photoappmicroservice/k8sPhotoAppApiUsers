package com.appsdeveloperblog.photoapp.api.users.exception;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class SqlBadRequestException extends RuntimeException {

	private String column;
	private String errorMessage;
	
	
	public SqlBadRequestException(String column, String errorMessage, RuntimeException e, String message) {
		super(message, e);
		this.column = column;
		this.errorMessage = errorMessage;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
