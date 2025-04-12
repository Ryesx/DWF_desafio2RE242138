package sv.edu.udb.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String apellido;

    @ManyToOne // Muchos alumnos pueden estar en una misma materia
    @JoinColumn(name = "id_materia") // Así se guarda como una FK en la tabla
    private Materia materia;
}
