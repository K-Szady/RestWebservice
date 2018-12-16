package pl.krzycho.korki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.krzycho.korki.model.Gender;
import pl.krzycho.korki.model.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonFileReaderService {
    @Autowired
    PersonService personService;
    @Autowired
    PersonParserService personParserService;
    @Value("${person.file.input.directory}")
    private String fileDirectory;
    @Value("${person.file.read.input.directory}")
    private String fileReadDirectory;
    @Scheduled(fixedDelay = 30000)
    public void fileReader() throws IOException {
        Files.walk(Paths.get(fileDirectory))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
    personService.saveAll(personParserService.fileDataReader(fileReadDirectory));

    }




}
