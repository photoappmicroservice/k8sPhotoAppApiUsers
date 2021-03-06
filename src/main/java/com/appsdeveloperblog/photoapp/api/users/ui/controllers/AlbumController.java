package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.appsdeveloperblog.photoapp.api.users.ui.model.AlbumResponseModel;

@FeignClient(name = "k8sphotoappapialbums",
				fallback = AlbumControllerFallback.class)//Kubernetes Service Name
@RibbonClient(name = "k8sphotoappapialbums")
public interface AlbumController {

	  @GetMapping("/users/{id}/albums")
	  public List<AlbumResponseModel> userAlbums(@PathVariable String id);
}

@Component
class AlbumControllerFallback implements AlbumController {
	
	@Override
	public List<AlbumResponseModel> userAlbums(String id) {
		return new ArrayList<>();
	}
}
