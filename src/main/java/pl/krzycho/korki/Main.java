package pl.krzycho.korki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.krzycho.korki.services.PersonFileReaderService;
import pl.krzycho.korki.services.PersonFileWritingService;

import java.io.IOException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {

        SpringApplication.run(Main.class);

    }
}
