package com.javacodegeeks.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebListclientesService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebAdditionService.class
			.getName());

	public WebListclientesService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	public String listclientes() {
		return restTemplate.getForObject(serviceUrl + "/listclientes", String.class);
	}
}
