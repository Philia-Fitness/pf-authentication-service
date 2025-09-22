package io.github.philiafitness.pfauthenticationservice;

import org.springframework.boot.SpringApplication;

public class TestPfAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PfAuthenticationServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
