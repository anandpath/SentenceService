package com.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class VerbService {
	@Autowired
	RestTemplate restClient;
	
	@HystrixCommand(fallbackMethod="fallBackCreateSentence")
	public String returnRes() {
		return restClient.getForObject("http://verb/word", String.class);
	}
	
	public String fallBackCreateSentence() {
		return "fallback word in service";
	}
}
