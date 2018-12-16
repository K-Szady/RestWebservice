package pl.krzycho.korki.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.krzycho.korki.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    public List<Person> findByName(String name);
    @Query("SELECT CASE WHEN count(e)>0 THEN true ELSE false END FROM Person e where e.pesel =:pesel")
    public boolean checkIfExist(@Param("pesel") int pesel);
}
