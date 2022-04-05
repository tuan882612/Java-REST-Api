package api.rest.Data;

import api.rest.DataAccess.PRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//These are just sample data cases
@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PRepository repository){
        return args -> {
            Person tuan = new Person(
                    1L,
                    "Tuan",
                    "CSE",
                    2024,
                    19
            );

            Person jack = new Person(
                    2L,
                    "jack",
                    "CSE",
                    2022,
                    21
            );

            repository.saveAll(List.of(tuan, jack));
        };
    }
}
