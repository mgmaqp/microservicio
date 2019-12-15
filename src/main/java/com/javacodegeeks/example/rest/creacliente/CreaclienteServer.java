package com.javacodegeeks.example.rest.creacliente;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class CreaclienteServer {

	protected Logger logger = Logger.getLogger(CreaclienteServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "creacliente-server");

		SpringApplication.run(CreaclienteServer.class, args);
	}
}
