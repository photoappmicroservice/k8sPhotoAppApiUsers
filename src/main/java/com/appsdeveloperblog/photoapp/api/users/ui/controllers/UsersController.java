package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsdeveloperblog.photoapp.api.users.ui.model.AlbumResponseModel;
import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserResponseModel;
import com.appsdeveloperblog.photoapp.api.users.ui.model.UserResponseModel;
import com.appsdeveloperblog.photoapp.api.users.config.AppConfig;
import com.appsdeveloperblog.photoapp.api.users.service.*;
import com.appsdeveloperblog.photoapp.api.users.shared.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RefreshScope
public class UsersController {

	
	private static final Logger log = LoggerFactory.getLogger(UsersController.class);

	
	@Autowired
	private AppConfig env;

	@Autowired
	UsersService usersService;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AlbumController albumController;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/status/check")
	public String status() {
		return env.getWelcomemessage()+" : Working on server port " + environment.getProperty("local.server.port") + ", with token = "
				+ env.getTokensecret();
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = usersService.createUser(userDto);

		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

	@GetMapping(value = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

		log.info("getUser method start");
		
		UserDto userDto = usersService.getUserByUserId(userId);
		UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
		
		/*
		 * String albumsUrl = String.format("http://albums-ws/users/%s/albums", userId);
		 * 
		 * ResponseEntity<List<AlbumResponseModel>> response =
		 * restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new
		 * ParameterizedTypeReference<List<AlbumResponseModel>>() { });
		 * 
		 * List<AlbumResponseModel> albumList = response.getBody();
		 */
		
		List<AlbumResponseModel> albumList = this.albumController.userAlbums(userId);
		
		returnValue.setAlbums(albumList);

		log.info("getUser method end");

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

}
