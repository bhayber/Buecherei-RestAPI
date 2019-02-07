package de.adesso.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "de" })
@EntityScan(basePackages = { "de" })
@ComponentScan(basePackages = { "de" })
@EnableTransactionManagement
@EnableJpaAuditing
public class App implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.info("Programm wurde gestartet", this);
	}
}
