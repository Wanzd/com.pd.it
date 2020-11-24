package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pd.businessobject.MapVO;

@RestController
@RequestMapping("remoteRest")
public class RemoteRest {

	private final RestTemplate restTemplate;

	@Autowired
	public RemoteRest(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@PostMapping("/digSari")
	public String addUser(@RequestBody MapVO userEntity) {
		HttpEntity<MapVO> entity = new HttpEntity<>(userEntity);
		ResponseEntity<String> resultResponseEntity = this.restTemplate
				.exchange("https://www.zhihu.com/special/19681091/trends", HttpMethod.POST, entity, String.class);
		if (resultResponseEntity.getStatusCode() == HttpStatus.OK) {
			return resultResponseEntity.getBody();
		}
		return null;
	}
}
