package ru.digitalleague.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.digitalleague.university.config.ApplicationConfiguration;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
