package com.nitin.microservices.command.foreignExchangeService;

import brave.sampler.Sampler;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.tracing.zipkin.ZipkinProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class CommandApplication {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private ZipkinProperties zipkinProperties;

	@Value("${spring.sleuth.web.skipPattern}")
	private String skipPattern;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}