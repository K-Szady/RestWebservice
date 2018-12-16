package pl.krzycho.korki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzycho.korki.model.Gender;
import pl.krzycho.korki.model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class PersonParserService {
    @Autowired
    PersonService personService;

    public List<Person> fileDataReader(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String sCurrentLine;
        List<Person> personList = new ArrayList<>();
        while((sCurrentLine = bufferedReader.readLine())!=null){
            String[] split = sCurrentLine.split(",");
            Person person = new Person();
            person.setName(split[0]);
            person.setSurName(split[1]);
            Integer result = Integer.valueOf(split[2]);
            person.setPesel(result);
            if(!(split[3].equals("null"))) {
                Gender stringEnum = Gender.valueOf(split[3]);
                person.setGender(stringEnum);
            }else {
                System.out.println("Null");
            }
            if (!(personService.checkIfExist(result))){
                personList.add(person);
            }else{
                System.out.println("Person already exist!");
            }


        }
        return personList;
    }
}
