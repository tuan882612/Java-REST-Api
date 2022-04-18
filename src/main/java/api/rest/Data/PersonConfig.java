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
                    "Tuan",
                    "1001",
                    "2025"
            );

            Person jack = new Person(
                    "jack",
                    "1002",
                    "2024"
            );

            Person sam = new Person(
                    "sam",
                    "1003",
                    "2028"
            );
            repository.saveAll(List.of(tuan, jack, sam));
        };
    }
}
