package sv.edu.udb.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ← CAMBIO AQUÍ
    private Long id;

    private String nombre;
}