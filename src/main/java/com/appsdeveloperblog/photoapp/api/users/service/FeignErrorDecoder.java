package com.appsdeveloperblog.photoapp.api.users.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.photoapp.api.users.exception.Four0FourException;
import com.appsdeveloperblog.photoapp.api.users.exception.Four0FourResponse;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	
	private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

	
	@Override
	public Exception decode(String methodKey, Response response) {

		log.error("error occured while microservice communication : "+methodKey+" : ", response.request().url()+ " : "+response.status());
		return new Four0FourException(null);
	}

	
}
