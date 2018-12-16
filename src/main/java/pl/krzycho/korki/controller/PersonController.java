package pl.krzycho.korki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.krzycho.korki.model.Gender;
import pl.krzycho.korki.model.Person;
import pl.krzycho.korki.repository.PersonRepository;
import pl.krzycho.korki.services.PersonFileWritingService;
import pl.krzycho.korki.services.PersonParserService;
import pl.krzycho.korki.services.PersonService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static pl.krzycho.korki.model.Gender.MALE;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;
    @Autowired
    PersonFileWritingService personFileWritingService;
    @Autowired
    PersonParserService personParserService;

    @GetMapping("/getOne/{id}")
    public Person getOne(@PathVariable("id") long id){
        return personService.getById(id);
    }

    @GetMapping("/getPersonsByName/{name}")
    public List<Person> getPersons(@PathVariable("name") String name){return personService.findByName(name);}

    @PutMapping(value = "/add")
    public void addPerson(@RequestBody Person person){
        personService.save(person);
    }
    @GetMapping("/getAllAsFile")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        File exportToFilePerson = personFileWritingService.exportToFilePerson();
        Path filePath = Paths.get(exportToFilePerson.getPath());
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
            }
    @GetMapping("/getAll")
    public List<Person> findAll(){ return personService.findAll(); }
    @GetMapping("/ifExist")
    public boolean checkIfExist(int pesel){return personService.checkIfExist(pesel);}

    @PostMapping("/upoladFile")
    public boolean upoladFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        personService.saveAll(personParserService.fileDataReader(convFile.getPath()));
        return true;
    }
    @GetMapping("/sort")
    public List<Person> sortPersonsList(){
        return personService.sortPersonsList(personService.findAll());
    }
}
