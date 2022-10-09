package com.vcs.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestComponent implements CommandLineRunner{
@Autowired
RestTemplate rs;
	@Override
	public void run(String... args) throws Exception {
		String s=rs.getForObject("https://localhost:8090/hello", String.class);
		System.out.println(s);
		
	}
	

}
