package api.rest.Controller;

import api.rest.DataLayer.Person;
import api.rest.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Person> getStudent() {
        return service.getStudent();
    }
}
