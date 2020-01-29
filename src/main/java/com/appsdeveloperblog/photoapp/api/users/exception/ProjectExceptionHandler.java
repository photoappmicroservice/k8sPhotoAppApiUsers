package com.appsdeveloperblog.photoapp.api.users.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(SqlBadRequestException.class)
	public ResponseEntity<?> handleSqlException(SqlBadRequestException sqlBadRequestException, WebRequest request) {
		
		
		sqlBadRequestException.printStackTrace();
		
		Map<String, String> map = new HashMap<>();
		
		map.put(sqlBadRequestException.getColumn(), sqlBadRequestException.getErrorMessage());
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EntityNotfoundException.class)
	public ResponseEntity<?> handleEntityNotfoundException(EntityNotfoundException entityNotfoundException,
			WebRequest request) {
		
		EntityNotFoundResponse entityNotFoundResponse = new EntityNotFoundResponse();
		
		entityNotFoundResponse.setErrorTime(new Date());
		entityNotFoundResponse.setMessage("Entity:"+entityNotfoundException.getEntityName()+": NOT FOUND");
		entityNotFoundResponse.setDetailedMessage(entityNotfoundException.getLocalizedMessage());
		entityNotFoundResponse.setEntityName(entityNotfoundException.getEntityName());
		
		return new ResponseEntity<>(entityNotFoundResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(LoginException.class)
	public ResponseEntity<?> handleLoginException(LoginException loginException,
			WebRequest request) {
		
		return new ResponseEntity<>(loginException.getResponse(), HttpStatus.BAD_REQUEST);
	}
	
	

	@ExceptionHandler(TokenExpirationException.class)
	public ResponseEntity<?> handleTokenExpirationException(TokenExpirationException tokenExpirationException, WebRequest request) {
		
		
		tokenExpirationException.printStackTrace();
		
		Map<String, String> map = new HashMap<>();
		
		map.put("error", tokenExpirationException.getMessage());
		map.put("token", tokenExpirationException.getJwtToken());
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(Four0FourException.class)
	public ResponseEntity<?> handleFour0FourException(Four0FourException four0FourException,
			WebRequest request) {
		return new ResponseEntity<>(new Four0FourResponse(), HttpStatus.BAD_REQUEST);
	}
}
