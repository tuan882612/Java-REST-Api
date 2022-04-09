package api.rest.Data;

import api.rest.DataAccess.PRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Month;
import java.util.List;
import java.time.LocalDate;

import static java.time.Month.JANUARY;
import static java.time.Month.OCTOBER;

//These are just sample data cases
@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PRepository repository){
        return args -> {
            Person tuan = new Person(
                    "Tuan",
                    "1001",
                    "2025"
            );

            Person jack = new Person(
                    "jack",
                    "1002",
                    "2024"
            );

            repository.saveAll(List.of(tuan, jack));
        };
    }
}
