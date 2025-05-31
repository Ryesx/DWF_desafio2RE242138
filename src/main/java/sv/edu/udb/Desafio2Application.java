package sv.edu.udb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

import sv.edu.udb.controller.dto.AlumnoResponse;

@SpringBootApplication
public class Desafio2Application {
    public static void main(String[] args) {
        SpringApplication.run(Desafio2Application.class, args);

        // Comprobación temporal del DTO compilado
        System.out.println("🔎 Campos actuales de AlumnoResponse:");
        Arrays.stream(AlumnoResponse.class.getDeclaredFields())
                .forEach(f -> System.out.println(" - " + f.getName()));
    }
}
