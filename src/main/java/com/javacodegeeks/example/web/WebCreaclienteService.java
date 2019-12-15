package com.javacodegeeks.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebCreaclienteService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebAdditionService.class
			.getName());

	public WebCreaclienteService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	public String creacliente(String nombre, String apellido, String edad, String fechanacimiento) {
		return restTemplate.getForObject(serviceUrl + "/creacliente?nombre={nombre}&apellido={apellido}&edad={edad}&fechanacimiento={fechanacimiento}", String.class, nombre, apellido, edad, fechanacimiento);
	}
}
