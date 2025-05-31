package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.dto.AlumnoRequest;
import sv.edu.udb.controller.dto.AlumnoResponse;
import sv.edu.udb.service.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping
    public List<AlumnoResponse> findAll() {
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public AlumnoResponse findById(@PathVariable Long id) {
        return alumnoService.findById(id);
    }

    @PostMapping
    public AlumnoResponse save(@Valid @RequestBody AlumnoRequest request) {
        return alumnoService.save(request);
    }

    @PutMapping("/{id}")
    public AlumnoResponse update(@PathVariable Long id, @Valid @RequestBody AlumnoRequest request) {
        return alumnoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        alumnoService.delete(id);
    }
}
