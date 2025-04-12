package sv.edu.udb.service.mapper;

import org.mapstruct.*;
import sv.edu.udb.controller.dto.AlumnoRequest;
import sv.edu.udb.controller.dto.AlumnoResponse;
import sv.edu.udb.domain.Alumno;
import sv.edu.udb.domain.Materia;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    // Primero convertimos el DTO a Alumno sin la Materia
    Alumno toAlumno(AlumnoRequest request);

    // Luego se asigna la materia manualmente desde el servicio

    @Mapping(source = "materia.nombre", target = "nombreMateria")
    AlumnoResponse toResponse(Alumno alumno);
}
