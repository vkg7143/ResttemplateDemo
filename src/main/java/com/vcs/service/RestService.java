package com.vcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
	@Autowired
	private RestTemplate r;
public String get() {
	String url="http://localhost:8090/hello";
	ResponseEntity<String> re=r.getForEntity(url, String.class);
	return re.getBody();
	
}
}
