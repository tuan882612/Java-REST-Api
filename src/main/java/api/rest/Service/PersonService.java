package api.rest.Service;

import api.rest.Data.Person;
import api.rest.DataAccess.PRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

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
        Optional<Person> personByType = pRepository.findPersonByType(person.getType());
        Optional<Person> personByName = pRepository.findPersonByName(person.getName());
        Optional<Person> personByYear = pRepository.findPersonByYear(person.getYear());

        if (personByType.isPresent() || personByName.isPresent()) {
            throw new InstanceAlreadyExistsException();
        }

//      check whether the input contains all parameters

        pRepository.save(person);
    }

    @Transactional
    public void updatePerson(String type, String name, String year) throws InstanceAlreadyExistsException {
        Person person = pRepository.findPersonByType(type)
                .orElseThrow(InstanceAlreadyExistsException::new);

        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(person.getName(), name)){
            Optional<Person> personName = pRepository.findPersonByName(name);
            if (personName.isPresent()){
                throw new InstanceAlreadyExistsException();
            }
            person.setName(name);
        }

        if(year != null &&
                year.length() > 0 &&
                !Objects.equals(person.getYear(), year)){
            person.setYear(year);
        }
    }
}
