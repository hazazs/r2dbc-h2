package hu.hazazs.r2dbc.h2;

import hu.hazazs.r2dbc.h2.entity.Person;
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

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
public class R2Dbch2Application {

	public static void main(String[] args) {
		SpringApplication.run(R2Dbch2Application.class, args);
	}

	@Bean
	@SuppressWarnings("unused")
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}

	@Bean
	@SuppressWarnings("unused")
	public CommandLineRunner init(PersonRepository personRepository) {
		return args ->
			personRepository.save(new Person(null, "Tibor", 39))
					.thenMany(personRepository.findAll())
					.subscribe();
	}

}