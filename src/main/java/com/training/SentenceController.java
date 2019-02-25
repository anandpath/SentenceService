package com.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.training.service.VerbService;

@RestController
public class SentenceController {
	/*
	 * Commented this for working with ribbon instae of Eureka Discovery Client
	 * @Autowired DiscoveryClient client;
	 */	
	@Autowired
	RestTemplate restClient;
	
	@Autowired
	VerbService verbService;
	
	@GetMapping("/sentence")
	@HystrixCommand(fallbackMethod="fallBackCreateSentence")
	public String createSentence() {
		/*
		 * Commented this for working with ribbon instae of Eureka Discovery Client
		 * List<ServiceInstance> instances = client.getInstances("verb");
		 * ServiceInstance instance = instances.get(0); String uri =
		 * instance.getUri().toString(); RestTemplate restClient = new RestTemplate();
		 */
		String response = restClient.getForObject("http://verb/word", String.class);
		return "Sentence created using " + response +" word.";
	}
	
	@GetMapping("/sentence2")
	public String createSentence2() {
		/*
		 * Commented this for working with ribbon instae of Eureka Discovery Client
		 * List<ServiceInstance> instances = client.getInstances("verb");
		 * ServiceInstance instance = instances.get(0); String uri =
		 * instance.getUri().toString(); RestTemplate restClient = new RestTemplate();
		 */
		String response = verbService.returnRes();
		return "Sentence created using " + response +".";
	}
	
	public String fallBackCreateSentence() {
		return "fallback word in Controller";
	}
}
