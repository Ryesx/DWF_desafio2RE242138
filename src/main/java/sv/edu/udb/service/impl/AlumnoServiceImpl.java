package sv.edu.udb.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.dto.AlumnoRequest;
import sv.edu.udb.controller.dto.AlumnoResponse;
import sv.edu.udb.domain.Alumno;
import sv.edu.udb.domain.Materia;
import sv.edu.udb.repository.AlumnoRepository;
import sv.edu.udb.repository.MateriaRepository;
import sv.edu.udb.service.AlumnoService;
import sv.edu.udb.service.mapper.AlumnoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final MateriaRepository materiaRepository;
    private final AlumnoMapper alumnoMapper;

    @Override
    public List<AlumnoResponse> findAll() {
        return alumnoRepository.findAll()
                .stream()
                .map(alumnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoResponse findById(Long id) {
        return alumnoRepository.findById(id)
                .map(alumnoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + id));
    }

    @Override
    public AlumnoResponse save(AlumnoRequest request) {
        Materia materia = materiaRepository.findById(request.getIdMateria())
                .orElseThrow(() -> new EntityNotFoundException("Materia no encontrada con ID: " + request.getIdMateria()));
        Alumno alumno = alumnoMapper.toAlumno(request, materia);
        return alumnoMapper.toResponse(alumnoRepository.save(alumno));
    }

    @Override
    public AlumnoResponse update(Long id, AlumnoRequest request) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado con ID: " + id));

        Materia materia = materiaRepository.findById(request.getIdMateria())
                .orElseThrow(() -> new EntityNotFoundException("Materia no encontrada con ID: " + request.getIdMateria()));

        alumno.setNombre(request.getNombre());
        alumno.setApellido(request.getApellido());
        alumno.setMateria(materia);

        return alumnoMapper.toResponse(alumnoRepository.save(alumno));
    }

    @Override
    public void delete(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Alumno no encontrado con ID: " + id);
        }
        alumnoRepository.deleteById(id);
    }
}
