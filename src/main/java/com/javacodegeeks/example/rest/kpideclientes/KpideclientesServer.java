package com.javacodegeeks.example.rest.kpideclientes;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class KpideclientesServer {

	protected Logger logger = Logger.getLogger(KpideclientesServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "kpideclientes-server");

		SpringApplication.run(KpideclientesServer.class, args);
	}
}
