package com.javacodegeeks.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class WebServer {

	public static final String ADDITION_SERVICE_URL = "http://addition-service";

	public static final String SUBTRACTION_SERVICE_URL = "http://subtraction-service";

	public static final String CREACLIENTE_SERVICE_URL = "http://creacliente-service";

	public static final String KPIDECLIENTES_SERVICE_URL = "http://kpideclientes-service";

	public static final String LISTCLIENTES_SERVICE_URL = "http://listclientes-service";

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebAdditionService additionService() {
		return new WebAdditionService(ADDITION_SERVICE_URL);
	}

	@Bean
	public WebArithmeticController additionController() {
		return new WebArithmeticController(additionService(), subtractionService(),creaclienteService(),kpideclientesService(), listclientesService() );
	}

	@Bean
	public WebSubtractionService subtractionService() {
		return new WebSubtractionService(SUBTRACTION_SERVICE_URL);
	}

	@Bean
	public WebCreaclienteService creaclienteService() {
		return new WebCreaclienteService(CREACLIENTE_SERVICE_URL);
	}

	@Bean
	public WebKpideclientesService kpideclientesService() {
		return new WebKpideclientesService(KPIDECLIENTES_SERVICE_URL);
	}

	@Bean
	public WebListclientesService listclientesService() {
		return new WebListclientesService(LISTCLIENTES_SERVICE_URL);
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}