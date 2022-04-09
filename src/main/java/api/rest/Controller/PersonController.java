package api.rest.Controller;

import api.rest.Data.Person;
import api.rest.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService personService){
        this.service = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Person> getStudent() {
        return service.getPersons();
    }

    @PostMapping
    public void postPerson(@RequestBody Person person) {
        service.addPerson(person);
    }
}
