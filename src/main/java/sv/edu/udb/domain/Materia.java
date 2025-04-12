package sv.edu.udb.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // Le dice a Spring que esta clase será una tabla en la base de datos
@Data   // Genera automáticamente getters, setters, equals, hashCode y toString
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los atributos
@Builder // Permite construir objetos con el patrón builder
public class Materia {

    @Id // Este campo será la clave primaria
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Se generará automáticamente
    private Long id;

    private String nombre; // Nombre de la materia
}
