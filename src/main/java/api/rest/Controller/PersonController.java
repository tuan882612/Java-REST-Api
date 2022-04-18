package api.rest.Controller;

import api.rest.Data.Person;
import api.rest.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
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
        return new ResponseEntity<>("Person with such type does not exist in database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleBadRequest(){
        return new ResponseEntity<>("Invalid person type inputted", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InstanceAlreadyExistsException.class)
    public ResponseEntity<Object> handleIsFound(){
        return new ResponseEntity<>("Person already exist in the DataBase", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<Person> getPersons() {
        return service.getPersons();
    }

    @GetMapping(path = "/{type}")
    public Optional<Person> getPerson(@PathVariable String type){
        return service.getPerson(type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) throws InstanceAlreadyExistsException {
        service.addPerson(person);
    }

    @PutMapping(path = "/{type}")
    public void putPerson(@PathVariable("type") String type,
                           @RequestParam(required = false) String name,
                          @RequestParam(required = false) String year) throws InstanceAlreadyExistsException {
        service.updatePerson(type, name, year);
    }
}
