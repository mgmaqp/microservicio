package com.javacodegeeks.example.rest.listclientes;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class ListclientesServer {

	protected Logger logger = Logger.getLogger(ListclientesServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "listclientes-server");

		SpringApplication.run(ListclientesServer.class, args);
	}
}
