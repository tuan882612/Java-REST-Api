package api.rest.Controller;

import api.rest.DataLayer.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    @GetMapping
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
