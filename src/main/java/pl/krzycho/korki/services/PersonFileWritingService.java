package pl.krzycho.korki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzycho.korki.model.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PersonFileWritingService {
    @Autowired
    PersonService personService;

    public File exportToFilePerson() throws IOException {
        List<Person> personList = personService.findAll();
        File tempFile = File.createTempFile("korki-", ".txt");
        FileWriter fileWriter = new FileWriter(tempFile);
        personList.forEach(person -> {
            try {
                fileWriter.write(person.toString() + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
        return tempFile;
    }
}
