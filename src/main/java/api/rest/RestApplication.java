package api.rest;

import api.rest.DataLayer.person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class RestApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestApplication.class, args);
	}
}
