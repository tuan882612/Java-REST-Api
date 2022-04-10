package api.rest.Service;

import api.rest.Data.Person;
import api.rest.DataAccess.PRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PRepository pRepository;

    @Test
    @DisplayName("Repository was called to get all entities")
    public void return_persons() {
        List<Person> data = pRepository.findAll();
        Assertions.assertTrue(data.size()>0);
    }
}