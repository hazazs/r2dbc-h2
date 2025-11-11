package hu.hazazs.r2dbc.h2;

import hu.hazazs.r2dbc.h2.repository.PersonRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
@SuppressWarnings("unused")
public class R2Dbch2Application {

	public static void main(String[] args) {
		SpringApplication.run(R2Dbch2Application.class, args);
	}

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}

	@Bean
	public CommandLineRunner init(PersonRepository personRepository) {
		return args -> personRepository
				.insertPerson(UUID.randomUUID().toString(), "Tibor", Period.between(
					LocalDate.of(1986, 7, 12),
					LocalDate.now())
						.getYears())
				.subscribe();
	}

}