package gr.uom.opensource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OpensourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpensourceApplication.class, args);
	}

}
