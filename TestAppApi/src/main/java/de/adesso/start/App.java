package de.adesso.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = { "de" })
@EntityScan(basePackages = { "de" })
@ComponentScan(basePackages = { "de" })
@EnableTransactionManagement
public class App implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

	}
}
