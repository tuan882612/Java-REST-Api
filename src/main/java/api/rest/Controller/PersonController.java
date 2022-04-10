package api.rest.Controller;

import api.rest.Data.Person;
import api.rest.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService personService){
        this.service = personService;
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> handleNotFound(){
        return new ResponseEntity<>("Person with such does not exist in database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleFound(){
        return new ResponseEntity<>("Invalid person type", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Person> getPersons() {
        return service.getPersons();
    }

    @GetMapping("/{type}")
    public Optional<Person> getStudent(@PathVariable String type){
        return service.getPerson(type);
    }

    @PostMapping
    public void postPerson(@RequestBody Person person) {
        service.addPerson(person);
    }
}
