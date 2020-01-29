package com.appsdeveloperblog.photoapp.api.users.exception;

public class LoginException extends RuntimeException {

	public LoginException() {
		super();
	}
	
	InvalidLoginResponse response;

	public InvalidLoginResponse getResponse() {
		return response;
	}

	public void setResponse(InvalidLoginResponse response) {
		this.response = response;
	}
	
	
}
