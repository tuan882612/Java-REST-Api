package api.rest.Service;
import api.rest.Data.Person;
import api.rest.DataAccess.PRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addPerson(Person person){
        Optional<Person> personByType = pRepository.findPersonByType(person.getType());
        if(personByType.isPresent()){
            throw new IllegalThreadStateException("Person already" + person.getType() + "exist in database");
        }
        pRepository.save(person);
    }
}
