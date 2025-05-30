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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ← CAMBIO AQUÍ
    private Long id;

    private String nombre;

    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;
}