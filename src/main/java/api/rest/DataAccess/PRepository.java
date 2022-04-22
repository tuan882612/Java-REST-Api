package api.rest.DataAccess;

import api.rest.Data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PRepository
        extends JpaRepository<Person, Long> {

    Optional<Person> findPersonByType(String type);
    Optional<Person> findPersonByName(String name);
    Optional<Person> findPersonByYear(String year);

    boolean existsByType(String type);
}
