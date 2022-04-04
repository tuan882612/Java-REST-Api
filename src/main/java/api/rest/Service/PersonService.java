package api.rest.Service;
import api.rest.DataLayer.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    public List<Person> getStudent() {
        return List.of(
                new Person(
                        1L,
                        "Tuan",
                        "CSE",
                        2024,
                        19
                )
        );
    }
}
