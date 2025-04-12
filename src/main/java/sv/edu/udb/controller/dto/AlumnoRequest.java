package sv.edu.udb.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotNull
    private Long idMateria; // Solo se recibe el ID de la materia
}
