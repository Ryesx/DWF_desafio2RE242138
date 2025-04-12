package sv.edu.udb.service.mapper;

import org.mapstruct.*;
import sv.edu.udb.controller.dto.AlumnoRequest;
import sv.edu.udb.controller.dto.AlumnoResponse;
import sv.edu.udb.domain.Alumno;
import sv.edu.udb.domain.Materia;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    // Convierte de DTO a entidad (recibe también la materia para inyectarla)
    Alumno toAlumno(AlumnoRequest request, Materia materia);

    // Convierte de entidad a DTO (extrae el nombre de la materia)
    @Mapping(source = "materia.nombre", target = "materia")
    AlumnoResponse toResponse(Alumno alumno);
}
