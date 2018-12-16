package pl.krzycho.korki.services;

import pl.krzycho.korki.model.Person;

import java.util.List;

public interface PersonService  {
    Person getById(long id);
    void save(Person person);
    List<Person> findByName(String name);
    List<Person> findAll();
    void saveAll(List<Person> personList);
    boolean checkIfExist(int pesel);
    List<Person> sortPersonsList(List<Person> personList);


}
