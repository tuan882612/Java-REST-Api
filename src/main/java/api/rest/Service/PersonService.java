package api.rest.Service;

import api.rest.Data.Person;
import api.rest.DataAccess.PRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        if (personByType.isEmpty()){
            throw new NoSuchElementException();
        }
        for (int i = 0; i < type.length(); i++) {
            if (Character.isLetter(type.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }
        return pRepository.findPersonByType(type);
    }

    public void addPerson(Person person){
        Optional<Person> personByType = pRepository.findPersonByType(person.getType());
        if (personByType.isPresent()){
            throw new IllegalThreadStateException("Person already" + person.getType() + "exist in database");
        }
        pRepository.save(person);
    }
}
