package com.appsdeveloperblog.photoapp.api.users.exception;

public class TokenExpirationException extends RuntimeException {

	public TokenExpirationException(String message, String token) {
		super(message);
		this.jwtToken = token;
	}
	
	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
