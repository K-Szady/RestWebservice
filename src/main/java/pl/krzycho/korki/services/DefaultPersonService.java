package pl.krzycho.korki.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzycho.korki.model.Person;
import pl.krzycho.korki.repository.PersonRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultPersonService implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person getById(long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);

    }

    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> findAll() {
       return Lists.newArrayList(personRepository.findAll());

    }

    public void saveAll(List<Person> personList){
        personRepository.saveAll(personList);
    }
    public boolean checkIfExist(int pesel){
        return  personRepository.checkIfExist(pesel);

    }

    @Override
    public List<Person> sortPersonsList(List<Person> personList) {
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getSurName().compareTo(o2.getSurName());
            }
        });
        return personList;
    }
}
