package api.rest.DataAccess;

import api.rest.Data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PRepository
        extends JpaRepository<Person, Long> {

//    @Query("SELECT s FROM Person s WHERE s.type = ?1")
    Optional<Person> findPersonByType(String type);
}
