package api.rest.DataAccess;

import api.rest.Data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PRepository
        extends JpaRepository<Person, Long> {
}
