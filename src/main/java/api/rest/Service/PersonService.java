package api.rest.Service;

import api.rest.Data.Person;
import api.rest.DataAccess.PRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import java.util.*;

@Service
public class PersonService {

    private final PRepository pRepository;

    @Autowired
    public PersonService(PRepository pRepository){
        this.pRepository = pRepository;
    }

    public List<Person> getPersons(){
        return pRepository.findAll();
    }

    public Optional<Person> getPerson(String type){
        Optional<Person> personByType = pRepository.findPersonByType(type);
        for (int i = 0; i < type.length(); i++) {
            if (Character.isLetter(type.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }
        if (personByType.isEmpty()){
            throw new NoSuchElementException();
        }
        return pRepository.findPersonByType(type);
    }

    public void addPerson(Person person) throws InstanceAlreadyExistsException {

        if (pRepository.findPersonByType(person.getType()).isPresent() ||
                pRepository.findPersonByName(person.getName()).isPresent()) {
            throw new InstanceAlreadyExistsException();
        }

        if (person.getType() == null || person.getType().length() == 0) {
            throw new IllegalArgumentException();
        }

        if (person.getName() == null || person.getName().length() == 0) {
            throw new IllegalArgumentException();
        }

        if (person.getYear() == null || person.getYear().length() == 0) {
            throw new IllegalArgumentException();
        }

        pRepository.save(person);
    }

    @Transactional
    public void updatePerson(Person person, String type) throws NoSuchElementException, InstanceAlreadyExistsException {

        Person original = pRepository.findPersonByType(person.getType())
                .orElseThrow(NoSuchElementException::new);

        String name = person.getName();
        String year = person.getYear();

        if(!Objects.equals(original.getType(), type)){
            throw new InputMismatchException();
        }

        if(name != null && name.length() > 0){
            Optional<Person> personName = pRepository.findPersonByName(name);
            if (personName.isPresent()){
                throw new InstanceAlreadyExistsException();
            }
            original.setName(name);
        }

        if(year != null && year.length() > 0){
            original.setYear(year);
        }
    }
}
