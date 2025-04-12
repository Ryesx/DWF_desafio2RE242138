package sv.edu.udb.controller.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String materia; // Mostramos solo el nombre de la materia
}
