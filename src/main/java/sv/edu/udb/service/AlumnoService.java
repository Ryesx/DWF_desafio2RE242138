package sv.edu.udb.service;

import sv.edu.udb.controller.dto.AlumnoRequest;
import sv.edu.udb.controller.dto.AlumnoResponse;

import java.util.List;

public interface AlumnoService {
    List<AlumnoResponse> findAll();
    AlumnoResponse findById(Long id);
    AlumnoResponse save(AlumnoRequest request);
    AlumnoResponse update(Long id, AlumnoRequest request);
    void delete(Long id);
}
