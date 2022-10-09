//package com.vcs.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//
//
//public class RestDemoController {
//	@Autowired
//	RestTemplate rs;
//
//	
//	public String get() {
//		String str = rs.getForObject("http://localhost:8090/hello", String.class);
//		return str;
//	}
//
//}
